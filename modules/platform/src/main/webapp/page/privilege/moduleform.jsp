<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sm" uri="/smartdp-tag"%>
<script type="text/javascript">
$(function(){
   $.crud.init({
   	onLaodEdit:function(row){
   		var operateIds = [];
   		for(var i=0,n=row.moduleOperates.length;i<n;i++){
   			operateIds.push(row.moduleOperates[i].operateId);
   		}
   	    $.ajax({
	   	    url: contextPath + '/platform-listAll-ModuleOperate.action' ,
	   	    success: function(data){
	   	    	for(var j=0,m=data.length;j<m;j++){
	   	    		var opt = '<option value="' + data[j].operateId + '"';
	   	    		if($.inArray(data[j].operateId, operateIds) >= 0){
	   	    			opt += ' SELECTED '
	   	    		}
	   	    		 opt = opt + '>' + data[j].operateName + '</option>';
	   	    		$('#moduleOperates').append(opt);
	   	    	}
	       	   $("#moduleOperates").multiselect2side({
	       		    selectedPosition: 'right',
	       		    moveOptions: false,
	       			labelsx: '待选区',
	       			labeldx: '已选区'
	       	   });
	   	    } 
	      });
   	}
   });
});

</script>
<sm:validform formid="smart-form" layout="div" dialog="false" action="platform-configOpetate-Menu.action">
<div style="width: 700px;padding: 10px;">
      <select name="moduleOperates" id='moduleOperates' multiple='multiple' size='28'  style="width: 300px;">
      </select>
</div>
</sm:validform>