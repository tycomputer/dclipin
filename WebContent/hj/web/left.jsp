<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% request.setAttribute("presentSort",com.tycomputer.common.web.CacheData.HJDATACATA); %>
<s:set></s:set>
<table cellspacing="0" cellpadding="0" width="100%" border="0">
	<tbody>
		<tr>
			<td colspan="2" height="22" bgcolor="#DCDCDC" class="STYLE52 STYLE55" style="font-size:14px; padding-left:15px;">货架产品分类</td>
		</tr>
		<s:iterator value="#request.presentSort" status="stuts">
			<tr>
				<td colspan="2" height="18" class="p_type">&nbsp;&nbsp;-<a href='hj/web/product.jsp?t=<s:property value="cataId" escape="false"/>'><s:property value="cataName" escape="false"/></a></td>
			</tr>
				<s:iterator value="#request.presentSort[#stuts.index].datatypes" status="sta">
				<s:if test="#sta.index % 2 == 0"><tr></s:if>
				<td class="p_deta">&nbsp;&nbsp;<a href='hj/web/product.jsp?t=<s:property value="cataId"/>&d=<s:property value="typeId" escape="false"/>'><s:property value="typeName" escape="false"/></a></td>
				<s:if test="#sta.index % 2 == 1"></tr></s:if>
				<s:if test="#sta.index % 2 == 0 && (#sta.isLast())"><td>&nbsp;</td></tr></s:if>
			</s:iterator>
		</s:iterator>		
	</tbody>
</table>
<br />
