package co.poynt.review_templates.engines;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.StringWriter;
import java.util.Map;
import java.util.UUID;

// https://freemarker.apache.org/docs/pgui_quickstart_createconfiguration.html

public class FreeMarker implements TemplateEngine
{
    Configuration cfg;

    public FreeMarker()
    {
        cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setFallbackOnNullLoopVariable(false);
    }

    @Override
    public String render(String template, Map<String, Object> model) throws Exception {
        Template t = new Template(UUID.randomUUID().toString(), template, cfg);
        StringWriter sw = new StringWriter();
        t.process(model, sw);
        return sw.toString();
    }
}
