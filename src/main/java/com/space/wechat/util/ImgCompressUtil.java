package com.space.wechat.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImgCompressUtil {

	private static int OUTPUT_WIDTH = 960;
	private static int OUTPUT_HEIGHT = 640;

	private static int WX_OUTPUT_WIDTH = 720;
	private static int WX_OUTPUT_HEIGHT = 400;

	private static boolean PROPORTION = true;
	private static Logger logger = LoggerFactory
			.getLogger(ImgCompressUtil.class);

	public static String compressPic(String imgpath) {
		try {
			logger.warn("imgpath=" + imgpath);
			// 获得源文件
			File file = new File(imgpath);
			if (!file.exists()) {
				return "";
			}

			FileInputStream fis = new FileInputStream(imgpath);
			BufferedImage bufferedImg = ImageIO.read(fis);
			int imgWidth = bufferedImg.getWidth();
			int imgHeight = bufferedImg.getHeight();

			Image img = ImageIO.read(file);
			// 判断图片格式是否正确
			if (img.getWidth(null) == -1) {
				System.out.println(" can't read,retry!" + "<BR>");
				return "no";
			} else {
				int newWidth;
				int newHeight;

				int orignWidth = img.getWidth(null);
				int orignHeight = img.getHeight(null);

				if (orignWidth < orignHeight) // 图片高比宽大，则缩放为 600*800
				{
					OUTPUT_WIDTH = 640;
					OUTPUT_HEIGHT = 960;
				}

				// 如果大小都未超限，则不需要缩放
				if (orignWidth < OUTPUT_WIDTH && orignHeight < OUTPUT_HEIGHT) {
					return "ok";
				}

				// 判断是否是等比缩放
				if (PROPORTION == true) {
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) img.getWidth(null))
							/ (double) OUTPUT_WIDTH;
					double rate2 = ((double) img.getHeight(null))
							/ (double) OUTPUT_HEIGHT;
					// 根据缩放比率大的进行缩放控制
					double rate = rate1 > rate2 ? rate1 : rate2;
					newWidth = (int) (((double) img.getWidth(null)) / rate);
					newHeight = (int) (((double) img.getHeight(null)) / rate);
				} else {
					newWidth = OUTPUT_WIDTH; // 输出的图片宽度
					newHeight = OUTPUT_HEIGHT; // 输出的图片高度
				}
				BufferedImage tag = new BufferedImage((int) newWidth,
						(int) newHeight, BufferedImage.TYPE_INT_RGB);

				/*
				 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
				 */
				tag.getGraphics().drawImage(
						img.getScaledInstance(newWidth, newHeight,
								Image.SCALE_SMOOTH), 0, 0, null);
				FileOutputStream out = new FileOutputStream(imgpath);
				// JPEGImageEncoder可适用于其他图片类型的转换
				// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				// encoder.encode(tag);
				ImageIO.write(tag, "jpeg", out);

				out.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return "ok";
	}

	public static BufferedImage compressPic(InputStream fis) {
		BufferedImage newImage = null;
		try {
			// 获得源文件
			if (fis == null) {
				return null;
			}
			BufferedImage bufferedImg = ImageIO.read(fis);
			int imgWidth = bufferedImg.getWidth();
			int imgHeight = bufferedImg.getHeight();

			// Image img = ImageIO.read(fis);
			// 判断图片格式是否正确
			if (imgWidth == -1) {
				System.out.println(" can't read,retry!" + "<BR>");
				return null;
			} else {
				int newWidth;
				int newHeight;

				int orignWidth = imgWidth;
				int orignHeight = imgHeight;

				if (orignWidth < orignHeight) // 图片高比宽大，则缩放为 400*720
				{
					WX_OUTPUT_WIDTH = 720;
					WX_OUTPUT_HEIGHT = 400;
				}

				// 如果大小都未超限，则不需要缩放
				if (orignWidth < OUTPUT_WIDTH && orignHeight < OUTPUT_HEIGHT) {
					return null;
				}

				// 判断是否是等比缩放
				if (PROPORTION == true) {
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) imgWidth) / (double) OUTPUT_WIDTH;
					double rate2 = ((double) imgHeight)
							/ (double) OUTPUT_HEIGHT;
					// 根据缩放比率大的进行缩放控制
					double rate = rate1 > rate2 ? rate1 : rate2;
					newWidth = (int) (((double) imgWidth) / rate);
					newHeight = (int) (((double) imgHeight) / rate);
				} else {
					newWidth = WX_OUTPUT_WIDTH; // 输出的图片宽度
					newHeight = WX_OUTPUT_HEIGHT; // 输出的图片高度
				}
				BufferedImage tag = new BufferedImage((int) newWidth,
						(int) newHeight, BufferedImage.TYPE_INT_RGB);

				/*
				 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
				 */
				newImage = new BufferedImage(newWidth, newHeight,
						bufferedImg.getType());
				Graphics g = newImage.getGraphics();

				g.drawImage(bufferedImg, 0, 0, newWidth, newWidth, null);
				g.dispose();

			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return newImage;
	}

	public static BufferedImage rotateImage(InputStream fis, final int degree) {
		// BufferedImage bufferedimage;
		BufferedImage img = null;
		try {
			// bufferedimage = ImageIO.read(fis);
			BufferedImage bufferedimage = ImageIO.read(fis);
			int newWidth;
			int newHeight;
			int w = bufferedimage.getWidth();
			int h = bufferedimage.getHeight();
			if (w > WX_OUTPUT_WIDTH) {
				w = WX_OUTPUT_WIDTH;
			}
			if (h > WX_OUTPUT_WIDTH) {
				h = WX_OUTPUT_HEIGHT;
			}

			int type = bufferedimage.getColorModel().getTransparency();
			Graphics2D graphics2d;
			(graphics2d = (img = new BufferedImage(w, h, type))
					.createGraphics()).setRenderingHint(
					RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			graphics2d.rotate(Math.toRadians(degree), w / 3.8, h / 1.8);
			graphics2d.drawImage(bufferedimage, 0, 0, null);
			graphics2d.dispose();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}

}
