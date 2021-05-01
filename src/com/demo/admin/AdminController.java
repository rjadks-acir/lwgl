package com.demo.admin;

import java.util.List;
import com.demo.common.model.Upload;
import com.demo.common.model.User;
import com.demo.common.model.UserInfo;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;


public class AdminController extends Controller{
	
	public void login(){
		User user=getModel(User.class);
		
	String sql="select * from user where username="+"'"+user.getUsername()+"'"+" and password="+"'"+user.getPassword()+"'"+" and role= "+"'"+user.getRole()+"'";
		List<User> list=User.dao.find(sql);
		if(list.size()>0){
			setSessionAttr("username",user.getUsername());
			setSessionAttr("realname",Db.queryStr("select realname from user  t where t.username= "+"'"+user.getUsername()+"'"));
			setSessionAttr("role", user.getRole());
			render("/index/index.html");
		}else{
			setAttr("error", "用户名或密码错误");
			render("/admin/login.html");
			
		}
		
	}
	
	public void userinfo(){
		String username=(String)getSessionAttr("username");
		String realname=(String)getSessionAttr("realname");
		String role=(String)getSessionAttr("role");
		long submitcount=Upload.dao.submitCount(username);
		UserInfo userinfo=new UserInfo();
		userinfo.setUsername(username);
		userinfo.setRealname(realname);
		userinfo.setSubmitcount(submitcount);
		userinfo.setRole(role);
		renderJson("userinfo",userinfo);
	}
	
	public void logout(){
		removeSessionAttr("username");
		removeSessionAttr("role");
		removeSessionAttr("realname");
		render("/admin/login.html");
	}
}
