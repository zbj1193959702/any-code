package com.hainiu.cat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@MapperScan(basePackages = {"com.hainiu.cat.dao.mapper"})
@ServletComponentScan
//@EnableElasticsearchRepositories(basePackages = "com.hainiu.cat.dao.mapper")
@SpringBootApplication
public class CatApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatApplication.class, args);
    }

}
