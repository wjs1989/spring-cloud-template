package com.wjs.nacos.config;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class NacosConfigManager implements ApplicationContextAware {
    private ConfigService configService;

    public ConfigService getConfigService() {
        return ServiceHolder.getInstance().getService();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        NacosConfigProperties properties = applicationContext
                .getBean(NacosConfigProperties.class);
        configService = properties.configServiceInstance();
        ServiceHolder holder = ServiceHolder.getInstance();
        if (!holder.alreadyInit) {
            ServiceHolder.getInstance().setService(properties.configServiceInstance());
        }
    }

    static class ServiceHolder {
        private ConfigService service = null;

        private boolean alreadyInit = false;

        private static final ServiceHolder holder = new ServiceHolder();

        ServiceHolder() {
        }

        static ServiceHolder getInstance() {
            return holder;
        }

        void setService(ConfigService service) {
            alreadyInit = true;
            this.service = service;
        }

        ConfigService getService() {
            return service;
        }
    }
}
