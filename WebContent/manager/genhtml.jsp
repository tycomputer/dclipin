<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/tld/tycomputer.tld" prefix="ty"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<ty:managerContextPath />
<title>生成静态网站</title>
<link type="text/css" rel="stylesheet" href="css/page.css">
</head>
<body>
<h1>生成静态网站</h1>
<s:form action="genhtml!genhtml">
<s:submit value="生成"></s:submit>
</s:form>
</body>
</html>
