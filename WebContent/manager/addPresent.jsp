<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/tycomputer.tld" prefix="ty"%>
<%@ taglib uri="/WEB-INF/tld/FCKeditor.tld" prefix="FCK" %>
<%@page import="java.util.List"%>
<%@page import="com.tycomputer.manager.action.PresentForm"%>
<%@page import="com.tycomputer.common.util.Constants"%>
<html>
<head>
<ty:managerContextPath/>
<title>添加修改礼品</title>
<link type="text/css" rel="stylesheet" href="css/page.css">
<script type="text/javascript" src="js/prototype/prototype.js"></script>
<script type="text/javascript" src="js/scriptaculous/scriptaculous.js"></script>
<ty:validationTag formId="present"/>
<script type="text/javascript">
	function toList(){
		document.location.href = "manager/present.do";
		return false;
	}
	function submitForm(){
		if ($F("pname") == ""){
			alert("礼品名称不能不填吧？^_^，请您敲几个键把它填上，谢谢合作！");
			return false;
		}
		if ($F("price") == ""){
			alert("礼品的价格为空，还是填上价格吧！");
			return false;
		}
		if ($F("inPrice") == ""){
			alert("礼品的价格为空，还是填上价格吧！");
			return false;
		}
		if ($F("buyNum") == ""){
			alert("数量不能为空，请还是填上吧！");
			return false;
		}
		if ($F("typeId") == ""){
			alert("您还没有选择礼品目录，请您选择！");
			return false;
		}
		if ($F("detaId") == ""){
			alert("您还没有选择礼品类别，请您选择！");
			return false;
		}
		if ($F("litPic")==""){
			if (!confirm("礼品的小图片还没有上传，您是否继续？")){
				return false;
			}
		}
		if ($F("bigPic")==""){
			if (!confirm("礼品的大图片还没有上传，您是否继续？")){
				return false;
			}
		}
		$("presentForm").submit();
	}
	var deta = new Array();
	<%List<String[]> l = (List<String[]>)request.getAttribute("datadeta");
	for (int i=0; i<l.size(); i++){
		String[] str  = l.get(i);%>
	deta[<%=i%>] = new Array('<%=str[0]%>','<%=str[1]%>','<%=str[2]%>');
	<%}%>
	function typeChange(detaid){
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
		if (detaid != "" && (detaid != undefined)){
			$("form.typeId").value = detaid;
		}
	}
	function showIMg(type,id){
		if (type == '0'){
			$("litSpan").innerHTML = "<img width='<%= Constants.litWidth %>px' height='<%= Constants.litHeight %>px' src='showPhotos?i=" + id + "' />";
			$("litPic").value = id;
		} else {
			$("bigSpan").innerHTML = "<img width='<%= Constants.bigWidth %>px' height='<%= Constants.bigHeight %>px' src='showPhotos?i=" + id + "' />";
			$("bigPic").value = id;
		}
	}
	function showBothIMg(bigId,litId){
		$("litPic").value = litId;
		$("bigPic").value = bigId;
		$("litSpan").innerHTML = "<img width='<%= Constants.litWidth %>px' height='<%= Constants.litHeight %>px' src='showPhotos?i=" + litId + "' />";
		$("bigSpan").innerHTML = "<img width='<%= Constants.bigWidth %>px' height='<%= Constants.bigHeight %>px' src='showPhotos?i=" + bigId + "' />";
	}
	function init(){
		typeChange('<s:property value="#request.form.typeId"/>');
		<s:if test="form.bigpic != null">
		$("pictd").appendChild(Builder.node('img',{src:'images/p/<s:property value="#request.form.bigpic"/>',border:"0"}));
		</s:if>
	}
	function checkPhotoForm(photoFileId){
		if ($(checkPhotoForm).value == ""){
			alert ("请先选择图片，再点击上传！");
			return false;
		}
		return true;
	}
</script>
</head>
<body onload="init();">
	<table width="100%" class="overHidden" border="1" cellpadding="3" cellspacing="0">
		<caption>添加修改礼品</caption>
		
		<s:form action="present!savePresent" enctype ="multipart/form-data">
		<tr>
			<td>名称</td>
			<td>				
				<s:textfield name="form.pname" id="form.pname" cssClass="required" maxlength="20" size="25" title="在这里输入礼品的大名！" />
			</td>
		</tr>
		<tr>
			<td>编号</td>
			<td>
				<s:property value="#request.form.uid"/>&nbsp;&nbsp;&nbsp;(系统自动生成)<s:hidden name="form.uid"/>
			</td>
		</tr>
		<tr>
			<td>标志</td>
			<td>
				<s:select cssClass="required" list="#{0:'正常',1:'停产'}" name="form.flag" id="form.flag"></s:select>
			</td>
		</tr>
		<tr>
			<td>目录</td>
			<td>				
				<ty:sqlSelect name="form.cataId" emptyOption="true" sql="select t.cataId,t.cataName from Datacata t " onchange="typeChange();"/>
			</td>
		</tr>
		<tr>
			<td>类别</td>
			<td><select id="form.typeId" name="form.typeId"></select></td>
		</tr>
		<tr>
			<td>网站价格</td>
			<td><s:textfield name="form.price" cssClass="required validate-number" maxlength="5" size="10" /></td>
		</tr>
		<tr>
			<td>进货价</td>
			<td><s:textfield name="form.inprice" cssClass="validate-number" maxlength="5" size="10" /></td>
		</tr>
		<tr>
			<td>搜索字</td>
			<td><s:textfield name="form.words" maxlength="50" size="50" title="在这里输入搜索字！" />&nbsp;&nbsp;&nbsp;(多个关键字中间用空格分隔，名称、简介不用添加，系统会自动按它们进行搜索)</td>
		</tr>
		<tr>
			<td>排序序号</td>
			<td><s:textfield name="form.ordersn" cssClass="validate-integer" maxlength="5" size="10" title="在这里输入排序序号！" />排序只对同一目录或类别下的礼品有效，查询全部礼品时以添加时间倒排序。</td>
		</tr>
		<tr>
			<td>起订数量</td>
			<td><s:textfield name="form.buynum" cssClass="validate-integer" maxlength="5" size="10" title="在这里输入起订数量！" /></td>
		</tr>
		<tr>
			<td>水印颜色</td>
			<td>
				<s:select cssClass="required" list="#{'0e09f6':'自定义','00ffff':'Aqua','000000':'Black','ff00ff':'Fuchsia','800000':'Gray','008000':'Gree','00ff00':'Lime','800000':'Maroon','000080':'Navy','808000':'Olive','800080':'Purple','ff0000':'Red','c0c0c0':'Silver','008080':'Teal','ffffff':'White','ffff00':'Yellow','0000ff':'Blue'}" name="form.color" id="form.color"></s:select>
				<table width="100%" border="1">
					 <tr>
					 	<td style="border-top: #c0c0c0 1px solid; border-left: #c0c0c0 1px solid" align="center" width="60" bgcolor="#0e09f6" height="20"><font color="#000000" size="2">自定义</font></td>
			            <td style="border-top: #c0c0c0 1px solid; border-left: #c0c0c0 1px solid" align="center" width="60" bgcolor="#00ffff" height="20"><font color="#000000" size="2">Aqua</font></td>
			            <td style="border-top: #c0c0c0 1px solid; border-left: #c0c0c0 1px solid" align="center" width="60" bgcolor="#000000" height="20"><font color="#ffffff" size="2">Black</font></td>
			            <td style="border-top: #c0c0c0 1px solid; border-left: #c0c0c0 1px solid" align="center" width="60" bgcolor="#ff00ff" height="20"><font color="#ffffff" size="2">Fuchsia</font></td>
			            <td style="border-top: #c0c0c0 1px solid; border-left: #c0c0c0 1px solid" align="center" width="60" bgcolor="#800000" height="20"><font color="#ffffff" size="2">Gray</font></td>
			            <td style="border-top: #c0c0c0 1px solid; border-left: #c0c0c0 1px solid" align="center" width="60" bgcolor="#008000" height="20"><font color="#ffffff" size="2">Gree</font></td>
			            <td style="border-top: #c0c0c0 1px solid; border-left: #c0c0c0 1px solid" align="center" width="60" bgcolor="#00ff00" height="20"><font color="#000000" size="2">Lime</font></td>
			            <td style="border-top: #c0c0c0 1px solid; border-left: #c0c0c0 1px solid" align="center" width="60" bgcolor="#800000" height="20"><font color="#ffffff" size="2">Maroon</font></td>
			            <td style="border-top: #c0c0c0 1px solid; border-left: #c0c0c0 1px solid" align="center" width="60" bgcolor="#000080" height="20"><font color="#ffffff" size="2">Navy</font></td>
			            <td style="border-top: #c0c0c0 1px solid; border-left: #c0c0c0 1px solid" align="center" width="60" bgcolor="#808000" height="20"><font color="#ffffff" size="2">Olive</font></td>
			            <td style="border-top: #c0c0c0 1px solid; border-left: #c0c0c0 1px solid; border-left: #c0c0c0 1px solid" align="center" width="60" bgcolor="#800080" height="20"><font color="#ffffff" size="2">Purple</font></td>
			            <td style="border-top: #c0c0c0 1px solid; border-left: #c0c0c0 1px solid; border-bottom: #c0c0c0 1px solid" align="center" width="60" bgcolor="#ff0000" height="20"><font color="#ffffff" size="2">Red</font></td>
			            <td style="border-top: #c0c0c0 1px solid; border-left: #c0c0c0 1px solid; border-bottom: #c0c0c0 1px solid" align="center" width="60" bgcolor="#c0c0c0" height="20"><font color="#ffffff" size="2">Silver</font></td>
			            <td style="border-top: #c0c0c0 1px solid; border-left: #c0c0c0 1px solid; border-bottom: #c0c0c0 1px solid" align="center" width="60" bgcolor="#008080" height="20"><font color="#ffffff" size="2">Teal</font></td>
			            <td style="border-top: #c0c0c0 1px solid; border-left: #c0c0c0 1px solid; border-bottom: #c0c0c0 1px solid" align="center" width="60" bgcolor="#ffffff" height="20"><font color="#000000" size="2">White</font></td>
			            <td style="border-top: #c0c0c0 1px solid; border-left: #c0c0c0 1px solid; border-bottom: #c0c0c0 1px solid" align="center" width="60" bgcolor="#ffff00" height="20"><font color="#000000" size="2">Yellow</font></td>
			            <td style="border-top: #c0c0c0 1px solid; border-left: #c0c0c0 1px solid; border-bottom: #c0c0c0 1px solid" align="center" width="60" bgcolor="#0000ff" height="20"><font color="#ffffff" size="2">Blue</font></td>
			        </tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>图片</td>
			<td id="pictd"><s:file name="pic" id="pic" /></td>
		</tr>
		<tr>
			<td>简介</td>
			<td>
				<FCK:editor instanceName="form.description" height="400px" width="80%" toolbarSet="ty">
					<jsp:attribute name="value">
					<%
						PresentForm form = (PresentForm)request.getAttribute("form"); out.print(form.getDescription());
					%>
					</jsp:attribute>
				</FCK:editor>
			</td>
		</tr>
		<tr>
			<td>零售</td>
			<td>
				<s:select cssClass="required" list="#{0:'不零售',1:'零售'}" name="form.resale" id="form.resale"></s:select>
			</td>
		</tr>
		<tr>
			<td>单位</td>
			<td><s:textfield name="form.unit" maxlength="20" size="20" title="在这里输入单位！" /></td>
		</tr>
		<tr>
			<td>规格</td>
			<td><s:textfield name="form.spec" maxlength="50" size="50" title="在这里输入规格！" /></td>
		</tr>
		<tr>
			<td>大小</td>
			<td><s:textfield name="form.itsize" maxlength="50" size="50" title="在这里输入大小！" /></td>
		</tr>
		<tr>
			<td>包装大小</td>
			<td><s:textfield name="form.packsize" maxlength="50" size="50" title="在这里输入包装大小！" /></td>
		</tr>
		<tr>
			<td>重量</td>
			<td><s:textfield name="form.weight" maxlength="50" size="50" title="在这里输入重量！" /></td>
		</tr>
		<tr>
			<td>材料</td>
			<td><s:textfield name="form.material" maxlength="50" size="50" title="在这里输入材料！" /></td>
		</tr>
		<tr>
			<td>备注</td>
			<td><s:textarea name="form.note" rows="8" cols="60" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit"  value="保存">
				<input type="button" value="返回" onclick="return toList();"> 
			</td>
		</tr>
		</s:form>
	</table>
</body>
</html>