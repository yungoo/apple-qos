package com.appleframework.qos.core.orm;

import java.util.LinkedHashMap;

/**
 * Created by haiyang on 15/7/23.
 */
public class MapQuery extends LinkedHashMap<String, Object> {

    public static MapQuery create() {
        return new MapQuery();
    }

    protected MapQuery() {
        super();
    }

    public MapQuery addParameters(String parameter, Object value) {
        this.put(parameter, value);

        return this;
    }

}
