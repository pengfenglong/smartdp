<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/framework/head-include.jsp"%>
<script type="text/javascript" src="scripts/shCore.js"></script>
<script type="text/javascript" src="scripts/shBrushJScript.js"></script>
<script type="text/javascript" src="/smartdp/framework/plugin/kindeditor/kindeditor-min.js"></script>
<link type="text/css" rel="stylesheet" href="styles/shCoreDefault.css"/>
<script type="text/javascript">SyntaxHighlighter.all();</script>
<sm:base type="codemirror"></sm:base>
<script type="text/javascript">
function test(){
	 var editor = CodeMirror.fromTextArea(document.getElementById("code"), {
		    lineNumbers: true,
		    styleActiveLine: true,
		    matchBrackets: true
		  });
	 editor.setOption("theme", "eclipse");
}
$(function(){
	test();
})
</script>
<sm:layoutcontainer>
	<sm:layout region="west" split="true" width="180px">
		<sm:tree id="fileTree" url="cms-fileTree-Template.action" onClick="function(node){
			$.ajax({
				url: contextPath+'/cms-getFileContent-Template.action?filepath='+node.id,
				dataType:'text',
				success:function(data){
					$('#code').html(data);
					test();
				}
			});
		}"></sm:tree>
	</sm:layout>
	<sm:layout region="center">
		<pre class="brush: js;"">
		function helloSyntaxHighlighter()
		{
			return "hi!";
		}
		</pre>
		<form><textarea id="code" name="code">
function findSequence(goal) {
  function find(start, history) {
    if (start == goal)
      return history;
    else if (start > goal)
      return null;
    else
      return find(start + 5, "(" + history + " + 5)") ||
             find(start * 3, "(" + history + " * 3)");
  }
  return find(1, "1");
}</textarea></form>
	</sm:layout>
</sm:layoutcontainer>
