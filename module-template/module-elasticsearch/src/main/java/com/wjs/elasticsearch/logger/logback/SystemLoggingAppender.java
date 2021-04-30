package com.wjs.elasticsearch.logger.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.wjs.elasticsearch.logger.model.SerializableSystemLog;
import com.wjs.elasticsearch.logger.util.ModuleUtils;
import com.wjs.model.util.IdGeneratorUtil;
import org.slf4j.MDC;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wenjs
 */
public class SystemLoggingAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    public static ApplicationEventPublisher publisher;

    public static final Map<String, String> staticContext = new ConcurrentHashMap<>();

    @Override
    protected void append(ILoggingEvent event) {

        if (publisher == null) {
            return;
        }

        StackTraceElement element = event.getCallerData()[0];
        IThrowableProxy proxies = event.getThrowableProxy();
        String message = event.getFormattedMessage();
        String stack;
        StringJoiner joiner = new StringJoiner("\n", message + "\n[", "]");
        Queue<IThrowableProxy> queue = new LinkedList<>();
        queue.add(proxies);
        while (queue.size() > 0) {
            IThrowableProxy proxy = queue.poll();
            if (proxy == null) {
                break;
            }
            int commonFrames = proxy.getCommonFrames();
            StackTraceElementProxy[] stepArray = proxy.getStackTraceElementProxyArray();

            StringBuilder stringBuilder = new StringBuilder();
            ThrowableProxyUtil.subjoinFirstLine(stringBuilder, proxy);
            joiner.add(stringBuilder);
            for (int i = 0; i < stepArray.length - commonFrames; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(CoreConstants.TAB);
                ThrowableProxyUtil.subjoinSTEP(sb, stepArray[i]);
                joiner.add(sb);
            }
            queue.addAll(Arrays.asList(proxy.getSuppressed()));
        }
        stack = joiner.toString();

        try {
            String gitLocation = null;
            String mavenModule = null;
            try {
                Class<?> clazz = Class.forName(element.getClassName());
                ModuleUtils.ModuleInfo moduleInfo = ModuleUtils.getModuleByClass(clazz);
                if (!StringUtils.isEmpty(moduleInfo.getGitRepository())) {
                    StringBuilder javaSb = new StringBuilder();
                    javaSb.append(moduleInfo.getGitLocation());
                    javaSb.append("src/main/java/");
                    javaSb.append((ClassUtils.getPackageName(Class.forName(element.getClassName())).replace(".", "/")));
                    javaSb.append("/");
                    javaSb.append(Class.forName(element.getClassName()).getSimpleName());
                    javaSb.append(".java#L");
                    javaSb.append(element.getLineNumber());
                    gitLocation = javaSb.toString();
                }
                mavenModule = moduleInfo.getArtifactId();
            } catch (Exception ignore) {

            }
            Map<String, String> context = new HashMap<>(staticContext);
            Map<String, String> mdc = MDC.getCopyOfContextMap();
            if (mdc != null) {
                context.putAll(mdc);
            }
            SerializableSystemLog info = new SerializableSystemLog()
                    .setId(IdGeneratorUtil.nextId())
                    .setMavenModule(mavenModule)
                    .setContext(context)
                    .setName(event.getLoggerName())
                    .setLevel(event.getLevel().levelStr)
                    .setClassName(element.getClassName())
                    .setMethodName(element.getMethodName())
                    .setLineNumber(element.getLineNumber())
                    .setExceptionStack(stack)
                    .setJava(gitLocation)
                    .setThreadName(event.getThreadName())
                    .setCreateTime(event.getTimeStamp())
                    .setMessage(message)
                    .setThreadId(String.valueOf(Thread.currentThread().getId()));
            try {
                publisher.publishEvent(info);
            } catch (Exception ignore) {
            }
        } catch (Exception e) {
        }
    }
}
