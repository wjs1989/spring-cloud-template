package com.wjs.redis.model;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/12 15:32
 */
public class RedisResult {
    private StringRedis stringRedis;
    private HashRedis hashRedis;
    private ListRedis listRedis;
    private MemberRedis memberRedis;

    public StringRedis getStringRedis() {
        return stringRedis;
    }

    public void setStringRedis(StringRedis stringRedis) {
        this.stringRedis = stringRedis;
    }

    public HashRedis getHashRedis() {
        return hashRedis;
    }

    public void setHashRedis(HashRedis hashRedis) {
        this.hashRedis = hashRedis;
    }

    public ListRedis getListRedis() {
        return listRedis;
    }

    public void setListRedis(ListRedis listRedis) {
        this.listRedis = listRedis;
    }

    public MemberRedis getMemberRedis() {
        return memberRedis;
    }

    public void setMemberRedis(MemberRedis memberRedis) {
        this.memberRedis = memberRedis;
    }
}
