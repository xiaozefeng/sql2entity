package org.dark;

import org.dark.domain.Column;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成Java源文件
 *
 * @author xiaozefeng
 * @date 2018/5/5 下午10:40
 */
public class CreateJavaFileTest {
    private final String packageName = "package com.dark.entity;";

    private final String clazzName = "Test";

    private static List<Column> columnList = new ArrayList<>();

    @BeforeClass
    public static void init() {

        columnList.add(new Column("name", "NAME", "String", "姓名"));
        columnList.add(new Column("age", "AGE","Integer", "年龄"));
        columnList.add(new Column("birth", "BIRTH","Date", "生日"));
        columnList.add(new Column("salary", "SALARY","BigDecimal", "工资"));
    }


    @Test
    public void testCreateJavaFile() throws IOException {
        File file = new File("/Users/xiaozefeng/Desktop/Test.java");
        //= new BufferedOutputStream(new FileOutputStream(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        StringBuilder sb = new StringBuilder();
        sb.append(packageName + "\n\n\n");
        for (Column column : columnList) {
            if (column.getType().equals("BigDecimal")) {
                sb.append("import java.math.BigDecimal;\n");
            }
            if (column.getType().equals("Date")) {
                sb.append("import java.util.Date;\n");
            }
        }
        sb.append("\n\n");

        sb.append("public class " + clazzName + "{\n\n");
        for (Column column : columnList) {
            String columnComment = column.getComment();
            if (columnComment != null && !"".equals(columnComment)) {
                sb.append("\t/**\n\t* " + column.getComment() + "\n\t*/\n");
            }
            sb.append("\tprivate " + columnComment + " " + column.getName() + ";\n\n");
        }
        sb.append("}");
        bw.write(sb.toString());
        bw.flush();
        bw.close();


    }
}
