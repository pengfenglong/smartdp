<!DOCTYPE html>
<html>    
    
<!-- Mirrored from xgcal.sinaapp.com/demo2/?lang=en-us by HTTrack Website Copier/3.x [XR&CO'2013], Thu, 31 Oct 2013 13:35:00 GMT -->
<head>
        <title>My Calendar</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="static/css/main.css">
        <link rel="stylesheet" href="static/css/plugin/minical.css">
        <link rel="stylesheet" href="static/css/plugin/calendar.css">
        <link rel="stylesheet" href="static/css/plugin/dailog.css">
    </head>    
    <body>
        <div id="mainpanel">
            <div id="toppanel">
                <div id="loadingpannel">Loading data...</div>
                <div id="errorpannel">Sorry, could not load your data, please try again later</div>
                <p class="logo">My Calendar</p>
                <div class="calbtnp1">
                    <button id="todaybtn" type="button" class="btn">Today</button>
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
                          Day                        </button>
                        <button id="weekbtn" type="button" class="btn current">
                         Week                        </button>
                        <button id="monthbtn" type="button" class="btn">
                          Month                        </button>
                    </div>
                </div>
            </div>
            <div id="leftpanel">
                <div class="addbtnp">
                    <button id="addcalbtn" type="button" class="btn btn-danger">
                        Create                    </button>
                </div>
                <div id="minical" class="minical">
                </div>
            </div>
            <div id="rightpanel">
                <div id="xgcalendarp">
                </div>
                <div class="bottom">
                    demo base on
                    <a target="_blank" href="../../github.com/xuanye/xgcalendar.html">
                        xgcalendar
                    </a>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="static/js/locales/en-us.js"></script>
        <script type="text/javascript" src="static/js/sea.js"></script>
        <script type="text/javascript" src="static/js/seaconfig.js">  </script>
        <script type="text/javascript">
            var loadingmsg = 'Loading data...';
            var sucessmsg = 'Success!';
            var processdatamsg = 'The request is being processed ...';
            seajs.use('page/index', function(app) {
                app.init();
            });
        </script>
    </body>


<!-- Mirrored from xgcal.sinaapp.com/demo2/?lang=en-us by HTTrack Website Copier/3.x [XR&CO'2013], Thu, 31 Oct 2013 13:35:00 GMT -->
</html>