# 注意:

HOHO的服务器现在已经跑起来了, 本文档中的所有url都需要换成

	http://210.76.97.29:8080
	
已创建了一个org和app, 具体信息是

	curl -X POST -i  "http://210.76.97.29:8080/management/organizations" -d '{"organization":"hoho","username":"hoho_admin","name":"Hoho Admin","email":"admin@hoho.com","password":"1234567890"}'
        curl -X POST -i -H "Authorization: Bearer YWMt9gIOoGq9EeOBfh-N5X4iHgAAAUM8hQCKJoDX7lMJBNqBU9mZoD5tp-Wutoc" "http://210.76.97.29:8080/management/organizations/hoho/applications" -d '{"name":"hoho"}'
        
Usergrid管理后台的登录地址是：

        http://210.76.97.29:8080/management/organizations	



## hoho应用中段子的数据结构

	{	
	actor : "刘少壮--发布人, 实际执行post的人, 这个同样server会自动添加 (来自于 /users/{usrename}/activities 中的username), 再post的时候不能够添加这个属性",
	author_username : "段子中要显示的发布人的username(是用户的primarykey)。用来在界面上显示段子的发送人, 虽然实际发送人都是admin, 但是界面上可以显示不同的名字"
	author_nick : "段子中要显示的发布人的昵称"
	author_avator : "段子中要显示的发布人的头像"
	content : "段子内容",
	published : "发布时间--UTC, 这个server会自动创建, 在post的时候不需要添加",
	verb : "post 表示段子, comment表示评论",
	isaudio : "该属性必须有。0 表示不是一个语音段子, 1 表示是语音段子",
	image : ["http://xxx.img", "http://yyy.jpg"],    //表示一个能够下载到段子中图片的地址的数组。照片可以为多个
	audio : "如果 isaudio属性是1的话, 那么程序应该从 audio属性的值来下载语音, 所以这里的值应该是个指向语音文件的url。请注意该值为字符串，不是数字"
	audio_length : "语音段子的长度。这样客户端可在不下载语音段子的情况就能显示语音段子的长度。长度值为int类型，单位为秒"
	like_count ： "该属性必须有。赞的数量"
	dislike_count: "该属性必须有。踩的数量"
	comment_count: "该属性必须有。评论的数量"
	like_byme ： "1 表示我（当前发出请求的用户）顶过, 0 我没有顶过"
	dislike_byme: "1 表示我（当前发出请求的用户）踩过, 0 我没有踩过"
	collect_byme: "1 表示我（当前发出请求的用户）收藏过, 0 我没有收藏过"
	}

## hoho应用中用户(User)的数据结构

	{	
	username : "这个是用户的primarykey. 必须是英文字母数字下划线横线的组合，规则如下："^[a-z0-9_-]{3,15}$"",
	nick : "用户昵称，可以为中文。",
	avator : "头像在图片服务器上的路径"
	signature : "个性签名",
	sex : "性别. F|M|U.不区分大小写",
	}

## hoho应用中评论的数据结构

	{	
	actor : "刘少壮--发布人, 同样,这个属性由server维护",
	author_nick : "显示的名称或者nickname"
	content : "评论内容",
	published : "发布时间--UTC",
	verb : "post 表示段子, comment表示评论",
	to : "被评论的段子的UUID"	
	}
	
## 设置未登陆用户也直接读取段子

所有的段子都是一个_activity_, 所以如果需要未登录用户(拥有guest权限)能够读取段子的话, 只需要guest role能够访问 *GET /activities/* 

可以参考 _usergrid/role.md_ 文档, 给guest 角色添加 _get /activities/*_ 的权限
        curl -X POST -i -H "Authorization: Bearer YWMt9gIOoGq9EeOBfh-N5X4iHgAAAUM8hQCKJoDX7lMJBNqBU9mZoD5tp-Wutoc" "http://210.76.97.29:8080/hoho/hoho/roles/guest/permissions" -d '{ "permission" : "get:/users/*/feed" }'

//post /roles/guest/permissions { "permission" : "get:/activities" }


## 设置未登陆用户也直接获取段子的评论

所有的段子都是一个_activity_, 所以如果需要未登录用户(拥有guest权限)能够读取段子的话, 只需要guest role能够访问 *GET /activities/* 

可以参考 _usergrid/role.md_ 文档, 给guest 角色添加 _get /activities/*_ 的权限
        curl -X POST -i -H "Authorization: Bearer YWMt9gIOoGq9EeOBfh-N5X4iHgAAAUM8hQCKJoDX7lMJBNqBU9mZoD5tp-Wutoc" "http://210.76.97.29:8080/hoho/hoho/roles/guest/permissions" -d '{ "permission" : "get:/activities" }'



## 用户注册

用户注册的时候调用下面的api

	curl -X POST -i "http://210.76.97.29:8080/hoho/hoho/users" -d '{"username":"admin","password":"123456"}'
	
会返回信息

	{
	  "action" : "post",
	  "application" : "3bec9de0-6abe-11e3-8606-41e5b3c17b39",
	  "params" : { },
	  "path" : "/users",
	  "uri" : "http://210.76.97.29:8080/hoho/hoho/users",
	  "entities" : [ {
	    "uuid" : "98b746e0-6ac0-11e3-914b-0b6cc7bc5592",
	    "type" : "user",
	    "created" : 1387686117965,
	    "modified" : 1387686117965,
	    "username" : "admin",
	    "activated" : true
	  } ],
	  "timestamp" : 1387686117951,
	  "duration" : 207,
	  "organization" : "hoho",
	  "applicationName" : "hoho"
        }
	
这里, _username_ 和 _password_ 是必须提供的, 除此之外, 还可以增加任意别的属性, 例如, 在用户注册页面让用户填写性别和email地址的话, 那么post的数据就会是


	curl -X POST -i "http://210.76.97.29:8080/hoho/hoho/users" -d '{"username":"test1","password":"test1", "sex":"M", "email":"stliu@apache.org"}'
	
因为Hoho支持不登陆也可以获取帖子，所以我们再创建一个特殊的用户，比如叫做_guest_,让_guest_关注_admin_。这样，未登录的用户只要去取_guest_的feed就能看到所有最新的帖子了。

	curl -X POST -i "http://210.76.97.29:8080/hoho/hoho/users" -d '{"username":"guest","password":"123456"}'
	curl -X POST -i "http://210.76.97.29:8080/hoho/hoho/users" -d '{"username":"jliu","password":"123456"}'


## 用户登陆

用户登陆的时候, 根据用户输入的账号和密码进行登陆

	curl -X POST -i  "http://210.76.97.29:8080/hoho/hoho/token" -d '{"grant_type":"password","username":"guest","password":"123456"}'

如果这个用户之前已经注册了, 并且这里提供的密码正确的话, 会返回


	{"access_token":"YWMtIzRPemrBEeO2Wa2zqdPoYAAAAUM8mdHnjdihm9yKXDd254Pk9XTVF0jAZcI",
	"expires_in":604800,
	"user":{"uuid":"98b746e0-6ac0-11e3-914b-0b6cc7bc5592",
	"type":"user",
	"created":1387686117965,
	"modified":1387686117965,
	"username":"admin",
	"activated":true}
	}

从这个返回值中, 可以得到两部分信息:

1. token

	这个是服务器用来标识这个用户已经登陆的, 用户登陆后的所有操作(这里的操作指的是app访问服务器的request), 都需要把这个token加到header当中, 在本文档后面所有描述到得request都会有这个header
	
		-H "Authorization: Bearer YWMtNda4DFzyEeOrOy_LuVzHjAAAAULiG1IrN8opggpytUDFmJkiocawbINICYk"

2. 用户的具体信息, app可以根据这个信息来构造一个user对象, 真实的环境中, 这个还会包含用户的头像/性别/email等等, 取决于注册的时候提供了哪些信息	
	

注意:  在用户第一次登陆的时候, app应该让这个用户"关注"管理员账号, 考虑微博就容易理解了, 相当于所有app用户都是系统管理员的粉丝, 这样, 系统管理员在后台发布了什么微博, 他的所有粉丝都能看到

这里假设系统管理员的账号叫做 _admin_ 那么, app在用户第一次登陆的时候, 应该执行这个request

	
        curl -X POST -i -H "Authorization: Bearer YWMt9gIOoGq9EeOBfh-N5X4iHgAAAUM8hQCKJoDX7lMJBNqBU9mZoD5tp-Wutoc" "http://210.76.97.29:8080/hoho/hoho/users/guest/following/users/admin"


这个request的意思是:

让用户guest(这个是用户名, 需要根据登陆的用户的用户名修改, 是个变量) 关注用户admin


## 修改用户信息
	curl -X PUT -i -H "Authorization: Bearer YWMt9gIOoGq9EeOBfh-N5X4iHgAAAUM8hQCKJoDX7lMJBNqBU9mZoD5tp-Wutoc" "http://210.76.97.29:8080/hoho/hoho/users/jliu" -d '{"username":"jliu","nick":"jervis Liu","avator":"http://tp3.sinaimg.cn/3244140934/180/5661089711/0", "signature":"today is a nice day", "sex":"M"}'


## 删除用户
        curl -X DELETE -i -H "Authorization: Bearer YWMtP_8IisA-EeK-a5cNq4Jt3QAAAT7fI10IbPuKdRxUTjA9CNiZMnQIgk0LEUE" "http://210.76.97.29:8080/hoho/hoho/users/ligangying"

## 获取用户列表
       curl -X GET -i -H "Authorization: Bearer YWMt39RfMMOqEeKYE_GW7tu81AAAAT71lGijyjG4VUIC2AwZGzUjVbPp_4qRD5k" "http://210.76.97.29:8080/hoho/hoho/users"


## 使用微博账号登陆

当前, app中使用sharesdk来做分享和第三方登陆功能, 这里, 我们需要的实际上是, 让用户使用他的新浪微博账户, 登陆到usergrid

	GET /stliu-org/hoho/auth/weibo?token={这个的值是新浪微博一个授权token, 注意不是usergrid的授权token}&ttl={这里的值是微博的token的有效期, 需要转换成毫秒, 新浪给出的是秒}


通过调用这个api, 系统会返回一个json数据和通过usergrid来直接登陆一样的数据

## 使用qq账号登陆

	GET /stliu-org/hoho/auth/qq?qq_access_token={同样的, 这里是qq给出的token}&oauth_consumer_key={}&openid={当前授权的QQ用户的openid}
	
	
详细的参数含义, 和如何从QQ的开放平台中获取这些值可以参考[这里](http://wiki.connect.qq.com/openapi调用说明_oauth2-0)	



## 获取新段子

上面说的是管理员通过管理后台来发布段子的操作, 这里介绍的是在app端, 一个用户如何看到管理员发布的段子

前面说过了, 一个用户登陆之后, app需要把这个用户注册为管理员的粉丝。然后查询这个用户的feed entity，就能看到所有的帖子了。

	curl -X GET -i "http://210.76.97.29:8080/hoho/hoho/users/guest/feed?ql=select+*+where+verb+%3D+'post'"
	
	
注意，这个操作是不需要登录并传入token的。
注意, 上面的ql后面的内容是http转义过的,实际上就是应用了个过滤器 where verb = 'post', 也就是只获取"段子", 下面我们会看到"评论"使用的是不同的动词类型
	
同样的, 上面的test1应该被替换为实际当前登陆的用户名（即当前登录用户能看到的所有帖子。因为在hoho中每个用户都关注了admin这个用户，所以当前用户都能看到admin这个用户发出的帖子）, token也需要被替换成当前登陆用户获取到的token
@papa,考虑到以后要支持每个人都能发帖，我们是否应该采取下列方式？
1.爬虫抓过来的帖子都是admin发的
2. 语音段子的发帖人就是真正的发帖人
3，以后支持每个人都能发帖，审核后的帖子的发帖人也是真正的发帖人？

这样会返回如下的内容


	
	{
	  "action" : "get",
	  "application" : "3bec9de0-6abe-11e3-8606-41e5b3c17b39",
	  "params" : {
	    "ql" : [ "select * where verb = 'post'" ]
	  },
	  "path" : "/users/0b5b285a-6ac2-11e3-a7a2-ed17ffbef7ec/feed",
	  "uri" : "http://210.76.97.29:8080/hoho/hoho/users/0b5b285a-6ac2-11e3-affbef7ec/feed",
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
	 	
上面返回值中的entities数组中的内容, 实际上就是管理员发布的段子(我这里给出的值可能是不准确的)


这个request会默认返回最多100个结果, 这样就有问题

1. 如果之前管理员发布了很多个段子, 假设200个, 那么这个request就只会获取到最新的100条
2. 一次如果传输100个段子的话, 可能数据量会很大, 在移动网络不稳定的大前提下, 很可能会失败

解决方案是使用分页, app判断一个页面上最多能显示多少条段子, 假设5条的话, 那么一次只从服务器取5条, 然后用户如果继续往下拉页面的话, 再去服务器取下5条数据(如果不明白的话, 打开微博的app看看)

### 如何做分页

参见 dev-docs/usergrid/使用limit和cursor


### 如何排序

参见上面返回段子的api, 可以看到所有的段子是再一个数组中被返回的, 那么这个数组中元素的顺序是怎么决定的呢?

默认情况下, 管理员发布的段子都会被服务器自动加上一个 _published_ 属性, 也就是发布时间, 那么用户在使用 /feed 来获取段子的时候, 系统会按照这个发布时间来自动排序, 按照最新到最老的顺序排列 -- 同样的, 和微博的行为一样

可能app有不同的排序需求, 例如想按照一个段子的重要性来排序, 那么, 管理员在发布段子的时候, 需要给每个段子加个属性, 例如priority

然后, app在发request的时候, 通过order by的形式来让服务器使用这个属性来排序, 而不是使用默认的 _published_ 来排序


	curl -X GET "http://210.76.97.29:8080/hoho/hoho/users/guest/feed?limit=5&ql=select+*+where+verb+%3D+'post'+order+by+priority+desc"
	
## 获取只听段子(只看有声音的段子)

参考上面的获取段子的api, 这里只不过加上一个过滤条件 "where isaudio = '1'"

	curl -X GET "http://210.76.97.29:8080/hoho/hoho/users/guest/feed?limit=5&ql=select+*+where+verb+%3D+'post'+and+isaudio+%3D+'1'"

## 顶/踩一个段子
	curl -X POST -i -H "Authorization: Bearer YWMtZt7t9GrOEeOIFyNPCrbWCwAAAUM88L--vR5w6w5T-ZZ3JBRwD_HAeeg-8Rk" "http://210.76.97.29:8080/hoho/hoho/activities/dbc83d6a-6acd-11e3-8ed0-adf38253f207/like" 
	curl -X POST -i -H "Authorization: Bearer YWMtZt7t9GrOEeOIFyNPCrbWCwAAAUM88L--vR5w6w5T-ZZ3JBRwD_HAeeg-8Rk" "http://210.76.97.29:8080/hoho/hoho/activities/dbc83d6a-6acd-11e3-8ed0-adf38253f207/dislike" 

	
通过这个api, 会在段子的json数据中增加一个属性 like_count / dislike_count , 并且每次调用都会给这个值 + 1  

最开始的段子, 还没有人顶或者踩的时候, 是没有这两个属性的, 如果有人顶或者踩了, server会自动创建

并且, 这个api没有需要提交的数据, 只需要最这个url执行post操作即可

	

## 获取热门段子
参考上面的获取段子的api, 这里只不过加上一个过滤条件 "order by like_count desc order by created desc",即按照评论数量多少排序
TODO: 可能还需要同时按照时间排序，否则会总是看到那几个最热门的帖子在最上面。

	curl -X GET -i "http://210.76.97.29:8080/hoho/hoho/users/guest/feed?limit=5&ql=select+*+where+verb+%3D+'post'+order+by+like_count+desc'"


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

	curl -X POST -i -H "Authorization: Bearer YWMtZt7t9GrOEeOIFyNPCrbWCwAAAUM88L--vR5w6w5T-ZZ3JBRwD_HAeeg-8Rk" "http://210.76.97.29:8080/hoho/hoho/users/guest/activities" -d '{"author_nick":"strong liu","content":"really funny","verb":"comment", "to" :"dbc83d6a-6acd-11e3-8ed0-adf38253f207"}' 	

评论成功后，还需要对评论做+1处理：
即：
        curl -X POST -i -H "Authorization: Bearer YWMtZt7t9GrOEeOIFyNPCrbWCwAAAUM88L--vR5w6w5T-ZZ3JBRwD_HAeeg-8Rk" "http://210.76.97.29:8080/hoho/hoho/activities/dbc83d6a-6acd-11e3-8ed0-adf38253f207/commented" 

有几点需要注意的:

1. 这里是stliu用户在评论, 所以url上面是 /users/stliu/activities, 也就是往用户自己的activities中去post数据
2. verb的值是comment, 表示这是一个"评论"的动作
3. to的值是上面段子的uuid


## 获取一个段子的所有评论


	curl -X GET -i "http://210.76.97.29:8080/hoho/hoho/activities?ql=select%20*%20where%20to%3D5ecf100a-5cf8-11e3-95de-ed03f77c8cb1%20and%20verb%3D'comment'&limit=5"

这个的查询语句没有被转义的版本实际上是

	ql = select * where to=5ecf100a-5cf8-11e3-95de-ed03f77c8cb1 and verb='comment' 
	
这里页可以使用上文中提到的分页

**TODO**	注意这里还没有涉及到

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

	curl -X POST -i -H "Authorization: Bearer YWMtNda4DFzyEeOrOy_LuVzHjAAAAULiG1IrN8opggpytUDFmJkiocawbINICYk" "http://210.76.97.29:8080/hoho/hoho/users/test1/collects/activities/5ecf100a-5cf8-11e3-95de-ed03f77c8cb1"
	
同样的, 上面request中的 test1需要替换为当前登陆的用户名, 后面的uuid需要替换为要收藏的段子的uuid, token也要换成当前登陆用户自己的token
	

## 获取自己收藏的段子

	curl -X GET -i -H "Authorization: Bearer YWMtNda4DFzyEeOrOy_LuVzHjAAAAULiG1IrN8opggpytUDFmJkiocawbINICYk" "http://210.76.97.29:8080/hoho/hoho/users/test1/collects/activities"
	
同理, 如果一个用户收藏的很多的话, app也需要在这里使用分页的逻辑	

## 取消收藏一个段子

	curl -X DELETE -i -H "Authorization: Bearer YWMtNda4DFzyEeOrOy_LuVzHjAAAAULiG1IrN8opggpytUDFmJkiocawbINICYk" "http://210.76.97.29:8080/hoho/hoho/users/test1/collects/activities/5ecf100a-5cf8-11e3-95de-ed03f77c8cb1"



# 当前后台操作主要包括以下几部分:

## 投稿

投稿的工作原理是把帖子发到app下的一个名字为“drafts”的entity里。

	curl -X POST -i -H "Authorization: Bearer YWMtZt7t9GrOEeOIFyNPCrbWCwAAAUM88L--vR5w6w5T-ZZ3JBRwD_HAeeg-8Rk" "http://210.76.97.29:8080/hoho/hoho/drafts" -d '{"author_username":"jliu","author_nick":"jervis liu", "author_avator":"","content" : "aiya","verb" : "post","isaudio" : "1","audio":"http://easemob.com/downloads/aiyadongwuyuan.mp3","audio_length":"35", "like_count":10,"dislike_count":2,"comment_count":5}'	

请注意，"isaudio" : "1"是必须的属性。如果是非语音帖子，该值需要设为"isaudio" : "0"。
"like_count":10,"dislike_count":2,"comment_count":5 等属性是必须要的属性，可以设为0.

发一个有图片的段子到投稿：

	curl -X POST -i -H "Authorization: Bearer YWMtZt7t9GrOEeOIFyNPCrbWCwAAAUM88L--vR5w6w5T-ZZ3JBRwD_HAeeg-8Rk" "http://210.76.97.29:8080/hoho/hoho/drafts" -d '{"author_username":"jliu","author_nick":"jervis liu", "author_avator":"","content" : "aiyaya","verb" : "post","isaudio" : "0","image" : [ "http://www.easemob.com/downloads/6-1311231I03BK.jpg" ], "like_count":10,"dislike_count":2,"comment_count":5}'	
	

## 审核

审核的操作实际上就是, 把别人投稿的段子, 从 drafts 中取出来。然后管理员审核, 然后再把审核通过的段子发布出去。


### 获取待审核的段子

       curl -X GET -i -H "Authorization: Bearer YWMtZt7t9GrOEeOIFyNPCrbWCwAAAUM88L--vR5w6w5T-ZZ3JBRwD_HAeeg-8Rk" "http://210.76.97.29:8080/hoho/hoho/drafts?limit=10"

	
返回的数据为


       {
         "action" : "get",
         "application" : "1b630e80-5fdb-11e3-b787-e35353985c71",
         "params" : {
           "limit" : [ "10" ]
         },
         "path" : "/drafts",
         "uri" : "https://api.easemob.com/hoho2/hoho2/drafts",
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
           "isaudio" : "1",
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
           "isaudio" : "1",
           "verb" : "post"
         } ],
         "timestamp" : 1387701202197,
         "duration" : 17,
         "organization" : "hoho2",
         "applicationName" : "hoho2",
         "count" : 6
      }
	
	
	
### 如何做分页
	
参见 dev-docs/usergrid/使用limit和cursor

	 
### 批准帖子。首先将被批准的帖子发布到admin的activities下


管理员使用自己的账号来发布段子, 应该明确一个账号的, 上面假设的系统管理员账号是admin

第一步也是和上面一样, 管理员使用自己的账号和密码来获取token

然后就是把批准后的段子发到admin账号的activities下面了

	curl -X POST -i -H "Authorization: Bearer YWMtIzRPemrBEeO2Wa2zqdPoYAAAAUM8mdHnjdihm9yKXDd254Pk9XTVF0jAZcI" "http://210.76.97.29:8080/hoho/hoho/users/admin/activities" -d '{"author_username" : "jliu","author_nick" : "jervis liu", "author_avator" : "","content" : "good morning","verb" : "post","isaudio" : "1","audio" : "http://easemob.com/downloads/aiyadongwuyuan.mp3","audio_length" : "35"}'
	
这里需要解释一下, 一个用户发微博也好, 发段子也好, 或者朋友圈, 在系统看来, 都是一个activity

而一个activity的含义包含了三个基本内容 -- 主谓宾, 也就是: 谁做了什么事情

举例来说, 上面的request的含义实际上是: 管理员(actor)发布了(verb:post)一个段子(content)

除了这三个属性之外, 上面其余的属性都是可以随意添加修改的, 取决于具体的业务需求


### 批准帖子。发布成功后从drafts中删除已经被批准的帖子
        curl -X DELETE -i -H "Authorization: Bearer YWMtP_tVQGjNEeO1ev-Wb65NewAAAUMvzcGy60g6_csFSfo902qX1ctmvUv5I80" "http://210.76.97.29:8080/hoho/hoho/drafts/940e8aaa-68e4-11e3-9536-83d07fdb96e1"

### 删除待审核的帖子。
        curl -X DELETE -i -H "Authorization: Bearer YWMtP_tVQGjNEeO1ev-Wb65NewAAAUMvzcGy60g6_csFSfo902qX1ctmvUv5I80" "http://210.76.97.29:8080/hoho/hoho/drafts/940e8aaa-68e4-11e3-9536-83d07fdb96e1"


### 删除已发布的帖子。
        curl -X DELETE -i -H "Authorization: Bearer YWMtP_tVQGjNEeO1ev-Wb65NewAAAUMvzcGy60g6_csFSfo902qX1ctmvUv5I80" "http://210.76.97.29:8080/hoho/hoho/admin/940e8aaa-68e4-11e3-9536-83d07fdb96e1"

### 获得已审核发布的帖子。
	curl -X GET "http://210.76.97.29:8080/hoho/hoho/users/admin/activities?limit=5&ql=select+*+where+verb+%3D+'post'+and+isaudio+%3D+'0'"


# 管理后台上传下载文件
## 上传文件
        > PUT /hohofiles1/test2.txt HTTP/1.1
        > Authorization: Basic ZGVtb3VzZXI6ZGVtb3Bhc3M=
        > Host: v0.api.upyun.com
        > Content-Length: 26

        ...文件内容...
        
即使用PUT方法，URL是v0.api.upyun.com/hohofiles/test2.txt （test2.txt为实际上传的文件名），Authorization header的值为"Basic ZGVtb3VzZXI6ZGVtb3Bhc3M=" (base64编码前的用户名和密码为：admin:admin123456)
更多详情具体可见又拍文档：http://wiki.upyun.com/index.php?title=HTTP_REST_API%E6%8E%A5%E5%8F%A3#.E4.B8.8A.E4.BC.A0.E6.96.87.E4.BB.B6

## 下载文件
以上操作成功后，用在浏览器中打开地址http://hohofiles.b0.upaiyun.com/test2.txt，检验上传是否成功