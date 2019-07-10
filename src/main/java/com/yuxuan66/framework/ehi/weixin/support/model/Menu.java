package com.yuxuan66.framework.ehi.weixin.support.model;

import java.util.List;

import com.yuxuan66.framework.ehi.weixin.support.model.enums.MenuType;

import lombok.Data;

/**
 * 菜单
 * 
 * @author yuxuan
 *
 */
@Data
public class Menu {

	/**
	 * 菜单的响应动作类型
	 */
	private MenuType type;
	/**
	 * 菜单标题，不超过16个字节，子菜单不超过60个字节
	 */
	private String name;
	/**
	 * 菜单KEY值，用于消息接口推送，不超过128字节 click等点击类型必须
	 */
	private String key;
	/**
	 * 网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url。
	 * media_id类型和view_limited类型必须
	 */
	private String url;
	/**
	 * 调用新增永久素材接口返回的合法media_id media_id类型和view_limited类型必须
	 */
	private String mediaId;
	/**
	 * 小程序的appid（仅认证公众号可配置） miniprogram类型必须
	 */
	private String appid;
	/**
	 * 小程序的页面路径 miniprogram类型必须
	 */
	private String pagepath;
	/**
	 * 子菜单
	 */
	private List<Menu> sub_button;

	public String getType() {
		if (type == null) {
			return null;
		}
		return type.value();
	}
}
