package com.elasticsearch.config;

import com.elasticsearch.ElasticRestClient;
import com.elasticsearch.embedded.EmbeddedElasticSearch;
import com.elasticsearch.embedded.EmbeddedElasticSearchProperties;
import com.elasticsearch.index.ElasticSearchIndexProperties;
import com.elasticsearch.service.reactive.DefaultReactiveElasticsearchClient;
import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.ApplicationProtocolConfig;
import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.IdentityCipherSuiteFilter;
import io.netty.handler.ssl.JdkSslContext;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.node.NodeValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.data.elasticsearch.client.ClientConfiguration;
// import org.springframework.data.elasticsearch.client.reactive.HostProvider;
// import org.springframework.data.elasticsearch.client.reactive.RequestCreator;
// import org.springframework.data.elasticsearch.client.reactive.WebClientProvider;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;

import reactor.netty.tcp.TcpClient;

import reactor.netty.http.client.HttpClient;
// import reactor.netty.transport.ProxyProvider;

import javax.net.ssl.SSLContext;

import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author wenjs
 */
@Configuration
@EnableConfigurationProperties({
        ElasticSearchProperties.class,
        EmbeddedElasticSearchProperties.class,
        ElasticSearchIndexProperties.class})
public class ElasticSearchConfiguration {
    private Logger log = LoggerFactory.getLogger(ElasticSearchConfiguration.class);

    /**
     * elasticSearch客户端
     */
    private final ElasticSearchProperties properties;

    /**
     * 嵌入式 elasticserach 配置
     */
    private final EmbeddedElasticSearchProperties embeddedProperties;

    public ElasticSearchConfiguration(ElasticSearchProperties properties, EmbeddedElasticSearchProperties embeddedProperties) {
        this.properties = properties;
        this.embeddedProperties = embeddedProperties;
    }

    @Bean
    public ElasticRestClient elasticRestClient() {

        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(properties.createHosts())
                .setRequestConfigCallback(properties::applyRequestConfigBuilder)
                .setHttpClientConfigCallback(properties::applyHttpAsyncClientBuilder));
        return new ElasticRestClient(client, client);
    }

    @Bean(destroyMethod = "close")
    public RestHighLevelClient restHighLevelClient(ElasticRestClient client) {
        return client.getWriteClient();
    }

    // @Bean
    // public DefaultReactiveElasticsearchClient reactiveElasticsearchClient(ClientConfiguration clientConfiguration) throws NodeValidationException {
    //     if (embeddedProperties.isEnabled()) {
    //         log.debug("starting embedded elasticsearch on {}:{}",
    //                 embeddedProperties.getHost(),
    //                 embeddedProperties.getPort());
    //
    //         new EmbeddedElasticSearch(embeddedProperties).start();
    //     }
    //
    //     WebClientProvider provider = getWebClientProvider(clientConfiguration);
    //
    //     HostProvider hostProvider = HostProvider.provider(provider, clientConfiguration.getHeadersSupplier(),
    //             clientConfiguration.getEndpoints().toArray(new InetSocketAddress[0]));
    //
    //     DefaultReactiveElasticsearchClient client =
    //             new DefaultReactiveElasticsearchClient(hostProvider, new RequestCreator() {
    //             });
    //
    //     client.setHeadersSupplier(clientConfiguration.getHeadersSupplier());
    //
    //     return client;
    // }
    //
    // private static WebClientProvider getWebClientProvider(ClientConfiguration clientConfiguration) {
    //
    //     Duration connectTimeout = clientConfiguration.getConnectTimeout();
    //     Duration soTimeout = clientConfiguration.getSocketTimeout();
    //
    //     TcpClient tcpClient = TcpClient.create();
    //
    //     if (!connectTimeout.isNegative()) {
    //         tcpClient = tcpClient.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Math.toIntExact(connectTimeout.toMillis()));
    //     }
    //
    //     if (!soTimeout.isNegative()) {
    //         tcpClient = tcpClient.doOnConnected(connection -> connection //
    //                 .addHandlerLast(new ReadTimeoutHandler(soTimeout.toMillis(), TimeUnit.MILLISECONDS))
    //                 .addHandlerLast(new WriteTimeoutHandler(soTimeout.toMillis(), TimeUnit.MILLISECONDS)));
    //     }
    //
    //     if (clientConfiguration.getProxy().isPresent()) {
    //         String proxy = clientConfiguration.getProxy().get();
    //         String[] hostPort = proxy.split(":");
    //
    //         if (hostPort.length != 2) {
    //             throw new IllegalArgumentException("invalid proxy configuration " + proxy + ", should be \"host:port\"");
    //         }
    //         tcpClient = tcpClient.proxy(proxyOptions -> proxyOptions.type(ProxyProvider.Proxy.HTTP).host(hostPort[0])
    //                 .port(Integer.parseInt(hostPort[1])));
    //     }
    //
    //     String scheme = "http";
    //     HttpClient httpClient = HttpClient.from(tcpClient);
    //
    //     if (clientConfiguration.useSsl()) {
    //
    //         Optional<SSLContext> sslContext = clientConfiguration.getSslContext();
    //
    //         if (sslContext.isPresent()) {
    //             httpClient = httpClient.secure(sslContextSpec -> {
    //                 sslContextSpec.sslContext(new JdkSslContext(sslContext.get(), true, null, IdentityCipherSuiteFilter.INSTANCE,
    //                         ApplicationProtocolConfig.DISABLED, ClientAuth.NONE, null, false));
    //             });
    //         } else {
    //             httpClient = httpClient.secure();
    //         }
    //
    //         scheme = "https";
    //     }
    //
    //     ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
    //     WebClientProvider provider = WebClientProvider.create(scheme, connector);
    //
    //     if (clientConfiguration.getPathPrefix() != null) {
    //         provider = provider.withPathPrefix(clientConfiguration.getPathPrefix());
    //     }
    //
    //     provider = provider.withDefaultHeaders(clientConfiguration.getDefaultHeaders()) //
    //             .withWebClientConfigurer(clientConfiguration.getWebClientConfigurer());
    //     return provider;
    // }
}
