package com.appleframework.qos.server.statistics.dao;

import com.appleframework.qos.core.orm.MapQuery;
import com.appleframework.qos.core.orm.PageQuery;
import org.springframework.stereotype.Repository;

import com.appleframework.qos.server.core.entity.DayStatNode;

import java.util.List;
import java.util.Map;


@Repository
public interface DayStatNodeDao {
	
	public void createTable();
	
	public void insert(DayStatNode dsn);
	
	public void update(DayStatNode dsn);
    	
	public DayStatNode getByDate(MapQuery query);

	public List<DayStatNode> findByAppAndDay(Map<String, Object> param);

	public List<DayStatNode> findPageByAppAndDay(PageQuery query);

}