package com.appleframework.qos.server.statistics;

import com.appleframework.qos.collector.core.utils.StringUtils;
import com.appleframework.qos.core.orm.Pagination;
import com.appleframework.qos.server.core.entity.AppInfo;
import com.appleframework.qos.server.statistics.service.AppInfoService;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.jvm.hotspot.debugger.Page;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by haiyang on 15/7/30.
 */
@Controller
@RequestMapping(value="/app_info/")
public class AppInfoController {

    @Resource
    private AppInfoService appInfoService;

    @RequestMapping(value="list")
    public ModelAndView list(@RequestParam(required = false) String keyword,
                             Integer pageNo, Integer pageSize) {
        if (pageNo == null) {
            pageNo = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        Pagination page = new Pagination(pageNo, pageSize);

        if (keyword == null) keyword = "";

        List<AppInfo> apps = appInfoService.findPage(page, keyword);
        Map<String, Object> model = Maps.newHashMap();
        model.put("list", apps);
        model.put("page", page);

        return new ModelAndView("app_info/list", model);
    }

    @RequestMapping(value="view")
    public ModelAndView view(@RequestParam long id) {
        AppInfo info = appInfoService.get(id);

        Map<String, Object> model = Maps.newHashMap();
        model.put("info", info);

        return new ModelAndView("app_info/view", model);
    }

    @RequestMapping(value="edit")
    public ModelAndView edit(@RequestParam long id) {
        AppInfo info = appInfoService.get(id);

        Map<String, Object> model = Maps.newHashMap();
        model.put("info", info);

        return new ModelAndView("app_info/edit", model);
    }

    @RequestMapping(value="check_code")
    public @ResponseBody String edit(@RequestParam String oldCode, @RequestParam String code) {
        Map<String, Object> model = Maps.newHashMap();

        if (!appInfoService.isUniqueByCode(oldCode, code)) {
            return "false";
        }
        return "true";
    }

    @RequestMapping(value="update")
    public @ResponseBody String update(AppInfo info) {
        appInfoService.update(info);
        return "保存成功";
    }

}
