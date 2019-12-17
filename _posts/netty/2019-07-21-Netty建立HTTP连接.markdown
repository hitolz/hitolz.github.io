---
layout: post
title: Netty建立HTTP连接
tags: [netty, blog]
image: '/images/posts/8.jpg'
---


## Http请求的组成：

![-w620](/images/posts/media/15637184845951/15637188509306.jpg)
>1. First part of the HTTP-Request that contains headers and so on
2. One more HttpContent part that contains chunks of data
3. Special HttpContent subtype that marks the end of the HTTP request and may also contain trailing headers
4. A full HTTP request with everything included

- 1、HttpRequest中包含了头部等信息
- 2、HttpContent中是请求数据，有多个
- 3、LastHttpContent，最后一个请求数据，http请求结束的标记
- 4、完整的Http请求包含上述所有

Http响应的组成:

![-w637](/images/posts/media/15637184845951/15637188325078.jpg)
>1. First part of the HTTP response that contains headers and so on
2. One more HttpContent part that contains chunks of data
3. Special HttpContent subtype that marks the end of the HTTP response and may also contain trailing headers
4. A full HTTP response with everything included

- 1、HttpResponse中包含头部等信息
- 2、HttpContent中包含响应数据，有多个
- 3、LastHttpContent是HttpResponse的结束标志
- 4、一个完整的HttpResponse包含上述所有部分

![-w748](/images/posts/media/15637184845951/15637194968474.jpg)

## 使用Netty服务端接收Http请求，并返回响应信息
channel是用来接收请求的，想要接收HTTP请求，只需要在channelPipeline中增加一个handler专门用来接收HTTP请求即可。

[code](https://github.com/hitolz/hitolz.github.io/tree/master/images/posts/code/netty)

### FullHttpRequest类的继承关系
![](/images/posts/media/15637184845951/15638509766698.jpg)