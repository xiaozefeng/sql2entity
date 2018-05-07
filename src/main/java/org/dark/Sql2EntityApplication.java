package org.dark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 *
 * @author xiaozefeng
 * @date 2018/5/5 下午10:15
 */
@SpringBootApplication
public class Sql2EntityApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sql2EntityApplication.class, args);
    }
}
