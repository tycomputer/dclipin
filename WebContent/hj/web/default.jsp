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
			<table width="100%">
				<tr>
					<td>
						<h1 class="a STYLE57" style="border-bottom: 1px solid #ddd">
							<span class="STYLE18"><img src="images/new-mark.png" width="16px" height="16px" border="0" />&nbsp;&nbsp;精品货架</span>
						</h1>
						<% 
						request.setAttribute("newPre",com.tycomputer.common.web.CacheData.HJ_NEW_PRESENT);
						request.setAttribute("comPre",com.tycomputer.common.web.CacheData.HJ_COM_PRESENT);
						%>
						<div class="listPic">
							<s:iterator value="#request.newPre" status="stuts">
								<dl>
									<dt>
										<a href='hjDetail.do?i=<s:property value="uid"/>' class='preview' target='_blank'>
											<img src='images/h/<s:property value="litpic"/>'  width="<%= Constants.litWidth %>px" height="<%= Constants.litHeight %>px" />
										</a>
									</dt>
									<dd><a href='hjDetail.do?i=<s:property value="uid"/>' title='<s:property value="pname"/>'><s:property value="pname"/></a></dd>
								</dl>
							</s:iterator>
						</div>
					</td>
				</tr>
				<% 
				//<tr>
				//	<td>
				//		<h1 class="a STYLE57" style="border-bottom: 1px solid #ddd">
				//			<span class="STYLE18"><img src="images/good-mark.png" width="16px" height="16px" border="0" />&nbsp;&nbsp;精品推荐</span>
				//		</h1>
				//		<div class="listPic">
				//			<s:iterator value="#request.comPre" status="stuts">
				//				<dl>
				//					<dt>
				//						<a href='presentDetail.do?i=<s:property value="uid"/>' class='preview' target='_blank'>
				//							<img src='images/h/<s:property value="litpic"/>' width="<%= Constants.litWidth % >px" height="<%= Constants.litHeight % >px"  />
				//						</a>
				//					</dt>
				//					<dd><a href='presentDetail.do?i=<s:property value="uid"/>' title='<s:property value="pname"/>'><s:property value="pname"/></a></dd>
				//				</dl>
				//			</s:iterator>				
				//		</div>	
				//	</td>
				// </tr>
				%>
			</table>
		</td>
	</tr>
</table>
<jsp:include page="foot.jsp" />
</body>
</html>
<script type="text/javascript">var _gaq = _gaq || [];_gaq.push(['_setAccount', 'UA-25201575-1']);_gaq.push(['_trackPageview']);(function() {var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);})();</script>