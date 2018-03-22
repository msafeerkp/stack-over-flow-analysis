package org.stackoverflow.analysis.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@JsonIgnoreProperties(ignoreUnknown=true)
public class QuestionModel {
	
	private List<String> tags;
	private Map<String, String> owner;
	private boolean can_close;
	private boolean can_flag;
	private int comment_count;
	private int delete_vote_count;
	private int reopen_vote_count;
	private int close_vote_count;
	private boolean is_answered;
	private int view_count;
	private int favorite_count;
	private int down_vote_count;
	private int up_vote_count;
	private long accepted_answer_id;
	private int answer_count;
	private int score;
	private long last_activity_date;
	private long creation_date;
	private long question_id;
	private String share_link;
	private String link;
	private String title;
	private String body;
	private long last_edit_date;
	private String closed_reason;
	//private Map<String, String> closed_details;
	private long closed_date;
	public String getClosed_reason() {
		return closed_reason;
	}
	public void setClosed_reason(String closed_reason) {
		this.closed_reason = closed_reason;
	}

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
	public boolean isCan_close() {
		return can_close;
	}
	public void setCan_close(boolean can_close) {
		this.can_close = can_close;
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
	public int getDelete_vote_count() {
		return delete_vote_count;
	}
	public void setDelete_vote_count(int delete_vote_count) {
		this.delete_vote_count = delete_vote_count;
	}
	public int getReopen_vote_count() {
		return reopen_vote_count;
	}
	public void setReopen_vote_count(int reopen_vote_count) {
		this.reopen_vote_count = reopen_vote_count;
	}
	public int getClose_vote_count() {
		return close_vote_count;
	}
	public void setClose_vote_count(int close_vote_count) {
		this.close_vote_count = close_vote_count;
	}
	public boolean isIs_answered() {
		return is_answered;
	}
	public void setIs_answered(boolean is_answered) {
		this.is_answered = is_answered;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public int getFavorite_count() {
		return favorite_count;
	}
	public void setFavorite_count(int favorite_count) {
		this.favorite_count = favorite_count;
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
	public long getAccepted_answer_id() {
		return accepted_answer_id;
	}
	public void setAccepted_answer_id(long accepted_answer_id) {
		this.accepted_answer_id = accepted_answer_id;
	}
	public int getAnswer_count() {
		return answer_count;
	}
	public void setAnswer_count(int answer_count) {
		this.answer_count = answer_count;
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
	public long getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(long question_id) {
		this.question_id = question_id;
	}
	public String getShare_link() {
		return share_link;
	}
	public void setShare_link(String share_link) {
		this.share_link = share_link;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
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
	/*public Map<String, String> getClosed_details() {
		return closed_details;
	}
	public void setClosed_details(Object closed_details) {
		try {
			if(closed_details !=null) {
				this.closed_details = new HashMap<>();
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode node = mapper.convertValue(closed_details, ObjectNode.class);
				if(node.has("reason")) {
					this.closed_details.put("reason", node.get("reason").textValue());
				}
				if(node.has("on_hold")) {
					this.closed_details.put("on_hold", node.get("on_hold").textValue());
				}
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}*/
	public long getClosed_date() {
		return closed_date;
	}
	public void setClosed_date(long closed_date) {
		this.closed_date = closed_date;
	}
	
	
	
	

}
