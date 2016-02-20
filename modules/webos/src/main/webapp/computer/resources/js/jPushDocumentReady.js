/*
 * jPushLanguage.js
 * name:xiaojia 小嘉
 * email:iatt@qq.com
 * qq:27314265d0
 * time:2012.4.10 16:27
*/

$(document).ready(

	function () {

		jPush.loadingData.templateData.all = 5;

		jPushLanguage.load();

		jPush.loadingSuccess('templateData');

		jPushApplication.getAppList();

		jPush.loadingSuccess('templateData');

		jPushInit.init();

		jQuery.jLayer.getTemplate();

		jPush.loadingSuccess('templateData');

		jPushApplication.contextmenu();

		jPush.loadingSuccess('templateData');

		jPushInit.ie6PNG();

	}

);