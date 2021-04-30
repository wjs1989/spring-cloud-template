package com.elasticsearch;

import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author wenjs
 */
public class ElasticRestClient {

    private RestHighLevelClient queryClient;

    private RestHighLevelClient writeClient;

    public ElasticRestClient(){
    }

    public ElasticRestClient(RestHighLevelClient queryClient, RestHighLevelClient writeClient) {
        this.queryClient = queryClient;
        this.writeClient = writeClient;
    }

    public RestHighLevelClient getQueryClient() {
        return queryClient;
    }

    public void setQueryClient(RestHighLevelClient queryClient) {
        this.queryClient = queryClient;
    }

    public RestHighLevelClient getWriteClient() {
        return writeClient;
    }

    public void setWriteClient(RestHighLevelClient writeClient) {
        this.writeClient = writeClient;
    }
}
