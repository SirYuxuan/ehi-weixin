package com.yuxuan66.framework.ehi.weixin.support.model;

import lombok.Data;

@Data
public class JSApiTicket {

	/**
	 * jsApi ticket
	 */
	private String ticket;
	/**
	 * 过期时间
	 */
	private long expiresTime;
}
