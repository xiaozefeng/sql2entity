package org.dark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.dark.domain.Column;

import java.util.List;

/**
 * @author xiaozefeng
 * @date 2018/5/5 下午11:58
 */
@Data
@AllArgsConstructor
public class SqlParseResultDTO {
    /**
     * 类名
     */
    private String clazzName;

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

}
