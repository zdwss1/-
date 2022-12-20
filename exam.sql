/*
 Navicat Premium Data Transfer

 Source Server         : exam
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : exam

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 20/12/2022 00:10:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `e_id` int(0) NOT NULL AUTO_INCREMENT,
  `t_id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '老师id',
  `e_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '考试id',
  `e_starttime` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '考试开始时间',
  `e_stoptime` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `e_autostart` tinyint(0) NULL DEFAULT NULL,
  `e_status` tinyint(0) NULL DEFAULT NULL COMMENT 'nothing\r\n\r\n',
  `q_path` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `q_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`e_id`) USING BTREE,
  INDEX `FK_t_e_fk`(`t_id`) USING BTREE,
  CONSTRAINT `FK_t_e_fk` FOREIGN KEY (`t_id`) REFERENCES `teacher` (`t_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '????ʵ?' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES (1, '002', '英语', '2023-05-26T08:00', '2023-05-26T10:00', 1, 1, '/file/6/3081661683A94B8FAD8BD05AF1372C8A.docx', '英语期末考试题.docx');
INSERT INTO `exam` VALUES (2, '003', '创新项目实践', '2023-12-12T22:23', '2023-01-01T02:19', 1, 2, '/file/7/0F27CA79CE61494185B085A5094FEDBC', '');
INSERT INTO `exam` VALUES (3, '004', '深度学习', '2023-12-19T22:21', '2023-12-24T22:23', 0, 3, '/file/8/0930E36AC340423FB943D81C16E28CCE.docx', '深度学习期末考试.docx');
INSERT INTO `exam` VALUES (4, '005', '计算机网络', '2023-12-25T08:30', '2023-12-25T10:00', 0, 4, NULL, NULL);
INSERT INTO `exam` VALUES (5, '001', '数学', '2023-06-08T17:02', '2023-06-08T18:02', 1, 0, NULL, NULL);
INSERT INTO `exam` VALUES (6, '001', '计算机网络', '2022-12-28T08:00', '2022-12-28T10:00', 1, 0, NULL, NULL);

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `f_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `s_id` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `f_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `f_path` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `f_size` int(0) NULL DEFAULT NULL,
  `f_time` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '?ļ??????еģ????????' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES (1, '001', '名单.xls', '/file/001/9D72E0D16F9B41FEA356EF756E6CE3D4.xls', NULL, '2022/12/9 /10:50');

-- ----------------------------
-- Table structure for global
-- ----------------------------
DROP TABLE IF EXISTS `global`;
CREATE TABLE `global`  (
  `g_id` int(0) NOT NULL,
  `task_interval` int(0) NULL DEFAULT NULL,
  `record_number` int(0) NULL DEFAULT NULL,
  `max_advance_time` int(0) NULL DEFAULT NULL,
  `min_file_size` int(0) NULL DEFAULT NULL,
  `max_file_size` int(0) NULL DEFAULT NULL,
  `teacher_can_clean` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`g_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = 'ϵͳʵ???洢ȫ????????Ϣ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of global
-- ----------------------------
INSERT INTO `global` VALUES (1, 22, 13, 11, 4, 200, 1);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `n_id` int(0) NOT NULL AUTO_INCREMENT,
  `e_id` int(0) NULL DEFAULT NULL,
  `n_text` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `n_time` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`n_id`) USING BTREE,
  INDEX `FK_e_n_fk`(`e_id`) USING BTREE,
  CONSTRAINT `FK_e_n_fk` FOREIGN KEY (`e_id`) REFERENCES `exam` (`e_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = 'ʵʱ??Ϣʵ?' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (2, 11, 't', '2021-06-22 15:48');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `s_i` int(0) NOT NULL AUTO_INCREMENT,
  `e_id` int(0) NULL DEFAULT NULL,
  `s_id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `s_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `s_class` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `s_ipaddress` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `s_online` tinyint(0) NULL DEFAULT NULL,
  `s_score` int(0) NULL DEFAULT NULL,
  `s_comment` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `s_fpath` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `s_fname` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `s_pwd` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`s_i`) USING BTREE,
  INDEX `FK_s_f_fk2`(`e_id`) USING BTREE,
  CONSTRAINT `FK_s_f_fk2` FOREIGN KEY (`e_id`) REFERENCES `exam` (`e_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 8, '2012080001', '李锦途', '20-3', NULL, NULL, NULL, NULL, NULL, NULL, '123456');
INSERT INTO `student` VALUES (2, 8, '2012080002', '程靖博', '20-3', NULL, NULL, NULL, NULL, NULL, NULL, '123456');
INSERT INTO `student` VALUES (3, 8, '2012080003', '徐立芃', '20-3', NULL, NULL, NULL, NULL, NULL, NULL, '123456');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `t_id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '˭?????',
  `t_pwd` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '??ʦ???',
  `t_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '??ʦ??ʵ????',
  `t_isadmin` tinyint(0) NOT NULL COMMENT '?Ƿ?Ϊ????Ա',
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '?û?????ģ????ʦ??????Աʵ?' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('001', '0adc3949ba59abbe56e057f20f883e', 'ljt_xlp_cjb', 1);

SET FOREIGN_KEY_CHECKS = 1;
