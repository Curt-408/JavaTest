package com.example.crop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 农作物信息管理系统 - 启动类
 * Spring Boot应用的入口点
 */
@SpringBootApplication
public class MainApplication {

    /**
     * 程序入口方法
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 启动Spring Boot应用
        SpringApplication.run(MainApplication.class, args);
    }
}
