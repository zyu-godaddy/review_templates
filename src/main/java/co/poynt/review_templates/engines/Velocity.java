package co.poynt.review_templates.engines;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.Map;

// https://velocity.apache.org/engine/2.3/user-guide.html
// https://velocity.apache.org/engine/2.3/developer-guide.html
public class Velocity implements TemplateEngine
{
    final VelocityEngine velocityEngine;
    public Velocity()
    {
        velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("parser.space_gobbling", "lines");
        velocityEngine.setProperty("runtime.strict_mode.enable", true);
        velocityEngine.init();
    }

    @Override
    public String render(String template, Map<String, Object> model) throws Exception
    {
        VelocityContext context = new VelocityContext();
        model.forEach(context::put);

        StringWriter sw = new StringWriter();
        boolean success = velocityEngine.evaluate(context, sw, "Velocity", template);
        if(!success) // uh?
            throw new Exception("velocityEngine.evaluate() returns false");

        return sw.toString();
    }
}
