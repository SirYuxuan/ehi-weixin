<p align="center">
	<a href="https://www.yuxuan66.com/"><img src="https://images.gitee.com/uploads/images/2019/0710/133735_aa0b0098_461804.png" width="400"></a>
</p>
<p align="center">
	<strong>Ehi-Weixin 提供微信的各种封装方法</strong>
</p>
<p align="center">
	<a target="_blank" href="https://search.maven.org/search?q=g%3a%22com.yuxuan66%22+AND+a%3a%22ehi-weixin%22">
		<img src="https://img.shields.io/maven-central/v/com.yuxuan66/ehi-weixin.svg?label=Maven%20Central" ></img>
	</a>
	<a target="_blank" href="https://www.apache.org/licenses/LICENSE-2.0.html">
		<img src="https://img.shields.io/:license-apache-blue.svg" ></img>
	</a>
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-1.8+-green.svg" ></img>
	</a>
	<a target="_blank" href='https://gitee.com/siryuxuan/ehi-weixin/stargazers'><img src='https://gitee.com/siryuxuan/ehi-weixin/badge/star.svg?theme=dark' alt='star'></img></a>

</p>
<p align="center">
	-- 雨轩博客：<a target="_blank" href="https://www.yuxuan66.com">https://www.yuxuan66.com</a>
</p>
<p align="center">
	-- QQ群①：<a target="_blank" href="https://jq.qq.com/?_wv=1027&k=5aDSNM1">875477818</a> --
</p>

-------------------------------------------------------------------------------

## 简介
Ehi-Weixin是一个基于微信SDK封装的工具包,不依赖任何容器,Web框架。它帮助我们来处理繁琐的微信操作，我们致力与让您只需要处理好业务实现而不需要关心微信API的变化。

所有接口同步微信SDK https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Access_Overview.html

### Simple

以获取AccessToken为例：

- 【以前】使用Http工具调用微信API,保存维护Token。然后使用
- 【现在】引入EhiWeixin,使用EhiWeixin的实例getToken();就可以随时获取Token。(系统自动维护并刷新Token,不会在请求时进行调用,当然您也可以手动调用方法进行刷新Token,详情参考下面的文档)

## 项目依赖

- [hutool-all](https://gitee.com/loolly/hutool) 使用定时任务,Http等工具
- [lombok](https://projectlombok.org/) 使用实体注解等



-------------------------------------------------------------------------------

## 文档 

[中文文档](http://doc.ehi.weixin.yuxuan66.com)

[参考API](https://docs.yuxuan66.com/ehi/weixin/)

-------------------------------------------------------------------------------

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
> EhiWeixin支持JDK8+，对Android平台没有测试，不能保证所有工具类获工具方法可用。

### 编译安装

访问Hutool的码云主页：[https://gitee.com/siryuxuan/ehi-weixin](https://gitee.com/siryuxuan/ehi-weixin) 下载整个项目源码 然后进入EhiWeixin项目目录执行：

```sh
mvn clean install
```

然后就可以使用Maven引入了。

-------------------------------------------------------------------------------

## 添砖加瓦

### 提供bug反馈或建议

- [码云Gitee](https://gitee.com/siryuxuan/ehi-weixin/issues)


### 贡献代码的步骤

1. 在Gitee上fork项目到自己的repo
2. 把fork过去的项目也就是你的项目clone到你的本地
3. 修改代码
4. commit后push到自己的库
5. 登录Gitee在你首页可以看到一个 pull request 按钮，点击它，填写一些说明信息，然后提交即可。
6. 等待作者合并

-------------------------------------------------------------------------------

## 捐赠

如果你觉得EhiWeixin不错，可以捐赠请作者吃包辣条~，在此表示感谢^_^。

点击以下链接，将页面拉到最下方点击“捐赠”即可。

[前往捐赠](https://gitee.com/siryuxuan/ehi-weixin)