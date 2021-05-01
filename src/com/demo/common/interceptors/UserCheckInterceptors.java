package com.demo.common.interceptors;

import java.util.HashSet;
import java.util.Set;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class UserCheckInterceptors  implements Interceptor{
	 private static final Set<String> excludedActionKeys = new HashSet<String>();
	
	 public static void addExcludedActionKey(String actionKey) {
		   excludedActionKeys.add(actionKey);
		  }
	@Override
	public void intercept(Invocation inv) {
		
		//添加拦截例外
			addExcludedActionKey("/admin/login");
			addExcludedActionKey("/admin/userinfo");
			addExcludedActionKey("/admin/logout");
			addExcludedActionKey("/");
			
			Controller controller=inv.getController();
		  if (!excludedActionKeys.contains(inv.getActionKey())) {
			  
			if( controller.getSessionAttr("username")==null){
				controller.setAttr("error", "请登录");
				controller.render("/admin/login.html");
			}else{
				  inv.invoke();
			}
			 
		    }else{
		    	  inv.invoke();
		    }
		

	}

}
