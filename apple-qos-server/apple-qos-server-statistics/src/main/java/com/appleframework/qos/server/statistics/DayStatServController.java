package com.appleframework.qos.server.statistics;

import com.appleframework.qos.collector.core.utils.StringUtils;
import com.appleframework.qos.core.orm.Pagination;
import com.appleframework.qos.server.statistics.service.DayStatMethodService;
import com.appleframework.qos.server.statistics.service.DayStatNodeService;
import com.appleframework.qos.server.statistics.service.DayStatService;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * Created by haiyang on 15/8/4.
 */
@Controller
@RequestMapping(value = "/day_stat_serv/")
public class DayStatServController {

    @Resource
    private DayStatMethodService dayStatMethodService;

    @RequestMapping(value="list")
    public ModelAndView list(String startDate,
                             String endDate,
                             String consumerAppName, String providerAppName,
                             String serviceInterface, String method,
                             Integer pageNo, Integer pageSize) {
        if (pageNo == null) {
            pageNo = 0;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        Pagination page = new Pagination(pageNo, pageSize);

        Date startDateValue = null, endDateValue = null;
        try {
            if (!StringUtils.isEmpty(startDate)) {
                startDateValue = DateUtils.parseDate(startDate, "yyyy-MM-dd");
            }

            if (!StringUtils.isEmpty(endDate)) {
                endDateValue = DateUtils.parseDate(endDate, "yyyy-MM-dd");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Pagination apps = dayStatMethodService.findPageByAppAndDay(page,
                startDateValue,
                endDateValue,
                consumerAppName,
                providerAppName,
                serviceInterface, method);
        Map<String, Object> model = Maps.newHashMap();
        model.put("list", apps.getList());
        model.put("page", apps);
        model.put("startDate", startDate);
        model.put("endDate", endDate);
        model.put("consumerAppName", consumerAppName);
        model.put("providerAppName", providerAppName);
        model.put("serviceInterface", serviceInterface);
        model.put("method", method);

        return new ModelAndView("day_stat_serv/list", model);
    }

}
