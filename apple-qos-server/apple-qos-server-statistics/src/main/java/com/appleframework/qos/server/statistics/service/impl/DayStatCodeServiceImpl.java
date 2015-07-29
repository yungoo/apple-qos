package com.appleframework.qos.server.statistics.service.impl;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.appleframework.qos.core.orm.MapQuery;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.appleframework.qos.server.statistics.dao.DayStatCodeDao;
import com.appleframework.qos.server.core.entity.DayStatCode;
import com.appleframework.qos.server.statistics.service.DayStatCodeService;

@Service("dayStatCodeService")
@Lazy(false)
public class DayStatCodeServiceImpl implements DayStatCodeService {

	@Resource
	private DayStatCodeDao dayStatCodeDao;
	
	@PostConstruct
	public void createTable() {
		dayStatCodeDao.createTable();
	}

	public void save(DayStatCode dsc) {
		dsc.setCreateTime(new Date());
		dayStatCodeDao.insert(dsc);
	}
	
	public void update(DayStatCode dsc) {
		dsc.setUpdateTime(new Date());
		dayStatCodeDao.update(dsc);
	}
	
	public DayStatCode getByDate(Date statDate, Long consumerAppId, Long providerAppId, String errorCode) {
		MapQuery query = MapQuery.create();
		query.addParameters("providerAppId", providerAppId);
		query.addParameters("consumerAppId", consumerAppId);
		query.addParameters("statDate", statDate);
		query.addParameters("errorCode", errorCode);
		return dayStatCodeDao.getByDate(query);
	}
	
}