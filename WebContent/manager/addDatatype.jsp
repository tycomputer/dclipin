<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/tycomputer.tld" prefix="ty"%>
<html>
<head>
<ty:managerContextPath/>
<script type="text/javascript" src="js/prototype/prototype.js"></script>
<script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>
<ty:validationTag formId="standData"/>
<title>礼品目录、类别维护</title>
<link type="text/css" rel="stylesheet" href="css/page.css">
<script type="text/javascript">
	function toList(){
		document.location.href = "manager/standData.do";
		return false;
	}
</script>

</head>
<body>
	<s:form action="standData!saveDatacata">
	<table width="100%" class=overHidden border="1" cellpadding="3" cellspacing="0">
		<caption>添加礼品目录</caption>
		<tr>
			<td>目录编号</td>
			<td>
				<s:hidden name="form.cataId" id="form.cataId" ></s:hidden>
				<s:property value="form.cataId"/>
			</td>
		</tr>
		<tr>
			<td>目录名称</td>
			<td>
				<s:textfield name="form.cataName" cssClass="required" id="form.cataName"  maxlength="20" size="15" />
			</td>
		</tr>
		<tr>
			<td>目录简写</td>
			<td>
				<s:if test="form.cataShort == null">
					<s:textfield name="form.cataShort" cssClass="required" id="form.cataShort"  maxlength="2" size="4" />
				</s:if>
				<s:else>
					<s:textfield name="form.cataShort" readonly="true" cssClass="required" id="form.cataShort"  maxlength="2" size="4" />
				</s:else>
			</td>
		</tr>
		<tr>
			<td>目录序号</td>
			<td>
				<s:textfield name="form.cataSn" cssClass="required validate-number" id="form.cataSn"  />
			</td>
		</tr>
		<tr>
			<td>类别状态</td>
			<td>
				<s:select cssClass="required" list="#{0:'启用',1:'停用'}" name="form.flag" id="form.flag"></s:select>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="保存">
				<input type="button" value="返回" onclick="return toList();"> 
			</td>
		</tr>
	</table>
	</s:form>
</body>
</html>