package template;

import data.Entity;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        SpringTemplateSender sender = new SpringTemplateSender();
        Map<String, String> props = new HashMap<>();
        props.put("ContentType", "application/json");
        Entity data = new Entity("entity_string_data");
        Object result = sender.post("http://127.0.0.1:8080/post", props, data, String.class,null);
        System.out.println(result);
    }
}