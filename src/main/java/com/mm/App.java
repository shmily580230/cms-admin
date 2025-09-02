package com.mm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.hutool.extra.spring.EnableSpringUtil;

/**
 * cms-admin
 *
 * @author lwl
 */
@EnableSpringUtil
@MapperScan("com.mm.modules.*.dao")
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}