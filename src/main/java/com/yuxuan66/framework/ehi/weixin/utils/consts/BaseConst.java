package com.yuxuan66.framework.ehi.weixin.utils.consts;

import cn.hutool.setting.Setting;

public interface BaseConst {
	/**
	 * 核心配置文件
	 */
	Setting setting = new Setting("weixin1.setting");
	/**
	 * 接口域名配置,由系统提供此配置文件
	 */
	Setting domain = new Setting("domain.setting");
}
