<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/tycomputer.tld" prefix="ty"%>
<%@page import="java.util.List"%>
<html>
<head>
<ty:managerContextPath/>
<title>新品、精品维护</title>
<link type="text/css" rel="stylesheet" href="css/page.css">
<script type="text/javascript" src="js/prototype/prototype.js"></script>
<script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>
<script>
	function toConfirm(type,index){
		if (!confirm("您真的要删除吗？")){
			return false;
		}
		var i = -1;
		var tbody ;
		if (type == "0"){
			tbody = $("new");
		} else {
			tbody = $("com");
		}
		var rows = tbody.rows;
		for (var i=0; i<rows.length; i++){
			if (rows[i].id == "tr" + index){
				tbody.deleteRow(i);
				return false;
			}
		}
		return false;
	}
	
	var presents = '<%= request.getAttribute("presents") %>'.evalJSON();
	

	function initCata(cataid,empty,catavalue){
		var count = $(cataid).options.length;
		for (var i=0; i<count; i++){
			$(cataid).options.remove(0);
		}
		count = 0;
		if (empty){
			var oOption = document.createElement("OPTION");
			$(cataid).options.add(oOption);
			oOption.innerText = "";
			oOption.value = "";
			count++;
		}
		for (var i=0; i<presents.length; i++){
			var cata = presents[i];
			var oOption = document.createElement("OPTION");
			$(cataid).options.add(oOption);
			oOption.innerText = cata.cataname;
			oOption.value = cata.cataid;
			count++;		
		}
		$(cataid).options.length = count;
		if (catavalue != "" && (catavalue != undefined)){
			$(cataid).value = catavalue;
		}
	}
	function cataChange(sel,empty,value){
		var index = sel.id.gsub("cataId","");
		var count = $("typeId"+index).options.length;
		for (var i=0; i<count; i++){
			$("typeId"+index).options.remove(0);
		}
		count = 0;
		if (empty){
			var oOption = document.createElement("OPTION");
			$("typeId"+index).options.add(oOption);
			oOption.innerText = "";
			oOption.value = "";
			count++;
		}
		if (sel.value != ""){
			for (var i=0; i<presents.length; i++){
				var cata = presents[i];
				if (cata.cataid == sel.value){
					for (var j=0; j<cata.types.length; j++){
						var type = cata.types[j];
						var oOption = document.createElement("OPTION");
						$("typeId"+index).options.add(oOption);
						oOption.innerText = type.typename;
						oOption.value = type.typeid;
						count++;
					}
						
				}					
			}
		}
		
		$("typeId"+index).options.length = count;
		if (value != "" && (value != undefined)){
			$("typeId"+index).value = value;
		}		
	}
	function typeChange(sel,empty,value){
		var index = sel.id.gsub("typeId","");
		var count = $("uids"+index).options.length;
		for (var i=0; i<count; i++){
			$("uids"+index).options.remove(0);
		}
		count = 0;
		if (empty){
			var oOption = document.createElement("OPTION");
			$("uids"+index).options.add(oOption);
			oOption.innerText = "";
			oOption.value = "";
			count++;
		}
		if (sel.value != ""){
			for (var i=0; i<presents.length; i++){
				var cata = presents[i];
				if (cata.cataid == $F("cataId"+index)){
					for (var j=0; j<cata.types.length; j++){
						var type = cata.types[j];
						if (type.typeid == sel.value){
							for (var k=0; k<type.presents.length; k++){
								var present = type.presents[k];
								var oOption = document.createElement("OPTION");
								$("uids"+index).options.add(oOption);
								oOption.innerText = present.presentname;
								oOption.value = present.presentid;
								count++;
							}
						}						
					}						
				}					
			}
		}		
		$("uids"+index).options.length = count;
		if (value != "" && (value != undefined)){
			$("uids"+index).value = value;
		}
	}
	var index = 0;
	function addRow(type){
		var trid = "tr"+index;		
		var tr = Builder.node('tr',{id:trid},
				[
					Builder.node('td',Builder.node('select',{name:"cataId",id:"cataId"+index,style:"width:200px",onchange:"cataChange(this,true,'');"})),
					Builder.node('td',Builder.node('select',{name:"typeId",id:"typeId"+index,style:"width:200px",onchange:"typeChange(this,true,'');"})),
					Builder.node('td',Builder.node('select',{name:"form.uids",id:"uids"+index,style:"width:200px"})),
					Builder.node('td',[
						Builder.node('input',{type:"hidden",name:"form.types",id:"types"+index,value:type}),
						Builder.node('input',{type:"text",name:"form.sns",id:"sns"+index,value:"",maxlength:"4",size:"5"})
					]),
					Builder.node('td',Builder.node('a',{href:"#",onclick:"return toConfirm('" + type + "','" + index + "');"},'删除'))
				]
			);
		
		if (type == "0"){
			$("new").appendChild(tr);	
		} else {
			$("com").appendChild(tr);	
		}
		initCata("cataId"+index,true,"");
		index++;
	}
	function initPresent(presentId,index){
		initCata(("cataId"+index),false,"");
		for (var i=0; i<presents.length; i++){
			var cata = presents[i];
			
				for (var j=0; j<cata.types.length; j++){
					var type = cata.types[j];
					
						for (var k=0; k<type.presents.length; k++){
							var present = type.presents[k];
							if (present.presentid == presentId){
								$("cataId"+index).value = cata.cataid;
								cataChange($("cataId"+index),false,type.typeid);
								typeChange($("typeId"+index),false,presentId);
								return;
							}
						}									
				}		
		}
	}
	function checkForm(){
		var presentIds = $$('select[name="uids"]');
		for (var i=0; i<presentIds.length; i++){
			if (presentIds[i].value == ""){
				alert ("请选择礼品名称");
				presentIds[i].focus();
				return false;
			}
		}
		var sns = $$('input[name="sns"]');
		for (var i=0; i<sns.length; i++){
			if (sns[i].value == ""){
				alert ("请填写序号");
				sns[i].focus();
				return false;
			}
			try{
				var a = parseInt(sns[i].value);
				if (isNaN(a) || a<=0){
					alert ("请序号必须为正整数！");
					sns[i].focus();
					return false;
				}
			}catch(e){
			}
		}
		return true;
	}
</script>
</head>
<body>
<s:form action="showedPresent!saveShowedPresent">
	<table width="100%" class=overHidden border="1" cellpadding="3" cellspacing="0">
		<caption>新品、精品维护</caption>
		<tr>
			<th>目录</th>
			<th>类别</th>
			<th>礼品</th>
			<th>排序</th>
			<th>操作</th>
		</tr>
		<tr>
			<td align="center" colspan="5">
				注意：<br>
				&nbsp;&nbsp;&nbsp;&nbsp;1.在保存时检查一下，不要在同一类中添加重复的礼品。<br>
 				&nbsp;&nbsp;&nbsp;&nbsp;2.程序中没有设置礼品个数，请注意添加的数量，以适应页面的显示<br>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="5" style="background-color : red">新礼品类</td>
		</tr>
		<tbody id="new">
		<s:iterator value="#request.newsList" status="stuts">
		<tr id="tr<s:property value="uuid"/>">
		
			<td><select name="cataId" id="cataId<s:property value="uuid"/>" onchange="typeChange(this,false);" style="width:200px"></select></td>
			<td><select name="typeId" id="typeId<s:property value="uuid"/>" onchange="detaChange(this,true);" style="width:200px"></select></td>
			<td><select name="form.uids" id="uids<s:property value="uuid"/>" style="width:200px"></select></td>
			<td>
				<input type="hidden" name="form.types" id="types<s:property value="uuid"/>" value="<s:property value="type"/>" />
				<input type="text" name="form.sns" id="sn<s:property value="uuid"/>" value="<s:property value="sn"/>" maxlength="4" size="5" title="排序序号，就是在网上显示的顺序！" />				
			</td>
			<td>
				<a onclick="return toConfirm('0','<s:property value="uuid"/>');" href="#">删除</a>
			</td>
		</tr>
		<script>initPresent('<s:property value="present.uid"/>','<s:property value="uuid"/>');</script>
		</s:iterator>
		</tbody>

		<tr><td align="center" colspan="5">&nbsp;</td></tr>
		<tr><td align="center" colspan="5">&nbsp;</td></tr>
		<tr>
			<td align="center" colspan="5" style="background-color : red">精礼品类</td>
		</tr>
		<tbody id="com">
		<s:iterator value="#request.compsList" status="stuts">
		<tr id="tr<s:property value="uuid"/>">
		
			<td><select name="cataId" id="cataId<s:property value="uuid"/>" onchange="typeChange(this,false);" style="width:200px"></select></td>
			<td><select name="typeId" id="typeId<s:property value="uuid"/>" onchange="detaChange(this,true);" style="width:200px"></select></td>
			<td><select name="form.uids" id="uids<s:property value="uuid"/>" style="width:200px"></select></td>
			<td>
				<input type="hidden" name="form.types" id="types<s:property value="uuid"/>" value="<s:property value="type"/>" />
				<input type="text" name="form.sns" id="sn<s:property value="uuid"/>" value="<s:property value="sn"/>" maxlength="4" size="5" title="排序序号，就是在网上显示的顺序！" />				
			</td>
			<td>
				<a onclick="return toConfirm('1','<s:property value="uuid"/>');" href="#">删除</a>
			</td>
		</tr>
		<script>initPresent('<s:property value="present.uid"/>','<s:property value="uuid"/>');</script>
		</s:iterator>
		</tbody>
		<tr>
			<td align="center" colspan="5">
				<input type="button" value="添加新品" onclick="addRow('0');">
				<input type="button" value="添加精品" onclick="addRow('1');">
				<input type="submit" value="保存" onclick="return checkForm();">
				<input type="button" value="返回" onclick="document.location.href='managerMenu.jsp';">
			</td>
		</tr>
	</table>
</s:form>
</body>
</html>