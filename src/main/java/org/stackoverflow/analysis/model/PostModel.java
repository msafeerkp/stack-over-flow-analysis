package org.stackoverflow.analysis.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class PostModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7892093455267205158L;
	
	private int score;
	private long last_activity_date;
	private long creation_date;
	private String post_type;
	private long post_id;
	private String link;
	private long last_edit_date;
	private Map<String, String> owner;
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public long getLast_activity_date() {
		return last_activity_date;
	}
	public void setLast_activity_date(long last_activity_date) {
		this.last_activity_date = last_activity_date;
	}
	public long getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(long creation_date) {
		this.creation_date = creation_date;
	}
	public String getPost_type() {
		return post_type;
	}
	public void setPost_type(String post_type) {
		this.post_type = post_type;
	}
	public long getPost_id() {
		return post_id;
	}
	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public long getLast_edit_date() {
		return last_edit_date;
	}
	public void setLast_edit_date(long last_edit_date) {
		this.last_edit_date = last_edit_date;
	}
	public Map<String, String> getOwner() {
		return owner;
	}
	public void setOwner(Map<String, String> owner) {
		this.owner = owner;
	}
	
	@Override
	public String toString() {
		return "PostModel [score=" + score + ", last_activity_date=" + last_activity_date + ", creation_date="
				+ creation_date + ", post_type=" + post_type + ", post_id=" + post_id + ", link=" + link
				+ ", last_edit_date=" + last_edit_date + ", owner=" + owner + "]";
	}
	

	

}
