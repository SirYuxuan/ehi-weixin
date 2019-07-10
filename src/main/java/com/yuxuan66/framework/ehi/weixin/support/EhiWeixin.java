package com.yuxuan66.framework.ehi.weixin.support;

import com.yuxuan66.framework.ehi.weixin.support.task.RefreshTask;
import com.yuxuan66.framework.ehi.weixin.utils.WeixinUtil;

import cn.hutool.cron.CronUtil;

/**
 * ehi-weixin核心类,重量级类,全局只会初始化一次,
 * 
 * @author yuxuan
 *
 */
public final class EhiWeixin extends WeixinUtil {

	private static volatile EhiWeixin ehiWeixin;
	private static Object lock = new Object();
	/**
	 * 微信授权 token
	 */
	private String accessToken;
	/**
	 * jsApi ticket
	 */
	private String jsTicket;

	private EhiWeixin() {

	}

	/**
	 * 初始化ehiWeixin实例
	 * 
	 * @return
	 */
	public static void builder() {
		if (ehiWeixin == null) {
			synchronized (lock) {
				if (ehiWeixin == null) {
					ehiWeixin = new EhiWeixin();
					// 开启hutool的Corn模块的秒兼容模式
					CronUtil.setMatchSecond(true);
					// 添加刷新的任务 每小时执行一次
					CronUtil.schedule("0 0 0/1 * * ? ", new RefreshTask());
					CronUtil.start();
					// 默认刷新
					refreshAccessToken();
					refreshJsTicket();
				}
			}
		}
	}

	// getter and setter

	public static String getAccessToken() {
		return ehiWeixin.accessToken;
	}

	public static void setAccessToken(String accessToken) {
		ehiWeixin.accessToken = accessToken;
	}

	public static String getJsTicket() {
		return ehiWeixin.jsTicket;
	}

	public static void setJsTicket(String jsTicket) {
		ehiWeixin.jsTicket = jsTicket;
	}

}
