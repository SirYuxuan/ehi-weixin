package com.yuxuan66.framework.ehi.weixin.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuxuan66.framework.ehi.weixin.support.EhiWeixin;
import com.yuxuan66.framework.ehi.weixin.support.exception.EhiWeixinException;
import com.yuxuan66.framework.ehi.weixin.support.model.JsSign;
import com.yuxuan66.framework.ehi.weixin.support.model.Menu;
import com.yuxuan66.framework.ehi.weixin.support.model.enums.MenuType;
import com.yuxuan66.framework.ehi.weixin.utils.consts.Const;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * 核心工具类,请不要直接使用此工具类,请使用EhiWeixin的实例来进行操作
 * 
 * @author yuxuan
 *
 */
public class WeixinUtil {

	private static final Log log = LogFactory.get();

	/**
	 * 消息处理,微信推送过来的消息
	 */
	public static void messageHandle(String body) {

	}

	/**
	 * 删除全部菜单
	 */
	public static void delMenu() {
		String url = StrUtil.format("cgi-bin/menu/delete?access_token={}", EhiWeixin.getAccessToken());
		get(url);
	}

	/**
	 * 返回当前公众号的菜单,暂时只能解析通过Api设置的菜单<br>
	 * 如果使用微信公众平台设置的菜单则返回空集合<br>
	 * 如果菜单没有打开,返回空集合
	 * 
	 * @return 菜单列表
	 */
	public static List<Menu> getMenu() {
		String url = StrUtil.format("cgi-bin/get_current_selfmenu_info?access_token={}", EhiWeixin.getAccessToken());
		Dict dict = get(url);
		JSONObject json = JSONUtil.parseObj(dict);
		List<Menu> menus = new ArrayList<Menu>();
		if (json.containsKey("selfmenu_info") && json.getInt("is_menu_open") == 1) {
			JSONObject selfmenuInfo = json.getJSONObject("selfmenu_info");
			JSONArray button = selfmenuInfo.getJSONArray("button");
			for (int i = 0; i < button.size(); i++) {
				JSONObject tmp = button.getJSONObject(i);
				Menu menu = tmp.toBean(Menu.class);
				List<Menu> subMenus = new ArrayList<Menu>();
				// 如果包含子集则说明不会有type类型
				if (tmp.containsKey("sub_button")) {
					JSONArray subArr = tmp.getJSONObject("sub_button").getJSONArray("list");
					for (int j = 0; j < subArr.size(); j++) {
						JSONObject tmpJ = subArr.getJSONObject(j);
						// 微信返回type为小写,无法关联枚举,手动处理
						tmpJ.put("type", tmpJ.getStr("type").toUpperCase());
						Menu subMenu = tmpJ.toBean(Menu.class);
						subMenus.add(subMenu);
					}
					menu.setSub_button(subMenus);
				} else {
					menu.setType(MenuType.valueOf(tmp.getStr("type").toUpperCase()));
				}
				menus.add(menu);
			}
		}
		return menus;
	}

	/**
	 * 添加微信菜单
	 * 
	 * @param menus 菜单列表
	 */
	public static void addMenu(List<Menu> menus) {
		JSONObject json = new JSONObject();
		json.put("button", menus);
		String url = StrUtil.format("cgi-bin/menu/create?access_token={}", EhiWeixin.getAccessToken());
		post(url, json.toString());
	}

	/**
	 * 微信服务接口校验
	 * 
	 * @param param
	 * @return
	 */
	public static String checkServerToken(Map<String, Object> param) {
		String signature = Convert.toStr(param.get("signature"));
		String timestamp = Convert.toStr(param.get("timestamp"));
		String nonce = Convert.toStr(param.get("nonce"));
		String echostr = Convert.toStr(param.get("echostr"));
		List<String> valList = new ArrayList<>();
		valList.add(Const.TOKEN);
		valList.add(timestamp);
		valList.add(nonce);
		Collections.sort(valList);
		Digester digester = new Digester(DigestAlgorithm.SHA1);
		String tmp = digester.digestHex(StrUtil.join("", valList));
		if (!tmp.equals(signature)) {
			echostr = "";
			throw new EhiWeixinException("sign check error.");
		}
		return echostr;
	}

	/**
	 * 刷新accessToken
	 * 
	 * @return accessToken
	 */
	public static String refreshAccessToken() {
		Dict param = Dict.create();
		param.put("appid", Const.WEIXIN_APPID);
		param.put("secret", Const.WEIXIN_APPSECRET);
		param.put("url", Const.DOMAIN);
		String url = StrUtil.format("cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}", param);
		String token = get(url).getStr("access_token");
		EhiWeixin.setAccessToken(token);
		if (log.isDebugEnabled()) {
			log.debug("get token success. new token=" + token);
		}
		return token;
	}

	/**
	 * 刷新jsTicket
	 * 
	 * @return jsTicket
	 */
	public static String refreshJsTicket() {
		String url = StrUtil.format("cgi-bin/ticket/getticket?access_token={}&type=jsapi", EhiWeixin.getAccessToken());
		String ticket = get(url).getStr("ticket");
		EhiWeixin.setJsTicket(ticket);
		if (log.isDebugEnabled()) {
			log.debug("get JSApiTicket success. new ticket=" + ticket);
		}
		return ticket;
	}

	/**
	 * 通过url获取js签名
	 * 
	 * @param url 被签名的url
	 * @return 签名
	 */
	public static JsSign getJsSign(String url) {
		JsSign sign = new JsSign();
		sign.setAppId(Const.WEIXIN_APPID);
		sign.setTimestamp(System.currentTimeMillis() / 1000);
		sign.setNoncestr(IdUtil.fastSimpleUUID());
		// 计算签名
		Map<String, Object> textMap = new HashMap<>();
		textMap.put("jsapi_ticket", EhiWeixin.getJsTicket());
		textMap.put("noncestr", sign.getNoncestr());
		textMap.put("timestamp", sign.getTimestamp());
		textMap.put("url", url);
		String param = StrUtil.format("jsapi_ticket={jsapi_ticket}&noncestr={noncestr}&timestamp={timestamp}&url={url}",
				textMap);
		Digester digester = new Digester(DigestAlgorithm.SHA1);
		sign.setSignature(digester.digestHex(param));
		return sign;

	}

	/**
	 * 发送post请求获取weixin api body
	 * 
	 * @param url  接口地址
	 * @param body 参数
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	protected static Dict post(String url, String body) {
		try {
			return curl(HttpUtil.post(Const.DOMAIN + url, new String(body.getBytes(), "utf-8")));
		} catch (UnsupportedEncodingException e) {
			throw new EhiWeixinException("UnsupportedEncodingException String Convert error.");
		}
	}

	/**
	 * 发送get请求获取weixin api body
	 * 
	 * @param url 接口地址
	 * @return
	 */
	protected static Dict get(String url) {
		return curl(HttpUtil.get(Const.DOMAIN + url));
	}

	private static Dict curl(String weixinBody) {
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
