package com.demo.title;

import com.demo.common.model.Title;
import com.jfinal.core.Controller;

public class TitleController   extends Controller{
	
	public void index() {
		setAttr("role",getSessionAttr("role"));
		setAttr("titlePage", Title.dao.paginate(getParaToInt(0, 1), 20));
		render("title.html");
	}
	
	public void add() {
		render("add.html");
	}
	
	public void save() {
		getModel(Title.class).put("username", getSessionAttr("username")).save();
		redirect("/title");
	}
	
	public void edit() {
		setAttr("title", Title.dao.findById(getParaToInt()));
		render("edit.html");
	}
	
	public void update() {
		getModel(Title.class).update();
		redirect("/title");
	}
	
	public void delete() {
		Title.dao.deleteById(getParaToInt());
		redirect("/title");
	}
	
	public void query(){
		String title=getPara("title");
		setAttr("role",getSessionAttr("role"));
		setAttr("titlePage", Title.dao.paginateByCondition(getParaToInt(0, 1), 50,title));
		render("title.html");
		
	}

}
