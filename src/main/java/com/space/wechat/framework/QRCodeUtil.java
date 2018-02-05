package com.space.wechat.framework;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.space.wechat.util.FileUtil;
import com.swetake.util.Qrcode;

public class QRCodeUtil {

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            二维码图片的内容
	 * @param imgPath
	 *            生成二维码图片完整的路径
	 * @param ccbpath
	 *            二维码图片中间的logo路径
	 */
	public static int createQRCode(String content, String imgPath,
			String ccbPath) {
		try {
			Qrcode qrcodeHandler = new Qrcode();
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			qrcodeHandler.setQrcodeVersion(7);

			// System.out.println(content);
			byte[] contentBytes = content.getBytes("gb2312");
			// 构造一个BufferedImage对象 设置宽、高
			BufferedImage bufImg = new BufferedImage(600, 600,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();

			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, 600, 600);

			// 设定图像颜色 > BLACK
			gs.setColor(Color.BLACK);

			// 设置偏移量 不设置可能导致解析出错
			int pixoff = 28;
			// 输出内容 > 二维码
			if (contentBytes.length > 0 && contentBytes.length < 120) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 12 + pixoff, i * 12 + pixoff, 12,
									12);
						}
					}
				}
			} else {
				System.err.println("QRCode content bytes length = "
						+ contentBytes.length + " not in [ 0,120 ]. ");
				return -1;
			}
			Image img = ImageIO.read(FileUtil.getInputStream(ccbPath));// 实例化一个Image对象。
			gs.drawImage(img, 228, 228, 144, 144, null);

			gs.setStroke(new BasicStroke(12));
			gs.setColor(Color.WHITE);
			gs.drawRect(228, 228, 144, 144);

			gs.dispose();
			bufImg.flush();

			// 生成二维码QRCode图片
			File imgFile = new File(imgPath);
			ImageIO.write(bufImg, "png", imgFile);

		} catch (Exception e) {
			e.printStackTrace();
			return -100;
		}
		return 0;
	}

	public static void main(String[] args) {
		createQRCode(
				"http://www.weixiaotong.com.cn",
				"/Users/kongfeiquan/Desktop/222.jpg",
				"http://weixt-static.oss-cn-qingdao.aliyuncs.com/0000118c8ce5b6931f4ad13ab10a0bca0b9659e1.jpg");
	}

}
