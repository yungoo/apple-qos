package com.appleframework.qos.server.statistics.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.appleframework.qos.core.orm.MapQuery;
import com.appleframework.qos.core.orm.PageQuery;
import com.appleframework.qos.core.orm.Pagination;
import com.appleframework.qos.server.core.entity.DayStatCode;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.appleframework.qos.server.statistics.dao.DayStatNodeDao;
import com.appleframework.qos.server.core.entity.DayStatNode;
import com.appleframework.qos.server.statistics.service.DayStatNodeService;

@Service("dayStatNodeService")
@Lazy(false)
public class DayStatNodeServiceImpl implements DayStatNodeService {

	@Resource
	private DayStatNodeDao dayStatNodeDao;
	
	@PostConstruct
	public void createTable() {
		dayStatNodeDao.createTable();
	}

	public void save(DayStatNode dsc) {
		dsc.setCreateTime(new Date());
		dayStatNodeDao.insert(dsc);
	}
	
	public void update(DayStatNode dsc) {
		dsc.setUpdateTime(new Date());
		dayStatNodeDao.update(dsc);
	}
	
	public DayStatNode getByDate(Date date, Long consumerAppId, Long providerAppId, Long nodeId) {
		MapQuery query = MapQuery.create();
		query.addParameters("providerAppId", providerAppId);
		query.addParameters("consumerAppId", consumerAppId);
		query.addParameters("providerNodeId", nodeId);
		query.addParameters("statDate", date);
		return dayStatNodeDao.getByDate(query);
	}

	@Override
	public List<DayStatNode> findByAppAndDay(Date startDate, Date endDate, String consumerAppName, String providerAppName) {
		MapQuery query = MapQuery.create();
		query.addParameters("providerAppName", providerAppName);
		query.addParameters("consumerAppName", consumerAppName);
		query.addParameters("statDate", startDate);
		query.addParameters("endDate", endDate);
		return dayStatNodeDao.findByAppAndDay(query);
	}

	@Override
	public Pagination findPageByAppAndDay(Pagination page, Date startDate, Date endDate, String consumerAppName, String providerAppName) {
		PageQuery query = PageQuery.create(page);

		query.addParameters("providerAppName", providerAppName);
		query.addParameters("consumerAppName", consumerAppName);
		query.addParameters("statDate", startDate);
		query.addParameters("endDate", endDate);

		List<DayStatNode> data = dayStatNodeDao.findPageByAppAndDay(query);
		page.setList(data);
		return page;
	}
}