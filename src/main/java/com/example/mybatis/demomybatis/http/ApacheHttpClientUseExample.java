package com.example.mybatis.demomybatis.http;

import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author jacksparrow414
 * @date 2021/2/28
 */
public class ApacheHttpClientUseExample {
    
    @SneakyThrows
    public void useApacheHttpClient() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet("www.baidu.com");
        CloseableHttpResponse response = httpClient.execute(get);
        HttpEntity entity = response.getEntity();
        int statusCode = response.getStatusLine().getStatusCode();
        response.close();
        httpClient.close();
    }
}
