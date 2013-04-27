<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/tld/tycomputer.tld" prefix="ty"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tycomputer.common.util.Constants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<ty:dclipinContextPath />
<title><%= Constants.siteTitle %></title>
<script>var arr='<s:property value='#request.arr' escape="false" />'.evalJSON();function showp(uid){var pre,next;for(var i=0;i<arr.size();i++){var p=arr[i];if(p.uid==uid){$("pname").innerHTML=p.pname;$("imgpic").src="images/p/"+p.pic;$("tduid").innerHTML=p.uid;$("tdcname").innerHTML=p.cname;$("tdtname").innerHTML=p.tname;$("tdprice").innerHTML=p.price;$("tdresale").innerHTML=p.resale;if(p.unit!=undefined){$("tdunit").innerHTML=p.unit;$("trunit").show()}else{$("trunit").hide()}if(p.spec!=undefined){$("tdspec").innerHTML=p.spec;$("trspec").show()}else{$("trspec").hide()}if(p.itsize!=undefined){$("tditsize").innerHTML=p.itsize;$("tritsize").show()}else{$("tritsize").hide()}if(p.packsize!=undefined){$("tdpacksize").innerHTML=p.packsize;$("trpacksize").show()}else{$("trpacksize").hide()}if(p.weight!=undefined){$("tdweight").innerHTML=p.weight;$("trweight").show()}else{$("trweight").hide()}if(p.material!=undefined){$("tdmaterial").innerHTML=p.material;$("trmaterial").show()}else{$("trmaterial").hide()}if(p.buynum!=undefined){$("tdbuynum").innerHTML=p.buynum;$("trbuynum").show()}else{$("trbuynum").hide()}if(p.desc!=undefined){$("tddesc").innerHTML=p.desc;$("trdesc").show()}else{$("trdesc").hide()}if(arr.size()>=(i+1)){next=arr[i+1]}break}else{pre=p}}if(pre!=null&&pre!=undefined){$("prespan").innerHTML="";var prea=Builder.node('a',{href:"#",onclick:"return showp('"+pre.uid+"');"},[Builder.node('img',{src:"images/al.png",width:"32px",height:"32px"}),pre.pname]);$("prespan").appendChild(prea)}else{$("prespan").innerHTML=""}if(next!=null&&next!=undefined){$("nextspan").innerHTML="";var nexta=Builder.node('a',{href:"#",onclick:"return showp('"+next.uid+"');"},[next.pname,Builder.node('img',{src:"images/ar.png",width:"32px",height:"32px"})]);$("nextspan").appendChild(nexta)}else{$("nextspan").innerHTML=""}return false}</script>
<style type="text/css">
.tn{font-weight:bold}
.bn{font-size:18px}
</style>
</head>
<body class="articlelist">
<jsp:include page="head.jsp" />
<table border="0" align="center" cellpadding="0" cellspacing="0" class="listWidth">
	<tr>
		<td width="227" align="left" valign="top">
		<jsp:include page="left.jsp" />		
		
		</td>		
		<td width="47"><a name="bb"/></td>

		<td valign="top">
			<table width="100%" align="center">
				<tr>
					<td align="center">
						<img id="imgpic" src='' border="0" width="<%= Constants.bigWidth %>px" height="<%= Constants.bigHeight %>px" title="礼品图片"/>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%">
							<tr>
								<td width="50%" align="left"><span id="prespan" class="bn"></span></td>
								<td width="50%" align="right"><span id="nextspan" class="bn"></span></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center">
						<br><h1 id="pname"></h1>
					</td>
				</tr>
				<tr>
					<td align="center">
						<table width="100%">							
							<tr><td width="20%" align="right"><span style="font-weight:bold ">【&nbsp;编&nbsp;&nbsp;号&nbsp;】</span></td><td align="left" id="tduid"></td></tr>
							<tr><td width="20%" align="right"><span style="font-weight:bold ">【&nbsp;目&nbsp;&nbsp;录&nbsp;】</span></td><td align="left" id="tdcname"></td></tr>
							<tr><td width="20%" align="right"><span style="font-weight:bold ">【&nbsp;类&nbsp;&nbsp;别&nbsp;】</span></td><td align="left" id="tdtname"></td></tr>
							<tr id="trprice"><td align="right"><span style="font-weight:bold ">【&nbsp;价&nbsp;&nbsp;格&nbsp;】</span></td><td align="left"><span id="tdprice"></span>元(此价格仅供参考)</td></tr>
							<tr id="trunit"><td align="right"><span style="font-weight:bold ">【&nbsp;单&nbsp;&nbsp;位&nbsp;】</span></td><td align="left" id="tdunit"></td></tr>
							<tr id="trspec"><td align="right"><span style="font-weight:bold ">【&nbsp;规&nbsp;&nbsp;格&nbsp;】</span></td><td align="left" id="tdspec"></td></tr>
							<tr id="tritsize"><td align="right"><span style="font-weight:bold ">【&nbsp;大&nbsp;&nbsp;小&nbsp;】</span></td><td align="left" id="tditsize"></td></tr>
							<tr id="trpacksize"><td align="right"><span style="font-weight:bold ">【&nbsp;包&nbsp;&nbsp;装&nbsp;】</span></td><td align="left" id="tdpacksize"></td></tr>
							<tr id="trweight"><td align="right"><span style="font-weight:bold ">【&nbsp;重&nbsp;&nbsp;量&nbsp;】</span></td><td align="left" id="tdweight"></td></tr>							
							<tr id="trmaterial"><td align="right"><span style="font-weight:bold ">【&nbsp;材&nbsp;&nbsp;料&nbsp;】</span></td><td align="left" id="tdmaterial"></td></tr>
							<tr id="trresale"><td align="right"><span style="font-weight:bold ">【&nbsp;零&nbsp;&nbsp;售&nbsp;】</span></td><td align="left" id="tdresale"></td></tr>							
							<tr id="trbuynum"><td align="right"><span style="font-weight:bold ">【起定量】</span></td><td align="left" id="tdbuynum"></td></tr>
							<tr id="trdesc"><td align="right" valign="top"><span style="font-weight:bold ">【&nbsp;简&nbsp;&nbsp;介&nbsp;】</span></td><td align="left" id="tddesc"></td></tr>
							
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
<script>showp('<s:property value='#request.p' />');</script>