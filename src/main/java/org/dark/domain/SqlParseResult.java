package org.dark.domain;

import lombok.Data;

import java.util.List;

/**
 * @author xiaozefeng
 * @date 2018/5/5 下午11:58
 */
@Data
public class SqlParseResult {
    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段列表
     */
    private List<Column> columnList;

    public SqlParseResult() {
    }

    public SqlParseResult(String tableName, List<Column> columnList) {
        this.tableName = tableName;
        this.columnList = columnList;
    }
}
