package org.dark.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dark.domain.Column;
import org.dark.domain.SqlParseResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL 建表语句解析器
 *
 * @author xiaozefeng
 * @date 2018/5/5 下午11:53
 */
@Component
@Slf4j
public class SqlParser {

    /**
     * 解析sql
     *
     * @param sql
     * @param ignoreTablePrefix
     * @return
     */
    public SqlParseResult parse(String sql, String ignoreTablePrefix) {
        sql = sql.trim().toLowerCase().replaceAll("`", "");
        String[] split = sql.split("\n");
        String tableName = getTableName(split[0], ignoreTablePrefix);
        log.info("tableName:{}", tableName);
        List<Column> columns = new ArrayList<>();
        for (int i = 1; i < split.length; i++) {
            if (i == split.length -1) {
                continue;
            }
            String trim = split[i].trim();
            if (trim.startsWith("key") || trim.startsWith("primary")) {
                continue;
            }
            Column column = new Column();
            column.setName(getColumnName(trim.split("\\s+")[0]));
            column.setType(convertToType(trim.split(" ")[1]));
            column.setComment(findComment(trim));
            columns.add(column);
        }
        return new SqlParseResult(tableName, columns);
    }

    /**
     * 处理字段有下划线的情况
     *
     * @param column
     * @return
     */
    private String getColumnName(String column) {
        assert column != null && !"".equals(column);
        if (!column.contains("_")) {
            return column;
        }
        StringBuilder sb = new StringBuilder();
        String[] split = column.split("_");
        for (int i = 0; i < split.length; i++) {
            if (i == 0) {
                sb.append(split[i]);
            } else {
                sb.append(capitalize(split[i]));
            }
        }
        return sb.toString();
    }

    /**
     * 处理注释
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
        return substring.replaceAll("'", "")
                .replace(",", "")
                .trim();
    }

    /**
     * 转换字段类型
     *
     * @param s
     * @return
     */
    private String convertToType(String s) {
        if (StringUtils.isBlank(s)) {
            return "String";
        }
        if (s.contains("int") || s.contains("tinyint")) {
            return "Integer";
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
     * @param ignoreTablePrefix
     * @return
     */
    private String getTableName(String s, String ignoreTablePrefix) {
        String table = "table";
        // create table tb_infor_job (

        int index = s.indexOf(table);
        String result = s.substring(index + table.length());
        String tableName = result.replaceAll("\\(", "").trim();
        if (StringUtils.isNotBlank(ignoreTablePrefix.toLowerCase())) {
            tableName = tableName.substring(ignoreTablePrefix.concat("_").length());
        }
        StringBuilder sb = new StringBuilder();
        if (tableName.contains("_")) {
            String[] split = tableName.split("_");
            for (String st : split) {
                sb.append(capitalize(st));
            }
        } else {
            sb.append(capitalize(tableName));
        }
        return sb.toString();
    }

    /**
     * 首字符大写
     *
     * @param origin
     * @return
     */
    private String capitalize(String origin) {
        return origin.substring(0, 1).toUpperCase().concat(origin.substring(1));
    }
}
