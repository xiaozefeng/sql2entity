package org.dark.controller;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.dark.core.CodeGenerator;
import org.dark.core.SqlParser;
import org.dark.domain.SqlParseResult;
import org.dark.dto.SqlRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author xiaozefeng
 * @date 2018/5/6 上午12:20
 */
@Controller
@Slf4j
public class CodeGenerateController {

    @Autowired
    private CodeGenerator codeGenerator;

    @Autowired
    private SqlParser sqlParser;

    /**
     * 代码生成
     *
     * @param response
     * @param sqlRequestDTO
     */
    @PostMapping("/codes/generate")
    @ResponseBody
    public void codeGenerate(HttpServletResponse response, SqlRequestDTO sqlRequestDTO) {
        log.info("请求参数:{}", sqlRequestDTO);
        List<SqlParseResult> parseResultList = sqlParser.parse(sqlRequestDTO.getSql(), sqlRequestDTO.getIgnoreTablePrefix());
        try {
            codeGenerator.generateCode(sqlRequestDTO.getBasePackage(), parseResultList, response);
        } catch (IOException | TemplateException e) {
            log.error(e.getMessage(), e);
        }


    }

    /**
     * 首页
     *
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
