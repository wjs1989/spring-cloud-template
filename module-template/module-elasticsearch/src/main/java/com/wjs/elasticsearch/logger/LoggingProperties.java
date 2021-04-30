package com.wjs.elasticsearch.logger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenjs
 */
@ConfigurationProperties(prefix = "logging")
public class LoggingProperties {

    private SystemLoggingProperties system = new SystemLoggingProperties();

    /**
     * 访问日志
     *
     *   org.hswebframework.web.logging.AccessLogger
     *   org.hswebframework.web.loggin.aop.EnableAccessLogger
     *   org.jetlinks.community.logging.event.AccessLoggingEvent
     *   org.jetlinks.community.logging.access.SerializableAccessLog
     */
    private AccessLoggingProperties access = new AccessLoggingProperties();

    public SystemLoggingProperties getSystem() {
        return system;
    }

    public void setSystem(SystemLoggingProperties system) {
        this.system = system;
    }

    public AccessLoggingProperties getAccess() {
        return access;
    }

    public void setAccess(AccessLoggingProperties access) {
        this.access = access;
    }

    public static class SystemLoggingProperties {
        /**
         * 系统日志上下文,通常用于在日志中标识当前服务等
         *
         * @see org.slf4j.MDC
         */
        private Map<String, String> context = new HashMap<>();

        public Map<String, String> getContext() {
            return context;
        }

        public void setContext(Map<String, String> context) {
            this.context = context;
        }
    }

    public static class AccessLoggingProperties {
        //指定按path过滤日志
        private List<String> pathExcludes = new ArrayList<>();

        public List<String> getPathExcludes() {
            return pathExcludes;
        }

        public void setPathExcludes(List<String> pathExcludes) {
            this.pathExcludes = pathExcludes;
        }
    }
}
