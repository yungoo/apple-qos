package com.appleframework.qos.server.statistics.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.appleframework.qos.core.orm.MapQuery;
import com.appleframework.qos.core.orm.PageQuery;
import com.appleframework.qos.core.orm.Pagination;
import com.appleframework.qos.server.core.entity.DayStatCode;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.appleframework.qos.server.core.entity.DayStatMethod;
import com.appleframework.qos.server.statistics.dao.DayStatMethodDao;
import com.appleframework.qos.server.statistics.service.DayStatMethodService;

@Service("dayStatMethodService")
@Lazy(false)
public class DayStatMethodServiceImpl implements DayStatMethodService {

	@Resource
	private DayStatMethodDao dayStatMethodDao;
	
	@PostConstruct
	public void createTable() {
		dayStatMethodDao.createTable();
	}

	public void save(DayStatMethod dsa) {
		dsa.setCreateTime(new Date());
		dayStatMethodDao.insert(dsa);
	}
	
	public void update(DayStatMethod dsa) {
		dsa.setUpdateTime(new Date());
		dayStatMethodDao.update(dsa);
	}
	
	public DayStatMethod getByDate(Date statDate, Long consumerAppId, Long providerAppId,
			String service, String method) {
		MapQuery query = MapQuery.create();
		query.addParameters("providerAppId", providerAppId);
		query.addParameters("consumerAppId", consumerAppId);
		query.addParameters("service", service);
		query.addParameters("method", method);
		query.addParameters("statDate", statDate);
		return dayStatMethodDao.getByDate(query);
	}

	@Override
	public Pagination findPageByAppAndDay(Pagination page, Date startDate, Date endDate, String consumerAppName, String providerAppName, String service, String method) {
		PageQuery query = PageQuery.create(page);

		query.addParameters("providerAppName", providerAppName);
		query.addParameters("consumerAppName", consumerAppName);
		query.addParameters("service", service);
		query.addParameters("method", method);
		if (startDate != null) {
			query.addParameters("startDate", startDate);
		} else {
			query.addParameters("startDate", null);
		}
		if (endDate != null) {
			query.addParameters("endDate", endDate);
		} else {
			query.addParameters("endDate", null);
		}
		List<DayStatMethod> data =  dayStatMethodDao.findPageByAppAndDay(query);
		page.setList(data);
		return page;
	}
}