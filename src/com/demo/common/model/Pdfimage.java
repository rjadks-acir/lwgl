package com.demo.common.model;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.demo.common.model.base.BasePdfimage;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Pdfimage extends BasePdfimage<Pdfimage> {
	public static final Pdfimage dao = new Pdfimage();
	
	public static List<Pdfimage> changePdfToImg(File file) {
		List<Pdfimage> list=new ArrayList<Pdfimage>();
		try {
			//放大倍率
			int n=2;
			//File file = new File("E:\\word\\2016\\论文示例\\09-正文.pdf");
			RandomAccessFile raf = new RandomAccessFile(file, "r");
			FileChannel channel = raf.getChannel();
			MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
			PDFFile pdffile = new PDFFile(buf);
			for (int i = 1; i <= pdffile.getNumPages(); i++) {
				Pdfimage pdfimage=new Pdfimage();
				pdfimage.setFilename(file.getName());
				PDFPage page = pdffile.getPage(i);
				
				pdfimage.setPage(page.getPageNumber());
				pdfimage.setPagesize(pdffile.getNumPages());
				Rectangle rect = new Rectangle(0, 0, ((int) page.getBBox().getWidth()),
						((int) page.getBBox().getHeight()));
				Image img = page.getImage(rect.width*n, rect.height*n, rect,
						null, // null for the ImageObserver
						true, // fill background with white
						true // block until drawing is done
				);
				BufferedImage tag = new BufferedImage(rect.width*n, rect.height*n, BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(img, 0, 0, rect.width*n, rect.height*n, null);
				//FileOutputStream out = new FileOutputStream("E:\\pdf\\img\\" + i + ".jpg"); // 输出到文件流
				//ImageIO.write(tag, "jpg", out);
				ByteArrayOutputStream os=new ByteArrayOutputStream();//新建流。
				//BufferedImage bi=null;//BufferedImage对象。
				ImageIO.write(tag, "jpg", os);//利用ImageIO类提供的write方法，将bi以png图片的数据模式写入流。
				//byte b[]=//从流中获取数据数组。
				pdfimage.setImage(os.toByteArray());
				list.add(pdfimage);
				//out.close();
			}
			channel.close();
			raf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}