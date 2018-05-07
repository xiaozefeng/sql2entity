package org.dark;

import org.dark.domain.Column;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 解析sql测试
 *
 * @author xiaozefeng
 * @date 2018/5/5 下午9:42
 */
public class SqlParseTest {
    private String sql = "create table `person` (\n" +
            "  `id` bigint(20) not null auto_increment,\n" +
            "  `name` varchar(20) not null default '' comment '姓名',\n" +
            "  `age` int(4) not null default '0' comment '年龄',\n" +
            "  `address` varchar(36) not null default '' comment '地址',\n" +
            "  `created_time` datetime not null default current_timestamp comment '创建时间',\n" +
            "  primary key (`id`)\n" +
            ") engine=innodb auto_increment=4 default charset=utf8;";

    @Test
    public void testParse() {
        sql = sql.toLowerCase().replaceAll("`", "");
        String[] split = sql.split("\n");
        String tableName = getTableName(split[0]);
        System.out.printf("tableName:%s \n", tableName);
        List<Column> columns = new ArrayList<>();
        for (int i = 1; i < split.length; i++) {
            if (split.length - 1 == i || split.length - 2 == i) {
                continue;
            }
            String temp = split[i].trim();
            Column column = new Column();
            column.setName(temp.split("\\s+")[0]);
            column.setType(convertToType(temp.split(" ")[1]));
            column.setComment(findComment(temp));
            columns.add(column);
        }
        System.out.printf("字段类别: %s", columns.toString());

    }

    /**
     * 查找注释
     *
     * @param temp
     * @return
     */
    private String findComment(String temp) {
        String comment = "comment";

        int index = temp.indexOf(comment);
        if (index == -1) {
            return "";
        }
        String substring = temp.substring(index + comment.length());
        return substring.replaceAll("'", "").trim();
    }

    private String convertToType(String s) {
        if (s == null || "".equals(s)) {
            return "String";
        }
        if (s.contains("varchar")) {
            return "String";
        }
        if (s.contains("int") || s.contains("tinyint")) {
            return "int";
        }
        if (s.contains("decimal")) {
            return "BigDecimal";
        }
        if (s.contains("datetime")) {
            return "Date";
        }
        return "String";
    }

    /**
     * 获取table
     *
     * @param s
     * @return
     */
    private String getTableName(String s) {
        String table = "table";
        int index = s.indexOf(table);
        String result = s.substring(index + table.length());
        return result.trim();
    }

    @Test
    public void testSplit() {
        sql = sql.replace("`", "");
        String[] result = sql.split("\n");
        Arrays.stream(result).forEach(System.out::println);
    }

    @Test
    public void parseColumn() {
        String column = "name varchar(20) not null default '' comment '姓名',";
        System.out.println(Arrays.toString(column.split(" ")));

    }

    @Test
    public void parseTable(){
        String table = "tb_hxb_loan_repayment";
        if (table.contains("tb")) {
            table = table.substring("tb_".length());
        }
        System.out.println(table);
        String[] split = table.split("_");
        StringBuilder sb = new StringBuilder();
        for (String st :split) {
            sb.append(capitalize(st));
        }
        System.out.println(sb.toString());

    }

    private String capitalize(String origin){
        return origin.substring(0, 1).toUpperCase().concat(origin.substring(1));
    }
}
