package com.wjs.mqtt.network;

import java.util.List;
import java.util.Optional;

/**
 * 网络组件类型，通常使用枚举实现
 *
 * @author zhouhao
 * @see DefaultNetworkType
 * @since 1.0
 */
public interface NetworkType {
    /**
     * @return 类型唯一标识
     */
    String getId();

    /**
     * @return 类型名称
     */
    default String getName() {
        return getId();
    }

    default  String getFlag () {
        return getId();
    }
    /**
     * 使用指定的ID创建一个NetworkType
     *
     * @param id ID
     * @return NetworkType
     */
    static  NetworkType of(String id) {
        return () -> id;
    }


}
