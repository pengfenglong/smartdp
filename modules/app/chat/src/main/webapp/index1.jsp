<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<sm:base type="jquery"></sm:base>
<script type="text/javascript" src="js/chat.js"></script>
<link href="images/chat.css" rel="stylesheet" media="screen" type="text/css" />
<style type="text/css">

</style>
</head>
<body>
<div id="mid_top">
<div class="list">
    <table border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="td_user td_user_click">老猪</td>
        <td class="td_hide td_hide_click">X</td>
      </tr>
    </table>
  </div>
</div>
<div id="top">头部</div>
<div id="body">
  <div id="left">
    <div class="box">
      <h3 class="h3 h3_1">最近聊天(<span class="n_geshu_1"></span>)</h3>
      <ul class="ul ul_1">
        <li></li>
      </ul>
      <h3 class="h3 h3_2">我的好友(<span class="n_geshu_2"></span>)</h3>
      <ul class="ul ul_2">
        <li></li>
      </ul>
      <h3 class="h3 h3_2">未读消息(<span class="n_geshu_3"></span>)</h3>
      <ul class="ul ul_3">
      </ul>
      
    </div>
    <div class="box_f"></div>
  </div>
  <div id="right">
    <div class="right_box">
      <div id="right_top">
        <!--<p><img src="images/head.jpg" alt="头象" /></p>
        老猪--></div>
      <div id="right_mid"></div>
      <div id="right_foot"></div>
      <div class="blank"></div>
    </div>
    <div class="right_box_foot"></div>
  </div>
  <div id="mid">
    <div id="mid_con">
      <div class="my_show">
        <div class="con_box"><div class="dandan_liaotian"><img src="user_img/dandan1.jpg" alt="聊天界面" /></div></div>
      </div>
    </div>
    <div id="mid_mid"></div>
    <div id="mid_foot">
      <div id="mid_say">
        <textarea name="" cols="" rows="" id="texterea"></textarea>
      </div>
      <div id="mid_sand">发送</div>
      <div class="blank"></div>
    </div>
    <div class="mid_box"></div>
  </div>
</div>
</body>
</html>
