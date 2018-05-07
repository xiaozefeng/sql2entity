package org.dark.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 表示字段
 *
 * @author xiaozefeng
 * @date 2018/5/5 下午10:15
 */
@Data
@AllArgsConstructor
public class Column {

    /**
     * 转换后的实体属性名
     */
    private String name;
    /**
     * 数据字段名
     */
    private String columnName;
    /**
     * 字段类型
     */
    private String type;

    /**
     * 注释
     */
    private String comment;

    public Column() {
    }


}
