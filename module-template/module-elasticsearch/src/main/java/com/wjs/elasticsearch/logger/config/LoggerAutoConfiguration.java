package com.wjs.elasticsearch.logger.config;
 
import com.wjs.elasticsearch.logger.LoggingProperties;
import com.wjs.elasticsearch.logger.logback.SystemLoggingAppender;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author wenjs
 */
@Configuration
@EnableConfigurationProperties({LoggingProperties.class})
public class LoggerAutoConfiguration implements ApplicationEventPublisherAware {


    private final LoggingProperties properties;

    public LoggerAutoConfiguration(LoggingProperties properties) {
        this.properties = properties;
        SystemLoggingAppender.staticContext.putAll(properties.getSystem().getContext());
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        SystemLoggingAppender.publisher = applicationEventPublisher;
    }

}
