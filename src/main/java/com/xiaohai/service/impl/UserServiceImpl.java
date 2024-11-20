package com.xiaohai.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaohai.mapper.QuestionSubmitMapper;
import com.xiaohai.mapper.UserMapper;
import com.xiaohai.model.dto.UserDTO;
import com.xiaohai.model.dto.UserLoginDTO;
import com.xiaohai.model.dto.UserRedis;
import com.xiaohai.model.po.User;
import com.xiaohai.model.vo.UserInfo;
import com.xiaohai.service.UserService;
import com.xiaohai.utils.Result;
import com.xiaohai.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.xiaohai.utils.Constants.LOGIN_USER_KEY;
import static com.xiaohai.utils.Constants.LOGIN_USER_TTL;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserMapper mapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean saveBatch(Collection<User> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<User> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<User> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(User entity) {
        return false;
    }

    @Override
    public User getOne(Wrapper<User> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<User> queryWrapper) {
        return Collections.emptyMap();
    }

    @Override
    public <V> V getObj(Wrapper<User> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<User> getBaseMapper() {
        return null;
    }

    @Override
    public Class<User> getEntityClass() {
        return null;
    }

    @Override
    public void register(UserDTO userDTO) throws Exception {

        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        String email = userDTO.getEmail();
        String code = userDTO.getCode();
        //1.判断code是否匹配。
        //首先根据uuid查询redis
        String redisCode = stringRedisTemplate.opsForValue().get(userDTO.getUuid());
        if(redisCode==null || !redisCode.equals(code)){
            throw new Exception("验证码错误!");
        }


        if(null == username || null == password || null == email || null == code){
            throw new Exception("系统异常:用户名或密码为空!");
        }
        //查询username是否重复
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> eq = lambdaQueryWrapper.select(User::getId).eq(User::getUsername, username);
        List<User> users = mapper.selectList(eq);
        if(users!=null && users.size()>0){
            throw new Exception("用户已存在");
        }
        //查询email是否重复
        LambdaQueryWrapper<User> lambdaQueryWrapper2 = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> eq2 = lambdaQueryWrapper2.select(User::getId).eq(User::getEmail, email);
        users = mapper.selectList(eq2);
        if(users!=null && users.size()>0){
            throw new Exception("用户邮箱已存在");
        }


        User user = new User();
        //生成盐值
        SecureRandom random = new SecureRandom();byte[] salt = new byte[16];
        random.nextBytes(salt);

        StringBuilder sb = new StringBuilder();
        for(byte b : salt) {
            sb.append(String.format("%02x", b));
        }
        String saltString = sb.toString();
        String saltedPassword = password + saltString;//加盐后密码
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hashedPassword = md.digest(saltedPassword.getBytes());//加盐后的md5
        String storedPassword = DatatypeConverter.printHexBinary(hashedPassword) + "," + saltString;
        //用户登录,直接插入
        BeanUtils.copyProperties(userDTO,user);
        user.setPassword(storedPassword);
//        user.setCreateTime(new Date());
//        user.setUpdateTime(new Date());
//        user.setId(1);user.setAvatarUrl("111");
        mapper.insert(user);
    }

    @Override
    public Result<String> login(UserLoginDTO user) {

        //判断账号密码
        if(user.getPassword()==null || user.getUsername()==null){
            return Result.fail("用户密码为空");
        }
        //从数据库中取出盐值和密码对
        User dbUser = mapper.selectOne(new LambdaQueryWrapper<User>().select(User::getPassword,User::getId,User::getUsername,User::getRole).eq(User::getUsername,user.getUsername()));
        if(dbUser==null){
            //用户不存在
            return Result.fail("该用户尚未注册!");
        }
        String dbCode = dbUser.getPassword();
        //分隔数据库中的密文和盐
        String[] strings = dbCode.split(",");
        String salt = strings[1];
        dbCode = strings[0];
        //md5对传入明文加盐加密
        String saltedPassword = user.getPassword() + salt;//加盐后密码
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hashedPassword = md.digest(saltedPassword.getBytes());//加盐后的md5
        String end = DatatypeConverter.printHexBinary(hashedPassword);
        log.info("数据库密文:{},传入后的加密密文:{},盐值{}",dbCode,end,salt);
        if(!end.equals(dbCode)){
            return Result.fail("密码错误!");
        }
        //走到这里密码校验成功了,可以处理token
        //todo:生成token并且返回

        String token = UUID.randomUUID().toString();
        user.setPassword(null);//屏蔽密码之后存到redis
        UserRedis userRedis = BeanUtil.copyProperties(dbUser,UserRedis.class);
        Map<String, Object> map = BeanUtil.beanToMap(userRedis, new HashMap<>()
                , CopyOptions.create().setIgnoreNullValue(true)
                        .setFieldValueEditor(
                                (name, value) -> value.toString()
                        ));
        //保存用户信息到redis
        stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY + token, map);
        //设置过期时间
        stringRedisTemplate.expire(LOGIN_USER_KEY + token, LOGIN_USER_TTL, TimeUnit.MINUTES);

        //todo 保存用户信息到ThreadLocal
        UserHolder.saveUser(userRedis);

        return Result.success(token);
    }

    @Override
    public Result<String> getAvator() {
        //根据当前登录用户id查询用户的头像地址
        Integer userId = UserHolder.getUser().getId();
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(User::getAvatarUrl).eq(User::getId, userId);
        User user = mapper.selectOne(lambdaQueryWrapper);
        log.info("获取用户头像地址：{}",user.getAvatarUrl());

        return Result.success(user.getAvatarUrl());
    }

    @Override
    public Result<String> logout(HttpServletRequest request) {
        Integer userId = UserHolder.getUser().getId();
        //从请求头获取token值
        String token = request.getHeader("Authorization");
        if(token==null){
            //用户身份已过期！
            return Result.success("无需！");
        }
        stringRedisTemplate.delete(LOGIN_USER_KEY + token);

        return Result.success("退出成功！");
    }
    @Autowired
    private QuestionSubmitMapper questionSubmitMapper;

    @Override
    public Result<UserInfo> info() {
        if(UserHolder.getUser()==null){throw new RuntimeException("用户没登陆");}
        Integer id = UserHolder.getUser().getId();
//        Integer id = 8;
        //1.查询用户基本信息-邮箱和用户名
        User user = mapper.getById(id);
        UserInfo ui = new UserInfo();
        ui.setEmail(user.getEmail());
        ui.setUsername(user.getUsername());
        ui.setAvatarUrl(user.getAvatarUrl());
        //2.根据提交记录表查询用户id为这个的所有记录数
        ui.setPassNumber(questionSubmitMapper.queryPassNumberById(id));
        ui.setWrongNumber(questionSubmitMapper.queryWrongNumberById(id));
        ui.setSubmitNumber(questionSubmitMapper.querySubmitNumberById(id));
        //3.获取通过题目题号大全
        ui.setPassQuestions(questionSubmitMapper.queryPassQuestions(id));

        return Result.success(ui);
    }

    @Override
    public Result<String> updateAvatar(User userDTO) {
        User byId = userMapper.getById(userDTO.getId());
        //TODO这里获取到旧的头像链接，可以通过minio删除这个图片
        byId.setAvatarUrl(userDTO.getAvatarUrl());
        userMapper.updateById(byId);
        return Result.success("更新成功");
    }
}
