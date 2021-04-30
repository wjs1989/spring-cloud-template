package com.elasticsearch.param;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wenjs
 */
public class QueryParam extends Param implements Serializable, Cloneable{
    private static final long serialVersionUID = 7941767360194797891L;
    public static final int DEFAULT_FIRST_PAGE_INDEX = Integer.getInteger("easyorm.page.fist.index", 0);
    public static final int DEFAULT_PAGE_SIZE = Integer.getInteger("easyorm.page.size", 25);

    /**
     * 是否分页
     */
    private boolean paging = true;

    /**
     * 第一页索引
     */
    private int firstPageIndex;

    /**
     * 页码
     */
    private int pageIndex;

    /**
     * 每页数量
     */
    private int pageSize;
    private List<Sort> sorts;

    private transient int pageIndexTmp;

    private boolean forUpdate;

    public QueryParam() {
        this.firstPageIndex = DEFAULT_FIRST_PAGE_INDEX;
        this.pageIndex = this.firstPageIndex;
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.sorts = new LinkedList();
        this.pageIndexTmp = 0;
        this.forUpdate = false;
    }

    public Sort orderBy(String column) {
        Sort sort = new Sort(column);
        this.sorts.add(sort);
        return sort;
    }

    public <Q extends QueryParam> Q noPaging() {
        this.setPaging(false);
        return (Q) this;
    }

    public <Q extends QueryParam> Q doPaging(int pageIndex) {
        this.setPageIndex(pageIndex);
        this.setPaging(true);
        return (Q) this;
    }

    public <Q extends QueryParam> Q doPaging(int pageIndex, int pageSize) {
        this.setPageIndex(pageIndex);
        this.setPageSize(pageSize);
        this.setPaging(true);
        return (Q) this;
    }

    public <Q extends QueryParam> Q rePaging(int total) {
        this.paging = true;
        if (this.pageIndex != 0 && this.pageIndex * this.pageSize >= total) {
            int tmp = total / this.getPageSize();
            this.pageIndex = total % this.getPageSize() == 0 ? tmp - 1 : tmp;
        }

        return (Q) this;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndexTmp = this.pageIndex;
        this.pageIndex = Math.max(pageIndex - this.firstPageIndex, 0);
    }

    public void setFirstPageIndex(int firstPageIndex) {
        this.firstPageIndex = firstPageIndex;
        this.pageIndex = Math.max(this.pageIndexTmp - this.firstPageIndex, 0);
    }

    public int getThinkPageIndex() {
        return this.pageIndex + this.firstPageIndex;
    }

    @Override
    public QueryParam clone() {
        return (QueryParam)super.clone();
    }

    public boolean isPaging() {
        return this.paging;
    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public List<Sort> getSorts() {
        return this.sorts;
    }

    public int getPageIndexTmp() {
        return this.pageIndexTmp;
    }

    public boolean isForUpdate() {
        return this.forUpdate;
    }

    public void setPaging(boolean paging) {
        this.paging = paging;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setSorts(List<Sort> sorts) {
        this.sorts = sorts;
    }

    public void setPageIndexTmp(int pageIndexTmp) {
        this.pageIndexTmp = pageIndexTmp;
    }

    public void setForUpdate(boolean forUpdate) {
        this.forUpdate = forUpdate;
    }

    public int getFirstPageIndex() {
        return this.firstPageIndex;
    }
}
