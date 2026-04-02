CREATE DATABASE IF NOT EXISTS crop_db DEFAULT CHARACTER SET utf8mb4;
USE crop_db;
CREATE TABLE IF NOT EXISTS crop (
                                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                    name VARCHAR(50) NOT NULL COMMENT '作物名称',
    category VARCHAR(30) COMMENT '作物类别',
    growth_cycle INT COMMENT '生长周期（天）',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );