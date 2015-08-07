package com.appleframework.qos.server.statistics.service;

import com.appleframework.qos.core.orm.Pagination;
import com.appleframework.qos.server.core.entity.AppInfo;

import java.util.List;

public interface AppInfoService {
	
	public AppInfo get(Long id);

	public void update(AppInfo appInfo) throws ServiceException;
		
	public List<AppInfo> findPage(Pagination page, String keyword);
	
	public boolean isUniqueByCode(String oldCode, String newCode);
	
	public boolean isExistByCode(String code);
}
