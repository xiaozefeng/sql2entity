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

    private String name;

    private String type;

    private String comment;

    public Column() {
    }

    public Column(String name, String type, String comment) {
        this.name = name;
        this.type = type;
        this.comment = comment;
    }
}
