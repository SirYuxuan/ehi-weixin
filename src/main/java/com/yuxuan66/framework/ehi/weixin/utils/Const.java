package com.yuxuan66.framework.ehi.weixin.utils;

import cn.hutool.setting.Setting;

/**
 * 系统核心常量
 * 
 * @projectName: [ehi-weixin]
 * @author: [Sir丶雨轩]
 * @createDate: [2019年7月1日 下午4:43:01]
 * @version: [v1.0]
 */
public interface Const {
	/**
	 * 核心配置文件
	 */
	Setting setting = new Setting("weixin.setting");
	Setting domain = new Setting("domain.setting");
	/**
	 * 鉴权信息
	 */
	interface Auth {
		/**
		 * 微信授权 appid
		 */
		String WEIXIN_APPID = setting.getStr("appId", "auth","");
		/**
		 * 微信授权 appsecret
		 */
		String WEIXIN_APPSECRET = setting.getStr("appSecret", "auth","");
	}

	/**
	 * 公众平台接口域名
	 */
	interface doMain {
		/**
		 * 通用接口
		 */
		String CURRENCY = domain.getStr("currency", "domain","");
		/**
		 * 通用异地容灾域名
		 */
		String DISASTERRECOVERY = domain.getStr("disasterRecovery", "domain","");
		/**
		 * 上海域名
		 */
		String SHANGHAI = domain.getStr("shangHai", "domain","");
		/**
		 * 深圳域名
		 */
		String SHENZHEN = domain.getStr("shenZhen", "domain","");
		/**
		 * 香港域名
		 */
		String XIANGGANG = domain.getStr("xiangGang", "domain","");
	}

}
