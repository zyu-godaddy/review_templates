package co.poynt.review_templates;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Util
{

    public static String getResourceAsString(String path)
    {
        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            URI uri = classLoader.getResource(path).toURI();
            byte[] bytes = Files.readAllBytes(Paths.get(uri));
            return new String(bytes, StandardCharsets.UTF_8);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
