package com.wjs.elasticsearch.elastic.params;

import java.util.List;

/**
 * @author wenjs
 */
public class PageImpl<T> implements Page<T> {

    private long total;
    private int page;
    private int size;
    private List<T> data;

    public PageImpl(long total, int page, int size, List<T> data) {
        this.total = total;
        this.page = page;
        this.size = size;
        this.data = data;
    }

    public static <T> PageImpl of(long total, int page, int size, List<T> data) {
        return new PageImpl(total, page, size, data);
    }

    public PageImpl setTotal(long total) {
        this.total = total;
        return this;
    }

    public PageImpl setPage(int page) {
        this.page = page;
        return this;
    }

    public PageImpl setSize(int size) {
        this.size = size;
        return this;
    }

    public PageImpl setData(List<T> data) {
        this.data = data;
        return this;
    }

    @Override
    public long getTotal() {
        return total;
    }

    @Override
    public List<T> getData() {
        return data;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getSize() {
        return size;
    }
}
