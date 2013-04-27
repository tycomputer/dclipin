<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/tycomputer.tld" prefix="ty"%>
<html>
<head>
<ty:managerContextPath/>
<title>礼品目录、类别维护</title>
<link type="text/css" rel="stylesheet" href="css/page.css">
<script type="text/javascript" src="js/prototype/prototype.js"></script>
<script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>
<ty:validationTag formId="standData"/>
<script>
	function addDetail(){
		var tr = Builder.node('tr',
					[
						Builder.node('td',Builder.node('input',{type:"hidden",name:"form.typeId"})),
						Builder.node('td',Builder.node('input',{type:"text",maxLength:"2",name:"form.typeSort","class":"required"})),
						Builder.node('td',Builder.node('input',{type:"text",name:"form.sn","class":"required validate-integer"})),
						Builder.node('td',Builder.node('input',{type:"text",name:"form.typeName","class":"required"}))
					]
				);
		$("detail").appendChild(tr);	
	}
	function toTypeList(){
		document.location.href = "manager/standData.do";
		return false;
	}
	function checkData(){
		var arr = $$('input[name="form.typeSort"]');
		for (var i=0; i<arr.size(); i++){
			if (arr[i].value==""){
				alert ("对不起，简写不能为空！");
				return false;
			}
			for (var j=i+1; j<arr.size(); j++){
				if (arr[i].value==arr[j].value){
					alert ("对不起，同一目录下不能有两个  " + arr[i].value + "  简写，请改正！");
					return false;
				}
			}			
		}
		return true;
	}
</script>
</head>
<body>
	<s:form action="standData!saveDeta">
	<table width="100%" class=overHidden border="1" cellpadding="3" cellspacing="0">
		<caption>礼品类别维护</caption>
		<thead>
		<tr>
			<td colspan="3">
				礼品目录名称：<s:property value="#request.datacata.cataName"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="添加类别" onclick="return addDetail();"> 
				<input type="hidden" name="form.cataId" value="<s:property value="#request.datacata.cataId"/>" >
			</td>
		</tr>
		<tr>
			<th>类别编号</th>
			<th>类别简写</th>
			<th>类别序号</th>
			<th>类别名称</th>
		</tr>
		</thead>
		<tbody id="detail">
			<s:iterator value="#request.datacata.datatypes" status="stuts">
			<tr>
				<td><input type="hidden" name="form.typeId" value='<s:property value="typeId"/>' ><s:property value="typeId"/></td>
				<td><input type="hidden" name="form.typeSort" value="<s:property value="typeSort"/>" ><s:property value="typeSort"/></td>
				<td><input type="text" name="form.sn" class="required validate-integer" value="<s:property value="sn"/>" ></td>
				<td><input type="text" name="form.typeName" class="required" value="<s:property value="typeName"/>" ></td>
			</tr>		
			</s:iterator>
		</tbody>
		<tr>
			<td colspan="4" align="center">
				<input type="submit" value="保存" onclick="return checkData();">
				<input type="button" value="返回" onclick="return toTypeList();"> 
			</td>
		</tr>
	</table>
	</s:form>
</body>
</html>