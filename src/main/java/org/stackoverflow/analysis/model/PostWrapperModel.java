package org.stackoverflow.analysis.model;

import java.io.Serializable;
import java.util.List;

public class PostWrapperModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4453924962610681264L;
	
	private List<PostModel> items;
	private long page;
	private long time;
	
	public List<PostModel> getItems() {
		return items;
	}
	public void setItems(List<PostModel> items) {
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
	@Override
	public String toString() {
		return "PostWrapperModel [items=" + items + ", page=" + page + ", time=" + time + "]";
	}	
	
	

}
