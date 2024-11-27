-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: oj
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `discussions`
--
create database oj;
use oj;
DROP TABLE IF EXISTS `discussions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discussions` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `title` varchar(20) NOT NULL COMMENT '讨论标题',
  `content` text NOT NULL COMMENT '讨论正文，存放富文本编辑器的数据',
  `question_number` int NOT NULL COMMENT '关联的题号',
  `create_user` int NOT NULL COMMENT '创建人user_id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `discussions_question_number_index` (`question_number`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='讨论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discussions`
--

LOCK TABLES `discussions` WRITE;
/*!40000 ALTER TABLE `discussions` DISABLE KEYS */;
INSERT INTO `discussions` VALUES (2,'咖啡你冲不冲','<h2><span style=\"font-size: 13px; font-family: 宋体;\"><sup>这是一首简单的小情歌！ &nbsp; &nbsp; &nbsp; &nbsp;</sup></span></h2><pre><code >WTF\n🤓咖啡不断加加加 加到厌倦</code></pre><p><span style=\"background-color: rgb(246, 226, 234);\"><s> </s></span><a href=\"www.baidu.com\" target=\"_blank\"><span style=\"background-color: rgb(246, 226, 234);\"><s>点我试试？</s></span></a><span style=\"background-color: rgb(246, 226, 234);\"><s> </s></span></p>',1,8,'2024-11-19 07:06:17'),(3,'关于讨论区如何排版的提示','<p style=\"text-align: left;\">没错，如果你完全不懂，那么你只需要点击上面的按钮即可排出你想要的效果。</p><p style=\"text-align: left;\">记得写文字时可以时不时看看右边的预览，如果你发现预览不对，那证明你的输入是不对的。<strong>注意预览并不完全跟真实情况一致</strong>，但对简单的排版已经够用了。</p><p style=\"text-align: left;\">事实上，讨论区采用 Markdown 语法和 KaTeX 数学公式语法进行排版。如果感兴趣可以自行学习了解。一个参考学习笔记(我个人的)：<a href=\"https://github.com/lr580/notes/blob/master/LaTeX.md\" target=\"\">点击这里</a></p><p style=\"text-align: left;\">常见问题：</p><ul><li style=\"text-align: left;\">代码显示不正确。 答：这是因为代码里的许多特殊字符对应 Markdown 语法有含义，如 # 代表一级标题。你可以点击代码块按钮进行输入，或以 ```c 开头(`是Tab键上面那个键，英文输入法下可输入)和 ``` 结尾输入代码。c 表示语言，可以换成 c++, java, python。</li><li style=\"text-align: left;\"></li></ul><p style=\"text-align: left;\">显示效果：</p><pre style=\"text-align: left;\"><code class=\"language-pre\">#include &lt;bits/stdc++.h&gt;\nusing namespace std;\nusing ll = long long;\nsigned main()\n{\n    cin.tie(0)-&gt;ios::sync_with_stdio(false);\n\n    return 0;\n}\n</code></pre><ul><li style=\"text-align: left;\">我输入的* 和 _ 不见了。 答：这是因为在 Markdown 里，它们有特殊含义(分别代表斜体和下划线)。你可以通过加转义符输出，如输入 \\*, \\_。如输入 a\\*b=ab 显示 a*b=ab。</li><li style=\"text-align: left;\">我的多行内容全部挤在一行了。 答：在 Markdown 里，用一个空行代表一个换行。直接回车换行是无用的。</li></ul><p style=\"text-align: left;\">如输入：</p><pre style=\"text-align: left;\"><code class=\"language-pre\">这是第一行\n\n这是第二行\n</code></pre><p style=\"text-align: left;\">显示：</p><p style=\"text-align: left;\">这是第一行</p><p style=\"text-align: left;\">这是第二行</p><ul><li style=\"text-align: left;\">图片太大了，如何缩放。 将上传的图片用 HTML 格式写，如：</li></ul><pre style=\"text-align: left;\"><code class=\"language-pre\">&lt;img src=\"/uploads/20220915/16632205436673.png\" alt=\"img\" style=\"width:500px\" /&gt;\n</code></pre><p style=\"text-align: left;\">其中 <span style=\"color: rgb(232, 62, 140);\"><code>src=</code></span> 填你点击了上传后自动出现的内容地址。注意不支持 zoom 属性。</p><p style=\"text-align: left;\">还有其他疑惑欢迎下面回帖提问，不定期回答并把更新本帖 QwQ</p><p style=\"text-align: left;\">附：本帖子的源码 <a href=\"https://cloud.socoding.cn/s/rDziP\" target=\"\">在这里</a></p><p style=\"text-align: left;\"><br></p>',1,8,'2024-11-19 07:12:21'),(4,'朴实无华的C语言28行','<h3 style=\"text-align: left;\">朴实无华的C语言28行</h3><pre style=\"text-align: left;\"><code class=\"language-pre\">#include &lt;stdio.h&gt;\n#include &lt;string.h&gt;\n#define  forfor(func) for(int i=0;i&lt;n;i++)\\\n					for(int j=0;j&lt;n;j++) {\\\n						(func); }\n\nint main()\n{\n	int n,m,p;\n	scanf(\"%d %d\",&amp;n,&amp;m);\n	int arr[n][n],arr2[n][n];\n	forfor(	scanf(\"%d\",&amp;arr[i][j]))\n	while (m--)\n	{\n		scanf(\"%d\",&amp;p);\n		if (p==1)   forfor(arr2[j][n-i-1]=arr[i][j])\n		if (p==2)	forfor(arr2[n-j-1][i]=arr[i][j])\n		if (p==3)	forfor(arr2[n-i-1][n-j-1]=arr[i][j])\n		for(int i=0;i&lt;n;i++){\n			for(int j=0;j&lt;n;j++){ \n				printf(\"%d \",arr2[i][j]);\n				arr[i][j]=arr2[i][j];\n			}\n			printf(\"\\n\");}\n	printf(\"\\n\");\n	}\n	return 0;\n}</code></pre><p><br></p>',1,8,'2024-11-19 07:18:23'),(5,'这是一个lonely的问题','<p>😀oh baby o~</p><pre><code >Lonely Dance !</code></pre><p>🙃</p>',2,8,'2024-11-20 02:57:17'),(6,'这是一首简单的小情歌','<p>😅</p>',2,8,'2024-11-20 02:58:38'),(7,'这是一首简单的小情歌','<p>😅我的发？</p>',2,8,'2024-11-20 02:58:50'),(8,'宝宝题','<pre><code >fucking eazy    </code></pre><p><br></p>',102,8,'2024-11-20 03:00:10');
/*!40000 ALTER TABLE `discussions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '题目唯一id',
  `title` varchar(128) NOT NULL COMMENT '题目大标题：例如1134.接雨水',
  `number` int NOT NULL COMMENT '题目编号，通常和大标题对应',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '是否删除 0否 1 是',
  `description` text COMMENT '题目描述',
  `input_desc` varchar(50) NOT NULL DEFAULT '无' COMMENT '输入描述',
  `output_desc` varchar(50) NOT NULL DEFAULT '无' COMMENT '输出描述',
  `total_commit_num` int NOT NULL DEFAULT '0' COMMENT '该题目总提交数',
  `pass_num` int NOT NULL DEFAULT '0' COMMENT '该题目通过数量',
  `limit_time` int NOT NULL DEFAULT '1000' COMMENT '限制时间-1s=1000ms',
  `limit_memory` int NOT NULL DEFAULT '256' COMMENT '限制内存-256mb',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_user` int NOT NULL COMMENT '创建人',
  `update_user` int NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `question_pk_2` (`number`),
  KEY `idx_question_number` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='题目信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (4,'两数和',102,0,'给定两个数字，输出它们的和。','给定两个数字','一个数字',0,0,1000,256,'2024-10-30 08:08:51','2024-10-30 08:08:51',1,1),(6,'接雨水',104,0,'###接雨水问题：啊u稍后哈佛爱好操i晒大家但是哦好像SIHXIHAI HIAS H\r\nAUSC AOHCS\r\nA OAUHCSAOICH A\r\nAH CAUOCHOS\r\nCUA CBASU;C\r\nu cACBHH\r\n','输入n，和一个长度n的数组','输出一个数字',0,0,1000,128,'2024-10-30 09:50:14','2024-10-30 09:50:14',1,1),(8,'找到K个最小数',103,0,'找到一个排列中k个最小的数','k，和一个排列','k个数字',0,0,1000,256,'2024-10-31 15:08:02','2024-10-31 15:08:02',1,1),(9,'闰年',105,0,'##计算每一年是否闰年','无','无',0,0,1000,256,'2024-10-31 15:08:02','2024-10-31 15:08:02',8,8),(10,'字符转换',107,0,'Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.\r\nYou may assume that each input would have exactly one solution, and you may not use the same element twice.\r\nYou can return the answer in any order.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(11,'反转三位整数',108,0,'You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contain a single digit. Add the two numbers and return the sum as a linked list.\r\nYou may assume the two numbers do not contain any leading zero, except the number 0 itself.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(12,'一元二次方程求解',109,0,'Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.\r\nA substring is a contiguous sequence of characters within a string.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(13,'平方与立方',110,0,'You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).\r\nFind two lines that together with the x-axis form a container, such that the container contains the most water.\r\nReturn the maximum amount of water a container can store.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(14,'华氏度转化摄氏度',111,0,'You are given an array prices where prices[i] is the price of a stock on day i.\r\nYou want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.\r\nReturn the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(15,'水仙花数',112,0,'Given an integer numRows, return the first numRows of Pascal\'s triangle.\r\nIn Pascal\'s triangle, each number is the sum of the two numbers directly above it as shown:\r\n```\r\n     [1],\r\n    [1,1],\r\n   [1,2,1],\r\n  [1,3,3,1],\r\n [1,4,6,4,1]\r\n```','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(16,'本金与利润之和',113,0,'Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.\r\nSymbol       Value\r\nI             1\r\nV             5\r\nX             10\r\nL             50\r\nC             100\r\nD             500\r\nM             1000\r\nFor example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.\r\nRoman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:\r\nI can be placed before V (5) and X (10) to make 4 and 9.\r\nX can be placed before L (50) and C (100) to make 40 and 90.\r\nC can be placed before D (500) and M (1000) to make 400 and 900.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(17,'移动零',114,0,'Write a function to find the longest common prefix string amongst an array of strings.\r\nIf there is no common prefix, return an empty string \"\".','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(18,'盛水最多容器',115,0,'Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.\r\nNotice that the result set must not contain duplicate triplets.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(19,'和为k的子数组',116,0,'Given an array of distinct integers nums and a target integer target, return all possible combinations that add up to target.\r\nThe solution set must not contain duplicate combinations. You can return the combinations in any order.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(20,'滑动窗口最大值',117,0,'Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.\r\nNote that the same word in the dictionary may be reused multiple times in the segmentation.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(21,'最大子数组和',118,0,'There is an integer array nums sorted in ascending order (with distinct values).\r\nPrior to being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the result is also sorted.\r\nGiven the target value nums[target], return its index. If the target is not found return -1.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(22,'轮转数组',119,0,'Given an integer rowIndex, return the rowIndexth row of the Pascal\'s triangle.\r\nIn Pascal\'s triangle, each number is the sum of the two numbers directly above it as shown:\r\n```\r\n     [1],\r\n    [1,1],\r\n   [1,2,1],\r\n  [1,3,3,1],\r\n [1,4,6,4,1]\r\n```','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(23,'除自身意外的数组乘积',120,0,'Given a triangle array, return the minimum path sum from top to bottom.\r\nFor each step, you can move to an adjacent number of the row below. More formally, if you are on a cell of row r and column c in the triangle array, then you can move to either cell[r+1][c] or cell[r+1][c+1].','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(24,'第一个缺少的正数',121,0,'You are given an array prices where prices[i] is the price of a stock on day i.\r\nYou want to maximize your profit by choosing a series of transactions (i.e., buying low and selling high so that you never buy and sell on the same day).\r\nFind and return the maximum profit you can achieve.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(25,'每日温度',122,0,'Given an m x n grid of characters board and a string word, return true if word exists in the grid.\r\nThe word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(26,'123. Text Justification',123,0,'Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.\r\nYou should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces \' \' when necessary so that each line has exactly maxWidth characters.\r\nExtra spaces between words should be distributed as evenly as possible. If the extra spaces cannot be divided evenly between words, the extra spaces between words should be placed randomly.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(27,'124. Populating Next Right Pointers in Each Node',124,0,'You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:\r\n```\r\nclass Node {\r\n    public int val;\r\n    public Node left;\r\n    public Node right;\r\n    public Node next;\r\n}\r\n```\r\nPopulate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.\r\nInitially, all next pointers are set to NULL.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(28,'125. Course Schedule',125,0,'There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.\r\nFor example, the input prerequisites = [[0,1],[1,2]] means you need to take course 1 before taking course 2.\r\nReturn true if you can finish all courses. Otherwise, return false.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(29,'126. Word Ladder',126,0,'A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:\r\nEvery adjacent pair of words differs by a single letter.\r\nEvery word after the first and before the last is in wordList.\r\nReturn the min number of transformation sequences from beginWord to endWord. If no such sequence exists return -1.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(30,'127. Surrounded Regions',127,0,'Given an m x n matrix board containing \'X\' and \'O\', capture all regions that are surrounded by \'X.\'\r\nA region is captured by flipping all \'O\'s into \'X\'s in that surrounded region.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(31,'128. Word Break II',128,0,'Given a string s and a dictionary of strings wordDict, return all unique ways to split the string into words from the dictionary.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(32,'129. Clone Graph',129,0,'Given a reference of a node in a connected component in an undirected graph with n nodes and with each node having a unique value from 1 to n. Return a deep copy of the graph.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(33,'130. Word Search II',130,0,'Given an m x n board of characters and a list of strings words representing words to search for in the board. Return all words in words that are present in the board. Each word must be constructed from letters of sequentially adjacent cells where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used above once per word.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(34,'131. Partition List',131,0,'Given the head of a linked list and a value x,\r\nreturn the list after partitioning it such that all nodes less than x come before nodes greater than or equal to x.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(35,'132. Reverse Nodes in k-Group',132,0,'Given the head of a linked list and an integer k,\r\nreverse the nodes of the list k at a time,\r\nand return the total number of nodes in the list.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(36,'133. Course Schedule III',133,0,'There are n different classes scheduled. You are given the array classes where classes[i] = [passi, totali]. You are start with an initial energy of E.\r\nYou gain energy expendedi amount of energy after completing the class i. However,\r\nif you have not enough energy to complete all of the class i then you cannot complete class i and lose all accumulated energy for this class.\r\nReturn the maximum number of classes that you can complete starting with initial energy E.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(37,'134. Find All Anagrams in a String',134,0,'Given two strings s and p,\r\nfind all the start indices of p\'s anagrams in s. You may return the answer in any order.\r\nAn Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,\r\ntypically using all the original letters exactly once.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(38,'135. Word Squares',135,0,'Given a set of words without duplicates,\r\nreturn all word squares.\r\nA sequence of words forms a valid word square if the kth word has length k and is a word taken from the set.\r\nYou may return the answer in any order.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(39,'136. Palindrome Partitioning',136,0,'Given a string s,\r\npartition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(40,'137. Find All Numbers Disappeared in an Array',137,0,'Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.','无','无',0,0,1000,256,'2024-11-06 11:29:47','2024-11-06 11:29:47',8,8),(43,'title',1006,0,'sasa','输入描述','输出描述',0,0,0,0,'2024-11-12 09:31:13','2024-11-12 09:31:13',8,8),(44,'title',1007,0,'sasa','输入描述','输出描述',0,0,0,0,'2024-11-12 09:58:18','2024-11-12 09:58:18',8,8),(45,'title',1008,0,'sasa','输入描述','输出描述',0,0,0,0,'2024-11-12 10:00:53','2024-11-12 10:00:53',8,8),(49,'你好，世界！',1,0,'输出HelloWorld','无','输出HelloWorld',0,0,1000,256,'2024-11-18 08:03:38','2024-11-18 08:03:38',8,8),(51,'A+B问题2',2,0,'第二个A+B问题','两个数','一个数',0,0,1000,128,'2024-11-18 08:06:53','2024-11-18 08:06:53',8,8);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_case`
--

DROP TABLE IF EXISTS `question_case`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_case` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question_number` int NOT NULL COMMENT '对应题目编号',
  `input_list` varchar(128) DEFAULT NULL COMMENT '一次运行的输入流（输入可以为空）',
  `output_list` varchar(128) NOT NULL COMMENT '一次运行的输出',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='测试用例';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_case`
--

LOCK TABLES `question_case` WRITE;
/*!40000 ALTER TABLE `question_case` DISABLE KEYS */;
INSERT INTO `question_case` VALUES (1,102,'1 2','3'),(2,102,'4 5','9'),(3,102,'9 4','13'),(4,103,'3 2 3 2 1','1 2'),(5,103,'4 1 0 0 1 1','0'),(6,1006,'1 2','3'),(7,1006,'4 6','10'),(8,1007,'1 2','3'),(9,1007,'4 6','10'),(10,1008,'1 2','3'),(11,1008,'4 6','10'),(13,1,' ','Hello,World!'),(14,1,'','Hello,World!'),(15,2,'1 2','3'),(16,2,'0 0','0');
/*!40000 ALTER TABLE `question_case` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_submit`
--

DROP TABLE IF EXISTS `question_submit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_submit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `run_record` int DEFAULT NULL COMMENT '运行记录',
  `user_id` int NOT NULL COMMENT '提交用户id',
  `question_number` int NOT NULL COMMENT '题目编号',
  `result` int DEFAULT NULL COMMENT '提交结果-枚举类型 1代表通过，其他和后端沟通',
  `run_time` int NOT NULL DEFAULT '0' COMMENT '执行时间',
  `run_memory` int NOT NULL DEFAULT '0' COMMENT '执行内存',
  `code` text NOT NULL COMMENT '代码文本',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_question_submit_question_number` (`question_number`),
  KEY `question_submit_user_id_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_submit`
--

LOCK TABLES `question_submit` WRITE;
/*!40000 ALTER TABLE `question_submit` DISABLE KEYS */;
INSERT INTO `question_submit` VALUES (1,0,8,103,1,0,100,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\n    }\r\n}','2024-10-31 08:08:30'),(2,0,8,103,1,0,100,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\n    }\r\n}','2024-10-31 08:14:51'),(3,0,5,103,2,0,100,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-10-31 08:16:31'),(4,0,5,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(5,0,5,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(6,0,8,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(7,0,5,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(8,0,5,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(9,0,5,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(10,0,5,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(11,0,8,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(12,0,5,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(13,0,8,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(14,0,8,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(15,0,8,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(16,0,8,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(17,0,8,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(18,0,8,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(19,0,5,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(20,0,5,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(21,0,8,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(22,0,8,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(23,0,8,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(24,0,5,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(25,0,8,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(26,0,5,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(27,0,8,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(28,0,5,102,1,120,128,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n    public static void main(String[] args) throws InterruptedException {\r\n        minK2();\r\n    }\r\n\r\n\r\n    private static void minK2(){\r\n        //第k小元素\r\n        Scanner sc = new Scanner(System.in);\r\n        int n = sc.nextInt();\r\n        int k = sc.nextInt();\r\n        int arr[] = new int[n];\r\n        for(int i=0;i<n;i++){\r\n            arr[i]=sc.nextInt();\r\n        }\r\n        Arrays.sort(arr);\r\n\r\n        for(int i=0;i<k;i++) {\r\n            System.out.print(arr[i] + \" \");\r\n\r\n        }\r\nwhile(true){}\r\n    }\r\n}','2024-11-01 15:58:45'),(29,0,5,103,1,10,100,'Sample code here','2024-10-26 04:01:34'),(30,0,5,103,0,76,293,'Sample code here','2024-10-13 04:01:34'),(31,0,5,103,2,10,209,'Sample code here','2024-10-20 04:01:34'),(32,0,8,103,1,79,163,'Sample code here','2024-10-12 04:01:34'),(33,0,8,103,2,49,493,'Sample code here','2024-10-24 04:01:34'),(34,0,8,102,1,0,100,'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        // 创建扫描器对象以读取标准输入\r\n        Scanner scanner = new Scanner(System.in);\r\n\r\n        // 读取第一个整数\r\n        int a = scanner.nextInt();\r\n\r\n        // 读取第二个整数\r\n        int b = scanner.nextInt();\r\n\r\n        // 计算两数之和\r\n        int sum = a + b;\r\n\r\n        // 输出结果\r\n        System.out.println(sum);\r\n\r\n        // 关闭扫描器\r\n        scanner.close();\r\n    }\r\n}\r\n','2024-11-09 13:11:20'),(35,0,8,102,1,0,100,'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        // 创建扫描器对象以读取标准输入\r\n        Scanner scanner = new Scanner(System.in);\r\n\r\n        // 读取第一个整数\r\n        int a = scanner.nextInt();\r\n\r\n        // 读取第二个整数\r\n        int b = scanner.nextInt();\r\n\r\n        // 计算两数之和\r\n        int sum = a + b;\r\n\r\n        // 输出结果\r\n        System.out.println(sum);\r\n\r\n        // 关闭扫描器\r\n        scanner.close();\r\n    }\r\n}\r\n','2024-11-09 13:12:17'),(36,0,8,102,1,0,100,'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        // 创建扫描器对象以读取标准输入\r\n        Scanner scanner = new Scanner(System.in);\r\n\r\n        // 读取第一个整数\r\n        int a = scanner.nextInt();\r\n\r\n        // 读取第二个整数\r\n        int b = scanner.nextInt();\r\n\r\n        // 计算两数之和\r\n        int sum = a + b;\r\n\r\n        // 输出结果\r\n        System.out.println(sum);\r\n\r\n        // 关闭扫描器\r\n        scanner.close();\r\n    }\r\n}\r\n','2024-11-09 13:13:27'),(37,0,8,102,1,0,100,'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        // 创建扫描器对象以读取标准输入\r\n        Scanner scanner = new Scanner(System.in);\r\n\r\n        // 读取第一个整数\r\n        int a = scanner.nextInt();\r\n\r\n        // 读取第二个整数\r\n        int b = scanner.nextInt();\r\n\r\n        // 计算两数之和\r\n        int sum = a + b;\r\n\r\n        // 输出结果\r\n        System.out.println(sum);\r\n\r\n        // 关闭扫描器\r\n        scanner.close();\r\n    }\r\n}\r\n','2024-11-09 13:14:02'),(38,0,8,102,0,0,100,'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        // 创建扫描器对象以读取标准输入\r\n        Scanner scanner = new Scanner(System.in);\r\n\r\n        // 读取第一个整数\r\n        int a = scanner.nextInt();\r\n\r\n        // 读取第二个整数\r\n        int b = scanner.nextInt();\r\n\r\n        // 计算两数之和\r\n        int sum =1;\r\n\r\n        // 输出结果\r\n        System.out.println(sum);\r\n\r\n        // 关闭扫描器\r\n        scanner.close();\r\n    }\r\n}\r\n','2024-11-09 13:16:01'),(39,0,8,102,3,0,100,'\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        // 创建扫描器对象以读取标准输入\r\n        Scanner scanner = new Scanner(System.in);\r\n\r\n        // 读取第一个整数\r\n        int a = scanner.nextInt();\r\n\r\n        // 读取第二个整数\r\n        int b = scanner.nextInt();\r\n\r\n        // 计算两数之和\r\n        int sum =1;\r\n\r\n        // 输出结果\r\n        System.out.println(sum);\r\n\r\n        // 关闭扫描器\r\n        scanner.close();\r\n    }\r\n}\r\n','2024-11-09 13:16:21'),(40,0,8,102,1,0,100,'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        // 创建扫描器对象以读取标准输入\r\n        Scanner scanner = new Scanner(System.in);\r\n\r\n        // 读取第一个整数\r\n        int a = scanner.nextInt();\r\n\r\n        // 读取第二个整数\r\n        int b = scanner.nextInt();\r\n\r\n        // 计算两数之和\r\n        int sum = a + b;\r\n\r\n        // 输出结果\r\n        System.out.println(sum);\r\n\r\n        // 关闭扫描器\r\n        scanner.close();\r\n    }\r\n}\r\n','2024-11-09 13:25:17'),(41,0,8,102,1,0,100,'import java.util.Scanner;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        // 创建扫描器对象以读取标准输入\r\n        Scanner scanner = new Scanner(System.in);\r\n\r\n        // 读取第一个整数\r\n        int a = scanner.nextInt();\r\n\r\n        // 读取第二个整数\r\n        int b = scanner.nextInt();\r\n\r\n        // 计算两数之和\r\n        int sum = a + b;\r\n\r\n        // 输出结果\r\n        System.out.println(sum);\r\n\r\n        // 关闭扫描器\r\n        scanner.close();\r\n    }\r\n}\r\n','2024-11-09 13:29:27'),(42,0,8,102,3,0,100,'import java.util.Scanner\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n        // 创建扫描器对象以读取标准输入\r\n        Scanner scanner = new Scanner(System.in);\r\n\r\n        // 读取第一个整数\r\n        int a = scanner.nextInt();\r\n\r\n        // 读取第二个整数\r\n        int b = scanner.nextInt();\r\n\r\n        // 计算两数之和\r\n        int sum = a + b;\r\n\r\n        // 输出结果\r\n        System.out.println(sum);\r\n\r\n        // 关闭扫描器\r\n        scanner.close();\r\n    }\r\n}\r\n','2024-11-09 13:29:38'),(43,0,8,102,3,0,100,'aa','2024-11-09 13:32:00'),(44,0,8,2,3,0,100,'Aaa\n\n\naaa\n\n\na\na\n\na\naaa\naa\naaaaa','2024-11-19 03:06:02');
/*!40000 ALTER TABLE `question_submit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_type`
--

DROP TABLE IF EXISTS `question_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_type` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `question_number` int NOT NULL COMMENT '问题id',
  `type_id` int NOT NULL COMMENT '类型id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` int NOT NULL COMMENT '创建人id',
  PRIMARY KEY (`id`),
  KEY `question_type_question_number_index` (`question_number`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='问题和类型关联表，多对多';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_type`
--

LOCK TABLES `question_type` WRITE;
/*!40000 ALTER TABLE `question_type` DISABLE KEYS */;
INSERT INTO `question_type` VALUES (1,102,2,'2024-11-12 11:32:37',8),(2,102,3,'2024-11-12 11:32:37',8),(3,103,3,'2024-11-12 11:32:37',8),(4,103,4,'2024-11-12 11:32:37',8),(5,1007,12,'2024-11-12 17:58:18',8),(6,1008,13,'2024-11-12 18:00:53',8),(7,1008,14,'2024-11-12 18:00:53',8),(9,1,16,'2024-11-18 16:03:37',8),(10,1,17,'2024-11-18 16:03:37',8),(11,2,18,'2024-11-18 16:06:53',8);
/*!40000 ALTER TABLE `question_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `replies`
--

DROP TABLE IF EXISTS `replies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `replies` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `content` text NOT NULL COMMENT '回复正文，采用富文本编辑器',
  `discussion_id` int NOT NULL COMMENT '关联的讨论id',
  `create_user` int NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `replies_discussion_id_index` (`discussion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='回复表-和讨论属于一对多';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `replies`
--

LOCK TABLES `replies` WRITE;
/*!40000 ALTER TABLE `replies` DISABLE KEYS */;
/*!40000 ALTER TABLE `replies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` text COMMENT '富文本内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='保存富文本测试';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (1,'<pre><code class=\"language-markdown\">## dsfF\nAHIOS</code></pre><p><br></p>'),(3,'<p>😂</p>'),(7,'<blockquote>u是u的话丢好的我爱ui我覅了我iu单独i昂贵回答和我iu电话问死</blockquote><hr/><p>🤣😂😅😟😭👻</p><p> <a href=\"igfiudgwiudwiudd\" target=\"_blank\">iwdu</a> </p><p><span style=\"background-color: rgb(120, 6, 80);\">u嘿嘿五的货物的货物断电后货物v</span></p><p><span style=\"background-color: rgb(251, 233, 230);\">好好滴哦和东欧带u单位iu单位id哦i等哈我的我hi端午</span></p><p><span style=\"background-color: rgb(251, 233, 230);\"><em>活动我和冬冬还是耦合度回答是你的事的就是你的hi收到ishd几点睡觉都i的红色都会</em></span></p><p><span style=\"background-color: rgb(251, 233, 230);\"><u><em>吃撒睡哈随hi四u大叔苏i</em></u></span></p><p><br></p>'),(20,'11');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `name` varchar(20) DEFAULT NULL COMMENT '类型名称-例如“二叉树”',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'二分查找'),(2,'数组'),(3,'栈'),(4,'哈希表'),(5,'队列'),(6,'堆'),(7,'贪心'),(8,'动态规划'),(9,'回溯'),(10,'排序'),(11,'2022新生杯'),(16,'输出训练'),(17,'新生作业'),(18,'输入输出练习');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键，用户唯一id',
  `user_name` varchar(20) NOT NULL COMMENT '用户名，也是系统唯一',
  `password` varchar(255) NOT NULL COMMENT '用户密码，用于登录',
  `email` varchar(30) NOT NULL COMMENT '用户邮箱，一个邮箱只能捆绑一个用户',
  `avatar_url` text COMMENT '用户头像url，使用minio的存储功能',
  `role` int NOT NULL DEFAULT '0' COMMENT '用户角色，区分管理员和普通用户，0代表普通，1代表管理员',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更改用户信息时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (5,'xiaohi','640582EB2A5537B2870ADAE49BB35C77,96cc6f8c44be2b1f200120ea60ba42a7','john.doe@example.com','117.72.91.159:8999/user01/20241105_0de0aa06-7214-44a5-82ec-09960d310aa1.jpg',0,'2024-10-29 16:54:06','2024-10-29 16:54:06'),(8,'wenhaihang','F0413375EA2164094CBD34CE6DB369F4,f2f1b03909fdf3a54c48b537eeafb63a','john.@example.com','117.72.91.159:8999/user01/20241120_7d8188bd-be72-4e64-8f83-8a52208e1aad.jpg',0,'2024-10-29 17:29:08','2024-10-29 17:29:08'),(9,'test','8C412DE6C3095A8AEE098ABA6B1CCA86,7abdc055fe2890f330f85c5b073bdf9d','2846845743@qq.com',NULL,0,'2024-11-08 10:06:45','2024-11-08 10:06:45'),(10,'caonima','88D2FAC56D34D9864D85A1B3A703698D,b019f415aff73eecc414cfddc13cd229','123456@qq.com',NULL,0,'2024-11-09 20:44:33','2024-11-09 20:44:33'),(11,'admin','75FC32B8CC3A6B0C83813B8BEC4F3776,a54b9b282f1af286b3dcc74205ba5d6e','admin@test.com','117.72.91.159:8999/user01/20241125_5243f624-258d-4b7d-a3ab-54ca38dcafbc.jpg',0,'2024-11-25 11:47:22','2024-11-25 11:47:22');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-27 14:10:38
