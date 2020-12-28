package com.wjs.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class LoggerHelper {
    private static LoggerHelper INSTANCE = new LoggerHelper();
    private Map<String, Logger> loggerCache = new HashMap<>();
    private ExecutorService executorService;

    private LoggerHelper() {
        executorService = new ThreadPoolExecutor(
                1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadFactory(){

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setDaemon(true);
                        thread.setName("com.wjs.netty.LoggerHelper.Thread");

                        return thread;
                    }
                });
    }

    public static LoggerHelper getInstance() {
        return INSTANCE;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Logger getLogger(Class classz) {
        Logger logger = loggerCache.get(classz.getName());

        if (logger != null) {
            return logger;
        }

        Logger loggerProxy = (Logger) Proxy.newProxyInstance(Logger.class.getClassLoader(), new Class[]{Logger.class}, createNewLoggerHandler(classz));
        loggerCache.put(classz.getName(), loggerProxy);
        return loggerProxy;
    }

    private LoggerHandler createNewLoggerHandler(Class classz) {
        return new LoggerHandler(LoggerFactory.getLogger(classz));
    }

    private class LoggerHandler implements InvocationHandler {

        private Logger logger;

        public LoggerHandler(Logger logger) {
            this.logger = logger;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            Runnable runnable = () -> {
                try {
                    if (method.getName().equals("debug") && !logger.isDebugEnabled()) {
                        return;
                    } else if (method.getName().equals("info") && !logger.isInfoEnabled()) {
                        return;
                    } else if (method.getName().equals("trace") && !logger.isTraceEnabled()) {
                        return;
                    } else if (method.getName().equals("error") && !logger.isErrorEnabled()) {
                        return;
                    } else if (method.getName().equals("warn") && !logger.isWarnEnabled()) {
                        return;
                    }

                    method.invoke(logger, args);
                } catch (Exception e) {
                }
            };

            if (executorService != null) {
                executorService.execute(runnable);
            } else {
                runnable.run();
            }

            return null;
        }
    }
}

