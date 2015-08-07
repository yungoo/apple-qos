package com.appleframework.qos.server.statistics.service.impl;

import javax.annotation.Resource;

import com.appleframework.qos.core.orm.PageQuery;
import com.appleframework.qos.core.orm.Pagination;
import com.appleframework.qos.server.statistics.mybatis2.dto.table.PagingCriteria;
import com.appleframework.qos.server.statistics.mybatis2.dto.table.SearchField;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.appleframework.qos.server.core.entity.AppInfo;
import com.appleframework.qos.server.statistics.dao.AppInfoDao;
import com.appleframework.qos.server.statistics.service.AppInfoService;

import java.util.List;

@Service("appInfoService")
public class AppInfoServiceImpl implements AppInfoService {

	@Resource
	private AppInfoDao appInfoDao;

	
	public AppInfo get(Long id) {
		return appInfoDao.get(id);
	}
	
	public void update(AppInfo appInfo) {
		appInfoDao.update(appInfo);
	}
	
	public boolean isExistByCode(String code) {
	    if(this.countByCode(code) > 0) {
	    	return true;
	    } else {
			return false;
		}
	}
	
	public int countByCode(String code) {
		return appInfoDao.countByCode(code);
	}
	
	public boolean isUniqueByCode(String oldCode, String newCode) {
		if (StringUtils.equalsIgnoreCase(oldCode, newCode)) {
			return true;
		} else {
			if (this.isExistByCode(newCode)) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	public List<AppInfo> findPage(Pagination page, String keyword) {
		PageQuery query = PageQuery.create(page);
		query.addParameters("keyword", keyword);
		return appInfoDao.findPage(query);
	}

}
