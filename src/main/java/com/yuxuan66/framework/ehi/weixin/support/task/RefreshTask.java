package com.yuxuan66.framework.ehi.weixin.support.task;

import com.yuxuan66.framework.ehi.weixin.support.EhiWeixin;

import cn.hutool.cron.task.Task;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * 任务执行器,自动获取刷新token
 * 
 * @projectName: [ehi-weixin]
 * @author: [Sir丶雨轩]
 * @createDate: [2019年7月1日 下午4:57:11]
 * @version: [v1.0]
 */
public class RefreshTask implements Task {

	private EhiWeixin weixin = EhiWeixin.getInstance();
	private final Log log = LogFactory.get();
	@Override
	public void execute() {
		log.info("RefreshTask run.");
		//刷新微信AccessToken
		weixin.refreshToken();
		//刷新JSApiTicket
		weixin.refreshJSApiTicket();
		log.info("RefreshTask end.");
	}

}
