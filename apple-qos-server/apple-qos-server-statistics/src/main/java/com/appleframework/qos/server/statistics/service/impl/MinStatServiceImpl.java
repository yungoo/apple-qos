package com.appleframework.qos.server.statistics.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.appleframework.qos.core.orm.PageQuery;
import com.appleframework.qos.core.orm.Pagination;
import org.springframework.stereotype.Service;

import com.appleframework.qos.server.core.entity.MinStat;
import com.appleframework.qos.server.statistics.dao.MinStatDao;
import com.appleframework.qos.server.statistics.service.MinStatService;

@Service("minStatService")
public class MinStatServiceImpl implements MinStatService {

	@Resource
	private MinStatDao minStatDao;
	
	@SuppressWarnings("deprecation")
	public Pagination findPageByAppAndDay(Pagination page, Date statDay,
			String consumerAppName, String providerAppName) {
		PageQuery query = PageQuery.create(page);
		query.addParameters("consumerAppName", consumerAppName);
		query.addParameters("providerAppName", providerAppName);
		query.addParameters("statDate", statDay);
		List<MinStat> ret =  minStatDao.findPage(query);
		page.setList(ret);
		return page;
	}
}
