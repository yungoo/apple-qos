package com.appleframework.qos.server.statistics.dao;

import com.appleframework.qos.core.orm.MapQuery;
import com.appleframework.qos.core.orm.PageQuery;
import org.springframework.stereotype.Repository;

import com.appleframework.qos.server.core.entity.DayStatMethod;

import java.util.List;

@Repository
public interface DayStatMethodDao {
	
	public void createTable();
	
	public void insert(DayStatMethod dsm);
	
	public void update(DayStatMethod dsm);
    	
	public DayStatMethod getByDate(MapQuery query);

	List<DayStatMethod> findPageByAppAndDay(PageQuery query);
}