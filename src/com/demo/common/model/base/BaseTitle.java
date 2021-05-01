package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseTitle<M extends BaseTitle<M>> extends Model<M> implements IBean {

	public void setTid(java.lang.Integer tid) {
		set("tid", tid);
	}

	public java.lang.Integer getTid() {
		return get("tid");
	}

	public void setTitle(java.lang.String title) {
		set("title", title);
	}

	public java.lang.String getTitle() {
		return get("title");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}

	public java.lang.String getContent() {
		return get("content");
	}

	public void setUsername(java.lang.String username) {
		set("username", username);
	}

	public java.lang.String getUsername() {
		return get("username");
	}

	public void setIselected(java.lang.String iselected) {
		set("iselected", iselected);
	}

	public java.lang.String getIselected() {
		return get("iselected");
	}

}
