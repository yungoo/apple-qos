package com.appleframework.qos.server.statistics;

import com.appleframework.qos.core.orm.Pagination;
import com.appleframework.qos.server.core.entity.AppInfo;
import com.appleframework.qos.server.statistics.service.AppInfoService;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sun.jvm.hotspot.debugger.Page;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by haiyang on 15/7/30.
 */
@Controller
public class web {

    @Resource
    private AppInfoService appInfoService;

    @RequestMapping(value="apps")
    public ModelAndView apps() {
        Pagination page = new Pagination(0, 20);
        String keyword = "";

        List<AppInfo> apps = appInfoService.findPage(page, keyword);
        Map<String, Object> model = Maps.newHashMap();
        model.put("list", apps);
        model.put("page", page);

        return new ModelAndView("app_info/list", model);
    }

}
