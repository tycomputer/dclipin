<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>育英才教育</title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="../css/reset.css" type="text/css" media="screen">
	<link rel="stylesheet" href="../css/style.css" type="text/css" media="screen">
	<link rel="stylesheet" href="../css/layout.css" type="text/css" media="screen">
	<script type="text/javascript" src="../js/jquery-1.4.2.js" ></script>
	<script type="text/javascript" src="../js/html5.js"></script>
	<script type="text/javascript" src="../js/maxheight.js"></script>    
	<script type="text/javascript" src="../js/script.js"></script>
	
	<script type="text/javascript" src="../js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="../js/jquery.nivo.slider.pack.js"></script>
	<!--[if lt IE 7]>
		<script type="text/javascript" src="http://info.template-help.com/files/ie6_warning/ie6_script_other.js"></script>
	<![endif]-->
	<link href='favicon16.ico' rel="SHORTCUT ICON">
</head>
<body id="<%= request.getParameter("page") %>" onLoad="new ElementMaxHeight();">
	<!-- header -->
	<header>
		<div class="main">
			<div class="container">

				<h1 <% if (!request.getParameter("page").equals("pageIndex")){ %> class="h11" <% } %>><a href="#" <% if (!request.getParameter("page").equals("pageIndex")){ %> class="h1a" <% }%>>欢迎您来到育英才</a></h1>
				<nav>
					<ul class="menu">
						<li class="n1"><a <% if (request.getParameter("n").equals("n1")) out.print("href='#' class='active'"); else out.print("href='index.jsp'"); %> >关&nbsp;于</a></li>
						<li class="n2"><a <% if (request.getParameter("n").equals("n2")) out.print("href='#' class='active'"); else out.print("href='classeq.jsp'"); %> >课程体系</a></li>
						<li class="n3"><a href="#">魅&nbsp;力&nbsp;教&nbsp;师</a></li>
						<li class="n4"><a <% if (request.getParameter("n").equals("n4")) out.print("href='#' class='active'"); else out.print("href='adviser.jsp'"); %> >顾问团队</a></li>
						<li class="n5"><a href="#">明&nbsp;星&nbsp;宝&nbsp;贝</a></li>
						<li class="n6"><a <% if (request.getParameter("n").equals("n6")) out.print("href='#' class='active'"); else out.print("href='hr.jsp'"); %> >招贤纳士</a></li>
						<li class="n7"><a <% if (request.getParameter("n").equals("n7")) out.print("href='#' class='active'"); else out.print("href='contact.jsp'"); %> >联系我们</a></li>
					</ul>
				</nav>
				<% if (request.getParameter("page").equals("pageIndex")){ %>
				<div id="slider">
					<img src="../imgs/img1.jpg" alt="" >
				</div>
				<% } %>
			</div>
		</div>
	</header>