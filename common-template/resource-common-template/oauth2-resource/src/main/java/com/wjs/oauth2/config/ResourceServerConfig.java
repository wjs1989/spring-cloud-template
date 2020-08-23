package com.wjs.oauth2.config;

import com.wjs.oauth2.handler.AuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.web.client.RestTemplate;

/**
 * oauth2 服务配置
 * 
 * @author ruoyi
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter
{
    @Autowired
    private ResourceServerProperties resourceServerProperties;

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

   // @Autowired
    //OAuth2RestTemplate oAuth2RestTemplate;

    @Bean
    public AuthIgnoreConfig authIgnoreConfig()
    {
        return new AuthIgnoreConfig();
    }

    @Bean("bRestTemplate")
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

//    @Bean
//    public OAuth2RestTemplate oAuth2RestTemplate(@Qualifier("bRestTemplate") RestTemplate restTemplate, OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails) throws NoSuchFieldException, IllegalAccessException {
//        ClientCredentialsAccessTokenProvider clientCredentialsAccessTokenProvider = new ClientCredentialsAccessTokenProvider();

//        Field field = OAuth2AccessTokenSupport.class.getDeclaredField("restTemplate");
//        field.setAccessible(true);
//      //  RestTemplate r = (RestTemplate)field.get(clientCredentialsAccessTokenProvider);
//        field.set(clientCredentialsAccessTokenProvider,restTemplate);
//
//        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(oAuth2ProtectedResourceDetails);
//
//        AccessTokenProvider accessTokenProvider = new AccessTokenProviderChain(Arrays.asList(new AuthorizationCodeAccessTokenProvider(), new ImplicitAccessTokenProvider(), new ResourceOwnerPasswordAccessTokenProvider(), clientCredentialsAccessTokenProvider));
//
//        oAuth2RestTemplate.setAccessTokenProvider(accessTokenProvider);
//        oAuth2RestTemplate.setErrorHandler(new CustomErrorHandler());
//
//        return oAuth2RestTemplate;
//    }

    @Bean
    public ResourceServerTokenServices tokenServices()
    {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        UserAuthenticationConverter userTokenConverter = new CommonUserConverter();
        accessTokenConverter.setUserTokenConverter(userTokenConverter);
        remoteTokenServices.setCheckTokenEndpointUrl(resourceServerProperties.getTokenInfoUri());
        remoteTokenServices.setClientId(oAuth2ClientProperties.getClientId());
        remoteTokenServices.setClientSecret(oAuth2ClientProperties.getClientSecret());
        remoteTokenServices.setRestTemplate(restTemplate());
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
        return remoteTokenServices;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();
        // 不登录可以访问
        authIgnoreConfig().getUrls().forEach(url -> registry.antMatchers(url).permitAll());
        registry.anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources)
    {
        resources.tokenServices(tokenServices());
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint ());
    }
}
