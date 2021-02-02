package com.hainiu.cat.service.config;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * create by biji.zhao on 2020/11/12
 */
//@Configuration
public class ElasticSearchConfig {
//    @Value("${elasticsearch.hostname}")
    private String hostname;
//
//    @Value("${elasticsearch.port}")
    private int port;

    public static final RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        COMMON_OPTIONS = builder.build();
    }

//    @Bean
    public RestHighLevelClient restHighLevelClient(){
        return null;
//        return new RestHighLevelClient(RestClient.builder(new HttpHost(hostname, port, "http")));
    }
}
