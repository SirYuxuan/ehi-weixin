package com.yuxuan66.framework.ehi.weixin.support.model;

import lombok.Data;

@Data
public class Sign {
	/**
	 * appid
	 */
	private String appId;
	/**
	 * 随机字符串
	 */
	private String noncestr;
	/**
	 * 时间戳
	 */
	private long timestamp;
	/**
	 * 签名后得到签名
	 */
	private String signature;

}
