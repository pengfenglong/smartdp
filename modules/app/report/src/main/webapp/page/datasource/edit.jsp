<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/framework/thrid-party/jquery/plugin/jquery-validate-1.7/jquery.validate.min.js"></script>
<style type="text/css">
body { font-size: 62.5%; padding: 10px;}
label { display: inline-block; width: 100px; }
legend { padding: 0.5em; }
fieldset{padding: 10px;}
fieldset fieldset label { display: block; }
    .cmxform { width: 98%; }
    .cmxform p{
        margin:5px;
    }
    .cmxform .width200px{
        width:200px;
    }
.cmxform label.error{ width:250px; margin-left: 10px;}
.test {
    width: 96%;
    padding:10px;
    margin-top:10px;
    margin-left:0px;
    margin-right:10px;
    text-align: center;
    font-weight: bold;
    color: red; 
}
</style>

<script type="text/javascript">
    function test()
    {
        $.ajax({
            url: '${ctx}/rae/testDatasourceConfig.action',
            type : 'POST',
            cache: false,
            timeout : 30000,
            data : $('form').serialize(),
            dataType:'json',
            success: function(data) 
            {
                $("#test").show().html(data.info);
            },
            error : function(jqXHR, textStatus, errorThrown){
                $("#test").show().html('测试不通过，请输入正确配置。');
            }
        });
    }
    
    $(function(){
        $(".cmxform").validate();
        
        $(".button").button();
        
        $("#dsType").bind("change", function(){
            var value = $(this).val();
            var $dsProperties = $("#dsProperties");
            if (value == '1'){
                $dsProperties.val("jdbc.driverClassName=com.microsoft.jdbc.sqlserver.SQLServerDriver#jdbc.url=jdbc:sqlserver://localhost:1433;DatabaseName=sample#jdbc.username=sa#jdbc.password=");
                $("#propertyRegex").val("#");
            }else if (value == '2'){
                $dsProperties.val("jdbc.driverClassName=oracle.jdbc.driver.OracleDriver;jdbc.url=jdbc:oracle:thin:@localhost:1521:test;jdbc.username=admin;jdbc.password=;");
            }else if (value == '3'){
                $dsProperties.val("jdbc.driverClassName=com.mysql.jdbc.Driver;jdbc.url=jdbc:mysql://localhost:3306/test;jdbc.username=root;jdbc.password=;");
            }else{
                $dsProperties.val("");
            }
            $("#propertyRegex").val(";");
        });
    });
</script>
<title>数据源编辑</title>
</head>
<body>
    <form class="cmxform" action="${ctx}/rae/saveDatasourceConfig.action" method="POST">
        <fieldset class="ui-widget ui-widget-content ui-corner-all">
        <legend class="ui-widget ui-widget-header ui-corner-all">修改数据源</legend>
        <s:hidden name="datasourceConfig.id"></s:hidden>
        <p>
          <s:textfield name="datasourceConfig.name" label="名称" cssClass="required ui-widget-content width200px" maxlength="32"/>
        </p>
        
        <p><s:select label="类型" id="dsType"
                        list="#{1:'SQLSserver',2:'Oracle',3:'Mysql',4:'其它'}"
                        name="datasourceConfig.dsType" emptyOption="false" cssClass="required width200px" /></p>
                        
        <p>
                <s:textarea label="配置" name="datasourceConfig.dsProperties" id="dsProperties"
                    cols="20" rows="10" cssClass="required ui-widget-content width200px" />
            </p>
                        
        <p><s:select id="propertyRegex" label="分隔符" list="{';','#',','}"
                        name="datasourceConfig.propertyRegex" emptyOption="false" cssClass="required width200px"/> </select></p>
                        
         <p style="margin-top: 5px;"><input type="button" onclick="test()" value="测试" class="button"/>&nbsp;&nbsp; 
                    <input type="submit" value="提交 " class="button"/>&nbsp;&nbsp;
                    <input type="reset" value="重置" class="button"/></p>
                    
                    
        </fieldset>
    </form>
    <div id="test" class="test ui-widget-content" style="display:none"></div>
</body>
</html>

