package com.yuxuan66.framework.ehi.weixin.support.task;

import com.yuxuan66.framework.ehi.weixin.support.EhiWeixin;

import cn.hutool.cron.task.Task;

/**
 * 任务执行器,自动获取刷新accessToken,ticket
 * 
 * @author yuxuan
 *
 */
public class RefreshTask implements Task {


	@Override
	public void execute() {
		// 刷新微信AccessToken
		EhiWeixin.refreshAccessToken();
		// 刷新JSApiTicket
		EhiWeixin.refreshJsTicket();
	}

}
