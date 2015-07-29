package com.appleframework.qos.core.orm;

/**
 * Created by haiyang on 15/7/23.
 */
public class PageQuery extends MapQuery {

    public static PageQuery create(Pagination page) {
        PageQuery query = new PageQuery();

        query.addParameters("_offset_", page.getFirstResult());
        query.addParameters("_count_", page.getPageSize());

        return query;
    }
}
