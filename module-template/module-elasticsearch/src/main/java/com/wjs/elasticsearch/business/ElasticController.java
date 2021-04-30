package com.wjs.elasticsearch.business;
 
import com.wjs.elasticsearch.model.SerializableAccessLog;
import com.wjs.elasticsearch.service.AccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wenjs
 */
@RestController
@RequestMapping("/ela")
public class ElasticController {
   // @Autowired
    private AccessLogService accessLogService;


}
