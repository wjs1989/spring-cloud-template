package com.wjs.netty.core;

/**
 * @author wenjs
 */
public class NettyPoolKey {

    private String address;

    /**
     * Gets get address.
     *
     * @return the get address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets set address.
     *
     * @param address the address
     * @return the address
     */
    public NettyPoolKey setAddress(String address) {
        this.address = address;
        return this;
    }
}
