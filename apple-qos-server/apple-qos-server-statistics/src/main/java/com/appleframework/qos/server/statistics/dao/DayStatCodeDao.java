package com.appleframework.qos.server.statistics.dao;

import com.appleframework.qos.core.orm.MapQuery;
import org.springframework.stereotype.Repository;

import com.appleframework.qos.server.core.entity.DayStatCode;

@Repository
public interface DayStatCodeDao {
	
	public void createTable();
	
	public void insert(DayStatCode dsc);
	
	public void update(DayStatCode dsc);
    	
	public DayStatCode getByDate(MapQuery query);
	
	/*public List<DayStatCode> findByAppAndDay(Map<String, Object> param);

	public List<DayStatCode> findPageByAppAndDay(PageQuery query);*/

}