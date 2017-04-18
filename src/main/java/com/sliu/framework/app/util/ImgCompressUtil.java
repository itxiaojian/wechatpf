package com.sliu.framework.app.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImgCompressUtil {
	
	
	/**
	 * 上传图片压缩
	 * @author oufeng
	 * @date 2016年9月13日
	 * file:文件类型
	 * outputWidth：输出的图片宽度
	 * outputHeight：输出的图片高度
	 * proportion：判断是否是等比缩放
	 * **/
	// 图片处理
		public static  byte[]  CompressPicture(MultipartFile file,int outputWidth,
				int outputHeight,Boolean proportion) {
			byte[] bytes = null ;
			try {
			    InputStream _file = file.getInputStream();
				Image img = ImageIO.read(_file);
				// 判断图片格式是否正确
				if (img.getWidth(null) == -1) {
					System.out.println(" can't read,retry!" + "<BR>");
				} else {
					int newWidth;
					int newHeight;
					// 判断是否是等比缩放
					if (proportion == true) {
						// 为等比缩放计算输出的图片宽度及高度
						double rate1 = ((double) img.getWidth(null))
								/ (double) outputWidth + 0.1;
						double rate2 = ((double) img.getHeight(null))
								/ (double) outputHeight + 0.1;
						// 根据缩放比率大的进行缩放控制
						double rate = rate1 > rate2 ? rate1 : rate2;
						newWidth = (int) (((double) img.getWidth(null)) / rate);
						newHeight = (int) (((double) img.getHeight(null)) / rate);
					} else {
						newWidth = outputWidth; // 输出的图片宽度
						newHeight = outputHeight; // 输出的图片高度
					}
					BufferedImage tag = new BufferedImage((int) newWidth,
							(int) newHeight, BufferedImage.TYPE_INT_RGB);

					/*
					 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
					 */
					tag.getGraphics().drawImage(
							img.getScaledInstance(newWidth, newHeight,
									Image.SCALE_SMOOTH), 0, 0, null);
					
					ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
					// JPEGImageEncoder可适用于其他图片类型的转换
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(byteOut);
					encoder.encode(tag);
					bytes = byteOut.toByteArray();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			return bytes;
		}
}
