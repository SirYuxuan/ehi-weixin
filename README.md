# ehi-weixin

#### 介绍
Ehi-Weixin 提供微信的各种封装方法
项目不依赖Spring，Spring MVC等容器或web框架。纯jar项目提供丰富的api供调用
项目内使用定时器维护token,ticket等。

#### 软件架构
软件架构说明
依赖:
hutool-all
lombok

#### 安装教程

1. 
`<dependency>
			<groupId>com.yuxuan66</groupId>
			<artifactId>ehi-weixin</artifactId>
			<version>0.0.2</version>
		</dependency>`


#### 使用说明
1. 目前已实现 token,ticket的维护,js sign的计算，
2. EhiWeixin 为系统内重量级对象提供所有的微信工具方法
3. EhiWeixin ehiWeixin = EhiWeixin.getInstance();//可返回唯一的EhiWeixin实例
4. JSApiTicket jsApiTicket = ehiWeixin.getJSApiTicket();//返回Js Ticket
5. Token token = ehiWeixin.getToken();//返回access token
6. Sign sign = ehiWeixin.getJsSign(String url);//根据被授权的url返回签名

当前为开发测试版本,系统将不断迭代,提供完善的微信操作相关方法。


#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

技术交流群:875477818