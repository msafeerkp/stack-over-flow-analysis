package org.stackoverflow.analysis.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CommentModel {
	
	private Map<String, String> owner;
	private boolean can_flag;
	private boolean edited;
	private int score;
	private String post_type;
	private long creation_date; 
	private long post_id;
	private long comment_id;
	private String link;
	private String body;
	public Map<String, String> getOwner() {
		return owner;
	}
	public void setOwner(Map<String, String> owner) {
		this.owner = owner;
	}
	public boolean isCan_flag() {
		return can_flag;
	}
	public void setCan_flag(boolean can_flag) {
		this.can_flag = can_flag;
	}
	public boolean isEdited() {
		return edited;
	}
	public void setEdited(boolean edited) {
		this.edited = edited;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getPost_type() {
		return post_type;
	}
	public void setPost_type(String post_type) {
		this.post_type = post_type;
	}
	public long getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(long creation_date) {
		this.creation_date = creation_date;
	}
	public long getPost_id() {
		return post_id;
	}
	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}
	public long getComment_id() {
		return comment_id;
	}
	public void setComment_id(long comment_id) {
		this.comment_id = comment_id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	

}
