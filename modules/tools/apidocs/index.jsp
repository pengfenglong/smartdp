<%@ page contentType="text/html;charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<head>
<title>在线API文档</title>
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/basicc67c.css?20120912" type="text/css" />
<script type="text/javascript" src='js/jquery/jquery-1.7.2.js'></script>
<script type="text/javascript"
	src='js/jquery/jquery.cookies.2.2.0.min.js'></script>
<link rel="stylesheet" href='js/bootstrap/css/bootstrap.min.css' />
<script src='js/bootstrap/js/bootstrap.min.js'></script>
<style>
.tool_content {
	width: 950px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 8px;
	margin-bottom: 25px;
}

.alpha_index {
	width: 15px;
}

.SourcesOfAlpha {
	line-height: 22px;
	margin: 20px 0 20px 5px;
	border-bottom: 1px solid #eee;
	padding: 10px 0 5px 0;
}

.SourcesOfAlpha strong {
	color: #A00;
	font-size: 30pt;
	float: left;
	margin-right: 50px;
}

.SourcesOfAlpha li {
	display: inline-block;
	margin: 0 10px;
}

.SourcesOfAlpha li a {
	font-size: 12pt;
}

.SourcesOfAlpha p {
	font-size: 12pt;
}

.CurrentAlpha {
	border: 2px solid #0A0;
	background-color: #eee;
}

.doc_href {
	vertical-align: middle;
}

#common_doc li {
	width: 110px;
	height: 100px;
	display: inline-block;
}
</style>
<style>
	/*share_start*/
	#share {position:fixed;_position:absolute;_top:expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight)-34+"px");bottom:34px;left:95%;width:30px;zoom:1;}
	#share a{background-image:url(img/share.png); background-repeat:no-repeat; display:block; width:30px; height:30px; margin-bottom:2px; overflow:hidden; text-indent:-999px;-webkit-transition: all 0.2s ease-in-out;-moz-transition: all 0.2s ease-in-out;-o-transition: all 0.2s ease-in-out;-ms-transition: all 0.2s ease-in-out;transition: all 0.2s ease-in-out;}
	#share a{}
	#share .sina {background-position:0 0; position:absolute; bottom:32px;}
	#share a.sina:hover { background-position:-30px 0;}
	#share .tencent { background-position:0 -30px; position:absolute; bottom:0;}
	#share a.tencent:hover { background-position:-30px -30px;}
	#share a#totop{background-position:0 -120px;position:absolute;bottom:64px;cursor:pointer;}
	#share a#totop:hover {background-position:-30px -120px;}
	/*share_end*/
</style>
<script>
//scrolltotop
$(function(){
        //首先将#back-to-top隐藏
        $("#totop").hide();
        //当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失
        $(function () {
            $(window).scroll(function(){
                if ($(window).scrollTop()>100){
                    $("#totop").fadeIn();
                }
                else
                {
                    $("#totop").fadeOut();
                }
            });
            //当点击跳转链接后，回到页面顶部位置
            $("#totop").click(function(){
                $('body,html').animate({scrollTop:0},200);
                return false;
            });
        });
    }); 
</script>
</head>
<body>
	<div id="share">
		<a id="totop" title="">返回顶部</a> <a href="http://sc.chinaz.com/"
			target="_blank" class="sina">关注站长素材新浪微博</a> <a
			href="http://sc.chinaz.com/" target="_blank" class="tencent">关注站长素材腾讯微博</a>
	</div>
	<div class="wrapper">
		<div id="mainContent" class="tool_content">
			<form class="form-search" style="float: right; margin: 0 0 0 0;"
				action="http://www.ostools.net/docsearch">
				<div class="input-prepend">
					<span class="add-on">apidocs搜索</span>
					<input type="text" style="height: 27px; margin: 5px;" class="input-xlarge search-query" name="q" placeholder="在此输入类或接口名" id="q_search" />
				</div>
				<button type="submit" class="btn">搜索</button>
			</form>
			<h1>常用API文档索引</h1>
			<div style="width: 950px;">
				<div id="common_doc" style="height: 110px">
					<ul style="margin: 15px 0 15px 0;">
						<li><a class="thumbnail"
							href="apidocs/apidoca434.html?api=jdk_7u4"><img
								src="img/logo/java.png" alt="JDK 7" /></img></a></li>
						<li><a class="thumbnail"
							href="apidocs/apidoca8c7.html?api=android/reference"><img
								src="img/logo/android.png" alt="Android"></img></a></li>
						<li><a class="thumbnail"
							href="apidocs/apidoc337b.html?api=mysql-5.1-zh"><img
								src="img/logo/mysql.png" alt="MySql中文"></img></a></li>
						<li><a class="thumbnail"
							href="apidocs/apidoc7dde.html?api=struts-2.3.4"><img
								src="img/logo/struts.png" alt="Struts"></img></a></li>
						<li><a class="thumbnail"
							href="apidocs/apidoca31f.html?api=jquery"><img
								src="img/logo/jquery_doc.png" alt="JQuery"></img></a></li>
						<li><a class="thumbnail"
							href="apidocs/apidocaab9.html?api=nginx-zh"><img
								src="img/logo/nginx.png" alt="nginx"></img></a></li>
						<li><a class="thumbnail"
							href="apidocs/apidoc021e.html?api=gtk%2B"><img
								src="img/logo/gtk.png" alt="GTK+"></img></a></li>
					</ul>
				</div>
				<p>所有API文档（按字母排序）：</p>
				<div id="AlphabetIndex" class="btn-toolbar">
					<div class="btn-group">
						<a href="#A" class="alpha_index btn">A</a> <a href="#B"
							class="alpha_index btn">B</a> <a href="#C"
							class="alpha_index btn">C</a> <a href="#D"
							class="alpha_index btn">D</a> <a href="#E"
							class="alpha_index btn">E</a> <a href="#F"
							class="alpha_index btn">F</a> <a href="#G"
							class="alpha_index btn">G</a> <a href="#H"
							class="alpha_index btn">H</a> <a href="#I"
							class="alpha_index btn">I</a> <a href="#J"
							class="alpha_index btn">J</a> <a href="#K"
							class="alpha_index btn">K</a> <a href="#L"
							class="alpha_index btn">L</a> <a href="#M"
							class="alpha_index btn">M</a> <a href="#N"
							class="alpha_index btn">N</a> <a href="#O"
							class="alpha_index btn">O</a> <a href="#P"
							class="alpha_index btn">P</a> <a href="#Q"
							class="alpha_index btn">Q</a> <a href="#R"
							class="alpha_index btn">R</a> <a href="#S"
							class="alpha_index btn">S</a> <a href="#T"
							class="alpha_index btn">T</a> <a href="#U"
							class="alpha_index btn">U</a> <a href="#V"
							class="alpha_index btn">V</a> <a href="#W"
							class="alpha_index btn">W</a> <a href="#X"
							class="alpha_index btn">X</a> <a href="#Y"
							class="alpha_index btn">Y</a> <a href="#Z"
							class="alpha_index btn">Z</a>
					</div>
					<div class="clear"></div>
				</div>
				<a name="A"></a>
				<div class="SourcesOfAlpha" name="A">
					<strong>A</strong>
					<ul>
						<li><a href="#">ant</a></li>
						<li><a href="#">axis2</a></li>
					</ul>
				</div>
				<a name="B"></a>
				<div class="SourcesOfAlpha" name="B">
					<strong>B</strong>
					<ul>
						<li><a href="#"> bootstrap</a></li>
					</ul>
				</div>
				<a name="C"></a>
				<div class="SourcesOfAlpha" name="C">
					<strong>C</strong>
					<ul>
						<li><a href="#">css</a></li>
					</ul>
				</div>
				<a name="D"></a>
				<div class="SourcesOfAlpha" name="D">
					<strong>D</strong>
					<ul>
						<li><a href="#">dom4j</a></li>
					</ul>
				</div>
				<a name="E"></a>
				<div class="SourcesOfAlpha" name="E">
					<strong>E</strong>
					<ul>
						<li><a href="#">extjs</a></li>
					</ul>
				</div>
				<a name="F"></a>
				<div class="SourcesOfAlpha" name="F">
					<strong>F</strong>
					<ul>
						<li><a href="#">freemarker</a></li>
					</ul>
				</div>
				<a name="G"></a>
				<div class="SourcesOfAlpha" name="G">
					<strong>G</strong>
					<ul>
						<li><a href="#">gson</a></li>
					</ul>
				</div>
				<a name="H"></a>
				<div class="SourcesOfAlpha" name="H">
					<strong>H</strong>
					<ul>
						<li><a href="apidocs/highchart/Highcharts/index.htm">Highcharts</a></li>
						<li><a href="apidocs/highchart/Highstock/index.htm">Highstock</a></li>
						<li><a href="apidocs/hibernate.pdf">hibernate</a></li>
						<li><a href="#">hadoop</a></li>
						<li><a href="#">httpclient</a></li>
						<li><a href="#">HTMLParser</a></li>
					</ul>
				</div>
				<a name="I"></a>
				<div class="SourcesOfAlpha" name="I">
					<strong>I</strong>
					<ul>
						<li><a href="#">IKAnalyzer</a></li>
					</ul>
				</div>
				<a name="J"></a>
				<div class="SourcesOfAlpha" name="J">
					<strong>J</strong>
					<ul>
						<li><a href="apidocs/jquery/index.html">jquery</a></li>
						<li><a href="apidocs/jquery-easyui/doc/index.html">jquery-easyui</a></li>
						<li><a href="apidocs/jquery-ui/development-bundle/demos/index.html">jquery-ui</a></li>
						<li><a href="apidocs/jquery-mobile/index.html">jquery-mobile</a></li>
						<li><a href="apidocs/jqzoom_ev-2.3/demos/demo_standard.html">jqzoom</a></li>
					</ul>
				</div>
				<a name="K"></a>
				<div class="SourcesOfAlpha" name="K">
					<strong>K</strong>
					<ul>
					</ul>
				</div>
				<a name="L"></a>
				<div class="SourcesOfAlpha" name="L">
					<strong>L</strong>
					<ul>
						<li><a href="#">log4j</a></li>
						<li><a href="#">lucene</a></li>
					</ul>
				</div>
				<a name="M"></a>
				<div class="SourcesOfAlpha" name="M">
					<strong>M</strong>
					<ul>
						<li><a href="#">maven</a></li>
						<li><a href="#">mina</a></li>
					</ul>
				</div>
				<a name="N"></a>
				<div class="SourcesOfAlpha" name="N">
					<strong>N</strong>
					<ul>
						<li><a href="#">node.js</a></li>
						<li><a href="#">nutz</a></li>
					</ul>
				</div>
				<a name="O"></a>
				<div class="SourcesOfAlpha" name="O">
					<strong>O</strong>
					<ul>
						<li><a href="#">openfire</a></li>
					</ul>
				</div>
				<a name="P"></a>
				<div class="SourcesOfAlpha" name="P">
					<strong>P</strong>
					<ul>
						<li><a href="#">php</a></li>
					</ul>
				</div>
				<a name="Q"></a>
				<div class="SourcesOfAlpha" name="Q">
					<strong>Q</strong>
					<ul>
					</ul>
				</div>
				<a name="R"></a>
				<div class="SourcesOfAlpha" name="R">
					<strong>R</strong>
					<ul>
					</ul>
				</div>
				<a name="S"></a>
				<div class="SourcesOfAlpha" name="S">
					<strong>S</strong>
					<ul>
						<li><a href="apidocs/spring.pdf">spring</a></li>
						<li><a href="#">spring-security</a></li>
						<li><a href="apidocs/struts.1.x.pdf">struts1.x</a></li>
						<li><a href="#">struts2.x</a></li>
					</ul>
				</div>
				<a name="T"></a>
				<div class="SourcesOfAlpha" name="T">
					<strong>T</strong>
					<ul>
						<li><a href="#">tomcat</a></li>
					</ul>
				</div>
				<a name="U"></a>
				<div class="SourcesOfAlpha" name="U">
					<strong>U</strong>
					<ul>
						<li><a href="apidocs/uploadify/index.html">uploadify</a></li>
						<li><a href="#">uml</a></li>
					</ul>
				</div>
				<a name="V"></a>
				<div class="SourcesOfAlpha" name="V">
					<strong>V</strong>
					<ul>
						<li><a href="#">velocity </a></li>
					</ul>
				</div>
				<a name="W"></a>
				<div class="SourcesOfAlpha" name="W">
					<strong>W</strong>
					<ul>
					</ul>
				</div>
				<a name="X"></a>
				<div class="SourcesOfAlpha" name="X">
					<strong>X</strong>
					<ul>
					</ul>
				</div>
				<a name="Y"></a>
				<div class="SourcesOfAlpha" name="Y">
					<strong>Y</strong>
					<ul>
						<li><a href="#">yui</a></li>
					</ul>
				</div>
				<a name="Z"></a>
				<div class="SourcesOfAlpha" name="Z">
					<strong>Z</strong>
					<ul>
						<li><a href="#">ztree</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	var s_flag = true;
	$(".go_search .gsc-input input").focus(function() {
		s_flag = false;
	});
	$(".go_search .gsc-input input").blur(function() {
		s_flag = true;
	});
	$("#q_search").focus(function() {
		s_flag = false;
	});
	$("#q_search").blur(function() {
		s_flag = true;
	});
	$("#mainContent").keyup(function(event) {
		if (s_flag && (event.which >= 65 && event.which <= 90)) {
			var alpha = String.fromCharCode(event.which).toUpperCase();
			window.location.href = "#" + alpha;
			$("div").removeClass("CurrentAlpha");
			$(".SourcesOfAlpha[name=" + alpha + "]").addClass("CurrentAlpha");
		}
	});
	$(document).ready(function() {
		$(".alpha_index").click(function() {
			$("div").removeClass("CurrentAlpha");
			var alpha = $(this).html();
			$(".SourcesOfAlpha[name=" + alpha + "]").addClass("CurrentAlpha");
		});
	});
</script>
</html>