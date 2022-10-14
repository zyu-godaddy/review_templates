package co.poynt.review_templates;

import co.poynt.review_templates.engines.FreeMarker;
import co.poynt.review_templates.engines.TemplateEngine;
import co.poynt.review_templates.engines.Velocity;
import co.poynt.review_templates.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        ArrayList<TemplateEngine> engines = new ArrayList<>();
        engines.add(new Velocity());
        engines.add(new FreeMarker());

        final int N = engines.size();

        // for each engine, the template written in its language
        List<String> templates = engines.stream().map(Main::loadTemplate).collect(Collectors.toList());

        Map<String,Object> model = loadModel();

        String expectedRender = Util.getResourceAsString("expectedRender.txt");

        for(int i=0; i<N; i++) {
            verifyRenderResult(engines.get(i), templates.get(i), model, expectedRender);
        }

        System.out.println("warmup before perf...");
        for(int i=0; i<N; i++) {
            perf(engines.get(i), templates.get(i), model, Duration.ofSeconds(10));
        }

        System.out.println("perf...");
        for(int i=0; i<N; i++) {
            long nanoPerRender = perf(engines.get(i), templates.get(i), model, Duration.ofSeconds(60));
            System.out.printf("perf: %s %,d %n", engines.get(i).getClass(), nanoPerRender);
        }

    }

    private static long perf(TemplateEngine engine, String template, Map<String, Object> model, Duration T) throws Exception
    {
        long t0 = System.nanoTime();
        long endT = t0 + T.toNanos();
        long count = 0;
        while(System.nanoTime()<endT) {
            count++;
            engine.render(template, model);
        }
        long nanoPerRender = (System.nanoTime()-t0)/count;
        return nanoPerRender;
    }

    static void verifyRenderResult(TemplateEngine engine, String template, Map<String, Object> model, String expectedRender) throws Exception
    {
        String result = engine.render(template, model);

        if(!result.equals(expectedRender)) {
            System.out.println("=== actual render ===");
            System.out.println(result);
            System.out.println("===");
            throw new Exception("unexpected render");
        }
    }

    static String loadTemplate(TemplateEngine engine)
    {
        String path = "templates/"+engine.getClass().getSimpleName()+".txt";
        return Util.getResourceAsString(path);
    }

    static Map<String,Object> loadModel() throws Exception
    {
        String json = Util.getResourceAsString("model.json");
        return new ObjectMapper()
                .readValue(json, ModelRoot.class)
                .asMap();
    }
}