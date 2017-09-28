package template;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

public class SpringTemplateSender {
    private RestTemplate restTemplate = new RestTemplate();

    public Object send(String url, HttpMethod method, Map<String, String> headers, String callbackUrl) {
        RequestCallback callback = getRequestCallback(callbackUrl);
        ResponseExtractor responseExtractor = new ObjectExtractor();
        return restTemplate.execute(url, method, callback, responseExtractor, headers);
    }

    public ResponseEntity get(String url, Map<String, String> headers, String callbackUrl) {
        RequestCallback callback = getRequestCallback(callbackUrl);
        ResponseExtractor responseExtractor = new ObjectExtractor();
        return restTemplate.getForEntity(url, String.class, callback, responseExtractor, headers);
    }

    public Object post(String url, Map<String, String> headers, Object object, Class callbackClass, String callbackUrl) {
        return restTemplate.postForObject(url, object, callbackClass, headers);
    }

    private RequestCallback getRequestCallback(String callBackString) {
        if (null == callBackString || callBackString.isEmpty()) {
            return null;
        } else {
            return new Callbacker(this.restTemplate, callBackString);
        }
    }

    private class ObjectExtractor implements ResponseExtractor {
        @Override
        public Object extractData(ClientHttpResponse clientHttpResponse) throws IOException {
            return clientHttpResponse.getBody().read();
        }
    }

    private class Callbacker implements RequestCallback {
        private RestTemplate restTemplate;
        private String url;

        public Callbacker(RestTemplate restTemplate, String url) {
            this.restTemplate = restTemplate;
        }

        @Override
        public void doWithRequest(ClientHttpRequest clientHttpRequest) throws IOException {
            restTemplate.execute(url, HttpMethod.GET, null, null, (Object) null);
        }
    }
}
