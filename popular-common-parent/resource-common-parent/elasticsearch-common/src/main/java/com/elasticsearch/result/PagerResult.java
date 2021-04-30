package com.elasticsearch.result;

import com.elasticsearch.param.QueryParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenjs
 */
public class PagerResult<E>  {
    /**
     *  "页码"
     */
    private int pageIndex;

    /**
     * 每页数据量
     */
    private int pageSize;

    /**
     * 数据总量
     */
    private int total;

    /**
     * 数据列表
     */
    private List<E> data;

    public static <E> PagerResult<E> empty() {
        return new PagerResult(0, new ArrayList());
    }

    public static <E> PagerResult<E> of(int total, List<E> list) {
        return new PagerResult(total, list);
    }

    public static <E> PagerResult<E> of(int total, List<E> list, QueryParam entity) {
        PagerResult<E> pagerResult = new PagerResult(total, list);
        pagerResult.setPageIndex(entity.getThinkPageIndex());
        pagerResult.setPageSize(entity.getPageSize());
        return pagerResult;
    }

    public PagerResult() {
    }

    public PagerResult(int total, List<E> data) {
        this.total = total;
        this.data = data;
    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getTotal() {
        return this.total;
    }

    public List<E> getData() {
        return this.data;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setData(List<E> data) {
        this.data = data;
    }
}
