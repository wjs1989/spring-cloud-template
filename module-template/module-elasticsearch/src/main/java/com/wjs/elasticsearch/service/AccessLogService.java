package com.wjs.elasticsearch.service;

import com.wjs.elasticsearch.elastic.params.Page;
import com.wjs.elasticsearch.model.SerializableAccessLog;

import java.util.Optional;

/**
 * @author wenjs
 */
public interface AccessLogService {

    Optional<SerializableAccessLog> findById(String id);

    SerializableAccessLog save(SerializableAccessLog blog);


}
