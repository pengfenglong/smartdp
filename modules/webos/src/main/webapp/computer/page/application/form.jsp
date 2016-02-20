<%@ page contentType="text/html;charset=UTF-8"%>
<form id="smart-form" method="post" >
	<table>
	
		<tr>
			<td align="right">			
				应用名称:
			</td>
			<td>			
				<input type="text" 
					   style="width: 230px; border: 1px solid #ccc" 
					   name="name"
					   class="easyui-validatebox"
				/>
			 </td>
		</tr>
		<tr>
			<td align="right">			
				编码:
			</td>
			<td>			
				<input type="text" 
					   style="width: 230px; border: 1px solid #ccc" 
					   name="id"
					   class="easyui-validatebox"
				/>
			 </td>
		</tr>	
		<tr>
			<td align="right">			
				分类:
			</td>
			<td>	
				<select class="easyui-combobox" name="catalog" panelHeight="250" style="width: 100px">
					<option value="amusement" selected="true">娱乐</option>
					<option value="game">游戏</option>
					<option value="life">生活</option>
					<option value="music">音乐</option>
					<option value="news">新闻</option>
					<option value="office">办公</option>
					<option value="sns">社交</option>
					<option value="sys">系统</option>
					<option value="tools">工具</option>
					<option value="website">网站</option>
					<option value="subject">专题</option>
				</select>		
			 </td>
		</tr>
		<tr>
			<td align="right">			
				图片:
			</td>
			<td>			
				<input type="text" 
					   style="width: 230px; border: 1px solid #ccc" 
					   name="ico"
					   class="easyui-validatebox"
				/>
			 </td>
		</tr>	
		<tr>
			<td align="right">			
				地址:
			</td>
			<td>			
				<input type="text" 
					   style="width: 230px; border: 1px solid #ccc" 
					   name="href"
					   class="easyui-validatebox"
				/>
			 </td>
		</tr>	
		<tr>
			<td align="right">			
				打开模式:
			</td>
			<td>	
				<select class="easyui-combobox" name="mode" panelHeight="50" style="width: 100px">
					<option value="dialog" selected="true">弹出框</option>
					<option value="window">新窗口</option>
					
				</select>		
			 </td>
		</tr>
		<tr>
			<td align="right">			
				大小:
			</td>
			<td>			
				<input type="text" 
					   style="width: 230px; border: 1px solid #ccc" 
					   name="size"
					   class="easyui-validatebox"
				/>
			 </td>
		</tr>	
		<tr>
			<td align="right">			
				窗口高度:
			</td>
			<td>			
				<input type="text" 
					   style="width: 230px; border: 1px solid #ccc" 
					   name="height"
					   class="easyui-validatebox"
				/>
			 </td>
		</tr>
		<tr>
			<td align="right">			
				窗口宽度:
			</td>
			<td>			
				<input type="text" 
					   style="width: 230px; border: 1px solid #ccc" 
					   name="width"
					   class="easyui-validatebox"
				/>
			 </td>
		</tr>		
		<tr>
			<td align="right">			
				窗口最小高度:
			</td>
			<td>			
				<input type="text" 
					   style="width: 230px; border: 1px solid #ccc" 
					   name="minheight"
					   class="easyui-validatebox"
				/>
			 </td>
		</tr>
		<tr>
			<td align="right">			
				窗口最小宽度:
			</td>
			<td>			
				<input type="text" 
					   style="width: 230px; border: 1px solid #ccc" 
					   name="minwidth"
					   class="easyui-validatebox"
				/>
			 </td>
		</tr>										

		<tr>
			<td align="right">			
				窗口是否全屏:
			</td>
			<td>	
				<select class="easyui-combobox" name="fullscreen" panelHeight="50" style="width: 100px">
					<option value="0" selected="true">是</option>
					<option value="1">否</option>
				</select>		
			 </td>
		</tr>
		<tr>
			<td align="right">			
				是否需要登录:
			</td>
			<td>			
				<select class="easyui-combobox" name="verify" panelHeight="50" style="width: 100px">
					<option value="1" selected="selected">是</option>
					<option value="0">否</option>
				</select>
			 </td>
		</tr>
		<tr>
			<td align="right">			
				窗口背景:
			</td>
			<td>			
				<input type="text" 
					   style="width: 230px; border: 1px solid #ccc" 
					   name="bodybg"
					   class="easyui-validatebox"
				/>
			 </td>
		</tr>
		<tr>
			<td align="right">			
				margin:
			</td>
			<td>			
				<input type="text" 
					   style="width: 230px; border: 1px solid #ccc" 
					   name="margin"
					   class="easyui-validatebox"
				/>
			 </td>
		</tr>
	</table>
</form>

