package com.demo.common.model;

import com.demo.common.model.base.BaseTitle;
import com.jfinal.plugin.activerecord.Page;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Title extends BaseTitle<Title> {
	public static final Title dao = new Title();
	
	public Page<Title> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from title order by tid asc");
	}

	public Object paginateByCondition(Integer pageNumber, int pageSize, String title) {
		return paginate(pageNumber, pageSize, "select *", "from title where title like"+"'%"+title+"%'"+"order by tid asc");

	}
}
