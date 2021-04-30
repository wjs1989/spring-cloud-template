package com.wjs.produce.config.redis;


import java.util.Random;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/12 10:17
 */
public enum RedisKeyEnum implements BaseRedisKey {

    DISTRICT_TREE("district_tree", "区域树", 600, 800),
    DISTRICT_CODE("district_code::", "区域Code", 1000, 1200),
    DISTRICT_ID("district_id::", "区域Id", 1000, 1200),

    USER_DEPT_ID("user_dept_id::", "用户部门", 3600, 7200),
    USER_DEPT_INFO("user_dept_info::", "用户部门", 3600, 7200),


    DATA_AUTHORITY("data_authority::", "数据权限", 60, 100)
    ;

    /**
     * redis 的Key
     */
    private String key;

    /**
     * 单位，s
     */
    private int minExpire;

    /**
     * 单位，s
     */
    private int maxExpire;

    /**
     * 描述
     */
    private String describe;

    RedisKeyEnum(String key, String describe, int minExpire, int maxExpire) {
        this.key = key;
        this.describe = describe;
        this.minExpire = minExpire;
        this.maxExpire = maxExpire;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public int getExpire() {
        Random r = new Random();
        return r.nextInt(maxExpire - minExpire + 1) + minExpire;
    }

}
