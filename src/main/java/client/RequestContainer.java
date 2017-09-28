package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class RequestContainer {
    private String url;
    private HttpMethod method = HttpMethod.GET;
    private RequestBody body=null;
    private Header[] headers = null;

    public RequestContainer(String url) {
        this.url = url;
    }

    public RequestContainer(String url, HttpMethod method, RequestBody body) {
        this.url = url;
        this.method = method;
        this.body = body;
    }

    public RequestContainer(String url, HttpMethod method, RequestBody body, Map<String, String> headers) {
        this.url = url;
        this.method = method;
        this.body = body;
        setHeaders(headers);
    }

    public void setHeaders(Map<String, String> headers) {
        if (headers!=null) {
            this.headers = new Header[headers.size()];
            int i = 0;
            for (Map.Entry<String, String> header : headers.entrySet()) {
                this.headers[i] = new BasicHeader(header.getKey(), header.getValue());
                i++;
            }
        }
    }

    public void setMethod(String method) {
        this.method = HttpMethod.valueOf(method);
    }


    public String getUrl() {
        return url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public Header[] getHeaders() {
        return headers;
    }


    public RequestBody getBody() {
        return body;
    }

    public void setBody(RequestBody body) {
        this.body = body;
    }
}
