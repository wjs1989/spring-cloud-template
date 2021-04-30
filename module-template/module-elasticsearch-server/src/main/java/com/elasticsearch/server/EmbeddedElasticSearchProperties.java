package com.elasticsearch.server;

import org.elasticsearch.common.settings.Settings;

/**
 * @author wenjs
 */
public class EmbeddedElasticSearchProperties {
    private boolean enabled = true;

    private String dataPath = "./data/elasticsearch";

    private String homePath = "./";

    private int port = 9200;

    private String host = "0.0.0.0";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public String getHomePath() {
        return homePath;
    }

    public void setHomePath(String homePath) {
        this.homePath = homePath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Settings.Builder applySetting(Settings.Builder settings) {
        return settings.put("network.host", host)
                .put("http.port", port)
                .put("path.data", dataPath)
                .put("path.home", homePath);
    }
}
