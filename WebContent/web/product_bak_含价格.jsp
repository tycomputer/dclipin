<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/tld/tycomputer.tld" prefix="ty"%>
<%@page import="com.tycomputer.common.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<ty:dclipinContextPath />
<title><%= Constants.siteTitle %></title>
<script>var psort='<%= com.tycomputer.common.web.CacheData.CATATYPE_JSON %>'.evalJSON();var tid,did,pid,tstr,dstr,pstr,timer,extime;var initFlag=true;document.observe('dom:loaded',function(){psort.each(function (p){var a=Builder.node('a',{id:"t"+p.di,href:"#",onclick:"return loadt('"+p.di+"');"},p.dn);$("spanf").appendChild(a);$("spanf").appendChild(document.createTextNode('\u00A0\u00A0\u00A0\u00A0\u00A0'));});<% String tid = request.getParameter("t"); String did=request.getParameter("d");if (tid==null){tid="all";}%>loadt('<%= tid %>');<% if (did!=null){out.print("setDid('"+did+"');");}%>setPid(0);initFlag=false;showPresent();});function loadt(id){if(id!=tid){$("spant").innerHTML="礼品小类：";$("t"+id).addClassName("psort");if((id!=null)&&(id!=undefined)){psort.each(function(p){if(p.di==id){var al=Builder.node('a',{id:"dall",name:"da",href:"#",onclick:"return setDid('all');"},"不限");$("spant").appendChild(al);$("dall").addClassName("psort");did="all";dstr="不限";$("spant").appendChild(document.createTextNode('\u00A0\u00A0\u00A0\u00A0\u00A0'));p.ts.each(function(t){var a=Builder.node('a',{href:"#",name:"da",id:"d"+t.ti,onclick:"return setDid('"+t.ti+"');"},t.tn);$("spant").appendChild(a);$("spant").appendChild(document.createTextNode('\u00A0\u00A0\u00A0\u00A0\u00A0'))})}else{$("t"+p.di).removeClassName("psort")}});if(id=="all"){$("tt").hide();did="all";dstr="不限";$("spant").innerHTML=""}else{$("tall").removeClassName("psort");$("tt").show()}}else{$("tt").hide()}tid=id;tstr=$("t"+id).innerText;if(!initFlag)setTimer()}return false}function setDid(id){if(did!=id){did=id;$$('a[name="da"]').each(function(aa){if(aa.id=="d"+id){aa.addClassName("psort");dstr=aa.innerText}else{aa.removeClassName("psort")}})}if(!initFlag)setTimer();return false}function setPid(id){if(pid!=id){pid=id;for(var i=0;i<10;i++){if(i==id){$("p"+i).addClassName("psort");pstr=$("p"+i).innerText}else{$("p"+i).removeClassName("psort")}}}if(!initFlag)setTimer();return false}function setTimer(){if(timer!=null){timer.stop();timer=null}extime=2;timer=new PeriodicalExecuter(function(pe){if(extime==0){showPresent();pe.stop()}else{$("timeButton").value=extime+" 秒后自动搜索，点击立即搜索";extime--}},1);return false}function goPage(n){var page;if((n!=null)&&(n!=undefined)){page=n}else{page=$F("sel")}new Ajax.Request("sp",{parameters:{"page":page},onCreate:function(t){$("_nav").innerHTML="信息加载中，请稍等...";$("_btnnav").innerHTML="信息加载中，请稍等..."},onSuccess:function(t){var json=t.responseText.evalJSON();$("spDiv").innerHTML="";$("_nav").innerHTML="";$("_btnnav").innerHTML="";show(json.data);$("_nav").innerHTML=json.nav;$("_btnnav").innerHTML=json.nav}});return false}function show(data){for(var i=0;i<data.size();i++){var p=data[i];var dl=Builder.node("dl",[Builder.node("dt",[Builder.node("a",{href:"presentDetail.do?i="+p.i,"class":'preview',target:'_blank'},[Builder.node("img",{title:p.n,width:'<%= Constants.litWidth %>px',height:'<%= Constants.litHeight %>px',src:"images/p/"+p.l})])]),Builder.node("dd",[Builder.node("a",{href:"presentDetail.do?i="+p.i},[p.n,Builder.node("br"),p.i])])]);$("spDiv").appendChild(dl)}}function showPresent(){$("timeButton").value="搜索";if(timer!=null){timer.stop();timer=null}new Ajax.Request("sp",{parameters:{t:tid,d:did,p:pid,w:$F(w)},onCreate:function(t){$("_nav").innerHTML="信息加载中，请稍等...";$("_btnnav").innerHTML="信息加载中，请稍等..."},onSuccess:function(t){var json=t.responseText.evalJSON();$("spDiv").innerHTML="";show(json.data);$("tstr").innerHTML=tstr;$("dstr").innerHTML=dstr;$("pstr").innerHTML=pstr;$("_nav").innerHTML=json.nav;$("_btnnav").innerHTML=json.nav}});return false}</script>
</head>
<body class="articlelist">
<jsp:include page="head.jsp" />
<table border="0" align="center" cellpadding="0" cellspacing="0" class="listWidth" style="border-bottom: 1px solid #9c9c9c">
	<tr id="tf">
		<td height="21" class="a STYLE39">
			<span class="a STYLE55" id="spanf">
				礼品分类：<a href="#" class="psort" onclick="return loadt('all');" id="tall" >全部</a>&nbsp;&nbsp;
			</span><br />
		</td>
	</tr>
	<tr id="tt">
		<td height="21" class="a STYLE39">
			<span class="a STYLE55" id="spant"></span><br />
		</td>
	</tr>
	<tr id="tp">
		<td height="21" class="a STYLE39">
			<span class="a STYLE55">
				价格区间：<a href="#" class="psort" onclick="return setPid(0);" id="p0">不限</a>&nbsp;&nbsp;
				<a href="#" onclick="return setPid(1);" id="p1">0-5元</a>&nbsp;&nbsp;
				<a href="#" onclick="return setPid(2);" id="p2">5-10元</a>&nbsp;&nbsp;
				<a href="#" onclick="return setPid(3);" id="p3">10-20元</a>&nbsp;&nbsp;
				<a href="#" onclick="return setPid(4);" id="p4">20-30元</a>&nbsp;&nbsp;
				<a href="#" onclick="return setPid(5);" id="p5">30-50元</a>&nbsp;&nbsp;
				<a href="#" onclick="return setPid(6);" id="p6">50-100元</a>&nbsp;&nbsp;
				<a href="#" onclick="return setPid(7);" id="p7">100-200元</a>&nbsp;&nbsp;
				<a href="#" onclick="return setPid(8);" id="p8">200-500元</a>&nbsp;&nbsp;
				<a href="#" onclick="return setPid(9);" id="p9">500元以上</a>
			</span><br />
		</td>
	</tr>
	<tr id="tp">
		<td height="21" class="a STYLE39">
			<span class="a STYLE55">
				关&nbsp;键&nbsp;字：<input type="text" name="w" id="w" onchange="return setTimer();" />
				<input type="button" id="timeButton" value="查看筛选结果" onclick="return showPresent();"/> 
			</span>
		</td>
	</tr>
	
</table>

<table class="tylistWidth" border="0" align="center" cellpadding="0" cellspacing="0" class="listWidth" style="border-bottom: 1px solid #9c9c9c">
	<tr>
		<td height="21" class="a STYLE39">
			<table width="100%">
				<tr>
					<td width="50%"><span class="a STYLE55">以下礼品的检索条件&nbsp;&nbsp;分类:<span id="tstr" class="spanNum"></span>&nbsp;&nbsp;小类:<span id="dstr" class="spanNum"></span>&nbsp;&nbsp;价格:<span id="pstr" class="spanNum"></span></span></td>
					<td width="50%" align="right"><span class="STYLE18" id="_nav" /></td>
				</tr>
			</table>
		</td>		
	</tr>
	<tr>
		<td><div id="spDiv" class="listPic"></div></td>
	</tr>
	<tr>
		<td height="21" class="a STYLE39" align="center">
			<span class="STYLE18" id="_btnnav" />
		</td>		
	</tr>
</table>
<jsp:include page="foot.jsp" />
</body>
</html>
