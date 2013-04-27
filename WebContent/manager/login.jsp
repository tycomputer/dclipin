<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<base href='<%= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/" %>'>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
<link href='css/cs.css' rel='stylesheet' media='screen' type='text/css' />
<title>登陆</title>
<link type="text/css" rel="stylesheet" href="css/page.css">
<script>
	function check(){
		var u = document.getElementById("form.username");
		if (u.value == ""){
			alert ("请您输入用户名！");
			u.focus();
			return false;
		}
		var p = document.getElementById("form.password");
		if (p.value == ""){
			alert ("请您输入用户名！");
			p.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<br>
<br>
<br>
<s:form action="login">
	<table width="50%" class=overHidden border="1" cellpadding="3" align="center" cellspacing="0">
		<caption>欢迎您来到管理平台！</caption>
		<tr>
			<td>用户名</td>
			<td><input type="text" name="form.username" id="form.username" value="" /></td>
		</tr>
		<tr>
			<td>密&nbsp;&nbsp;码</td>
			<td><input type="password" name="form.password" id="form.password" value="" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="登陆" onclick="return check();"/></td>
		</tr>

	</table>
</s:form>
</body>
</html>