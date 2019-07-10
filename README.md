# ehi-weixin

#### 介绍
Ehi-Weixin 提供微信的各种封装方法
项目不依赖Spring，Spring MVC等容器或web框架。纯jar项目提供丰富的api供调用
项目内使用定时器维护token,ticket等。

#### 软件架构
软件架构说明
依赖:
- hutool-all
- lombok

## 安装

### Maven
在项目的pom.xml的dependencies中加入以下内容:

```xml
<dependency>
    <groupId>com.yuxuan66</groupId>
    <artifactId>ehi-weixin</artifactId>
    <version>0.0.2</version>
</dependency>
```

### Gradle
```
compile 'com.yuxuan66:ehi-weixin:0.0.2'
```

### 非Maven项目

点击以下任一链接，下载`ehi-weixin-X.X.X.jar`即可：

- [Maven中央库1](https://repo1.maven.org/maven2/com/yuxuan66/ehi-weixin/0.0.2/)
- [Maven中央库2](http://repo2.maven.org/maven2/com/yuxuan66/ehi-weixin/0.0.2/)

> 注意
> ehi-weixin支持JDK8+，对Android平台没有测试，不能保证所有工具类获工具方法可用。

#### 使用说明
```
1. 目前已实现 token,ticket的维护,js sign的计算，
2. EhiWeixin 为系统内重量级对象提供所有的微信工具方法
3. EhiWeixin ehiWeixin = EhiWeixin.getInstance();//可返回唯一的EhiWeixin实例
4. JSApiTicket jsApiTicket = ehiWeixin.getJSApiTicket();//返回Js Ticket
5. Token token = ehiWeixin.getToken();//返回access token
6. Sign sign = ehiWeixin.getJsSign(String url);//根据被授权的url返回签名
```

当前为开发测试版本,系统将不断迭代,提供完善的微信操作相关方法。


### 提供bug反馈或建议

- [码云Gitee](https://gitee.com/siryuxuan/ehi-weixin/issues)

技术交流群:875477818