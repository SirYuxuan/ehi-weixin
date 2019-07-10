package com.yuxuan66.framework.ehi.weixin.support.exception;

/**
 * 系统异常类
 * @projectName: [ehi-weixin]
 * @author: [Sir丶雨轩]
 * @createDate: [2019年7月1日 下午5:21:19]
 * @version: [v1.0]
 */
public class EhiWeixinException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EhiWeixinException(String msg) {
		super(msg);
	}

}
