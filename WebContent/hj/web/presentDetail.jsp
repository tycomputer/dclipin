<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/tld/tycomputer.tld" prefix="ty"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tycomputer.common.util.Constants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<ty:hjContextPath />
<title><%= Constants.hjsiteTitle %></title>
</head>
<body class="articlelist">
<jsp:include page="head.jsp" />
<table border="0" align="center" cellpadding="0" cellspacing="0" class="listWidth">
	<tr>
		<td width="227" align="left" valign="top">
		<jsp:include page="left.jsp" />		
		
		</td>		
		<td width="47">&nbsp;</td>

		<td valign="top">
			<table width="100%" align="center">
				<tr>
					<td align="center">
						<h1><s:property value="#request.p.pname"/></h1>
					</td>
				</tr>
				<tr>
					<td align="center">
						<br />
						<img src='images/p/<s:property value="#request.p.bigpic"/>' border="0" width="<%= Constants.bigWidth %>px" height="<%= Constants.bigHeight %>px" title="《<s:property value="#request.p.pname"/>》礼品图片"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<table>
							
							<tr><td width="20%" align="right"><span style="font-weight:bold ">【&nbsp;编&nbsp;&nbsp;号&nbsp;】</span></td><td align="left"><s:property value="#request.p.uid"/></td></tr>
													
							<s:if test="#request.p.priceStr != ''">
							<tr><td align="right"><span style="font-weight:bold ">【&nbsp;价&nbsp;&nbsp;格&nbsp;】</span></td><td align="left"><s:property value="#request.p.priceStr"/>元(此价格仅供参考)</td></tr>
							</s:if>
							<s:if test="#request.p.unit != ''">
							<tr><td align="right"><span style="font-weight:bold ">【&nbsp;单&nbsp;&nbsp;位&nbsp;】</span></td><td align="left"><s:property value="#request.p.unit"/></td></tr>
							</s:if>
							<s:if test="#request.p.spec != ''">
							<tr><td align="right"><span style="font-weight:bold ">【&nbsp;规&nbsp;&nbsp;格&nbsp;】</span></td><td align="left"><s:property value="#request.p.spec"/></td></tr>
							</s:if>
							<s:if test="#request.p.itsize != ''">
							<tr><td align="right"><span style="font-weight:bold ">【&nbsp;大&nbsp;&nbsp;小&nbsp;】</span></td><td align="left"><s:property value="#request.p.itsize"/></td></tr>
							</s:if>
							<s:if test="#request.p.packsize != ''">
							<tr><td align="right"><span style="font-weight:bold ">【&nbsp;包&nbsp;&nbsp;装&nbsp;】</span></td><td align="left"><s:property value="#request.p.packsize"/></td></tr>
							</s:if>
							<s:if test="#request.p.weight != ''">
							<tr><td align="right"><span style="font-weight:bold ">【&nbsp;重&nbsp;&nbsp;量&nbsp;】</span></td><td align="left"><s:property value="#request.p.weight"/></td></tr>
							</s:if>
							<s:if test="#request.p.material != ''">
							<tr><td align="right"><span style="font-weight:bold ">【&nbsp;材&nbsp;&nbsp;料&nbsp;】</span></td><td align="left"><s:property value="#request.p.material"/></td></tr>
							</s:if>
							
							<tr><td align="right"><span style="font-weight:bold ">【&nbsp;零&nbsp;&nbsp;售&nbsp;】</span></td><td align="left"><s:if test="#request.p.resale ==0 ">不零售</s:if><s:else>零售</s:else></td></tr>
							<s:if test="#request.p.buynum != 0">
							<tr><td align="right"><span style="font-weight:bold ">【起定量】</span></td><td align="left"><s:property value="#request.p.buynum"/></td></tr>
							</s:if>
							<s:if test="#request.p.description != ''">
							<tr><td align="right" valign="top"><span style="font-weight:bold ">【&nbsp;简&nbsp;&nbsp;介&nbsp;】</span></td><td align="left"><s:property value="#request.p.description" escape="false" /></td></tr>
							</s:if>	
						</table>
						
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<jsp:include page="foot.jsp" />
</body>
</html>
<script type="text/javascript">var _gaq = _gaq || [];_gaq.push(['_setAccount', 'UA-25201575-1']);_gaq.push(['_trackPageview']);(function() {var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);})();</script>