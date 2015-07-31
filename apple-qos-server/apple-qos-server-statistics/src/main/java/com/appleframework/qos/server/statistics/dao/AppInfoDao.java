package com.appleframework.qos.server.statistics.dao;

import com.appleframework.qos.core.orm.PageQuery;
import com.appleframework.qos.server.statistics.mybatis2.dto.table.PagingCriteria;
import org.springframework.stereotype.Repository;

import com.appleframework.qos.server.core.entity.AppInfo;

import java.util.List;

@Repository
public interface AppInfoDao {
	
	public AppInfo get(Long id);
		
	public void update(AppInfo appInfo);
	
	public Integer countByCode(String code);
	
	public List<AppInfo> findPage(PagingCriteria query);
		    
}