package com.demo.image;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.demo.common.model.Pdfimage;
import com.jfinal.core.Controller;

public class ImageController extends Controller{

	public void getpdfimage() throws IOException{
		String page=getPara("page");
		String filename=getPara("filename");
		byte[] img=Pdfimage.dao.findFirst("select * from pdfimage where page="+"'"+page+"'"+"and filename="+"'"+filename+"'").getImage();
		getResponse().getOutputStream().write(img);
		renderNull();
	}
	
	private byte[] getBytes(String filePath){  
        byte[] buffer = null;  
        try {  
            File file = new File(filePath);  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }
	
}
