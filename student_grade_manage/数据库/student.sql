/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : student

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 09/06/2023 14:50:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student_grade
-- ----------------------------
DROP TABLE IF EXISTS `student_grade`;
CREATE TABLE `student_grade`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sno` int NULL DEFAULT NULL,
  `math` double NULL DEFAULT NULL,
  `english` double NULL DEFAULT NULL,
  `java` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_grade
-- ----------------------------
INSERT INTO `student_grade` VALUES (1, 1001, 100, 100, 100);
INSERT INTO `student_grade` VALUES (2, 1002, 99, 97, 98);
INSERT INTO `student_grade` VALUES (3, 1003, 88, 88, 88);
INSERT INTO `student_grade` VALUES (4, 1006, 91, 97, 90);
INSERT INTO `student_grade` VALUES (5, 1008, 64, 82, 90);

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student`  (
  `id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` int NOT NULL,
  `javascore` double(10, 1) NOT NULL,
  `gaoshuscore` double(10, 1) NOT NULL,
  `englishscore` double(10, 1) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES ('1001', '杨皓辰', '男', 20, 100.0, 100.0, 56.0);
INSERT INTO `tb_student` VALUES ('1002', '凌妃依', '女', 20, 98.0, 99.0, 97.0);
INSERT INTO `tb_student` VALUES ('1003', '云曦', '女', 22, 88.0, 88.0, 88.0);
INSERT INTO `tb_student` VALUES ('1004', '吟游驱', '女', 200, 90.0, 91.0, 97.0);
INSERT INTO `tb_student` VALUES ('1005', '逆叠', '男', 25, 100.0, 100.0, 99.0);
INSERT INTO `tb_student` VALUES ('1006', '真不熟', '男', 18, 90.0, 64.0, 82.0);
INSERT INTO `tb_student` VALUES ('1007', '说的道理', '男', 23, 42.0, 60.0, 90.0);
INSERT INTO `tb_student` VALUES ('1008', '风清扬', '男', 32, 90.0, 90.0, 90.0);
INSERT INTO `tb_student` VALUES ('1009', '顶真', '男', 18, 70.0, 80.0, 90.0);
INSERT INTO `tb_student` VALUES ('1010', '王源风烟', '男', 32, 85.0, 59.0, 80.0);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `address` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `post` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', '123456', '男', 21, '广西', '18888888888', '管理员');
INSERT INTO `tb_user` VALUES (2, '1', '123', '男', 1, '1', '1', '测试员');
INSERT INTO `tb_user` VALUES (11, '杨皓辰', '123456', '男', 21, '苏黎世', '66666', '首席CEO');
INSERT INTO `tb_user` VALUES (21, '消愁', '123', '女', 22, '吉林', '123456', '秘书');
INSERT INTO `tb_user` VALUES (24, '真不熟', '123', '女', 20, '上海', '7758758', '打工人');
INSERT INTO `tb_user` VALUES (25, '孙光旭', '123456', '男', 30, '安农大', '1111111111', '老师');
INSERT INTO `tb_user` VALUES (26, '刘小兵', '123456', '男', 40, 'aq', '1836512341', '老师');

SET FOREIGN_KEY_CHECKS = 1;
