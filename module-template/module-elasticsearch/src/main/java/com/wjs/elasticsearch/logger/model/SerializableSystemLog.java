package com.wjs.elasticsearch.logger.model;

import java.util.Map;

/**
 * @author wenjs
 */
public class SerializableSystemLog {
   // "ID")
    private Long id;

   // "模块")
    private String mavenModule;

   // "名称")
    private String name;

   // "线程名")
    private String threadName;

   // "日志级别")
    private String level;

   // "类名")
    private String className;

   // "方法名")
    private String methodName;

   // "行号")
    private int lineNumber;

   // "代码地址")
    private String java;

   // "日志内容")
    private String message;

   // "异常栈")
    private String exceptionStack;

   // "日志时间")
    private long createTime;

   // "线程ID")
    private String threadId;

   // "上下文")
    private Map<String, String> context;


    public Long getId() {
        return id;
    }

    public SerializableSystemLog setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMavenModule() {
        return mavenModule;
    }

    public SerializableSystemLog setMavenModule(String mavenModule) {
        this.mavenModule = mavenModule;
        return this;
    }

    public String getName() {
        return name;
    }

    public SerializableSystemLog setName(String name) {
        this.name = name;
        return this;
    }

    public String getThreadName() {
        return threadName;
    }

    public SerializableSystemLog setThreadName(String threadName) {
        this.threadName = threadName;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public SerializableSystemLog setLevel(String level) {
        this.level = level;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public SerializableSystemLog setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public SerializableSystemLog setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public SerializableSystemLog setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
        return this;
    }

    public String getJava() {
        return java;
    }

    public SerializableSystemLog setJava(String java) {
        this.java = java;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public SerializableSystemLog setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getExceptionStack() {
        return exceptionStack;
    }

    public SerializableSystemLog setExceptionStack(String exceptionStack) {
        this.exceptionStack = exceptionStack;
        return this;
    }

    public long getCreateTime() {
        return createTime;
    }

    public SerializableSystemLog setCreateTime(long createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getThreadId() {
        return threadId;
    }

    public SerializableSystemLog setThreadId(String threadId) {
        this.threadId = threadId;
        return this;
    }

    public Map<String, String> getContext() {
        return context;
    }

    public SerializableSystemLog setContext(Map<String, String> context) {
        this.context = context;
        return this;
    }
}
