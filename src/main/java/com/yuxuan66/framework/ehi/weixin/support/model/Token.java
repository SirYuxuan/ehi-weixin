package com.yuxuan66.framework.ehi.weixin.support.model;

import lombok.Data;

/**
 * token po
 * 
 * @projectName: [ehi-weixin]
 * @author: [Sir丶雨轩]
 * @createDate: [2019年7月1日 下午5:04:15]
 * @version: [v1.0]
 */
@Data
public class Token {
	/**
	 * access_token
	 */
	private String token;
	/**
	 * 过期时间
	 */
	private long expiresTime;

}
