<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/tycomputer.tld" prefix="ty"%>
<html>
<head>
<ty:managerContextPath/>
<title>礼品目录、类别维护</title>
<link type="text/css" rel="stylesheet" href="css/page.css">
<script>
	function toConfirm(){
		//confirm("这将删除该礼品目录、类别，已添加的礼品也将删除，并且不能恢复，请您确认？");
		alert("对不起，暂时不提供删除的功能！！");
		return false;
	}
</script>
</head>
<body>
	<table width="100%" class=overHidden border="1" cellpadding="3" cellspacing="0">
		<caption>礼品目录、类别维护</caption>
		<tr>
			<th>目录编号</th>
			<th>目录名称</th>
			<th>目录简写</th>
			<th>排序</th>
			<th>目录标志</th>
			<th><a href="manager/standData!addDatacata.do">添加</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="login.do">返回</a></th>
		</tr>
		<s:iterator value="#request.datacata" status="stuts">
		<tr>
			<td><s:property value="cataId"/></td>
			<td><s:property value="cataName"/></td>
			<td><s:property value="cataSort"/></td>
			<td><s:property value="cataSn"/></td>
			<td>
				<s:if test="flag == 0">启用</s:if>
				<s:else>停用</s:else>
			</td>
			<td>
				<a href="manager/standData!editDatacata.do?form.cataId=<s:property value='cataId'/>">修改</a>
				<a onclick="return toConfirm();" href="manager/standData!delDatacata.do?form.cataId=<s:property value='cataId'/>">删除</a>
				<a href="manager/standData!toDetaList.do?form.cataId=<s:property value='cataId'/>">类别</a>
			</td>
		</tr>
		</s:iterator>
	</table>
</body>
</html>