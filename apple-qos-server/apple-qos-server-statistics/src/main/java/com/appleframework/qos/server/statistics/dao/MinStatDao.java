package com.appleframework.qos.server.statistics.dao;

import java.util.List;

import com.appleframework.qos.core.orm.PageQuery;
import org.springframework.stereotype.Repository;

import com.appleframework.qos.server.core.entity.MinStat;

@Repository
public interface MinStatDao {
	
	List<MinStat> findPage(PageQuery query);
	
}