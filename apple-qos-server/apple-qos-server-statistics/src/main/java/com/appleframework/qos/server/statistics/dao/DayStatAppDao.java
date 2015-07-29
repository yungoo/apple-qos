package com.appleframework.qos.server.statistics.dao;

import java.util.List;

import com.appleframework.qos.core.orm.MapQuery;
import com.appleframework.qos.core.orm.PageQuery;
import org.springframework.stereotype.Repository;

import com.appleframework.qos.server.core.entity.DayStatApp;

@Repository
public interface DayStatAppDao {
	
	public void createTable();
	
	public void insert(DayStatApp dsa);
	
	public void update(DayStatApp dsa);
    	
	public DayStatApp getByDate(MapQuery query);
	
	public List<DayStatApp> findByDate(MapQuery query);
	
	public List<DayStatApp> findPageByDate(PageQuery query);
	
	public List<DayStatApp> findPageByBetweenDate(PageQuery query);
		
}