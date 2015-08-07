package com.appleframework.qos.core.orm;

/**
 * Created by haiyang on 15/7/23.
 */
public class PageQuery extends MapQuery {
    private Pagination page;

    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public static PageQuery create(Pagination page) {
        PageQuery query = new PageQuery();

        query.page = page;

        return query;
    }
}
