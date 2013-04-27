<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String doname = request.getServerName();
	if (doname.indexOf("dclipin") != -1){		
		response.sendRedirect("web/default.jsp");
	} else if (doname.indexOf("xrdshuojia") != -1){		
		response.sendRedirect("hj/web/default.jsp");
	} else {		
		response.sendRedirect("jsp/index.jsp");		
	}
%>