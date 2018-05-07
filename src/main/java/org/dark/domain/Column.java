package org.dark.domain;

import lombok.Data;

/**
 * 表示字段
 *
 * @author xiaozefeng
 * @date 2018/5/5 下午10:15
 */
@Data
public class Column {

    /**
     * 字段名称
     */
    private String name;
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

    public Column(String name, String type, String comment) {
        this.name = name;
        this.type = type;
        this.comment = comment;
    }
}
