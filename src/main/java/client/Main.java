package client;

import data.Entity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ClientHttpSender sender = new ClientHttpSender();
        RequestContainer container = new RequestContainer("http://127.0.0.1:8080/post");
        container.setMethod("POST");
        Entity entity = new Entity("TEST");
        container.setBody(new RequestBody(entity, DataType.OBJECT));
        HttpResponse response = sender.doRequest(container);
        System.out.println("******************************");
        try {
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
