package com.yuxuan66.test;

import java.util.ArrayList;
import java.util.List;

import com.yuxuan66.framework.ehi.weixin.support.EhiWeixin;
import com.yuxuan66.framework.ehi.weixin.support.model.Menu;
import com.yuxuan66.framework.ehi.weixin.support.model.enums.MenuType;

import cn.hutool.json.JSONUtil;

public class EhiWeixinTest {

	public static void main(String[] args) {
		EhiWeixin.builder();
		// 添加菜单测试
		List<Menu> menus = new ArrayList<Menu>();
		Menu menu = new Menu();
		menu.setName("测试1");
		menu.setType(MenuType.CLICK);
		menu.setKey("test1");
		Menu menu2 = new Menu();
		menu2.setName("子集");
		menu2.setType(MenuType.CLICK);
		menu2.setKey("ziji1");
		List<Menu> subN = new ArrayList<Menu>();
		subN.add(menu2);
		subN.add(menu2);
		menu.setSub_button(subN);
		menus.add(menu);
		menus.add(menu);
		menus.add(menu);
		EhiWeixin.addMenu(menus);

		// 添加菜单测试
		List<Menu> l = EhiWeixin.getMenu();
		System.out.println(JSONUtil.toJsonStr(l));
		//EhiWeixin.delMenu();
		List<Menu> l1 = EhiWeixin.getMenu();
		System.out.println(JSONUtil.toJsonStr(l1));

	}
}
