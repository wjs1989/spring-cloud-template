package com.elasticsearch.param;

/**
 * @author wenjs
 */
public class Sort  extends Column {

    /**
     *  description = "排序方式",
     *  allowableValues = {"asc", "desc"},
     */
    private String order = "asc";

    public Sort() {
    }

    public Sort(String column) {
        this.setName(column);
    }

    public String getOrder() {
        return "desc".equalsIgnoreCase(this.order) ? this.order : (this.order = "asc");
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void asc() {
        this.order = "asc";
    }

    public void desc() {
        this.order = "desc";
    }
}
