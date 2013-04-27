package com.tycomputer.common.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import com.tycomputer.common.util.Constants;

public class DclipinContextPath extends MySimpleTagSupport {
	

	@Override
	public void doTag() throws JspException, IOException {
		StringBuffer sb = new StringBuffer();
		try {

			
			HttpServletRequest request = getRequest();
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
			sb.append("<base href='").append(basePath).append("'>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		sb.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
		sb.append("<meta name='Description' Content=").append(Constants.description).append("'>");
		sb.append("<meta name='keywords' content='").append(Constants.keywords).append("'>");
		sb.append("<link href='css/cs.css' rel='stylesheet' media='screen' type='text/css' />");
		sb.append("<script type='text/javascript' src='js/prototype/prototype.js'></script>");
		sb.append("<link href='favicon.ico' rel=\"SHORTCUT ICON\">");
		sb.append("<script type='text/javascript' src='js/scriptaculous/scriptaculous.js?load=builder,effects'></script>");
//		sb.append("<script type='text/javascript' src='js/carousel.js'></script>\n");
//		sb.append("<script>document.observe('dom:loaded', function() {new Carousel('carousel-wrapper', $$('#carousel-content .slide'),null,{auto:true,circular:true,frequency:5});});</script>\n");
		sb.append("<script type='text/javascript' src='js/ferdinand.slider.js'></script>");
		sb.append("<script>document.observe('dom:loaded',function(){new Ferdinand.Slider('slider-demo',{url:'js/image.json'});});</script>");
		sb.append("<script type='text/javascript'>var _gaq = _gaq || [];_gaq.push(['_setAccount', 'UA-24829826-1']);_gaq.push(['_trackPageview']);(function() {var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);})();</script>");
		this.getJspContext().getOut().print(sb.toString());

	}
}
