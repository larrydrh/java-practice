SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE IF NOT EXISTS  `hotword`  (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `hotword_pack_id` int(32) NOT NULL,
  `value` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `prob` float(3, 1) NULL DEFAULT 2.0,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `last_update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `hotword_pack_id`(`hotword_pack_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS  `hotwordpack`  (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `last_update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `hotwordpack_uid`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE IF NOT EXISTS  `users` (
    `uid`              int(32)      NOT NULL AUTO_INCREMENT,
    `name`             varchar(256) NOT NULL,
    `api_key`          varchar(64)  NOT NULL,
    `last_update_time` bigint       NOT NULL,
    PRIMARY KEY (`uid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `rasr_user_limit` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `app_id`  int(64) UNIQUE NOT NULL,
  `max_connections`  int(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;