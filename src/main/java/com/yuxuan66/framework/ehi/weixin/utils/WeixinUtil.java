package com.yuxuan66.framework.ehi.weixin.utils;

import java.util.HashMap;
import java.util.Map;

import com.yuxuan66.framework.ehi.weixin.support.EhiWeixin;
import com.yuxuan66.framework.ehi.weixin.support.exception.EhiWeixinException;
import com.yuxuan66.framework.ehi.weixin.support.model.Sign;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public  class WeixinUtil {

	private static EhiWeixin weixin = EhiWeixin.getInstance();

	/**
	 * 通过url获取js签名
	 * 
	 * @author: [Sir丶雨轩]
	 * @createDate: [2019年7月2日 下午7:04:33]
	 * @param url
	 * @return
	 */
	public static Sign getJsSign(String url) {
		Sign sign = new Sign();
		sign.setAppId(Const.Auth.WEIXIN_APPID);
		sign.setTimestamp(System.currentTimeMillis() / 1000);
		sign.setNoncestr(IdUtil.fastSimpleUUID());
		Map<String, Object> textMap = new HashMap<>();
		textMap.put("jsapi_ticket", weixin.getJSApiTicket().getTicket());
		textMap.put("noncestr", sign.getNoncestr());
		textMap.put("timestamp", sign.getTimestamp());
		textMap.put("url", url);
		String text = StrUtil.format("jsapi_ticket={jsapi_ticket}&noncestr={noncestr}&timestamp={timestamp}&url={url}",
				textMap);
		Digester digester = new Digester(DigestAlgorithm.SHA1);
		sign.setSignature(digester.digestHex(text));
		return sign;

	}

	/**
	 * 发送get请求获取weixin api body
	 * 
	 * @author: [Sir丶雨轩]
	 * @createDate: [2019年7月2日 下午6:34:53]
	 * @param url 请求地址
	 * @return
	 */
	protected static Dict get(String url) {
		String weixinBody = HttpUtil.get(url);
		if (!JSONUtil.isJson(weixinBody)) {
			throw new EhiWeixinException("parseJson error! weixinBody=" + weixinBody);
		}
		JSONObject weixinJson = JSONUtil.parseObj(weixinBody);
		if (weixinJson.containsKey("errcode") && !"0".equals(weixinJson.getStr("errcode"))) {
			throw new EhiWeixinException("weixin api error! weixinJson=" + weixinJson);
		}
		Dict dict = Dict.create();
		dict.putAll(weixinJson);
		return dict;
	}
}
