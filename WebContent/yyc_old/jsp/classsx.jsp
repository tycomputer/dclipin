<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<jsp:include page="htmlHead.jsp">
	<jsp:param value="page2" name="page"/>
	<jsp:param value="n2" name="n"/>
</jsp:include>
<!-- content -->
	<section id="content">
		<div class="main">
			<div class="wrapper">
				<article class="col-1 col-indent maxheight">
					<div class="box bg3 maxheight">

						<h5>课&nbsp;程&nbsp;体&nbsp;系</h5>
						<div class="indent">
							<span class="text">课程是学校育人的蓝图。</span>
							<div class="top2">
								<ul class="list">
									<li><a href="classeq.jsp">幼儿情商教育</a></li>
									<li><a href="classqz.jsp">亲子园</a></li>
									<li><a href="#" onclick="return false;">幼儿课外</a></li>
								</ul>
							</div>
							
						</div>
					</div>
				</article>
				<article class="col-2 col-indent maxheight">

					<div class="list text maxheight"  style="BACKGROUND-IMAGE: url(../imgs/bgEq.jpg); BACKGROUND-REPEAT: repeat-x">
						<h2>幼儿课外 </h2>
						<div class="indent1">							
							<img src="../imgs/eq1.gif" alt="" class="fleft img-padding"/>
							<p>.</p>
							
						</div>

					</div>
					
					
				</article>
				
			</div>
		</div>
	</section>
<jsp:include page="footer.jsp"></jsp:include>