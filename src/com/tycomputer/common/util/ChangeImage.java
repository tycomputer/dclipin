package com.tycomputer.common.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * 日期 : 2010-9-10 上午10:33:38<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dclipin<br>
 * 功能 : <br>
 */
public class ChangeImage {

	/**
	 * 
	 * 功能说明 :
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		File dir = new File("D:/tomcat/apache-tomcat-6.0.18/webapps/dclipin/images/p/");
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isFile() && ((file.getName().toLowerCase().endsWith(".jpg")) || (file.getName().toLowerCase().endsWith(".jpeg"))|| (file.getName().toLowerCase().endsWith(".png")))) {
				try {
					Image src = ImageIO.read(file);
					int width = src.getWidth(null);
					int height = src.getHeight(null);
					if (width == 394 && (height == 394)) {
						// 大图片加字
						//File bigImg = new File("D:/tomcat/apache-tomcat-6.0.18/webapps/dclipin/images/newp/", file.getName());
						File bigImg = new File("D:/tomcat/apache-tomcat-6.0.18/webapps/dclipin/images/newp/", file.getName());
						BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

						Graphics2D g = image.createGraphics();
						g.drawImage(src, 0, 0, width, height, null);

						g.setColor(new Color(Integer.parseInt("0e09f6".substring(0, 2), 16), Integer.parseInt("0e09f6".substring(2, 4), 16), Integer.parseInt(
								"0e09f6".substring(4), 16)));
						g.setFont(new Font(ImageUtils.fontName, ImageUtils.fontStyle, ImageUtils.fontSize));
						g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ImageUtils.alpha));
						String conName = "东创伟业";
						g.drawString(conName, (width - (ImageUtils.getLength(conName) * ImageUtils.fontSize)) / 2, (height - 40 -ImageUtils.fontSize ));
						
						
						g.setColor(new Color(Integer.parseInt("0e09f6".substring(0, 2), 16), Integer.parseInt("0e09f6".substring(2, 4), 16), Integer.parseInt(
								"0e09f6".substring(4), 16)));
						g.setFont(new Font(ImageUtils.urlfontName, Font.BOLD , ImageUtils.urlfontSize));
						g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ImageUtils.alpha));
						String string = "www.bjdcwy.com";
						g.drawString(string, (width - (ImageUtils.getLength(string) * ImageUtils.urlfontSize)) / 2, (height - 40));

						g.dispose();
						ImageIO.write((BufferedImage) image, "jpg", bigImg);
					} else {

						int bytesum = 0;
						int byteread = 0;
						InputStream inStream = new FileInputStream(file); // 读入原文件
						FileOutputStream fs = new FileOutputStream("D:/tomcat/apache-tomcat-6.0.18/webapps/dclipin/images/newp/" + file.getName());
						byte[] buffer = new byte[1444];
						int length;
						while ((byteread = inStream.read(buffer)) != -1) {
							bytesum += byteread; // 字节数 文件大小
							fs.write(buffer, 0, byteread);
						}
						inStream.close();

					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("pass  --->  " +file.getName());
			}
		}
		System.out.println("________________________________________");

	}

}
