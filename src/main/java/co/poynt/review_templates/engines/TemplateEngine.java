package co.poynt.review_templates.engines;

import java.util.Map;

public interface TemplateEngine
{
    String render(String template, Map<String,Object> model) throws Exception;
}
