package com.appleframework.qos.server.statistics.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.appleframework.qos.core.orm.Pagination;
import com.appleframework.qos.server.core.entity.DayStatNode;

public interface DayStatNodeService {
	
	void createTable();

	void save(DayStatNode dsn);
	
	void update(DayStatNode dsn);
	
	DayStatNode getByDate(Date date, Long consumerAppId, Long providerAppId, Long nodeId);

	List<DayStatNode> findByAppAndDay(Date startDate, Date endDate, String consumerAppName, String providerAppName);

	Pagination findPageByAppAndDay(Pagination page, Date startDate, Date endDate, String consumerAppName, String providerAppName);
}
