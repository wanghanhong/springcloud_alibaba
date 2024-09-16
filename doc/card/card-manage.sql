/*
 Navicat Premium Data Transfer

 Source Server         : 172.22.1.21
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 172.22.1.21:3306
 Source Schema         : card_manage

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 06/05/2021 16:54:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bs_city
-- ----------------------------
DROP TABLE IF EXISTS `bs_city`;
CREATE TABLE `bs_city`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增列',
  `city_code` bigint(20) NOT NULL COMMENT '市代码',
  `city_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '市名称',
  `short_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简称',
  `province_code` bigint(20) NOT NULL COMMENT '省代码',
  `lng` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经度',
  `lat` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `level` int(1) NULL DEFAULT NULL COMMENT '层级',
  `sort` int(6) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `Index_1`(`city_code`) USING BTREE,
  INDEX `index_2`(`province_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 391 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '城市设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bs_county
-- ----------------------------
DROP TABLE IF EXISTS `bs_county`;
CREATE TABLE `bs_county`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增列',
  `county_code` bigint(20) NOT NULL COMMENT '区代码',
  `city_code` bigint(20) NOT NULL COMMENT '父级市代码',
  `county_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '市名称',
  `short_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简称',
  `lng` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经度',
  `lat` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `level` int(1) NULL DEFAULT NULL COMMENT '层级',
  `sort` int(6) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `Index_1`(`county_code`) USING BTREE,
  INDEX `index_2`(`city_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3679 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '地区设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bs_province
-- ----------------------------
DROP TABLE IF EXISTS `bs_province`;
CREATE TABLE `bs_province`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增列',
  `province_code` bigint(20) NOT NULL COMMENT '省份代码',
  `province_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省份名称',
  `short_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简称',
  `lng` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经度',
  `lat` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `level` int(1) NULL DEFAULT NULL COMMENT '层级',
  `sort` int(6) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `Index_1`(`province_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '省份设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bs_region
-- ----------------------------
DROP TABLE IF EXISTS `bs_region`;
CREATE TABLE `bs_region`  (
  `region_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '国标代码',
  `parent_region_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级代码',
  `region_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `merger_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总称',
  `short_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简称',
  `merger_short_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总称',
  `region_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '等级',
  `city_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区号代码',
  `zip_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮编',
  `pinyin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '拼音',
  `jianpin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简拼',
  `first_char` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '首字母',
  `longitude` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '经度',
  `latitude` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`region_code`) USING BTREE,
  INDEX `index_name`(`region_code`, `parent_region_code`, `region_level`) USING BTREE,
  INDEX `index_region_code`(`region_code`) USING BTREE,
  INDEX `index_parent_region_code`(`parent_region_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '区域全国' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bs_street
-- ----------------------------
DROP TABLE IF EXISTS `bs_street`;
CREATE TABLE `bs_street`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增列',
  `street_code` bigint(20) NOT NULL COMMENT '街道代码',
  `county_code` bigint(20) NOT NULL COMMENT '父级区代码',
  `street_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '街道名称',
  `short_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简称',
  `lng` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经度',
  `lat` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `level` int(1) NULL DEFAULT NULL COMMENT '层级',
  `sort` int(6) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `Index_1`(`street_code`) USING BTREE,
  INDEX `index_2`(`county_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42361 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '街道设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bs_village
-- ----------------------------
DROP TABLE IF EXISTS `bs_village`;
CREATE TABLE `bs_village`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `street_code` bigint(20) UNSIGNED NOT NULL COMMENT '镇id',
  `village_code` bigint(20) UNSIGNED NOT NULL COMMENT '村id--唯一',
  `village_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '村名称',
  `lng` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经度',
  `lat` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `level` int(1) NULL DEFAULT NULL COMMENT '层级',
  `sort` int(6) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_1`(`village_code`) USING BTREE,
  INDEX `index_2`(`street_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 668390 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '省市县镇村数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_base_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_base_dict`;
CREATE TABLE `t_base_dict`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dict_id` int(11) NOT NULL COMMENT '字典id',
  `dict_type_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型id',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `sortno` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` int(1) NULL DEFAULT NULL COMMENT '删除标识(0 正常 1删除)',
  `parent_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_time`(`create_time`) USING BTREE,
  INDEX `idx_`(`dict_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1242 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典详细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_base_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `t_base_dict_type`;
CREATE TABLE `t_base_dict_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_type_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典ID',
  `dict_type_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `parent_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级ID',
  `unique_id` int(11) NULL DEFAULT NULL COMMENT '自定义ID，用来计数',
  `sortno` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `is_can` int(1) UNSIGNED ZEROFILL NULL DEFAULT 1 COMMENT '是否可以删除',
  `delete_flag` int(1) NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type_id`) USING BTREE,
  INDEX `idx_time`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类别' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_base_group
-- ----------------------------
DROP TABLE IF EXISTS `t_base_group`;
CREATE TABLE `t_base_group`  (
  `dept_id` bigint(20) NOT NULL COMMENT 'ID',
  `dept_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `status` int(20) NULL DEFAULT NULL COMMENT '默认群组',
  `remark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `delete_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  PRIMARY KEY (`dept_id`) USING BTREE,
  INDEX `idx_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '群组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_card
-- ----------------------------
DROP TABLE IF EXISTS `t_card`;
CREATE TABLE `t_card`  (
  `iccid` bigint(20) NOT NULL COMMENT 'iccid卡号',
  `msisdn` bigint(20) NULL DEFAULT NULL COMMENT '接入号码',
  `sim_status` int(11) NULL DEFAULT NULL COMMENT '卡状态(可激活-1  测试激活-2 测试去激活-3 测试期激活-4 在用-5 停机-6)',
  `sim_status_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '卡状态名称',
  `cust_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户名称',
  `cust_code` bigint(30) NULL DEFAULT NULL COMMENT '客户编码',
  `offline` int(10) NULL DEFAULT NULL COMMENT '断网状态 （1-未断网  2-已断网）',
  `offline_type` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '断网类型',
  `activation_time` datetime(0) NULL DEFAULT NULL COMMENT '激活时间',
  `id_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `imsi4g` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '4GIMSI',
  `imsi3g` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '3GIMSI',
  `is_poolmember` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否属于流量池',
  `pool_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流量池类型',
  `join_date` datetime(0) NULL DEFAULT NULL COMMENT '用户发展时间',
  `product_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主产品名称',
  `product_id` bigint(30) NULL DEFAULT NULL COMMENT '主产品ID',
  `group_id` bigint(20) NULL DEFAULT NULL COMMENT '群组ID',
  `group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '群组名称',
  `change_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次变更时间',
  `tags` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `delete_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  PRIMARY KEY (`iccid`) USING BTREE,
  INDEX `idx_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '卡信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_card_bill
-- ----------------------------
DROP TABLE IF EXISTS `t_card_bill`;
CREATE TABLE `t_card_bill`  (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `iccid` bigint(20) NULL DEFAULT NULL COMMENT '卡ID',
  `billing_cycle` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账期',
  `query_flag` int(11) NULL DEFAULT NULL COMMENT '业务类型(1-业务级  2-账户级)',
  `acct_item_id` bigint(20) NULL DEFAULT NULL COMMENT '账目类型ID',
  `acct_item_name` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账目类型名称',
  `acct_charge` decimal(11, 2) NULL DEFAULT NULL COMMENT '欠费金额',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `delete_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '卡账单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_card_data
-- ----------------------------
DROP TABLE IF EXISTS `t_card_data`;
CREATE TABLE `t_card_data`  (
  `id` bigint(20) NOT NULL COMMENT '详单ID',
  `msisdn` bigint(20) NULL DEFAULT NULL COMMENT '接入号',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `duration` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '时长',
  `usage` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用量',
  `net_type` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网络类型',
  `area` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通话地点',
  `net_inner` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上网内容',
  `apn` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'APN',
  `data_charge` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据费用',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` int(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据详单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_card_history
-- ----------------------------
DROP TABLE IF EXISTS `t_card_history`;
CREATE TABLE `t_card_history`  (
  `id` bigint(20) NOT NULL COMMENT '详单ID',
  `order_number` bigint(20) NULL DEFAULT NULL COMMENT '订单编号',
  `status` int(5) NULL DEFAULT NULL COMMENT '订单状态代码',
  `status_dic` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单状态',
  `result` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单结果',
  `order_type` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单来源',
  `type` int(11) NULL DEFAULT NULL COMMENT '订单类型',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `cust_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户ID',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '操作员ID',
  `workstatus` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `delete_flag` int(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作历史订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_card_identity
-- ----------------------------
DROP TABLE IF EXISTS `t_card_identity`;
CREATE TABLE `t_card_identity`  (
  `iccid` bigint(20) NOT NULL COMMENT 'iccid卡号',
  `msisdn` bigint(20) NULL DEFAULT NULL COMMENT '接入号码',
  `status` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SIM卡状态',
  `info_clear` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '删除实名制状态',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `delete_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  PRIMARY KEY (`iccid`) USING BTREE,
  INDEX `idx_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '卡信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_card_nbiot
-- ----------------------------
DROP TABLE IF EXISTS `t_card_nbiot`;
CREATE TABLE `t_card_nbiot`  (
  `id` bigint(20) NOT NULL COMMENT '记录ID',
  `msisdn` bigint(20) NULL DEFAULT NULL COMMENT '接入号',
  `send_time` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  `receive_time` datetime(0) NULL DEFAULT NULL COMMENT '接收时间',
  `type` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '呼叫类型',
  `charge` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '费用',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` int(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_card_package
-- ----------------------------
DROP TABLE IF EXISTS `t_card_package`;
CREATE TABLE `t_card_package`  (
  `id` bigint(20) NOT NULL COMMENT '套餐ID',
  `iccid` bigint(20) NULL DEFAULT NULL COMMENT '卡ID',
  `msisdn` bigint(20) NULL DEFAULT NULL COMMENT '卡接入号',
  `code` bigint(30) NULL DEFAULT NULL COMMENT '套餐编号',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '套餐名称',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `effective_date` datetime(0) NULL DEFAULT NULL COMMENT '失效日期',
  `expired_date` datetime(0) NULL DEFAULT NULL COMMENT '失效日期',
  `total` int(11) NULL DEFAULT NULL COMMENT '总量',
  `used` int(11) NULL DEFAULT NULL COMMENT '使用量',
  `use_rate` double(11, 0) NULL DEFAULT NULL COMMENT '使用率',
  `remain` int(11) NULL DEFAULT NULL COMMENT '剩余量',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `delete_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  `offer_inst_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '卡主套餐' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_card_package_sub
-- ----------------------------
DROP TABLE IF EXISTS `t_card_package_sub`;
CREATE TABLE `t_card_package_sub`  (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `iccid` bigint(20) NULL DEFAULT NULL COMMENT '卡ID',
  `code` bigint(20) NULL DEFAULT NULL COMMENT '套餐编号',
  `name` varchar(126) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '套餐名称',
  `package_sub_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '复数套餐名称',
  `offer_inst_id` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '套餐编号',
  `status` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `effective_date` datetime(0) NULL DEFAULT NULL COMMENT '生效日期',
  `expired_date` datetime(0) NULL DEFAULT NULL COMMENT '失效日期',
  `total` double(11, 2) NULL DEFAULT NULL COMMENT '总量',
  `used` double(11, 2) NULL DEFAULT NULL COMMENT '使用量',
  `use_rate` double(11, 1) NULL DEFAULT NULL COMMENT '使用率',
  `remain` int(11) NULL DEFAULT NULL COMMENT '剩余量',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `delete_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '卡附属套餐' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_card_product
-- ----------------------------
DROP TABLE IF EXISTS `t_card_product`;
CREATE TABLE `t_card_product`  (
  `id` bigint(20) NOT NULL,
  `card_product_id` bigint(20) NULL DEFAULT NULL COMMENT 'ID',
  `iccid` bigint(20) NULL DEFAULT NULL COMMENT '卡ID',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品编码',
  `name` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主产品名称',
  `notify_rate` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '提箱阈值',
  `status` int(11) NULL DEFAULT NULL COMMENT '\r\n运营商定义状态',
  `effective_date` datetime(0) NULL DEFAULT NULL COMMENT '生效时间',
  `prod_inst_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cust_code` int(11) NULL DEFAULT NULL,
  `cust_name` decimal(10, 0) NULL DEFAULT NULL,
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `delete_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  `deploy_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `deploy_info_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `directed_groups_flag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `expired_date` datetime(0) NULL DEFAULT NULL,
  `group_num` int(11) NULL DEFAULT NULL,
  `modify_flag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `unsubscribe_flag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `verified` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '卡主产品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_card_product_data
-- ----------------------------
DROP TABLE IF EXISTS `t_card_product_data`;
CREATE TABLE `t_card_product_data`  (
  `id` bigint(20) NOT NULL COMMENT '记录ID',
  `iccid` bigint(25) NULL DEFAULT NULL COMMENT 'ICCID',
  `prod_inst_id` bigint(20) NULL DEFAULT NULL COMMENT '产品实例ID',
  `prod_code` varchar(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品编号',
  `msisdn` bigint(20) NULL DEFAULT NULL COMMENT '接入号',
  `cmp_status` int(11) NULL DEFAULT NULL COMMENT 'SIM卡状态',
  `crm_dic` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '运营商定义状态',
  `crm_main_status` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '运营商状态标识',
  `last_status_changed_time` datetime(0) NULL DEFAULT NULL COMMENT '状态最后变更时间',
  `notify` int(5) NULL DEFAULT NULL COMMENT '阈值',
  `offnet_id` bigint(20) NULL DEFAULT NULL COMMENT '达量断网ID',
  `offnet_prod_code` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '达量断网产品编号',
  `offnet_threshold` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '达量断网阈值',
  `offnet_type` int(10) NULL DEFAULT NULL COMMENT '达量断网类型',
  `offer_infos` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属销售品信息',
  `func_prod_infos` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '已开通功能类信息',
  `tags` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` int(1) NULL DEFAULT NULL COMMENT '删除标记（0-未删除  1-已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '卡产品资料' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_card_product_sub
-- ----------------------------
DROP TABLE IF EXISTS `t_card_product_sub`;
CREATE TABLE `t_card_product_sub`  (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `card_product_sub_id` bigint(20) NULL DEFAULT NULL COMMENT '主ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '功能产品名称',
  `deploy_info` varchar(126) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置信息',
  `effective_date` datetime(0) NULL DEFAULT NULL COMMENT '生效日期',
  `modify_flag` int(10) NULL DEFAULT NULL COMMENT '能否编辑 0-能 1-不能',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `delete_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cust_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cust_name` varchar(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `deploy_info_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `directed_groups_flag` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `expired_date` datetime(0) NULL DEFAULT NULL,
  `group_num` int(11) NULL DEFAULT NULL,
  `notify_rate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `prod_inst_id` bigint(30) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `unsubscribe_flag` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '能否退订',
  `verified` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '卡功能产品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_card_sms
-- ----------------------------
DROP TABLE IF EXISTS `t_card_sms`;
CREATE TABLE `t_card_sms`  (
  `id` bigint(20) NOT NULL COMMENT '详单ID',
  `msisdn` bigint(20) NULL DEFAULT NULL COMMENT '接入号',
  `acc_nbr` bigint(20) NULL DEFAULT NULL COMMENT '目标接入号',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  `ticket_type_id` int(10) NULL DEFAULT NULL COMMENT '短信类别 1-主叫  2-被叫',
  `ticket_type` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '短信类型',
  `charge_cnt_ch` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合计话费',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` int(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '短信详单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_card_status
-- ----------------------------
DROP TABLE IF EXISTS `t_card_status`;
CREATE TABLE `t_card_status`  (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `iccid` bigint(20) NULL DEFAULT NULL COMMENT 'iccid卡号',
  `msisdn` bigint(20) NULL DEFAULT NULL COMMENT '接入号',
  `card_status` int(11) NULL DEFAULT NULL COMMENT '变更状态(可激活-1  测试激活-2 测试去激活-3 测试期激活-4 在用-5 停机-6)',
  `card_status_name` varchar(66) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '卡状态名称',
  `change_time` datetime(0) NULL DEFAULT NULL COMMENT '修改状态时间',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `delete_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '卡状态变更记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_card_use
-- ----------------------------
DROP TABLE IF EXISTS `t_card_use`;
CREATE TABLE `t_card_use`  (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `iccid` bigint(20) NULL DEFAULT NULL COMMENT '卡ID',
  `msisdn` bigint(20) NULL DEFAULT NULL COMMENT '接入号',
  `resource_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用量分类',
  `package_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '套餐名称',
  `total` double(11, 1) NULL DEFAULT NULL COMMENT '套餐总量',
  `usage` double(11, 1) NULL DEFAULT NULL COMMENT '使用量',
  `rest` double(11, 1) NULL DEFAULT NULL COMMENT '使用率',
  `use_rate` double(11, 1) NULL DEFAULT NULL COMMENT '剩余量',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '计费开始',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '计费结束',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `delete_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '套餐使用量' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_card_use_count
-- ----------------------------
DROP TABLE IF EXISTS `t_card_use_count`;
CREATE TABLE `t_card_use_count`  (
  `id` bigint(20) NOT NULL,
  `iccid` bigint(30) NULL DEFAULT NULL COMMENT 'ICCID',
  `access_number` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接入号',
  `sim_status` int(2) NULL DEFAULT NULL COMMENT 'SIM卡状态',
  `bill_date` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账期',
  `data_usage` decimal(10, 2) NULL DEFAULT NULL COMMENT '流量使用量',
  `voice_usage` int(11) NULL DEFAULT NULL COMMENT '语音用量',
  `sms_usage` int(11) NULL DEFAULT NULL COMMENT '短信用量',
  `nb_usage` int(11) NULL DEFAULT NULL COMMENT 'NB消息用量',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` int(1) NULL DEFAULT NULL COMMENT '删除标记(0-未删除  1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '套餐使用量' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_card_voice
-- ----------------------------
DROP TABLE IF EXISTS `t_card_voice`;
CREATE TABLE `t_card_voice`  (
  `id` bigint(20) NOT NULL COMMENT '详单ID',
  `number` bigint(20) NULL DEFAULT NULL COMMENT '接入号',
  `acc_nbr` bigint(20) NULL DEFAULT NULL COMMENT '目标接入号',
  `ticket_type` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '语音类型',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `duration` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通话时长',
  `duration_ch` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ticket_charge` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '费用',
  `area_code` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通话地点',
  `product_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` int(1) NULL DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '语音详单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_package
-- ----------------------------
DROP TABLE IF EXISTS `t_package`;
CREATE TABLE `t_package`  (
  `package_id` bigint(20) NOT NULL COMMENT 'ID',
  `package_no` int(11) NULL DEFAULT NULL COMMENT '套餐编号',
  `package_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '套餐名称',
  `type` int(11) NULL DEFAULT NULL COMMENT '\r\n类型',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `delete_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  PRIMARY KEY (`package_id`) USING BTREE,
  INDEX `idx_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '套餐' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_package_sub
-- ----------------------------
DROP TABLE IF EXISTS `t_package_sub`;
CREATE TABLE `t_package_sub`  (
  `package_sub_id` bigint(20) NOT NULL COMMENT 'ID',
  `package_id` bigint(20) NOT NULL COMMENT '主ID',
  `package_no` int(11) NULL DEFAULT NULL COMMENT '套餐编号',
  `package_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '套餐名称',
  `type` int(11) NULL DEFAULT NULL COMMENT '\r\n类型',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `delete_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  PRIMARY KEY (`package_sub_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '附属套餐' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_package_use
-- ----------------------------
DROP TABLE IF EXISTS `t_package_use`;
CREATE TABLE `t_package_use`  (
  `package_use_id` bigint(20) NOT NULL COMMENT 'ID',
  `group_acc_nbr` bigint(30) NOT NULL COMMENT '流量池群号',
  `balance_amount` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当月可用量',
  `balance_used` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当月使用量',
  `balance_available` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当月剩余量',
  `date` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账期',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` int(1) NULL DEFAULT NULL COMMENT '删除标记(0-未删除  1-已删除)',
  PRIMARY KEY (`package_use_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '总套餐使用量' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_package_use_member
-- ----------------------------
DROP TABLE IF EXISTS `t_package_use_member`;
CREATE TABLE `t_package_use_member`  (
  `subs_id` bigint(20) NOT NULL COMMENT '成员号码',
  `active_time` datetime(0) NULL DEFAULT NULL COMMENT '加入时间',
  `billing_nbr` bigint(20) NULL DEFAULT NULL,
  `status` int(10) NULL DEFAULT NULL COMMENT 'SIM卡状态',
  `status_dic` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SIM卡状态',
  `balance_account` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ratable_amount` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ratable_total` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pool_nbr` bigint(20) NULL DEFAULT NULL COMMENT '流量池号码',
  `limit_value` int(11) NULL DEFAULT NULL COMMENT '流量限额',
  `limit_type` int(11) NULL DEFAULT NULL COMMENT '限额类型',
  `limit_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '限额名称',
  `area_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开卡地',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `delete_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT 'createDate',
  `prod_inst_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '成员套餐使用量' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_pool
-- ----------------------------
DROP TABLE IF EXISTS `t_pool`;
CREATE TABLE `t_pool`  (
  `id` bigint(20) NOT NULL COMMENT '流量池ID',
  `type` int(1) NULL DEFAULT 1 COMMENT '0  前向 1后向',
  `ec_id` bigint(20) NULL DEFAULT NULL COMMENT '客户ID',
  `ec_name` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户名称',
  `all_number` decimal(12, 2) NULL DEFAULT NULL COMMENT '流量池容量',
  `aval_num` decimal(12, 2) NULL DEFAULT NULL COMMENT '当月可用量',
  `main_status` bigint(20) NULL DEFAULT NULL COMMENT 'SIM卡状态',
  `main_status_desc` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SIM卡状态文字',
  `used_num` decimal(12, 2) NULL DEFAULT NULL COMMENT '当月已用量',
  `has_num` decimal(12, 2) NULL DEFAULT NULL COMMENT '当月剩余量',
  `date` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账期',
  `active_time` datetime(0) NULL DEFAULT NULL COMMENT '激活时间',
  `expore_time` datetime(0) NULL DEFAULT NULL COMMENT '失效时间',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `delete_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  `bind_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `eccust_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `eccust_id` int(11) NULL DEFAULT NULL,
  `eproduct` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `group_to_eccust` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mode_cD` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `offer_inst` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `offerinst_iD` int(11) NULL DEFAULT NULL,
  `prod_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `prod_inst_id` int(11) NULL DEFAULT NULL,
  `prod_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `prod_number` int(11) NULL DEFAULT NULL,
  `region_id` int(11) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status_date` datetime(0) NULL DEFAULT NULL,
  `status_dis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流量池' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_pool_member
-- ----------------------------
DROP TABLE IF EXISTS `t_pool_member`;
CREATE TABLE `t_pool_member`  (
  `pool_member_id` bigint(20) NOT NULL COMMENT 'ID',
  `pool_nbr` bigint(20) NULL DEFAULT NULL COMMENT '流量池号码',
  `subs_id` bigint(20) NULL DEFAULT NULL COMMENT '成员号码',
  `limit_value` int(11) NULL DEFAULT NULL COMMENT '流量限额',
  `limit_type` int(11) NULL DEFAULT NULL COMMENT '限额类型',
  `limit_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '限额名称',
  `area_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开卡地',
  `status` int(11) NULL DEFAULT NULL COMMENT 'SIM卡状态',
  `status_dic` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SIM卡状态',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `delete_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  `active_time` datetime(0) NULL DEFAULT NULL COMMENT '激活时间',
  `balance_account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `billing_nbr` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ratable_amount` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ratable_total` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pool_member_id`) USING BTREE,
  INDEX `idx_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流量池成员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_pool_package
-- ----------------------------
DROP TABLE IF EXISTS `t_pool_package`;
CREATE TABLE `t_pool_package`  (
  `pool_product_id` bigint(20) NOT NULL,
  `subs_id` bigint(20) NULL DEFAULT NULL COMMENT '流量池号码',
  `prod_id` int(11) NULL DEFAULT NULL COMMENT '产品ID',
  `prod_name` int(11) NULL DEFAULT NULL COMMENT '产品名称',
  `prod_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品编号',
  `product_nbr` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品编号',
  `active_time` datetime(0) NULL DEFAULT NULL COMMENT '生效时间',
  `prod_status_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品描述',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `delete_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  `prod_inst_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `acc_prod_inst_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_date` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `begin_date` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ext_pord_inst_id` int(11) NULL DEFAULT NULL,
  `modify_flag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ownerCustId` int(11) NULL DEFAULT NULL,
  `paymentType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `prodType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `restrictedZone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `statusDate` datetime(0) NULL DEFAULT NULL,
  `stopType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  `useCustId` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`pool_product_id`) USING BTREE,
  INDEX `idx_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流量池成员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_pool_product
-- ----------------------------
DROP TABLE IF EXISTS `t_pool_product`;
CREATE TABLE `t_pool_product`  (
  `pool_product_id` bigint(20) NOT NULL,
  `subs_id` bigint(20) NULL DEFAULT NULL COMMENT '流量池号码',
  `prod_id` int(11) NULL DEFAULT NULL COMMENT '产品ID',
  `prod_name` int(11) NULL DEFAULT NULL COMMENT '产品名称',
  `prod_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品编号',
  `product_nbr` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品编号',
  `active_time` datetime(0) NULL DEFAULT NULL COMMENT '生效时间',
  `prod_status_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品描述',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `delete_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  `prod_inst_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `acc_prod_inst_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_date` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `begin_date` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ext_pord_inst_id` int(11) NULL DEFAULT NULL,
  `modify_flag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ownerCustId` int(11) NULL DEFAULT NULL,
  `paymentType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `prodType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `restrictedZone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `statusDate` datetime(0) NULL DEFAULT NULL,
  `stopType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  `useCustId` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`pool_product_id`) USING BTREE,
  INDEX `idx_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流量池成员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product`  (
  `id` bigint(20) NOT NULL COMMENT '产品ID',
  `code` bigint(30) NULL DEFAULT NULL COMMENT '产品编号',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `type` int(11) NULL DEFAULT NULL COMMENT '产品类型',
  `effective_date` datetime(0) NULL DEFAULT NULL COMMENT '生效时间',
  `expired_time` datetime(0) NULL DEFAULT NULL COMMENT '失效日期',
  `notify_rate` int(11) NULL DEFAULT NULL COMMENT '阈值提醒比例',
  `unsubscribe_flag` int(11) NULL DEFAULT NULL COMMENT '判断是否退订',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `delete_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '主产品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_product_sub
-- ----------------------------
DROP TABLE IF EXISTS `t_product_sub`;
CREATE TABLE `t_product_sub`  (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `code` int(11) NULL DEFAULT NULL COMMENT '主ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '功能产品名称',
  `type` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置信息',
  `effective_date` decimal(10, 0) NULL DEFAULT NULL COMMENT '失效日期',
  `expired_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否退订',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `delete_flag` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '删除标识(0 正常 1删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '功能产品' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
