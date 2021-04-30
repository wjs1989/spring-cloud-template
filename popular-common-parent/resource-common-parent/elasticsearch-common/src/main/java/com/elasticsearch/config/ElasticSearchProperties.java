package com.elasticsearch.config;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * elasticsearch 客户端属性
 * @author wenjs
 */
@ConfigurationProperties(prefix = "elasticsearch.client")
public class ElasticSearchProperties {
    private String host = "localhost";
    private int port = 9200;

    private int connectionRequestTimeout = 5000;
    private int connectTimeout = 2000;
    private int socketTimeout = 2000;
    private int maxConnTotal = 30;
    private List<String> hosts;


    public HttpHost[] createHosts() {
        if (CollectionUtils.isEmpty(hosts)) {
            return new HttpHost[]{new HttpHost(host, port, "http")};
        }

        return hosts.stream().map(HttpHost::create).toArray(HttpHost[]::new);
    }

    public RequestConfig.Builder applyRequestConfigBuilder(RequestConfig.Builder builder) {

        builder.setConnectTimeout(connectTimeout);
        builder.setConnectionRequestTimeout(connectionRequestTimeout);
        builder.setSocketTimeout(socketTimeout);

        return builder;
    }

    public HttpAsyncClientBuilder applyHttpAsyncClientBuilder(HttpAsyncClientBuilder builder) {
        builder.setMaxConnTotal(maxConnTotal);

        return builder;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public int getMaxConnTotal() {
        return maxConnTotal;
    }

    public void setMaxConnTotal(int maxConnTotal) {
        this.maxConnTotal = maxConnTotal;
    }

    public List<String> getHosts() {
        return hosts;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }
}
