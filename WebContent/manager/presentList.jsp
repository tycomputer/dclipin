<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/tycomputer.tld" prefix="ty"%>
<%@page import="java.util.List"%>
<html>
<head>
<ty:managerContextPath/>
<title>礼品管理</title>
<link type="text/css" rel="stylesheet" href="css/page.css">
<script type="text/javascript" src="js/prototype/prototype.js"></script>
<script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>
<script>
	function toConfirm(){
		return confirm("这将删除该技术文章，请您确认？");
	}

		var _AJAX_PAGE_CELLFUNCS_DEFAULT = [ 
			function(data) { return data[0]; }, 
			function(data) { return data[1]; },
			function(data) { return data[2]; },
			function(data) { return data[3]; },
			function(data) { return data[4]; },
			function(data) { return (data[5]==0) ? "正常" : "停产"; },
			function(data) { return (data[6]==0) ? "不零售" : "零售"; },
			function(data) { return data[7]; },
			function(data) { return data[8]; },
			function(data) { return "<a href='manager/present!toEdit.do?form.uid=" + data[9] + "'>修改</a>&nbsp;&nbsp;<a onclick='return ask();' href='manager/present!toDele.do?form.uid=" + data[9] + "'>删除</a>" ; }
		];
		function ask(){
			return confirm("删除后不能恢复，您也可以将它设置为《停产》，请您确认？/");
		}
		var deta = new Array();
		<%
		List<String[]> l = (List<String[]>)request.getAttribute("datadeta");
		for (int i=0; i<l.size(); i++){
			String[] str  = l.get(i);
		%>
		deta[<%= i %>] = new Array('<%= str[0] %>','<%=  str[1] %>','<%=  str[2] %>');
		<%}%>
		function typeChange(typeid){
			var count = $("form.typeId").options.length;
			for (var i=0; i<count; i++){
				$("form.typeId").options.remove(0);
			}
			count = 0;
			var oOption = document.createElement("OPTION");
			$("form.typeId").options.add(oOption);
			oOption.innerText = "";
			oOption.value = "";
			count++;
			for (var i=0; i<deta.length; i++){
				if (deta[i][0]==$F("form.cataId")){
					var oOption = document.createElement("OPTION");
					$("form.typeId").options.add(oOption);
					oOption.innerText = deta[i][2];
					oOption.value = deta[i][1];
					count++;							
				}
			}
			$("form.typeId").options.length = count;
			if (typeid != "" && (typeid != undefined)){
				$("form.typeId").value = typeid;
			}
		}
		function init(){
			typeChange('<s:property value="#request.form.typeId"/>');
			<s:if test="#request.myuid != null">
				window.open('../presentDetail.do?i=<s:property value="#request.myuid"/>');
			</s:if>
		}
</script>
</head>
<body onload="init();">
	<table width="100%" class=overHidden border="1" cellpadding="3" cellspacing="0">
		<caption>礼品管理</caption>
	<tr>
	
		<td colspan="8">			
			<s:form action="present">
				<a href="manager/present!addPresent.do" >添加礼品</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="manager/managerMenu.jsp">返回</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				目录：<ty:sqlSelect name="form.cataId" emptyOption="true" sql="select t.cataId,t.cataName from Datacata t " onchange="typeChange();"/>
				类别：<select name="form.typeId" id="form.typeId" ></select>
				名称：<s:textfield name="form.pname" id="form.pname" />
				排序：<s:select cssClass="required" list="#{'addData desc':'添加时间','datatype.typeId':'目录类别'}" name="form.order" id="form.order" />
				<input type="submit" value="搜索" />
				
			</s:form>
		</td>
	</tr>		
		<ty:ajaxPage sql="sql"  size="20" 
					colwidths="50,50,50,50,40,20,20,70,30,60" genCellFuncs="false"
					heads="目录,类别,名称,网站价格,进货价格,标志,零售,关键字,排序号,操作" />
	</table>
</body>
</html>