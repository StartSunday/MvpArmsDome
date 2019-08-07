package com.jess.arms.http;

/**
 * Created by ChenYuYun on 2018/9/20.
 * 分页
 */
public class Pager {
    public int page;
    public int pageSize;
    public String sortField;
    public String sortOrder;
    public String like;
    public boolean paging = true;

    public Pager() {

    }

    public Pager(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public Pager(int page, int pageSize, String sortField, String sortOrder) {
        this.page = page;
        this.pageSize = pageSize;
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
}
