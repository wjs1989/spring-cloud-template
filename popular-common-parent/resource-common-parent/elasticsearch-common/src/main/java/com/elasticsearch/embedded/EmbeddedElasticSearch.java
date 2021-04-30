package com.elasticsearch.embedded;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.InternalSettingsPreparer;
import org.elasticsearch.node.Node;
import org.elasticsearch.transport.Netty4Plugin;

import java.util.Collections;

/**
 * @author wenjs
 */
public class EmbeddedElasticSearch extends Node {

    static {
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }

    public EmbeddedElasticSearch(EmbeddedElasticSearchProperties properties) {
        super(InternalSettingsPreparer.prepareEnvironment(
                properties.applySetting(
                        Settings.builder()
                                .put("node.name", "test")
                                .put("discovery.type", "single-node")
                                .put("transport.type", Netty4Plugin.NETTY_TRANSPORT_NAME)
                                .put("http.type", Netty4Plugin.NETTY_HTTP_TRANSPORT_NAME)
                                .put("network.host", "0.0.0.0")
                                .put("http.port", 9200)
                ).build(), Collections.emptyMap(), null, () -> "default"),
                Collections.singleton(Netty4Plugin.class), false);
    }

}
