import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class Util {
    public static String path(Class aClass, String resource) throws URISyntaxException {
        URL url = aClass.getResource(resource);
        return Paths.get(url.toURI()).toString();
    }
}