(function(window, document, $){
    $(document).ready(function(){
    	
       var logger = new Log4js.Logger("main");
       logger.setLevel(Log4js.Level.ALL);
       logger.addAppender(new Log4js.ConsoleAppender(logger));
 	   logger.debug("您正在使用的是ACP的JAVASCRIPT日志系统。");
 	   
 	   window.logger = log = logger;
 	  /**
 	   var ajaxLog = new Log4js.getLogger("ajaxTest");
	   ajaxLog.setLevel(Log4js.Level.ALL);
	   var ajaxAppender = new Log4js.AjaxAppender(ajaxLog, "./log4j.jsp");
	   ajaxAppender.setThreshold(5);
	   ajaxLog.addAppender(ajaxAppender);

	   var windowEventLog = new Log4js.getLogger("windowEventTest");
	   windowEventLog.setLevel(Log4js.Level.ALL);
	   windowEventLog.addAppender(new Log4js.WindowsEventAppender(windowEventLog));

	   
	   
	   var consoleLog = new Log4js.Logger("consoleTest");
	   consoleLog.setLevel(Log4js.Level.ALL);
	   consoleLog.addAppender(new Log4js.ConsoleAppender(consoleLog, true));
	   
	   var windowLog = new Log4js.getLogger("windowTest"); 
	   windowLog.setLevel(Log4js.Level.ALL);
	   windowLog.addAppender(new Log4js.ConsoleAppender(windowLog)); //to windowLog to seperate windowLog window
	   
	   var alertLog = new Log4js.Logger("alertTest"); 
	   alertLog.setLevel(Log4js.Level.ALL);
	   alertLog.addAppender(new Log4js.JSAlertAppender(alertLog));

	   var operaLog = new Log4js.Logger("operaTest"); 
	   operaLog.setLevel(Log4js.Level.ALL);
	   var operaAppender = new Log4js.OperaJSConsoleAppender(operaLog);
	   operaLog.addAppender(operaAppender);
	   
	   var mozLog = new Log4js.getLogger("mozillaTest"); 
	   mozLog.setLevel(Log4js.Level.ALL);
	   var appender = new Log4js.MozJSConsoleAppender(mozLog);
	  //TODO appender.setLayout(new PatternLayout("%p-> %m"));
	   mozLog.addAppender(appender);
	   **/
    });
})(window, document, jQuery);