package com.wjs.model.keygen;


import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DefaultKeyGenerator implements KeyGenerator {
    private static final Logger log = LoggerFactory.getLogger(DefaultKeyGenerator.class);
    public static final long EPOCH;
    public static final String WORKER_ID_PROPERTY_KEY = "sharding-jdbc.default.key.generator.worker.id";
    public static final String WORKER_ID_ENV_KEY = "SHARDING_JDBC_DEFAULT_KEY_GENERATOR_WORKER_ID";
    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKER_ID_BITS = 10L;
    private static final long SEQUENCE_MASK = 4095L;
    private static final long WORKER_ID_LEFT_SHIFT_BITS = 12L;
    private static final long TIMESTAMP_LEFT_SHIFT_BITS = 22L;
    private static final long WORKER_ID_MAX_VALUE = 1024L;
    private static TimeService timeService = new TimeService();
    private static long workerId;
    private long sequence;
    private long lastTime;

    public DefaultKeyGenerator() {
    }

    public static void initWorkerId() {
        String workerId = System.getProperty("sharding-jdbc.default.key.generator.worker.id");
        if (!Strings.isNullOrEmpty(workerId)) {
            setWorkerId(Long.valueOf(workerId));
        } else {
            workerId = System.getenv("SHARDING_JDBC_DEFAULT_KEY_GENERATOR_WORKER_ID");
            if (!Strings.isNullOrEmpty(workerId)) {
                setWorkerId(Long.valueOf(workerId));
            }
        }
    }

    public static void setWorkerId(long workerId) {
        Preconditions.checkArgument(workerId >= 0L && workerId < 1024L);
        DefaultKeyGenerator.workerId = workerId;
    }

    @Override
    public synchronized Number generateKey() {
        long currentMillis = timeService.getCurrentMillis();
        Preconditions.checkState(this.lastTime <= currentMillis, "Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", new Object[]{this.lastTime, currentMillis});
        if (this.lastTime == currentMillis) {
            if (0L == (this.sequence = ++this.sequence & 4095L)) {
                currentMillis = this.waitUntilNextTime(currentMillis);
            }
        } else {
            this.sequence = 0L;
        }

        this.lastTime = currentMillis;
        if (log.isDebugEnabled()) {
            log.debug("{}-{}-{}", new Object[]{(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date(this.lastTime)), workerId, this.sequence});
        }

        return currentMillis - EPOCH << 22 | workerId << 12 | this.sequence;
    }

    private long waitUntilNextTime(long lastTime) {
        long time;
        for (time = timeService.getCurrentMillis(); time <= lastTime; time = timeService.getCurrentMillis()) {
        }

        return time;
    }

    public long getSequence() {
        return this.sequence;
    }

    public long getLastTime() {
        return this.lastTime;
    }

    public static void setTimeService(TimeService timeService) {
        DefaultKeyGenerator.timeService = timeService;
    }

    public static long getWorkerId() {
        return workerId;
    }

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 10, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        EPOCH = calendar.getTimeInMillis();
        initWorkerId();
    }
}