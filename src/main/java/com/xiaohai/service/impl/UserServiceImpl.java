package com.xiaohai.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaohai.mapper.UserMapper;
import com.xiaohai.model.dto.UserDTO;
import com.xiaohai.model.dto.UserLoginDTO;
import com.xiaohai.model.dto.UserRedis;
import com.xiaohai.model.po.User;
import com.xiaohai.service.UserService;
import com.xiaohai.utils.MD5;
import com.xiaohai.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

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
        if(null == username || null == password || null == email){
            throw new Exception("系统异常:用户名或密码为空!");
        }
        //查询username是否重复
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> eq = lambdaQueryWrapper.select(User::getId).eq(User::getUsername, username);
        List<User> users = mapper.selectList(eq);
        if(users!=null && users.size()>0){
            throw new Exception("用户已存在");
        }
        //查询username是否重复
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

        return Result.success(token);
    }
}
