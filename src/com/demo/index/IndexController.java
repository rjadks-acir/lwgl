package com.demo.index;

import com.jfinal.core.Controller;

/**
 * IndexController
 */
public class IndexController extends Controller {
	public void index() {
		if(getSessionAttr("username")==null){
			render("/admin/login.html");
		}else{render("index.html");}
		
	}
}





