package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

import java.io.IOException;

public class ClientHttpSender {
    private HttpClient client = HttpClientBuilder.create().build();
    private ObjectMapper mapper = new ObjectMapper();
    private static final String ERROR_SEND = "Http request sending error";

    public HttpResponse doRequest(RequestContainer container) {
        HttpRequestBase httpRequest;
        switch (container.getMethod()) {
            case POST: {
                httpRequest = new HttpPost(container.getUrl());
                HttpPost post = (HttpPost) httpRequest;
                post.setEntity(getEntityFromBodyData(container.getBody()));
                break;
            }
            case PUT: {
                httpRequest = new HttpPut(container.getUrl());
                HttpPut put = (HttpPut) httpRequest;
                put.setEntity(getEntityFromBodyData(container.getBody()));
                break;
            }
            case DELETE: {
                httpRequest = new HttpDelete(container.getUrl());
                break;
            }
            default: {
                httpRequest = new HttpGet(container.getUrl());
            }
        }
        httpRequest.setHeaders(container.getHeaders());
        try {
            return client.execute(httpRequest);
        } catch (IOException e) {
            System.out.println(ERROR_SEND + e);
        }
        return new BasicHttpResponse(new BasicStatusLine(null, 500, ERROR_SEND));
    }

    private HttpEntity getEntityFromBodyData(RequestBody body) {
        if (body != null) {
            switch (body.getType()) {
                case OBJECT: {
                    try {
                        return new StringEntity(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body.getObject()), ContentType.APPLICATION_JSON);
                    } catch (JsonProcessingException e) {
                        System.out.println("Object to JSON serialization error:" + e);
                    }
                    break;
                }
                case JSON: {
                    try {
                        String jsonData = (String) body.getObject();
                        return new StringEntity(jsonData, ContentType.APPLICATION_JSON);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a JSON string, it's not a valid string even:" + e);
                    }
                    break;
                }
                default: {
                    try {
                        String data = (String) body.getObject();
                        return new StringEntity(data, ContentType.TEXT_PLAIN);
                    } catch (ClassCastException e) {
                        System.out.println("Object is not a valid string:" + e);
                    }
                }
            }
        }
        return null;
    }
}
