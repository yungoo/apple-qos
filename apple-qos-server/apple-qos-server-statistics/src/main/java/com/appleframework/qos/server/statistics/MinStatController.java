package com.appleframework.qos.server.statistics;

import com.appleframework.qos.collector.core.utils.StringUtils;
import com.appleframework.qos.core.orm.Pagination;
import com.appleframework.qos.server.statistics.service.DayStatMethodService;
import com.appleframework.qos.server.statistics.service.MinStatService;
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
@RequestMapping(value = "/min_stat/")
public class MinStatController {

    @Resource
    private MinStatService minStatService;

    @RequestMapping(value="list")
    public ModelAndView list(String statDate,
                             String endDate,
                             String consumerAppName, String providerAppName,
                             Integer pageNo, Integer pageSize) {
        if (pageNo == null) {
            pageNo = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        Pagination page = new Pagination(pageNo, pageSize);

        Date statDateValue = null;
        try {
            if (!StringUtils.isEmpty(statDate)) {
                statDateValue = DateUtils.parseDate(statDate, "yyyy-MM-dd");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Pagination apps = minStatService.findPageByAppAndDay(page,
                statDateValue,
                consumerAppName,
                providerAppName);
        Map<String, Object> model = Maps.newHashMap();
        model.put("list", apps.getList());
        model.put("page", apps);
        model.put("statDate", statDate);
        model.put("consumerAppName", consumerAppName);
        model.put("providerAppName", providerAppName);

        return new ModelAndView("min_stat/list", model);
    }

}
