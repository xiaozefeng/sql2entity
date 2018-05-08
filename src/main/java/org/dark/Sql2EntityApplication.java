package org.dark;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

/**
 * 启动类
 *
 * @author xiaozefeng
 * @date 2018/5/5 下午10:15
 */
@SpringBootApplication
public class Sql2EntityApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Sql2EntityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        File tempDir = new File("/temp");
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }
    }
}
