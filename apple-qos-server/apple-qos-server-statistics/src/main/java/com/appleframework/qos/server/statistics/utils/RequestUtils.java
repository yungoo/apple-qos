package com.appleframework.qos.server.statistics.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
	/***
	 * 获取客户端IP地址;这里通过了Nginx获取;X-Real-IP,
	 * @param request
	 * @return
	 */
	public static String getRemoteIPAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		String host = request.getRemoteHost();
		return ip;
	}
}
