package org.dark.dto;

import lombok.Data;

/**
 * @author xiaozefeng
 * @date 2018/5/6 上午12:23
 */
@Data
public class SqlRequestDTO {
    /**
     * 包名
     */
    private String basePackage;

    /**
     * sql
     */
    private String sql;
    /**
     * 忽略表前缀
     */
    private String ignoreTablePrefix;
}
