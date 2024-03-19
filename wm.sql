/*
 Navicat Premium Data Transfer

 Source Server         : mysql8
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3307
 Source Schema         : wm

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 20/03/2024 02:19:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for items
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` bigint UNSIGNED NULL DEFAULT NULL,
  `store_id` bigint UNSIGNED NULL DEFAULT NULL,
  `stock` int UNSIGNED NULL DEFAULT NULL,
  `created_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1770151058677473283 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of items
-- ----------------------------
INSERT INTO `items` VALUES (1, 1, 1, 2, '2024-03-19 21:13:47', '2024-03-19 21:13:47');
INSERT INTO `items` VALUES (1770092434848559106, 1770068435640799234, 1, 694, '2024-03-19 22:18:17', '2024-03-20 01:07:05');
INSERT INTO `items` VALUES (1770092434848559107, 1770068435640799234, 2, 620, '2024-03-20 00:38:30', '2024-03-20 00:38:30');
INSERT INTO `items` VALUES (1770151058677473282, 1770149324555149314, 1, 80, '2024-03-20 02:11:14', '2024-03-20 02:12:40');

-- ----------------------------
-- Table structure for ledgers
-- ----------------------------
DROP TABLE IF EXISTS `ledgers`;
CREATE TABLE `ledgers`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` bigint UNSIGNED NULL DEFAULT NULL,
  `store_id` bigint UNSIGNED NULL DEFAULT NULL,
  `quantity` int UNSIGNED NULL DEFAULT NULL,
  `type` enum('IN','OUT') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `created_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1770151415973453827 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ledgers
-- ----------------------------
INSERT INTO `ledgers` VALUES (1770093239827718146, 1770068435640799234, 1, 200, 'IN', '2024-03-19 22:19:29');
INSERT INTO `ledgers` VALUES (1770093321599868930, 1770068435640799234, 1, 200, 'IN', '2024-03-19 22:21:49');
INSERT INTO `ledgers` VALUES (1770093486054334466, 1770068435640799234, 1, 20, 'OUT', '2024-03-19 22:22:28');
INSERT INTO `ledgers` VALUES (1770093498515611650, 1770068435640799234, 1, 20, 'OUT', '2024-03-19 22:22:31');
INSERT INTO `ledgers` VALUES (1770094201350995969, 1770068435640799234, 1, 30, 'OUT', '2024-03-19 22:25:19');
INSERT INTO `ledgers` VALUES (1770094213954879489, 1770068435640799234, 1, 30, 'OUT', '2024-03-19 22:25:22');
INSERT INTO `ledgers` VALUES (1770094222263795714, 1770068435640799234, 1, 30, 'OUT', '2024-03-19 22:25:24');
INSERT INTO `ledgers` VALUES (1770134914004434946, 1770068435640799234, 1, 36, 'OUT', '2024-03-20 01:07:05');
INSERT INTO `ledgers` VALUES (1770151058736193537, 1770149324555149314, 1, 600, 'IN', '2024-03-20 02:11:14');
INSERT INTO `ledgers` VALUES (1770151415973453826, 1770149324555149314, 1, 520, 'OUT', '2024-03-20 02:12:40');

-- ----------------------------
-- Table structure for msgs
-- ----------------------------
DROP TABLE IF EXISTS `msgs`;
CREATE TABLE `msgs`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `outbound_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `status` int UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1770151415973453828 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of msgs
-- ----------------------------
INSERT INTO `msgs` VALUES (1770134914025406465, '2024-03-19 01:07:05', '出库内容:1770068435640799234数量:36', 1);
INSERT INTO `msgs` VALUES (1770134914025406466, '2024-02-01 01:23:54', '1', 0);
INSERT INTO `msgs` VALUES (1770151415973453827, '2024-03-20 02:12:40', '出库内容:1770149324555149314,数量:520', 0);

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `price` decimal(10, 2) UNSIGNED NULL DEFAULT NULL,
  `created_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1770149364090658820 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES (1770068435640799234, '女士衬衫', '6kg', 35.60, '2024-03-19 20:42:55', '2024-03-19 20:50:16');
INSERT INTO `products` VALUES (1770069430689198082, '男士衬衫', '7kg', 29.90, '2024-03-19 20:46:53', '2024-03-19 23:43:19');
INSERT INTO `products` VALUES (1770149324555149314, '男士鞋子', '42码', 68.80, '2024-03-20 02:04:21', '2024-03-20 02:04:21');
INSERT INTO `products` VALUES (1770149364090658818, '女士鞋子', '36码', 75.40, '2024-03-20 02:04:30', '2024-03-20 02:04:30');

-- ----------------------------
-- Table structure for stores
-- ----------------------------
DROP TABLE IF EXISTS `stores`;
CREATE TABLE `stores`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `created_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stores
-- ----------------------------
INSERT INTO `stores` VALUES (1, '1号店', '2024-03-19 20:59:26', '2024-03-19 20:59:26');
INSERT INTO `stores` VALUES (2, '2号店', '2024-03-19 20:59:35', '2024-03-19 20:59:35');

SET FOREIGN_KEY_CHECKS = 1;
