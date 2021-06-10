package com.demo.upload;
import java.io.File;
import java.util.Date;
import java.util.List;

import com.demo.common.model.Pdfimage;
import com.demo.common.model.Teacher;
import com.demo.common.model.Title;
import com.demo.common.model.Type;
import com.demo.common.model.Upload;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
public class UploadController extends Controller{
	
	public void index(){
		String role=getSessionAttr("role");
		String username=getSessionAttr("username");
	
		//List<Upload> list=	Upload.dao.find("select t.id,b.type type,c.title ,t.user_id,t.filename,t.uploadtime from upload t ,type b, title c where t.title=c.tid and t.type=b.type_code ");
		Page<Upload> page=Upload.dao.paginate(getParaToInt(0, 1), 50,role,username);
		setAttr("uploadPage",page);
		setAttr("role",role);
		render("upload.html");
	}
	
	public void add(){
		setAttr("title",Title.dao.find("select * from title where iselected=0"));
		setAttr("type", Type.dao.find("select * from type where ifuser=1"));
		setAttr("teacher", Teacher.dao.find("select * from teacher"));
		render("add.html");
	}
	public void save() {
		UploadFile file=getFile("file");
		//String fileName=file.getFileName().substring(0, file.getFileName().length()-4);
		//String[] str=fileName.split("_");
		//String path=PathKit.getWebRootPath()+"\\file\\"+System.currentTimeMillis()+".pdf";
		String path=System.currentTimeMillis()+".pdf";
		File newFile=new File(path);
		File oldFile=file.getFile();
		oldFile.renameTo(newFile);
		
		List<Pdfimage> list=Pdfimage.changePdfToImg(newFile);
		Db.batchSave(list, list.size());
		
		String type=getPara("type");
		String title=getPara("title");
		String keywords=getPara("keywords");
		
		String filename=file.getFileName();
		//oldFile.delete();
		
		Upload upload=new Upload(); 
		upload.setKeywords(keywords);
		upload.setUsername((String)getSessionAttr("username"));
		upload.set("type", type)
		.set("title", title)
		.set("realfilename", filename)
		.set("uploadtime", new Date())
		.set("filename", newFile.getName())
		.save();
		Db.update("update title t set t.iselected=1 where t.tid="+"'"+title+"'");
		index();
	}
	
	public void show() {
	String id=getPara("id");
	Upload upload=Upload.dao.find(" select t.id,b.type type,c.title ,d.realname username,t.keywords,t.realfilename,t.filename,t.uploadtime,t.score,t.comment from upload t ,type b, title c ,user d where t.username=d.username and t.title=c.tid and t.type=b.type_code and t.ndelete='0' and t.id= "+id).get(0);
	setAttr("upload",upload);
	setAttr("role",getSessionAttr("role"));
	setAttr("pagesize",Db.queryInt("select b.pagesize from upload t ,(select distinct filename,pagesize from pdfimage) b where t.filename=b.filename and t.id="+id));
	render("show.html");
	}
	
	public void approve(){
		String id=getPara("id");
		String score=getPara("score");
		String comment=getPara("comment");
		Db.update("update upload set score="+"'"+score+"'" +",comment="+"'"+comment+"'"+"where id="+"'"+id+"'");
		Upload upload=Upload.dao.find(" select t.id,b.type type,c.title ,t.username,t.realfilename,t.filename,t.uploadtime,t.score,t.comment from upload t ,type b, title c where t.title=c.tid and   t.ndelete='0' and t.type=b.type_code and t.id= "+id).get(0);
		setAttr("upload",upload) ;
		render("show.html");
	}
	
	public void delete(){
		String id=getPara("id");
		Db.update("update upload t set t.ndelete='1' where t.id="+"'"+id+"'");
		index();
	}
	
	public void getitle(){
		String id=getPara("id");
		List<Title> list=Title.dao.find(" select b.* from teacher t ,title b where t.teacherno=b.username and b.iselected='0' and t.id="+"'"+id+"'");
		renderJson(list);

	}
	public void getTeacher(){
		String tid=getPara("tid");
		Teacher teacher=Teacher.dao.findById(tid);
		renderJson(teacher);
	}
	public void query(){
		String role=getSessionAttr("role");
		String author=getPara("author");
		Page<Upload> page=Upload.dao.paginateByCondition(getParaToInt(0, 1), 50,author);
		setAttr("uploadPage",page);
		setAttr("role",role);
		setAttr("author",author);
		render("upload.html");
		
	}
}
