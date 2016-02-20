<%@ page contentType="text/html;charset=UTF-8"%>
<html>    
<head>
        <title>我的日程</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="static/css/main.css">
        <link rel="stylesheet" href="static/css/plugin/minical.css">
        <link rel="stylesheet" href="static/css/plugin/calendar.css">
        <link rel="stylesheet" href="static/css/plugin/dailog.css">
    </head>    
    <body>
        <div id="mainpanel">
            <div id="toppanel">
                <div id="loadingpannel">正在加载数据...</div>
                <div id="errorpannel">非常抱歉，无法加载您的活动，请稍后再试</div>
                <p class="logo">我的日程</p>
                <div class="calbtnp1">
                    <button id="todaybtn" type="button" class="btn">今天</button>
                    <div class="btngroup">
                        <span id="prevbtn" type="button" class="btn prevbtn"><em></em></span>
                        <span id="nextbtn" type="button" class="btn nextbtn"><em></em></span>
                    </div>
                    <div id="dateshow"></div>
                </div>
                <div class="calbtnp3">
                    <div>
                        <a id="langch" href="index759b.jsp?lang=zh-cn">中文</a>                       
						<span>|</span>
                        <a id="langen" href="index0e49.jsp?lang=en-us">English</a>
                    </div>
                </div>
                <div class="calbtnp2">
                    <div id="viewswithbtn" class="btngroup">
                        <button id="daybtn" type="button" class="btn">
                          日                        </button>
                        <button id="weekbtn" type="button" class="btn current">
                         周                        </button>
                        <button id="monthbtn" type="button" class="btn">
                          月                        </button>
                    </div>
                </div>
            </div>
            <div id="leftpanel">
                <div class="addbtnp">
                    <button id="addcalbtn" type="button" class="btn btn-danger">
                        新建                    </button>
                </div>
                <div id="minical" class="minical">
                </div>
            </div>
            <div id="rightpanel">
                <div id="xgcalendarp">
                </div>
                <div class="bottom">
                </div>
            </div>
        </div>
        <script type="text/javascript" src="static/js/locales/zh-cn.js"></script>
        <script type="text/javascript" src="static/js/sea.js"></script>
        <script type="text/javascript" src="static/js/seaconfig.js">  </script>
        <script type="text/javascript">
            var loadingmsg = '正在加载数据...';
            var sucessmsg = '操作成功!';
            var processdatamsg = '正在处理请求...';
            seajs.use('page/index', function(app) {
                app.init();
            });
        </script>
    </body>

</html>