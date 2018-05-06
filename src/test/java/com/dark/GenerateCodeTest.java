package com.dark;

import freemarker.template.TemplateException;
import org.dark.core.CodeGenerator;
import org.dark.core.SqlParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author xiaozefeng
 * @date 2018/5/6 上午12:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GenerateCodeTest {

    @Autowired
    private CodeGenerator codeGenerator;

    @Autowired
    private SqlParser sqlParser;


    @Test
    public void testCodeGenerate() throws IOException, TemplateException {
        String sql = "CREATE TABLE `PERSON` (\n" +
                "  `id` bigint(20) NOT NULL AUTO_INCREMENT,\n" +
                "  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '姓名',\n" +
                "  `age` int(4) NOT NULL DEFAULT '0' COMMENT '年龄',\n" +
                "  `address` varchar(36) NOT NULL DEFAULT '' COMMENT '地址',\n" +
                "  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;";
    }
}
