package com.alanpoi.common.esclient;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConditionalOnProperty(name = {"alanpoi.elasticsearch.enable"}, havingValue = "true")
public class ESClientBean {
    private static final Logger log = LoggerFactory.getLogger(ESClientBean.class);

    @Value("${alanpoi.elasticsearch.nodes:127.0.0.1:9200}")
    private String esNodes;
    @Value("${alanpoi.elasticsearch.username:}")
    private String esUsername;
    @Value("${alanpoi.elasticsearch.password:}")
    private String esPassword;
    @Value("${alanpoi.elasticsearch.connect-timeout:3000}")
    private Integer connectTimeout;
    @Value("${alanpoi.elasticsearch.socket-timeout:5000}")
    private Integer socketTimeout;

    @Bean("esRestClient")
    public RestClient getESRestClient() {
        HttpHost[] hosts = getElasticNodes();
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(esUsername, esPassword));
        RestClient restClient = RestClient.builder(hosts)
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider))
                .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder.setConnectTimeout(connectTimeout)
                        .setSocketTimeout(socketTimeout)).build();

        return restClient;
    }

    private HttpHost[] getElasticNodes() {
        String[] arrs = esNodes.split(",");
        List<HttpHost> hosts = new ArrayList<>(arrs.length);
        for (String strHost : arrs) {
            String[] arrHost = strHost.split(":");
            String host = arrHost[0];
            int port = Integer.valueOf(arrHost[1]);
            hosts.add(new HttpHost(host, port, "http"));
        }
        if (hosts.size() == 0) {
            return null;
        }
        return hosts.toArray(new HttpHost[hosts.size()]);
    }

    @Bean
    public ESClientApi getESApi(@Qualifier("esRestClient") RestClient escli) {
        ESClientApi esapi = new ESClientApi(escli);
        return esapi;
    }
}
