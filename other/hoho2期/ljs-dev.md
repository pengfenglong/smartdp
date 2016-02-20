# 基本信息:

## 服务器基本信息
ljs目前使用easemob的服务器。 本文档中的所有url都需要换成

	http://api.easemob.com
	
已创建了一个org和app, 具体信息是

	curl -X POST -i  "http://api.easemob.com/management/organizations" -d '{"organization":"ljs","username":"ljs_admin","name":"LJS Admin","email":"admin@ljs.com","password":"1234567890"}'
        curl -X POST -i -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" "http://api.easemob.com/management/organizations/ljs/applications" -d '{"name":"ljs"}'
        
Usergrid管理后台的登录地址是：

        http://api.easemob.com/management/organizations	

## Usergrid返回的response的数据结构。请注意，在这个reponse的json数据中，只有"entities"才是真正的数据体，并且总是一个数组类型。

	{
	  "action" : "post",                                     //请求类型，可以为get|post|put|delete
	  "application" : "3bec9de0-6abe-11e3-8606-41e5b3c17b39",//请求的app的UUID.在usergrid中每个app即可通过他的appkey唯一标识，也可以通过他的uuid唯一标识
	  "params" : { },                                        //请求的参数。
	  "path" : "/users",                                     //请求的请求路径。
	  "uri" : "http://210.76.97.29:8080/hoho/hoho/users",    //请求URI。
	  "entities" : [ {                                       //response的数据体。总是一个数组。
	    "uuid" : "98b746e0-6ac0-11e3-914b-0b6cc7bc5592",     //返回的数据entity的uuid
	    "type" : "user",                                     //返回的数据entity的类型
	    "created" : 1387686117965,                           //返回的数据entity的创建时间
	    "modified" : 1387686117965,                          //返回的数据entity的最后更新时间
	    "username" : "admin",                                //返回的数据entity的是user类型。username是user类型entity的主键。
	    "activated" : true                                   //返回的数据entity的是否被激活
	  } ],
	  "timestamp" : 1387686117951,                           //请求的时间戳。
	  "duration" : 207,                                      //请求所用时常
	  "organization" : "hoho",                               //请求的app所属的org
	  "applicationName" : "hoho"                             //请求的app的名字
        }

## 邻居说帖子的数据结构

	{	
	"actor" : "刘少壮--发布人, 实际执行post的人, 这个同样server会自动添加 (来自于 /users/{usrename}/activities 中的username), 再post的时候不能够添加这个属性",
	"author_username" : "段子中要显示的发布人的username(是用户的primarykey)。用来在界面上显示段子的发送人, 虽然实际发送人都是admin, 但是界面上可以显示不同的名字",
	"author_nick" : "段子中要显示的发布人的昵称",
	"author_avator" : "段子中要显示的发布人的头像",
	"content" : "段子内容",
	"published" : "发布时间--UTC, 这个server会自动创建, 在post的时候不需要添加",
	"verb" : "post 表示段子, comment表示评论",
	"image" : ["http://xxx.img", "http://yyy.jpg"],    //表示一个能够下载到段子中图片的地址的数组。照片可以为多个
	"audio" : "如果 isaudio属性是1的话, 那么程序应该从 audio属性的值来下载语音, 所以这里的值应该是个指向语音文件的url。",
	"audio_length" : "语音段子的长度。这样客户端可在不下载语音段子的情况就能显示语音段子的长度。长度值为int类型，单位为秒。如果因为某种原因，语音段子的长度未知，该值可以不出现，也可以为0",
	"topic_tag" ： "话题分类标签",
	"to_community" ： "发到community的name。这个属性是审核需要的",
	"like_count" ： "该属性必须有。赞的数量",
	"dislike_count": "该属性必须有。踩的数量",
	"comment_count": "该属性必须有。评论的数量",
	"like_byme" ： "1 表示我（当前发出请求的用户）顶过, 0 我没有顶过",
	"dislike_byme": "1 表示我（当前发出请求的用户）踩过, 0 我没有踩过",
	"collect_byme": "1 表示我（当前发出请求的用户）收藏过, 0 我没有收藏过"
	}

## 邻居说中用户(User)的数据结构

	{	
	"username" : "这个是用户的primarykey. 必须是英文字母数字下划线横线的组合，规则如下："^[a-z0-9_-]{3,15}$",
	"nick" : "用户昵称，可以为中文。",
	"avator" : "头像在图片服务器上的路径",
	"signature" : "个性签名",
	"sex" : "性别. F|M|U.不区分大小写",
	"communityName" ： "["863520001"]", //用户所属的社区。为一个数组。一个用户可以属于多个社区
	"building" : "["2"]", //用户所属的社区的楼号。为一个数组。一个用户可以属于多个社区。这里的楼号是和社区一一对应的。
	"mobile" : "绑定的手机号"
	}

## 邻居说中评论的数据结构

	{	
	"actor" : "刘少壮--发布人, 同样,这个属性由server维护",
	"author_nick" : "显示的名称或者nickname",
	"content" : "评论内容",
	"published" : "发布时间--UTC",
	"verb" : "post 表示段子, comment表示评论",
	"to" : "被评论的段子的UUID"	
	}
	

## 邻居说中社区的数据结构

	{	
	"name" : "这个是社区的primarykey. 比如865320001，其中86532为国家区号加地区区号，0001为小区在当地的编号",
	"nick" : "社区显示用的名称，可以为中文",
	"province" : "小区所属省份",
	"city" : "小区所属城市",
	"district" : "小区所属区",
	"address" : "小区所属具体地址"
	}
	
## 关于发帖及activity	
一个用户发微博也好, 发段子也好, 或者朋友圈, 在系统看来, 都是一个activity。一个activity的含义包含了三个基本内容 -- 主谓宾, 也就是: 谁做了什么事情
	
比如以下的这个例子：

	curl -X POST -i -H "Authorization: Bearer YWMtIzRPemrBEeO2Wa2zqdPoYAAAAUM8mdHnjdihm9yKXDd254Pk9XTVF0jAZcI" "http://210.76.97.29:8080/hoho/hoho/users/admin/activities" -d '{"author_username" : "jliu","author_nick" : "jervis liu", "author_avator" : "","content" : "good morning","verb" : "post","isaudio" : "1","audio" : "http://easemob.com/downloads/aiyadongwuyuan.mp3","like_count":0,"dislike_count":0,"comment_count":0}'

上面的request的含义实际上是: 管理员(actor)发布了(verb:post)一个段子(content)


## 如何做分页

系统默认返回最多100个结果。参见 dev-docs/usergrid/使用limit和cursor  


# 社区相关API

## 添加一个小区

POST /${orgName}/${appName}/communties
描述:创建一个新的社区
权限: app用户登录
Url参数: 无
Request body: 
        {
	"name" : "1",
	"nick" : "水清木华",
	"province" : "山东",
	"city" : "青岛",
	"district" : "崂山",
	"address" : "崂山路5号"
         }
错误代码：
        401 Unauthorized： 认证失败。返回的response body为json数据：
        {"error":"expired_token",
         "timestamp":1389492673539,
         "duration":0,
         "exception":"org.usergrid.rest.exceptions.SecurityException",
         "error_description":"Unable to authenticate due to expired access token"
        }

        400 Bad Request: 如果要创建的小区已经存在。返回的response body为json数据：       
        Entity community requires that property named name be unique, value of 1 exists
        
curl示例：

	curl -X POST -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" -i "http://api.easemob.com/ljs/ljs/communities" -d '{ "name" : "2", "nick" : "shuiqingmuhua", "province" : "shandong", "city" : "qingdao", "district" : "laoshan", "address" : "No 5, laoshan road" }'
	
返回的json数据的entities包含了新创建的社区.


	{
          "action" : "post",
          "application" : "339d73d0-7a86-11e3-8907-b7afdbf7c043",
          "params" : { },
          "path" : "/communities",
          "uri" : "http://api.easemob.com/ljs/ljs/communities",
          "entities" : [ {
            "uuid" : "73645a1a-7b30-11e3-b708-f7da4a11895a",
            "type" : "community",
            "name" : "2",
            "created" : 1389493377585,
            "modified" : 1389493377585,
            "address" : "No 5, laoshan road",
            "city" : "qingdao",
            "district" : "laoshan",
            "nick" : "shuiqingmuhua",
            "province" : "shandong"
          } ],
          "timestamp" : 1389493377581,
          "duration" : 21,
          "organization" : "ljs",
          "applicationName" : "ljs"
         }
        
## 更新小区信息

PUT /${orgName}/${appName}/communties/${communityName}
描述:创建一个新的社区
权限: app用户登录
Url参数: 无
Request body: 
        {
	"address" : "崂山路6号" //这里把小区的地址改为崂山路6号。注意，不需要修改的属性可以不出现在request中。
         }
错误代码：
        401 Unauthorized： 认证失败。返回的response body为json数据：
        {"error":"expired_token",
         "timestamp":1389492673539,
         "duration":0,
         "exception":"org.usergrid.rest.exceptions.SecurityException",
         "error_description":"Unable to authenticate due to expired access token"
        }
curl示例：

	curl -X PUT -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" -i "http://api.easemob.com/ljs/ljs/communities/4" -d '{ "address" : "No 6, laoshan road" }'
返回的json数据的entities包含了更新后的社区信息.


	{
	  "action" : "put",
	  "application" : "339d73d0-7a86-11e3-8907-b7afdbf7c043",
	  "params" : { },
	  "path" : "/communities",
	  "uri" : "http://api.easemob.com/ljs/ljs/communities",
	  "entities" : [ {
	    "uuid" : "73645a1a-7b30-11e3-b708-f7da4a11895a",
	    "type" : "community",
	    "name" : "2",
	    "created" : 1389493377585,
	    "modified" : 1389493948631,
	    "address" : "No 6, laoshan road",
	    "city" : "qingdao",
	    "district" : "laoshan",
	    "nick" : "shuiqingmuhua",
	    "province" : "shandong"
	  } ],
	  "timestamp" : 1389493948624,
	  "duration" : 16,
	  "organization" : "ljs",
	  "applicationName" : "ljs"
        }

          
注：如果要修改的小区不存在，则会创建一个新小区


## 删除小区

DELETE /${orgName}/${appName}/communties/${communityName}
描述:创建一个新的社区
权限: app用户登录
Url参数: 无
Request body: 无
错误代码：
        401 Unauthorized： 认证失败。返回的response body为json数据：
        {"error":"expired_token",
         "timestamp":1389492673539,
         "duration":0,
         "exception":"org.usergrid.rest.exceptions.SecurityException",
         "error_description":"Unable to authenticate due to expired access token"
        }
        404 Not Found: 要删除的小区不存在。返回的response body为json数据：       
        {"error":"service_resource_not_found",
        "timestamp":1389494177532,
        "duration":1,
        "exception":"org.usergrid.services.exceptions.ServiceResourceNotFoundException",
        "error_description":"Service resource not found"
        }        
curl示例：

	curl -X DELETE -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" -i "http://api.easemob.com/ljs/ljs/communities/3" 
	
返回的json数据的entities包含了被删除的社区基本信息.


	{
	  "action" : "delete",
	  "application" : "339d73d0-7a86-11e3-8907-b7afdbf7c043",
	  "params" : { },
	  "path" : "/communities",
	  "uri" : "http://api.easemob.com/ljs/ljs/communities",
	  "entities" : [ {
	    "uuid" : "94c80fd4-7b31-11e3-928c-4552c8e41bcf",
	    "type" : "community",
	    "name" : "3",
	    "created" : 1389493863100,
	    "modified" : 1389493863100,
	    "address" : "No 6, laoshan road"
	  } ],
	  "timestamp" : 1389494163387,
	  "duration" : 59,
	  "organization" : "ljs",
	  "applicationName" : "ljs"
        }
        
        
## 获得小区列表

GET /${orgName}/${appName}/communties
描述:获得小区列表
权限: app用户登录
Url参数: 无
Request body: 无
错误代码：
        401 Unauthorized： 认证失败。返回的response body为json数据：
        {"error":"expired_token",
         "timestamp":1389492673539,
         "duration":0,
         "exception":"org.usergrid.rest.exceptions.SecurityException",
         "error_description":"Unable to authenticate due to expired access token"
        }
curl示例：

	curl -X GET -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" -i "http://api.easemob.com/ljs/ljs/communities"
返回的json数据的entities包含了社区信息.


	{
	  "action" : "get",
	  "application" : "339d73d0-7a86-11e3-8907-b7afdbf7c043",
	  "params" : { },
	  "path" : "/communities",
	  "uri" : "http://api.easemob.com/ljs/ljs/communities",
	  "entities" : [ {
	    "uuid" : "1b6140ba-7b2d-11e3-b312-07fe2d3e042b",
	    "type" : "community",
	    "name" : "1",
	    "created" : 1389491941435,
	    "modified" : 1389493838001,
	    "address" : "No 6, laoshan road",
	    "city" : "闈掑矝",
	    "district" : "宕傚北",
	    "nick" : "姘存竻鏈ㄥ崕",
	    "province" : "灞变笢"
	  }, {
	    "uuid" : "73645a1a-7b30-11e3-b708-f7da4a11895a",
	    "type" : "community",
	    "name" : "2",
	    "created" : 1389493377585,
	    "modified" : 1389493948631,
	    "address" : "No 6, laoshan road",
	    "city" : "qingdao",
	    "district" : "laoshan",
	    "nick" : "shuiqingmuhua",
	    "province" : "shandong"
	  } ],
	  "timestamp" : 1389494635714,
	  "duration" : 13,
	  "organization" : "ljs",
	  "applicationName" : "ljs",
	  "count" : 2
        }
        


## 获得指定小区详情

GET /${orgName}/${appName}/communties/${communityName}
描述:获得指定小区
权限: app用户登录
Url参数: 无
Request body: 无
错误代码：
        401 Unauthorized： 认证失败。返回的response body为json数据：
        {"error":"expired_token",
         "timestamp":1389492673539,
         "duration":0,
         "exception":"org.usergrid.rest.exceptions.SecurityException",
         "error_description":"Unable to authenticate due to expired access token"
        }
        
        404 Not Found: 指定的小区不存在。返回的response body为json数据：       
        {"error":"service_resource_not_found",
        "timestamp":1389494177532,
        "duration":1,
        "exception":"org.usergrid.services.exceptions.ServiceResourceNotFoundException",
        "error_description":"Service resource not found"
        }         
curl示例：

	curl -X GET -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" -i "http://api.easemob.com/ljs/ljs/communities/2"

返回的json数据的entities包含了社区信息.


	{
	  "action" : "get",
	  "application" : "339d73d0-7a86-11e3-8907-b7afdbf7c043",
	  "params" : { },
	  "path" : "/communities",
	  "uri" : "http://api.easemob.com/ljs/ljs/communities",
	  "entities" : [ {
	    "uuid" : "73645a1a-7b30-11e3-b708-f7da4a11895a",
	    "type" : "community",
	    "name" : "2",
	    "created" : 1389493377585,
	    "modified" : 1389493948631,
	    "address" : "No 6, laoshan road",
	    "city" : "qingdao",
	    "district" : "laoshan",
	    "nick" : "shuiqingmuhua",
	    "province" : "shandong"
	  } ],
	  "timestamp" : 1389494871246,
	  "duration" : 7,
	  "organization" : "ljs",
	  "applicationName" : "ljs"
       }


# ljs用户体系相关API

## 用户注册

POST /${orgName}/${appName}/users
描述:创建一个新的app user
权限: 无需授权
Url参数: 无
Request body: 
        {
          "username":"admin",   //用户名
          "password":"123456"  //密码
         }
错误代码：        
	400 Bad Request: 如果要创建的用户已经存在。返回的response body为json数据：       
        Entity community requires that property named username be unique, value of admin exists
curl示例：

	curl -X POST -i "http://api.easemob.com/ljs/ljs/users" -d '{"username":"admin","password":"123456"}'
	
返回的json数据的entities包含了新创建的user.


	{
	  "action" : "post",
	  "application" : "339d73d0-7a86-11e3-8907-b7afdbf7c043",
	  "params" : { },
	  "path" : "/users",
	  "uri" : "http://api.easemob.com/ljs/ljs/users",
	  "entities" : [ {
	    "uuid" : "d023ed4a-7b37-11e3-94a0-2769650b88ed",
	    "type" : "user",
	    "created" : 1389496539668,
	    "modified" : 1389496539668,
	    "username" : "admin",
	    "activated" : true
	  } ],
	  "timestamp" : 1389496539665,
	  "duration" : 109,
	  "organization" : "ljs",
	  "applicationName" : "ljs"
        }
	
这里, _username_ 和 _password_ 是必须提供的, 除此之外, 还可以增加任意别的属性, 例如, 在用户注册页面让用户填写性别和email地址的话, 那么post的数据就会是


	curl -X POST -i "http://api.easemob.com/ljs/ljs/users" -d '{"username":"test1","password":"test1", "sex":"M", "email":"stliu@apache.org"}'
	


## 用户登陆
POST /${orgName}/${appName}/token
描述: 登录并授权，获得一个token。
权限: 无需授权
Url参数: 无
Request body: 
       {
         "grant_type":"password", //认证类型。目前支持password类型，即用户名密码验证
         "username":"guest",      //登录用户名
         "password":"123456"      //密码
        }
错误代码：
        400 Bad Request: 用户名或密码错误。返回的response body为json数据：
        {"error":"invalid_grant",
        "error_description":"invalid username or password"
        }
        
curl示例：

	curl -X POST -i  "http://api.easemob.com/ljs/ljs/token" -d '{"grant_type":"password","username":"admin","password":"123456"}'

如果这个用户之前已经注册了, 并且这里提供的密码正确的话, 会返回

	{
	"access_token":"YWMtIzRPemrBEeO2Wa2zqdPoYAAAAUM8mdHnjdihm9yKXDd254Pk9XTVF0jAZcI",   //登录成功后获取的token
	"expires_in":604800,   //token的有效期。目前服务器设为3天有效
	"user":
	   {
	    "uuid":"98b746e0-6ac0-11e3-914b-0b6cc7bc5592", //登录用户的uuid
	    "type":"user",                                 //entity类型。这里因为是用户类型，所以值为user
	    "created":1387686117965,                       //该对象的创建时间
	    "modified":1387686117965,                      //该对象的最后更新时间
	    "username":"admin",                            //用户名。是用户对象的主键
	    "activated":true                               //该对象是否激活
	    }
	}


从这个返回值中, 可以得到两部分信息:

1. token

	这个是服务器用来标识这个用户已经登陆的, 用户登陆后的所有操作(这里的操作指的是app访问服务器的request), 都需要把这个token加到header当中, 在本文档后面所有描述到得request都会有这个header
	
	-H "Authorization: Bearer YWMtNda4DFzyEeOrOy_LuVzHjAAAAULiG1IrN8opggpytUDFmJkiocawbINICYk"

2. 用户的具体信息, app可以根据这个信息来构造一个user对象, 真实的环境中, 这个还会包含用户的头像/性别/email等等, 取决于注册的时候提供了哪些信息	
	


## 修改用户信息

PUT /${orgName}/${appName}/users/${userName}
描述:修改用户信息
权限: app用户登录
Url参数: 无
Request body: 
        {
          "usertype":"customer" //这里可以有一个或多个要修改或者要添加的用户属性。不需要修改的属性可以不出现
         }
错误代码：
        401 Unauthorized： 认证失败。返回的response body为json数据：
        {"error":"expired_token",
         "timestamp":1389492673539,
         "duration":0,
         "exception":"org.usergrid.rest.exceptions.SecurityException",
         "error_description":"Unable to authenticate due to expired access token"
         }
curl示例：

	curl -X PUT -i -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" "http://api.easemob.com/ljs/ljs/users/admin" -d '{"usertype":"customer"}'
	
返回的json数据的entities包含了更新后的user.


	{
	  "action" : "put",
	  "application" : "339d73d0-7a86-11e3-8907-b7afdbf7c043",
	  "params" : { },
	  "path" : "/users",
	  "uri" : "http://api.easemob.com/ljs/ljs/users",
	  "entities" : [ {
	    "uuid" : "d023ed4a-7b37-11e3-94a0-2769650b88ed",
	    "type" : "user",
	    "created" : 1389496539668,
	    "modified" : 1389497741139,
	    "username" : "admin",
	    "activated" : true,
	    "usertype" : "customer"
	  } ],
	  "timestamp" : 1389497741130,
	  "duration" : 18,
	  "organization" : "ljs",
	  "applicationName" : "ljs"
        }
	
上面的这个例子是为叫"jliu"的用户增加一个叫usertype的属性，其值为customer。比如以前的user属性设计里并没有用户类型这个属性，现在想区分每个用户的类型，比如这个用户可以是”住户”或“物业“或”商家“。那么只需要调用以上接口即可为jliu用户增加这个属性。如果用户已经有了usertype这个属性，那么以上接口调用会更新usertype的属性值。


## 获得用户列表

GET /${orgName}/${appName}/users
描述:获得用户列表
权限: app用户登录
Url参数: 无
Request body: 无
错误代码：
        401 Unauthorized： 认证失败。返回的response body为json数据：
        {"error":"expired_token",
         "timestamp":1389492673539,
         "duration":0,
         "exception":"org.usergrid.rest.exceptions.SecurityException",
         "error_description":"Unable to authenticate due to expired access token"
        }
curl示例：

	curl -X GET -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" -i "http://api.easemob.com/ljs/ljs/users"
返回的json数据的entities包含了用户信息.


	{
	  "action" : "get",
	  "application" : "339d73d0-7a86-11e3-8907-b7afdbf7c043",
	  "params" : { },
	  "path" : "/users",
	  "uri" : "http://api.easemob.com/ljs/ljs/users",
	  "entities" : [ {
	    "uuid" : "d023ed4a-7b37-11e3-94a0-2769650b88ed",
	    "type" : "user",
	    "created" : 1389496539668,
	    "modified" : 1389497741139,
	    "username" : "admin",
	    "activated" : true,
	    "usertype" : "customer"
	  } ],
	  "timestamp" : 1389497976457,
	  "duration" : 7,
	  "organization" : "ljs",
	  "applicationName" : "ljs",
	  "count" : 1
       }
                


## 获取指定用户详情

GET /${orgName}/${appName}/users/${userName}
描述:获取用户信息
权限: app用户登录
Url参数: 无
Request body: 无
错误代码：
        401 Unauthorized： 认证失败。返回的response body为json数据：
        {"error":"expired_token",
         "timestamp":1389492673539,
         "duration":0,
         "exception":"org.usergrid.rest.exceptions.SecurityException",
         "error_description":"Unable to authenticate due to expired access token"
        }
        
        404 Not Found: 指定的用户不存在。返回的response body为json数据：       
        {"error":"service_resource_not_found",
        "timestamp":1389494177532,
        "duration":1,
        "exception":"org.usergrid.services.exceptions.ServiceResourceNotFoundException",
        "error_description":"Service resource not found"
        } 
curl示例：
        curl -X GET -i -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" "http://api.easemob.com/ljs/ljs/users/admin"

返回的json数据的entities包含了user.


	{
	  "action" : "get",
	  "application" : "339d73d0-7a86-11e3-8907-b7afdbf7c043",
	  "params" : { },
	  "path" : "/users",
	  "uri" : "http://api.easemob.com/ljs/ljs/users",
	  "entities" : [ {
	    "uuid" : "d023ed4a-7b37-11e3-94a0-2769650b88ed",
	    "type" : "user",
	    "created" : 1389496539668,
	    "modified" : 1389497741139,
	    "username" : "admin",
	    "activated" : true,
	    "usertype" : "customer"
	  } ],
	  "timestamp" : 1389497868539,
	  "duration" : 6,
	  "organization" : "ljs",
	  "applicationName" : "ljs"
        }
        
        
# 邻居说(papa)API

## 关注一个特定用户
一个用户登陆之后, 这个用户需要注册为某个人的粉丝。然后这个用户查看自己的feed entity，就能看到所有关注了的人的帖子了。

POST /${orgName}/${appName}/users/${userNameA}/following/users/${userNameB}
描述:userNameA关注userNameB。关注成功后userNameA在自己的feed里就能看到所有userNameB发的帖子了（帖子即activity)
权限: app用户登录
Url参数: 无
Request body: 无
错误代码：
        401 Unauthorized： 认证失败。返回的response body为json数据：
        {"error":"expired_token",
         "timestamp":1389492673539,
         "duration":0,
         "exception":"org.usergrid.rest.exceptions.SecurityException",
         "error_description":"Unable to authenticate due to expired access token"
        }
        
curl示例：

        curl -X POST -i -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" "http://api.easemob.com/ljs/ljs/users/jliu/following/users/admin"
	
返回的json数据的entities包含了该用户关注（following)的用户列表.
	
	{
	  "action" : "post",
	  "application" : "339d73d0-7a86-11e3-8907-b7afdbf7c043",
	  "params" : { },
	  "path" : "/users/b25e64ca-7b69-11e3-90c0-3b4545fc26b6/following",
	  "uri" : "http://api.easemob.com/ljs/ljs/users/b25e64ca-7b69-11e3-90c0-3b4545fc
	26b6/following",
	  "entities" : [ {
	    "uuid" : "d023ed4a-7b37-11e3-94a0-2769650b88ed",
	    "type" : "user",
	    "created" : 1389496539668,
	    "modified" : 1389497741139,
	    "username" : "admin",
	    "activated" : true,
	    "usertype" : "customer"
	  } ],
	  "timestamp" : 1389518003628,
	  "duration" : 33,
	  "organization" : "ljs",
	  "applicationName" : "ljs"
        }
        

## 关注整个小区这个群体所发的帖子

TODO:

## 发帖到审核箱子

发帖后需要通过审核，其他人才能看到。发帖到审核箱的工作原理是把帖子发到app下的一个名字为“drafts”的entity里。

POST /${orgName}/${appName}/drafts
描述:发帖到审核箱
权限: app用户登录
Url参数: 无
Request body: 见帖子的数据结构
错误代码：
        401 Unauthorized： 认证失败。返回的response body为json数据：
        {"error":"expired_token",
         "timestamp":1389492673539,
         "duration":0,
         "exception":"org.usergrid.rest.exceptions.SecurityException",
         "error_description":"Unable to authenticate due to expired access token"
        }
        
curl示例：
	curl -X POST -i -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" "http://api.easemob.com/ljs/ljs/drafts" -d '{"author_username":"jliu","author_nick":"jervis liu", "author_avator":"","content" : "aiyaaya","verb" : "post","audio":"http://easemob.com/downloads/aiyadongwuyuan.mp3","audio_length":"35", "like_count":0,"dislike_count":0,"comment_count":0}'	

返回的json数据的entities包含了发到draft下的这个新帖子的详情.
	
	{
	  "action" : "post",
	  "application" : "339d73d0-7a86-11e3-8907-b7afdbf7c043",
	  "params" : { },
	  "path" : "/drafts",
	  "uri" : "http://api.easemob.com/ljs/ljs/drafts",
	  "entities" : [ {
	    "uuid" : "5c7afcaa-7b9e-11e3-a633-d9709262704d",
	    "type" : "draft",
	    "created" : 1389540583786,
	    "modified" : 1389540583786,
	    "audio" : "http://easemob.com/downloads/aiyadongwuyuan.mp3",
	    "audio_length" : "35",
	    "author_avator" : "",
	    "author_nick" : "jervis liu",
	    "author_username" : "jliu",
	    "comment_count" : 0,
	    "content" : "aiyaaya",
	    "dislike_count" : 0,
	    "like_count" : 0,
	    "verb" : "post"
	  } ],
	  "timestamp" : 1389540583767,
	  "duration" : 47,
	  "organization" : "ljs",
	  "applicationName" : "ljs"
       }

TODO:发到一个特定的小区。这个怎么实现？发帖时让用户选择要发到哪个社区group? 当前我们只支持用户只能属于一个小区，所以我们在本地记住用户所属于的小区group的id?
json数据里怎么表示发到一个特定的小区？

## 获取新帖子

这里介绍的是在app端, 一个用户如何看到其他用户发布的段子

GET /${orgName}/${appName}/users/feed
描述:看自己的feed里的帖子
权限: app用户登录
Url参数: ql=select+*+where+verb+%3D+'post' (转义前为ql=select * where verb = 'post')
Request body: 无
错误代码：
        401 Unauthorized： 认证失败。返回的response body为json数据：
        {"error":"expired_token",
         "timestamp":1389492673539,
         "duration":0,
         "exception":"org.usergrid.rest.exceptions.SecurityException",
         "error_description":"Unable to authenticate due to expired access token"
        }
        
curl示例：
	curl -X GET -i -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" "http://api.easemob.com/ljs/ljs/users/jliu/feed?ql=select+*+where+verb+%3D+'post'"

注意, 上面的ql后面的内容是http转义过的,实际上就是应用了个过滤器 where verb = 'post', 也就是只获取"帖子", 下面我们会看到"评论"使用的是不同的动词类型
返回的json数据的entities包含了该用户能看到的帖子.

	
	{
	  "action" : "get",
	  "application" : "3bec9de0-6abe-11e3-8606-41e5b3c17b39",
	  "params" : {
	    "ql" : [ "select * where verb = 'post'" ]
	  },
	  "path" : "/users/0b5b285a-6ac2-11e3-a7a2-ed17ffbef7ec/feed",
	  "uri" : "http://api.easemob.com/ljs/ljs/users/0b5b285a-6ac2-11e3-affbef7ec/feed",
	  "entities" : [ {
	    "uuid" : "dbc83d6a-6acd-11e3-8ed0-adf38253f207",
	    "type" : "activity",
	    "created" : 1387691813942,
	    "modified" : 1387691813942,
	    "actor" : {
	      "uuid" : "98b746e0-6ac0-11e3-914b-0b6cc7bc5592"
	    },
	    "content" : "good morning",
	    "verb" : "post",
	    "published" : 1387691813942,
	    "audio" : "http://easemob.com/downloads/aiyadongwuyuan.mp3",
	    "audio_length" : "35",
	    "author_nick" : "jervis liu",
	    "author_username" : "jliu",
	    "isaudio" : "1"
	  } ],
	  "timestamp" : 1387697099857,
	  "duration" : 27,
	  "organization" : "hoho",
	  "applicationName" : "hoho"
       }   


## 顶/踩一个段子
	curl -X POST -i -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" "http://api.easemob.com/ljs/ljs/activities/dbc83d6a-6acd-11e3-8ed0-adf38253f207/like" 
	curl -X POST -i -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" "http://api.easemob.com/ljs/ljs/activities/dbc83d6a-6acd-11e3-8ed0-adf38253f207/dislike" 

	
通过这个api, 会在段子的json数据中增加一个属性 like_count / dislike_count , 并且每次调用都会给这个值 + 1  

最开始的段子, 还没有人顶或者踩的时候, 是没有这两个属性的, 如果有人顶或者踩了, server会自动创建

并且, 这个api没有需要提交的数据, 只需要最这个url执行post操作即可


## 对段子加评论

使用场景

假设用户stliu在手机app中看到了一个admin用户发布的段子, 这时候, app内部, 获取到的段子内容应该是

	{
      "uuid": "5ecf100a-5cf8-11e3-95de-ed03f77c8cb1",
      "type": "activity",
      "created": 1386170756352,
      "modified": 1386170756352,
      "author_nick" :"管理员"
      "actor": {
        "uuid": "67ed2fea-5cf4-11e3-9dee-9f8110ecbed4"
      },
      "content": "小熊打豆豆",
      "verb": "post",
      "published": 1386170756352
    }
	
然后stliu想给这个段子加一条评论, 那么在app内部, 应该发送下面的post请求

	curl -X POST -i -H "Authorization: Bearer YWMtZt7t9GrOEeOIFyNPCrbWCwAAAUM88L--vR5w6w5T-ZZ3JBRwD_HAeeg-8Rk" "http://api.easemob.com/ljs/ljs/users/guest/activities" -d '{"author_nick":"strong liu","content":"really funny","verb":"comment", "to" :"dbc83d6a-6acd-11e3-8ed0-adf38253f207"}' 	

评论成功后，还需要对评论做+1处理：
即：
        curl -X POST -i -H "Authorization: Bearer YWMtZt7t9GrOEeOIFyNPCrbWCwAAAUM88L--vR5w6w5T-ZZ3JBRwD_HAeeg-8Rk" "http://api.easemob.com/ljs/ljs/activities/dbc83d6a-6acd-11e3-8ed0-adf38253f207/commented" 

有几点需要注意的:

1. 这里是stliu用户在评论, 所以url上面是 /users/stliu/activities, 也就是往用户自己的activities中去post数据
2. verb的值是comment, 表示这是一个"评论"的动作
3. to的值是上面段子的uuid


## 获取一个段子的所有评论


	curl -X GET -i "http://api.easemob.com/ljs/ljs/activities?ql=select%20*%20where%20to%3D5ecf100a-5cf8-11e3-95de-ed03f77c8cb1%20and%20verb%3D'comment'&limit=5"

这个的查询语句没有被转义的版本实际上是

	ql = select * where to=5ecf100a-5cf8-11e3-95de-ed03f77c8cb1 and verb='comment' 
	
这里页可以使用上文中提到的分页


## 收藏段子

从上面获取的每一个段子中, 见下面

	{
	      "uuid": "5ecf100a-5cf8-11e3-95de-ed03f77c8cb1",
	      "type": "activity",
	      "created": 1386170756352,
	      "modified": 1386170756352,
	      "actor": {
	        "uuid": "67ed2fea-5cf4-11e3-9dee-9f8110ecbed4"
	      },
	      "content": "小熊打豆豆",
	      "verb": "post",
	      "published": 1386170756352
	    }

可以看到, 都有一个UUID, 用户在收藏一个段子的时候, 就会用到这个uuid

	curl -X POST -i -H "Authorization: Bearer YWMtNda4DFzyEeOrOy_LuVzHjAAAAULiG1IrN8opggpytUDFmJkiocawbINICYk" "http://api.easemob.com/ljs/ljs/users/test1/collects/activities/5ecf100a-5cf8-11e3-95de-ed03f77c8cb1"
	
同样的, 上面request中的 test1需要替换为当前登陆的用户名, 后面的uuid需要替换为要收藏的段子的uuid, token也要换成当前登陆用户自己的token
	

## 获取自己收藏的段子

	curl -X GET -i -H "Authorization: Bearer YWMtNda4DFzyEeOrOy_LuVzHjAAAAULiG1IrN8opggpytUDFmJkiocawbINICYk" "http://api.easemob.com/ljs/ljs/users/test1/collects/activities"
	

## 取消收藏一个段子

	curl -X DELETE -i -H "Authorization: Bearer YWMtNda4DFzyEeOrOy_LuVzHjAAAAULiG1IrN8opggpytUDFmJkiocawbINICYk" "http://api.easemob.com/ljs/ljs/users/test1/collects/activities/5ecf100a-5cf8-11e3-95de-ed03f77c8cb1"



# 后台操作
	

## 审核

审核的操作实际上就是, 把别人投稿的帖子, 从 drafts 中取出来。然后管理员审核, 然后再把审核通过的帖子发布出去。


### 获取待审核的帖子列表
GET /${orgName}/${appName}/drafts
描述:获取待审核的帖子列表
权限: app用户登录
Url参数: 无
Request body: 无
错误代码：
        401 Unauthorized： 认证失败。返回的response body为json数据：
        {"error":"expired_token",
         "timestamp":1389492673539,
         "duration":0,
         "exception":"org.usergrid.rest.exceptions.SecurityException",
         "error_description":"Unable to authenticate due to expired access token"
        }
        
curl示例：
       curl -X GET -i -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" "http://api.easemob.com/ljs/ljs/drafts?limit=10"

low priority: TODO: 如何获得特定小区的待审核帖子？

返回的json数据的entities部分即为待审核的帖子列表


       {
         "action" : "get",
         "application" : "1b630e80-5fdb-11e3-b787-e35353985c71",
         "params" : {
           "limit" : [ "10" ]
         },
         "path" : "/drafts",
         "uri" : "https://api.easemob.com/ljs/ljs/drafts",
         "entities" : [ {
           "uuid" : "fa9a3bfa-6adf-11e3-9d1d-09e6ebeb6189",
           "type" : "draft",
           "created" : 1387699596591,
           "modified" : 1387699596591,
           "audio" : "http://easemob.com/downloads/aiyadongwuyuan.mp3",
           "audio_length" : "35",
           "author_nick" : "jervis liu",
           "author_username" : "jliu",
           "content" : "aiya",
           "verb" : "post"
         }, {
           "uuid" : "d24f076a-6ae0-11e3-a36e-99133218367f",
           "type" : "draft",
           "created" : 1387699958486,
           "modified" : 1387699958486,
           "audio" : "http://easemob.com/downloads/aiyadongwuyuan.mp3",
           "audio_length" : "35",
           "author_nick" : "jervis liu",
           "author_username" : "jliu",
           "content" : "aiya",
           "verb" : "post"
         } ],
         "timestamp" : 1387701202197,
         "duration" : 17,
         "organization" : "hoho2",
         "applicationName" : "hoho2",
         "count" : 6
      }
	
### 获取指定的待审核帖子
GET /${orgName}/${appName}/drafts/${uuid}
描述:获取指定的待审核的帖子
权限: app用户登录
Url参数: 无
Request body: 无
错误代码：
        401 Unauthorized： 认证失败。返回的response body为json数据：
        {"error":"expired_token",
         "timestamp":1389492673539,
         "duration":0,
         "exception":"org.usergrid.rest.exceptions.SecurityException",
         "error_description":"Unable to authenticate due to expired access token"
        }
        
curl示例：
       curl -X GET -i -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" "http://api.easemob.com/ljs/ljs/drafts/fa9a3bfa-6adf-11e3-9d1d-09e6ebeb6189"


返回的json数据的entities部分即为待审核的帖子


       {
         "action" : "get",
         "application" : "1b630e80-5fdb-11e3-b787-e35353985c71",
         "params" : {
           "limit" : [ "10" ]
         },
         "path" : "/drafts",
         "uri" : "https://api.easemob.com/ljs/ljs/drafts",
         "entities" : [ {
           "uuid" : "fa9a3bfa-6adf-11e3-9d1d-09e6ebeb6189",
           "type" : "draft",
           "created" : 1387699596591,
           "modified" : 1387699596591,
           "audio" : "http://easemob.com/downloads/aiyadongwuyuan.mp3",
           "audio_length" : "35",
           "author_nick" : "jervis liu",
           "author_username" : "jliu",
           "content" : "aiya",
           "verb" : "post"
         }],
         "timestamp" : 1387701202197,
         "duration" : 17,
         "organization" : "hoho2",
         "applicationName" : "hoho2",
         "count" : 6
      }

	 
### 批准帖子：步骤1： 将被批准的帖子发布到真正的发帖人的activities下
POST /${orgName}/${appName}/users/admin/activities
描述:获取待审核的段子
权限: app用户登录
Url参数: 无
Request body: 见帖子的数据结构
错误代码：
        401 Unauthorized： 认证失败。返回的response body为json数据：
        {"error":"expired_token",
         "timestamp":1389492673539,
         "duration":0,
         "exception":"org.usergrid.rest.exceptions.SecurityException",
         "error_description":"Unable to authenticate due to expired access token"
        }
        
curl示例：
	curl -X POST -i -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" "http://api.easemob.com/ljs/ljs/users/admin/activities" -d '{"author_username" : "jliu","author_nick" : "jervis liu", "author_avator" : "","content" : "good morning","verb" : "post","audio" : "http://easemob.com/downloads/aiyadongwuyuan.mp3","like_count":0,"dislike_count":0,"comment_count":0}'
TODO: 用什么账号的权限可以做到可以发activity到任意User的activity下？	


### 批准帖子：步骤2： 发布成功后从drafts中删除已经被批准的帖子
DELETE /${orgName}/${appName}/drafts/${uuid}
描述:获取待审核的段子
权限: app用户登录
Url参数: 无
Request body: 无
错误代码：
        401 Unauthorized： 认证失败。返回的response body为json数据：
        {"error":"expired_token",
         "timestamp":1389492673539,
         "duration":0,
         "exception":"org.usergrid.rest.exceptions.SecurityException",
         "error_description":"Unable to authenticate due to expired access token"
        }
        
curl示例：
        curl -X DELETE -i -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" "http://api.easemob.com/ljs/ljs/drafts/940e8aaa-68e4-11e3-9536-83d07fdb96e1"


### 删除待审核的帖子。
DELETE /${orgName}/${appName}/drafts/${uuid}
描述:删除待审核的帖子
权限: app用户登录
Url参数: 无
Request body: 无
错误代码：
        401 Unauthorized： 认证失败。返回的response body为json数据：
        {"error":"expired_token",
         "timestamp":1389492673539,
         "duration":0,
         "exception":"org.usergrid.rest.exceptions.SecurityException",
         "error_description":"Unable to authenticate due to expired access token"
        }
        
curl示例：
        curl -X DELETE -i -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" "http://api.easemob.com/ljs/ljs/drafts/940e8aaa-68e4-11e3-9536-83d07fdb96e1"


### 获得已审核发布的帖子。
GET /${orgName}/${appName}/users/admin/activities
描述:获取已审核的帖子
权限: app用户登录
Url参数: 无
Request body: 无
错误代码：
        401 Unauthorized： 认证失败。返回的response body为json数据：
        {"error":"expired_token",
         "timestamp":1389492673539,
         "duration":0,
         "exception":"org.usergrid.rest.exceptions.SecurityException",
         "error_description":"Unable to authenticate due to expired access token"
        }
        
curl示例：
	curl -X GET  -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" "http://api.easemob.com/ljs/ljs/users/admin/activities?limit=5&ql=select+*+where+verb+%3D+'post'"
low priority: TODO:怎么做（去取小区这个group下的帖子？）:目前后台可以显示所有小区的帖子

### 获得指定的已审核发布的帖子。
GET /${orgName}/${appName}/users/admin/activities/${uuid}
描述:获取指定的已审核的帖子
权限: app用户登录
Url参数: 无
Request body: 无
错误代码：
        401 Unauthorized： 认证失败。返回的response body为json数据：
        {"error":"expired_token",
         "timestamp":1389492673539,
         "duration":0,
         "exception":"org.usergrid.rest.exceptions.SecurityException",
         "error_description":"Unable to authenticate due to expired access token"
        }
        
curl示例：
	curl -X GET  -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" "http://api.easemob.com/ljs/ljs/users/admin/activities/fa9a3bfa-6adf-11e3-9d1d-09e6ebeb6189"



### 删除已审核发布的帖子。
DELETE /${orgName}/${appName}/activities/${uuid}
描述:删除已审核发布的帖子
权限: app用户登录
Url参数: 无
Request body: 无
错误代码：
        401 Unauthorized： 认证失败。返回的response body为json数据：
        {"error":"expired_token",
         "timestamp":1389492673539,
         "duration":0,
         "exception":"org.usergrid.rest.exceptions.SecurityException",
         "error_description":"Unable to authenticate due to expired access token"
        }
        
curl示例：
        curl -X DELETE -i -H "Authorization: Bearer YWMtI-n89nqGEeOOXyXq3bWeNwAAAUOj8rinBuWhDVWIeFPgEotHz6AOCflJ0AA" "http://api.easemob.com/ljs/ljs/activities/b3cf3de4-6ebb-11e3-8826-d79a71427245"

