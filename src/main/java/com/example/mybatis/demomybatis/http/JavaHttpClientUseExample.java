package com.example.mybatis.demomybatis.http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import lombok.SneakyThrows;

/**
 * <a href="https://stackoverflow.com/questions/2793150/how-to-use-java-net-urlconnection-to-fire-and-handle-http-requests">使用示例</a>
 * @author jacksparrow414
 * @date 2021/2/28
 */
public class JavaHttpClientUseExample {
    
    @SneakyThrows
    public void useJavaHttpClient() {
        URL url = new URL("www.baidu.com");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStream inputStream = connection.getInputStream();
        connection.disconnect();
    }
}
