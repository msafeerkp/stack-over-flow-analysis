package org.stackoverflow.analysis.model;


import java.util.List;
import java.util.Map;

public class AnswerModel {
	
	private List<String> tags;
	private Map<String, String> owner;
	private boolean can_flag;
	private int comment_count;
	private int down_vote_count;
	private int up_vote_count;
	private boolean is_accepted;
	private int score;
	private long last_activity_date;
	private long creation_date;
	private long answer_id;
	private long question_id;
	private String title;
	private String body;
	private long last_edit_date;
	
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
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
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}
	public int getDown_vote_count() {
		return down_vote_count;
	}
	public void setDown_vote_count(int down_vote_count) {
		this.down_vote_count = down_vote_count;
	}
	public int getUp_vote_count() {
		return up_vote_count;
	}
	public void setUp_vote_count(int up_vote_count) {
		this.up_vote_count = up_vote_count;
	}
	public boolean isIs_accepted() {
		return is_accepted;
	}
	public void setIs_accepted(boolean is_accepted) {
		this.is_accepted = is_accepted;
	}
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
	public long getAnswer_id() {
		return answer_id;
	}
	public void setAnswer_id(long answer_id) {
		this.answer_id = answer_id;
	}
	public long getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(long question_id) {
		this.question_id = question_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public long getLast_edit_date() {
		return last_edit_date;
	}
	public void setLast_edit_date(long last_edit_date) {
		this.last_edit_date = last_edit_date;
	}
	
	
	

}
