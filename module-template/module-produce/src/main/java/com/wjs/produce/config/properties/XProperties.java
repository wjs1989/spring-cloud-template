package com.wjs.produce.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Validated
@ConfigurationProperties(prefix = "wenjs.x")
public class XProperties {

    @NotNull
    private String name;

    @NotEmpty
    private String hostUrl;

    private List<String> servers;

    /**
     * 时间 默认秒
     */
    @DurationUnit(ChronoUnit.MILLENNIA)
    private  Duration durationTime;

    @DataSizeUnit(DataUnit.MEGABYTES)
    private DataSize maxDataSize;

    /**
     * 标识弃用  @DeprecatedConfigurationProperty
     * @return
     */
    @DeprecatedConfigurationProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public List<String> getServers() {
        return servers;
    }

    public void setServers(List<String> servers) {
        this.servers = servers;
    }

    public Duration getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(Duration durationTime) {
        this.durationTime = durationTime;
    }

    public DataSize getMaxDataSize() {
        return maxDataSize;
    }

    public void setMaxDataSize(DataSize maxDataSize) {
        this.maxDataSize = maxDataSize;
    }
}
