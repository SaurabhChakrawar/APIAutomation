package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T readJson(String path, Class<T> clazz) throws IOException {
        return mapper.readValue(new File(path), clazz);
    }

    public static void writeJson(String path, Object data) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), data);
    }

    public static String toJsonString(Object data) throws IOException {
        return mapper.writeValueAsString(data);
    }
}
