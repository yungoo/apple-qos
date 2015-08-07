package com.appleframework.qos.server.statistics.service;

import java.util.Date;

import com.appleframework.qos.core.orm.Pagination;
import com.appleframework.qos.server.core.entity.DayStatMethod;

public interface DayStatMethodService {
	
	void createTable();

	void save(DayStatMethod dsm);
	
	void update(DayStatMethod dsm);
	
	DayStatMethod getByDate(Date statDate, Long consumerAppId, Long providerAppId,
			String service, String method);

	Pagination findPageByAppAndDay(Pagination page, Date startDate, Date endDate, String consumerAppName, String providerAppName, String service, String method);
}