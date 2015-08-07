package com.appleframework.qos.server.statistics.access;

import com.appleframework.qos.collector.core.utils.StringUtils;
import com.appleframework.qos.server.statistics.utils.RequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by haiyang on 15/8/7.
 */
public class AccessControlFilter extends OncePerRequestFilter {

    private static final Set<String> whiteSet = new HashSet<String>();

    public AccessControlFilter() {
        super();

        addRequiredProperty("whiteList");
    }

    @Override
    protected void initFilterBean() throws ServletException {
        super.initFilterBean();

        final String configuredAccessableIPs = getFilterConfig().getInitParameter("whiteList");
        String[] ips = configuredAccessableIPs.split(",");
        for (String ip : ips) {
            whiteSet.add(ip);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String ip = RequestUtils.getRemoteIPAddress(request);

        if (StringUtils.isEmpty(ip) || (!containsInWhiteList(ip) && !isLocal(ip))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "禁止访问");
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean isLocal(String ip) {
        return "0:0:0:0:0:0:0:1".equals(ip) || "127.0.0.1".equals(ip);
    }

    private boolean containsInWhiteList(String ip) {
        return whiteSet.contains(ip);
    }
}
