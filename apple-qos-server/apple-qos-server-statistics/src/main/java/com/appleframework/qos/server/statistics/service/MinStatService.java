package com.appleframework.qos.server.statistics.service;

import java.util.Date;
import java.util.List;

import com.appleframework.qos.core.orm.Pagination;
import com.appleframework.qos.server.core.entity.MinStat;

public interface MinStatService {
	
	Pagination findPageByAppAndDay(Pagination page, Date statDay,
			String consumerAppName, String providerAppName);
	
}
