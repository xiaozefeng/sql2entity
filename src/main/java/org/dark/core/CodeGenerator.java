package org.dark.core;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.dark.domain.SqlParseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author xiaozefeng
 * @date 2018/5/5 下午11:34
 */
@Component
public class CodeGenerator {

    @Autowired
    private Configuration cfg;

    public OutputStream generateCode(String packageName,
                                     SqlParseResult sqlParseResult,
                                     HttpServletResponse response) throws IOException, TemplateException {
        // 从 resources/templates 下找bean.ftl
        Template temp = cfg.getTemplate("bean.ftl");

        // Create the root hash
        Map<String, Object> root = new HashMap<>();
        root.put("packageName", packageName);
        root.put("className", sqlParseResult.getTableName());
        root.put("columns", sqlParseResult.getColumnList());
        String fileName = "bean.java";
        response.setHeader("Content-Disposition","attachment; filename="+fileName);
        response.setContentType("application/octet-stream; charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();
        Writer out = new OutputStreamWriter(outputStream);
        temp.process(root, out);

        outputStream.flush();
        outputStream.close();
        return outputStream;

    }
}
