package org.dark.dto;

import lombok.Data;
import org.dark.domain.Column;

import java.util.List;

/**
 * @author xiaozefeng
 * @date 2018/5/5 下午11:58
 */
@Data
public class SqlParseResultDTO {
    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段列表
     */
    private List<Column> columnList;

    public SqlParseResultDTO() {
    }

    public SqlParseResultDTO(String tableName, List<Column> columnList) {
        this.tableName = tableName;
        this.columnList = columnList;
    }
}
