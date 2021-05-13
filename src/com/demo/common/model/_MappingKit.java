package com.demo.common.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {

	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("blog", "id", Blog.class);
		arp.addMapping("pdfimage", "id", Pdfimage.class);
		arp.addMapping("teacher", "id", Teacher.class);
		arp.addMapping("title", "tid", Title.class);
		arp.addMapping("type", "type_code", Type.class);
		arp.addMapping("upload", "id", Upload.class);
		arp.addMapping("user", "uid", User.class);
	}
}

