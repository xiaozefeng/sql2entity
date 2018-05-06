package org.dark.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaozefeng
 * @date 2018/5/5 下午11:54
 */
@Configuration
@Slf4j
public class FreemarkerConfiguration {
    //
    //@Bean(name = "myFreeMarkerConfiguration")
    //public freemarker.template.Configuration configuration() {
    //    freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_26);
    //    try {
    //        cfg.setDirectoryForTemplateLoading(new File("src/main/resources"));
    //    } catch (IOException e) {
    //        log.error(e.getMessage(), e);
    //    }
    //    cfg.setDefaultEncoding("UTF-8");
    //    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    //    return cfg;
    //}
}
