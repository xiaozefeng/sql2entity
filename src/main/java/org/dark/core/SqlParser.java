package org.dark.core;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dark.constants.SqlConstant;
import org.dark.domain.Column;
import org.dark.dto.SqlParseResultDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * SQL 建表语句解析器
 *
 * @author xiaozefeng
 * @date 2018/5/5 下午11:53
 */
@Component
@Slf4j
public class SqlParser {

    private final String SQL_SEPARATOR = "charset=utf8;";

    /**
     * 解析sql
     *
     * @param sql
     * @param ignoreTablePrefix
     * @return
     */
    public List<SqlParseResultDTO> parse(String sql, String ignoreTablePrefix) {
        List<SqlParseResultDTO> results = new ArrayList<>();
        sql = sql.toLowerCase().replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1");
        String[] split = sql.split(SQL_SEPARATOR);
        Arrays.stream(split).filter(StringUtils::isNotBlank).forEach(s-> results.add(getSqlParseResult(s, ignoreTablePrefix)));
        return results;
    }

    /**
     * 解析sql
     *
     * @param sql
     * @param ignoreTablePrefix
     * @return
     */
    private SqlParseResultDTO getSqlParseResult(String sql, String ignoreTablePrefix) {
        sql = sql.trim().toLowerCase().replaceAll("`", "").replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1");
        String[] split = sql.split("\n");
        Map<String, String> map = getTableName(split[0], ignoreTablePrefix);
        String clazzName = map.get("clazzName");
        log.info("处理后的表名:{}", clazzName);
        List<Column> columns = new ArrayList<>();
        for (int i = 1; i < split.length; i++) {
            if (i == split.length - 1) {
                continue;
            }
            String trim = split[i].trim();
            if (trim.startsWith(SqlConstant.KEY) || trim.startsWith(SqlConstant.PRIMARY)) {
                continue;
            }
            Column column = new Column();
            String columnName = trim.split("\\s+")[0];
            column.setColumnName(columnName.toUpperCase());
            column.setName(getFieldName(columnName));
            column.setType(convertToType(trim.split(" ")[1]));
            column.setComment(findComment(trim));
            columns.add(column);
        }

        return new SqlParseResultDTO(clazzName, map.get("tableName").toUpperCase(), columns);
    }

    /**
     * 处理字段有下划线的情况
     *
     * @param column
     * @return
     */
    private String getFieldName(String column) {
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
                sb.append(StringUtils.capitalize(split[i]));
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
    private Map getTableName(String s, String ignoreTablePrefix) {
        int index = s.indexOf(SqlConstant.TABLE);
        String result = s.substring(index + SqlConstant.TABLE.length());
        String tableName = result.replaceAll("\\(", "").trim();
        if (StringUtils.isNotBlank(ignoreTablePrefix.toLowerCase())) {
            tableName = tableName.substring(ignoreTablePrefix.concat(SqlConstant.UNDERLINE).length());
        }
        StringBuilder sb = new StringBuilder();
        if (tableName.contains(SqlConstant.UNDERLINE)) {
            String[] split = tableName.split(SqlConstant.UNDERLINE);
            for (String st : split) {
                sb.append(StringUtils.capitalize(st));
            }
        } else {
            sb.append(StringUtils.capitalize(tableName));
        }
        return ImmutableMap.of("clazzName", sb.toString(), "tableName", tableName);
    }
}
