package com.yuxuan66.framework.ehi.weixin.support;

import java.util.Date;

import com.yuxuan66.framework.ehi.weixin.support.model.JSApiTicket;
import com.yuxuan66.framework.ehi.weixin.support.model.Token;
import com.yuxuan66.framework.ehi.weixin.support.task.RefreshTask;
import com.yuxuan66.framework.ehi.weixin.utils.Const;
import com.yuxuan66.framework.ehi.weixin.utils.WeixinUtil;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * ehi-weixin核心类,重量级类,全局只会初始化一次,
 * 
 * @projectName: [ehi-weixin]
 * @author: [Sir丶雨轩]
 * @createDate: [2019年7月1日 下午4:50:37]
 * @version: [v1.0]
 */
public final class EhiWeixin extends WeixinUtil {

	private static volatile EhiWeixin ehiWeixin;
	private static Object lock = new Object();
	private final Log log = LogFactory.get();
	/**
	 * 微信授权 token
	 */
	private Token token;
	/**
	 * jsApi ticket
	 */
	private JSApiTicket jSApiTicket;

	private EhiWeixin() {

	}

	/**
	 * 初始化整体系统
	 * 
	 * @author: [Sir丶雨轩]
	 * @createDate: [2019年7月1日 下午4:51:57]
	 */
	private void init() {
		// 开启hutool的Corn模块的秒兼容模式
		CronUtil.setMatchSecond(true);
		// 添加刷新的任务 0 0/59 1/1 * * ?
		CronUtil.schedule("0 0/59 1/1 * * ? ", new RefreshTask());
		CronUtil.start();

		ehiWeixin.refreshToken(true);
		ehiWeixin.refreshJSApiTicket(true);

	}

	/**
	 * 刷新JSApiTicket不强制
	 * 
	 * @author: [Sir丶雨轩]
	 * @createDate: [2019年7月2日 下午6:27:16]
	 * @return
	 */
	public JSApiTicket refreshJSApiTicket() {
		return refreshJSApiTicket(false);
	}

	/**
	 * 刷新JSApiTicket
	 * 
	 * @author: [Sir丶雨轩]
	 * @createDate: [2019年7月2日 下午6:22:24]
	 * @param force 是否强制,非强制时会判断是否过期,如未过期不会刷新
	 * @return
	 */
	public JSApiTicket refreshJSApiTicket(boolean force) {
		if (!force) {
			if (this.jSApiTicket != null
					&& (this.jSApiTicket.getExpiresTime() - 10 * 60 * 1000) > System.currentTimeMillis()) {
				return this.jSApiTicket;
			}
		}
		String url = StrUtil.format("{}cgi-bin/ticket/getticket?access_token={}&type=jsapi", Const.doMain.CURRENCY,
				this.token.getToken());
		Dict weixinJson = WeixinUtil.get(url);
		JSApiTicket ticket = new JSApiTicket();
		ticket.setTicket(weixinJson.getStr("ticket"));
		ticket.setExpiresTime(DateUtil.offsetSecond(new Date(), weixinJson.getInt("expires_in")).getTime());
		this.setJSApiTicket(ticket);
		if (log.isDebugEnabled()) {
			log.debug("get JSApiTicket success. new ticket=" + JSONUtil.parseObj(ticket));
		}
		return ticket;

	}

	/**
	 * 刷新accesstoken不强制
	 * 
	 * @author: [Sir丶雨轩]
	 * @createDate: [2019年7月2日 下午6:27:16]
	 * @return
	 */
	public Token refreshToken() {
		return refreshToken(false);
	}

	/**
	 * 刷新accesstoken
	 * 
	 * @author: [Sir丶雨轩]
	 * @createDate: [2019年7月2日 下午6:22:24]
	 * @param force 是否强制,非强制时会判断是否过期,如未过期不会刷新
	 * @return
	 */
	public Token refreshToken(boolean force) {
		if (!force) {
			if (this.token != null && (this.token.getExpiresTime() - 10 * 60 * 1000) > System.currentTimeMillis()) {
				return this.token;
			}
		}
		Dict param = Dict.create();
		param.put("appid", Const.Auth.WEIXIN_APPID);
		param.put("secret", Const.Auth.WEIXIN_APPSECRET);
		param.put("url", Const.doMain.CURRENCY);
		String url = StrUtil.format("{url}cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}",
				param);
		Dict weixinJson = WeixinUtil.get(url);
		Token token = new Token();
		token.setToken(weixinJson.getStr("access_token"));
		token.setExpiresTime(DateUtil.offsetSecond(new Date(), weixinJson.getInt("expires_in")).getTime());
		this.setToken(token);
		if (log.isDebugEnabled()) {
			log.debug("get token success. new token=" + JSONUtil.parseObj(token));
		}
		return token;
	}

	/**
	 * 获取唯一的ehiWeixin实例
	 * 
	 * @author: [Sir丶雨轩]
	 * @createDate: [2019年7月1日 下午4:54:34]
	 * @return
	 */
	public static EhiWeixin getInstance() {
		if (ehiWeixin == null) {
			synchronized (lock) {
				if (ehiWeixin == null) {
					ehiWeixin = new EhiWeixin();
					ehiWeixin.init();
				}
			}
		}
		return ehiWeixin;
	}

	// getter and setter

	public Token getToken() {
		return this.token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public JSApiTicket getJSApiTicket() {
		return this.jSApiTicket;
	}

	public void setJSApiTicket(JSApiTicket jSApiTicket) {
		this.jSApiTicket = jSApiTicket;
	}

}
