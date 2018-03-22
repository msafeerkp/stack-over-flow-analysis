package org.stackoverflow.analysis.model;

import java.io.Serializable;
import java.util.List;

public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4453924962610681264L;
	
	private Object items;
	private long page;
	private long time;
	private String metaType;
	
	public Object getItems() {
		return items;
	}
	public void setItems(Object items) {
		this.items = items;
	}
	public long getPage() {
		return page;
	}
	public void setPage(long page) {
		this.page = page;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getMetaType() {
		return metaType;
	}
	public void setMetaType(String metaType) {
		this.metaType = metaType;
	}
	
	
	
	

}
