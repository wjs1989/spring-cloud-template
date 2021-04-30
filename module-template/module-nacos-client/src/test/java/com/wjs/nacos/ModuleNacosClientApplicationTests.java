package com.wjs.nacos;


import java.util.Properties;
        import java.util.concurrent.Executor;
        import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
        import com.alibaba.nacos.api.config.listener.Listener;
        import com.alibaba.nacos.api.exception.NacosException;

/**
 * Config service example
 *
 * @author Nacos
 *
 */
public class ModuleNacosClientApplicationTests {

    public static void main(String[] args) throws NacosException, InterruptedException {
        String serverAddr = "10.204.125.109:8848";
        String dataId = "service.vgroupMapping.seata-server-tx";
        String group = "SEATA_GROUP";
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        properties.put(PropertyKeyConst.NAMESPACE, "public");
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);
        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("recieve:" + configInfo);
            }

            @Override
            public Executor getExecutor() {
                return null;
            }
        });
    }
}
