package com.yuxuan66.framework.ehi.weixin.utils.consts;

/**
 * 系统核心配置
 * 
 * @author yuxuan
 *
 */
public interface Config extends BaseConst {

	/**
	 * 接口域名类型
	 */
	String DOMAINTYPE = setting.getStr("doMain", "config", "currency");
	/**
	 * 接口域名
	 */
	String DOMAIN = domain.getStr(Config.DOMAINTYPE, "domain", "");
	/**
	 * 接口配置信息,用于响应微信认证
	 */
	String TOKEN = setting.getStr("token", "config", "");
}
