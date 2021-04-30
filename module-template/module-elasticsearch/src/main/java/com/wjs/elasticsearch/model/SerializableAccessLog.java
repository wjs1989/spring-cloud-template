package com.wjs.elasticsearch.model;


import com.wjs.elasticsearch.elastic.annotations.EsDocument;
import com.wjs.elasticsearch.elastic.annotations.EsField;
import com.wjs.elasticsearch.elastic.annotations.EsId;
import com.wjs.elasticsearch.elastic.enums.FieldType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wenjs
 */
@EsDocument(index = "access_logger_2021-04", type = "_doc")
public class SerializableAccessLog {
    /**
     * 日志id
     */
    @EsId
    private String id;

    /**
     * 访问的操作
     *
     */
    @EsField(type = FieldType.KEYWORD)
    private String action;

    /**
     * 描述
     *
     */
    private String describe;

    /**
     * 访问对应的java方法
     */
    private String method;

    /**
     * 访问对应的java类
     */
    private String target;

    /**
     * 请求的参数,参数为java方法的参数而不是http参数,key为参数名,value为参数值.
     */
    private Map<String, Object> parameters;

    /**
     * 请求者ip地址
     */
    private String ip;

    /**
     * 请求的url地址
     */
    private String url;

    /**
     * http 请求头集合
     */
    private Map<String, String> httpHeaders = new HashMap<>();

    /**
     * 上下文
     */
    private Map<String, String> context = new HashMap<>();

    /**
     * http 请求方法, GET,POST...
     */
    private String httpMethod;

    /**
     * 响应结果,方法的返回值
     */
    //private Object response;

    /**
     * 请求时间戳
     *
     * @see System#currentTimeMillis()
     */
    private long requestTime;

    /**
     * 响应时间戳
     *
     * @see System#currentTimeMillis()
     */
    private long responseTime;

    /**
     * 异常信息,请求对应方法抛出的异常
     */
    private String exception;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHttpHeaders() {
        return httpHeaders;
    }

    public void setHttpHeaders(Map<String, String> httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public Map<String, String> getContext() {
        return context;
    }

    public void setContext(Map<String, String> context) {
        this.context = context;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
