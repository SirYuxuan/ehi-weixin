package com.yuxuan66.framework.ehi.weixin.utils.consts;

/**
 * 授权信息
 * 
 * @author yuxuan
 *
 */
public interface Auth extends BaseConst {
	/**
	 * 微信授权 appid
	 */
	String WEIXIN_APPID = setting.getStr("appId", "auth", "");
	/**
	 * 微信授权 appsecret
	 */
	String WEIXIN_APPSECRET = setting.getStr("appSecret", "auth", "");
}
