/*
 * @(#)ImageUtils.java 
 * 
 * Copyright 2010
 * All rights reserved.
 *
 */
package com.tycomputer.common.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 日期 : 2010-7-8<br>
 * 作者 : zhangliuhua<br>
 * 项目 : dclipin<br>
 * 功能 : <br>
 */
public final class ImageUtils {

	public static String fontName = "楷体";
	public static String urlfontName = "宋体";
	public static int fontStyle = Font.PLAIN;
	public static Color color = new Color(242,241,249);
	public static int fontSize = 40;
	public static int urlfontSize = 35;
	public static float alpha = 0.7f;

	public static String[] pressResizeImg(String path, String extFileName, File file, String colorStr,boolean isDclipin) {
		try {
			File img = file;// new File(srcFile);
			// 大图片

			String bigImgName = UUIDHexGenerator.newUUID() + extFileName;
			File bigImg = new File(path, bigImgName);
			Image src = ImageIO.read(img);
			
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);

			g.setColor(new Color(Integer.parseInt(colorStr.substring(0, 2), 16), Integer.parseInt(colorStr.substring(2, 4), 16), Integer.parseInt(colorStr.substring(4), 16)));
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			if (isDclipin){
				g.drawString(Constants.waterName, (width - (getLength(Constants.waterName) * fontSize)) / 2, (height - 40 - fontSize));	
			} else {
				g.drawString(Constants.hjwaterName, (width - (getLength(Constants.hjwaterName) * fontSize)) / 2, (height - 40 - fontSize));
			}
			

			g.setFont(new Font(urlfontName, fontStyle, urlfontSize));
			if (isDclipin){
				g.drawString(Constants.waterUrl, (width - (getLength(Constants.waterUrl) * urlfontSize)) / 2, (height - 40));
			} else {
				g.drawString(Constants.hjwaterUrl, (width - (getLength(Constants.hjwaterUrl) * urlfontSize)) / 2, (height - 40));
			}
			
			g.dispose();
			ImageIO.write((BufferedImage) image, "jpg", bigImg);

			// 创建小图片
			double ratio = 0.0; // 缩放比例
			// img = new File(srcFile);
			String litImgName = UUIDHexGenerator.newUUID() + extFileName;
			File litImg = new File(path, litImgName);
			image = ImageIO.read(img);

			Image itemp = image.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
			// 计算比例
			if ((image.getHeight() > Constants.litHeight) || (image.getWidth() > Constants.litWidth)) {
				if (image.getHeight() > image.getWidth()) {
					ratio = (new Integer(Constants.litHeight)).doubleValue() / image.getHeight();
				} else {
					ratio = (new Integer(Constants.litWidth)).doubleValue() / image.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(image, null);
			}
			if (true) {
				BufferedImage image2 = new BufferedImage(Constants.litWidth, Constants.litHeight, BufferedImage.TYPE_INT_RGB);
				Graphics2D gg = image2.createGraphics();
				gg.setColor(Color.white);
				gg.fillRect(0, 0, Constants.litWidth, Constants.litHeight);
				if (Constants.litWidth == itemp.getWidth(null))
					gg.drawImage(itemp, 0, (Constants.litHeight - itemp.getHeight(null)) / 2, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
				else
					gg.drawImage(itemp, (Constants.litWidth - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
				gg.dispose();
				itemp = image2;
			}
			// 小图片水印
			// width = itemp.getWidth(null);
			// height = itemp.getHeight(null);
			// BufferedImage litimage = new BufferedImage(width, height,
			// BufferedImage.TYPE_INT_RGB);
			// g = litimage.createGraphics();
			// g.drawImage(itemp, 0, 0, width, height, null);
			// g.setColor(color);
			// int litNameSize = (int)(fontSize *
			// ((double)Constants.litHeight/(double)Constants.bigHeight));
			// g.setFont(new Font(fontName, fontStyle, litNameSize));
			// g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
			// alpha));
			// g.drawString(Constants.waterName, (width -
			// (getLength(Constants.waterName) * litNameSize)) / 2, (height - 20
			// - litNameSize));
			//
			// int litUrlSize = (int)(urlfontSize *
			// ((double)Constants.litHeight/(double)Constants.bigHeight));
			// g.setFont(new Font(urlfontName, fontStyle, litUrlSize));
			// g.drawString(Constants.waterUrl, (width -
			// (getLength(Constants.waterUrl) * litUrlSize)) / 2, (height -
			// 20));
			// g.dispose();
			//

			ImageIO.write((RenderedImage) itemp, "jpg", litImg);

			return new String[] { bigImgName, litImgName };

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String[] pressResize(String srcFile) {
		try {
			File img = new File(srcFile);
			// 大图片
			String extFileName = srcFile.substring(srcFile.lastIndexOf('.'));
			String bigImgName = img.getParent() + UUIDHexGenerator.newUUID() + extFileName;
			File bigImg = new File(bigImgName);
			Image src = ImageIO.read(img);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			g.setColor(Color.RED);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			g.drawString(Constants.waterName, (width - (getLength(Constants.waterName) * fontSize)) / 2, (height - 40 - fontSize));

			g.setFont(new Font(urlfontName, fontStyle, urlfontSize));
			g.drawString(Constants.waterUrl, (width - (getLength(Constants.waterUrl) * urlfontSize)) / 2, (height - 40));
			g.dispose();
			ImageIO.write((BufferedImage) image, "jpg", bigImg);

			// 创建小图片
			double ratio = 0.0; // 缩放比例
			// img = new File(srcFile);
			String litImgName = img.getParent() + UUIDHexGenerator.newUUID() + extFileName;
			File litImg = new File(litImgName);
			image = ImageIO.read(img);

			Image itemp = image.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
			// 计算比例
			if ((image.getHeight() > Constants.litHeight) || (image.getWidth() > Constants.litWidth)) {
				if (image.getHeight() > image.getWidth()) {
					ratio = (new Integer(Constants.litHeight)).doubleValue() / image.getHeight();
				} else {
					ratio = (new Integer(Constants.litWidth)).doubleValue() / image.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(image, null);
			}
			if (true) {
				BufferedImage image2 = new BufferedImage(Constants.litWidth, Constants.litHeight, BufferedImage.TYPE_INT_RGB);
				Graphics2D gg = image2.createGraphics();
				gg.setColor(Color.white);
				gg.fillRect(0, 0, Constants.litWidth, Constants.litHeight);
				if (Constants.litWidth == itemp.getWidth(null))
					gg.drawImage(itemp, 0, (Constants.litHeight - itemp.getHeight(null)) / 2, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
				else
					gg.drawImage(itemp, (Constants.litWidth - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
				gg.dispose();
				itemp = image2;
			}
			// 小图片水印
			width = itemp.getWidth(null);
			height = itemp.getHeight(null);
			BufferedImage litimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			g = litimage.createGraphics();
			g.drawImage(itemp, 0, 0, width, height, null);
			g.setColor(Color.RED);
			int litNameSize = (int) (fontSize * ((double) Constants.litHeight / (double) Constants.bigHeight));
			g.setFont(new Font(fontName, fontStyle, litNameSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			g.drawString(Constants.waterName, (width - (getLength(Constants.waterName) * litNameSize)) / 2, (height - 20 - litNameSize));

			int litUrlSize = (int) (urlfontSize * ((double) Constants.litHeight / (double) Constants.bigHeight));
			g.setFont(new Font(urlfontName, fontStyle, litUrlSize));
			g.drawString(Constants.waterUrl, (width - (getLength(Constants.waterUrl) * litUrlSize)) / 2, (height - 20));
			g.dispose();

			ImageIO.write(litimage, "jpg", litImg);

			return new String[] { bigImgName, litImgName };

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 功能说明 :
	 * 
	 * @param pressText
	 * @param url
	 * @param targetImg
	 */
	public static void pressText(String pressText, String url, String targetImg) {
		try {
			File img = new File(targetImg);
			Image src = ImageIO.read(img);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			g.setColor(Color.RED);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			g.drawString(pressText, (width - (getLength(pressText) * fontSize)) / 2, (height - 40 - fontSize));

			g.setFont(new Font(urlfontName, fontStyle, urlfontSize));
			g.drawString(url, (width - (getLength(url) * urlfontSize)) / 2, (height - 40));
			g.dispose();
			ImageIO.write((BufferedImage) image, "jpg", img);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 缩放
	 * 
	 * @param filePath
	 *            图片路径
	 * @param height
	 *            高度
	 * @param width
	 *            宽度
	 * @param bb
	 *            比例不对时是否需要补白
	 */
	public static void resize(String srcFile, String destFile, int height, int width, boolean bb) {
		try {
			double ratio = 0.0; // 缩放比例
			File f = new File(srcFile);
			File dest = new File(destFile);

			BufferedImage bi = ImageIO.read(f);

			Image itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);

			// 计算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
				if (bi.getHeight() > bi.getWidth()) {
					ratio = (new Integer(height)).doubleValue() / bi.getHeight();
				} else {
					ratio = (new Integer(width)).doubleValue() / bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
			}
			if (bb) {
				BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, width, height);
				if (width == itemp.getWidth(null))
					g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
				else
					g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
				g.dispose();
				itemp = image;
			}
			ImageIO.write((BufferedImage) itemp, "jpg", dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		// pressImage("d:/a.jpg", "d:/aa.jpg", 0, 0, 0.5f);
		// pressText("东创伟业", "www.dclipin.com", "d:/a.jpg");
		// resize("d:/a.jpg","d:/ab.jpg", 150, 150, true);

		String[] strings = pressResize("d:/showPhotos.jpg");
		System.out.println(strings[0]);
		System.out.println(strings[1]);
	}

	public static int getLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if (new String(text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length / 2;
	}

}
// /**
// * 图片水印
// *
// * @param pressImg
// * 水印图片
// * @param targetImg
// * 目标图片
// * @param x
// * 修正值 默认在中间
// * @param y
// * 修正值 默认在中间
// * @param alpha
// * 透明度
// */
// public final static void pressImage(String pressImg, String targetImg, int x,
// int y, float alpha) {
// try {
// File img = new File(targetImg);
// Image src = ImageIO.read(img);
// int wideth = src.getWidth(null);
// int height = src.getHeight(null);
// BufferedImage image = new BufferedImage(wideth, height,
// BufferedImage.TYPE_INT_RGB);
// Graphics2D g = image.createGraphics();
// g.drawImage(src, 0, 0, wideth, height, null);
// // 水印文件
// Image src_biao = ImageIO.read(new File(pressImg));
// int wideth_biao = src_biao.getWidth(null);
// int height_biao = src_biao.getHeight(null);
// g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
// g.drawImage(src_biao, (wideth - wideth_biao) / 2, (height - height_biao) / 2,
// wideth_biao, height_biao, null);
// // 水印文件结束
// g.dispose();
// ImageIO.write((BufferedImage) image, "jpg", img);
// } catch (Exception e) {
// e.printStackTrace();
// }
// }

//
// /**
// * 缩放
// *
// * @param filePath
// * 图片路径
// * @param height
// * 高度
// * @param width
// * 宽度
// * @param bb
// * 比例不对时是否需要补白
// */
// public static void resize(String filePath, int height, int width, boolean bb)
// {
// try {
// double ratio = 0.0; // 缩放比例
// File f = new File(filePath);
//
// BufferedImage bi = ImageIO.read(f);
// Image itemp = bi.getScaledInstance(width, height,
// BufferedImage.SCALE_SMOOTH);
// // 计算比例
// if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
// if (bi.getHeight() > bi.getWidth()) {
// ratio = (new Integer(height)).doubleValue() / bi.getHeight();
// } else {
// ratio = (new Integer(width)).doubleValue() / bi.getWidth();
// }
// AffineTransformOp op = new
// AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
// itemp = op.filter(bi, null);
// }
// if (bb) {
// BufferedImage image = new BufferedImage(width, height,
// BufferedImage.TYPE_INT_RGB);
// Graphics2D g = image.createGraphics();
// g.setColor(Color.white);
// g.fillRect(0, 0, width, height);
// if (width == itemp.getWidth(null))
// g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
// itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
// else
// g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
// itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
// g.dispose();
// itemp = image;
// }
// ImageIO.write((BufferedImage) itemp, "jpg", f);
// } catch (IOException e) {
// e.printStackTrace();
// }
// }