package org.dark.core;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.dark.domain.SqlParseResult;
import org.dark.utils.ZipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 * 采用freemarker
 *
 * @author xiaozefeng
 * @date 2018/5/5 下午11:34
 */
@Component
public class CodeGenerator {

    @Autowired
    private Configuration cfg;

    private final static String TEMP_DIR = "src/main/resources/temp";

    public OutputStream generateCode(String packageName,
                                     List<SqlParseResult> sqlParseResultList,
                                     HttpServletResponse response) throws IOException, TemplateException {
        // 从 resources/templates 下找bean.ftl
        Template temp = cfg.getTemplate("bean.ftl");
        String zipName = "beans.zip";
        response.setHeader("Content-Disposition", "attachment; filename=" + zipName);
        response.setContentType("application/octet-stream; charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        for (SqlParseResult sqlParseResult :sqlParseResultList) {
            Map<String, Object> root = new HashMap<>();
            root.put("packageName", packageName);
            root.put("className", sqlParseResult.getTableName());
            root.put("columns", sqlParseResult.getColumnList());
            String fileName = sqlParseResult.getTableName().concat(".java");
            OutputStream fos = new FileOutputStream(new File(TEMP_DIR, fileName));
            Writer out = new OutputStreamWriter(fos);
            temp.process(root, out);
            fos.flush();
            fos.close();
            ZipUtil.doZip(new File(TEMP_DIR, fileName), zipOutputStream);
            //清理临时文件
            Files.delete(Paths.get(TEMP_DIR,fileName));
        }

        zipOutputStream.close();
        outputStream.flush();
        outputStream.close();
        return outputStream;

    }
}
