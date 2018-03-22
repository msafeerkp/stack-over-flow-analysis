package org.stackoverflow.analysis.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TagModel {
	
	private List<String> synonyms;
	private long last_activity_date;
	private boolean has_synonyms;
	private boolean is_moderator_only;
	private boolean is_required;
	private long count;
	private String name;
	public List<String> getSynonyms() {
		return synonyms;
	}
	public void setSynonyms(List<String> synonyms) {
		this.synonyms = synonyms;
	}
	public long getLast_activity_date() {
		return last_activity_date;
	}
	public void setLast_activity_date(long last_activity_date) {
		this.last_activity_date = last_activity_date;
	}
	public boolean isHas_synonyms() {
		return has_synonyms;
	}
	public void setHas_synonyms(boolean has_synonyms) {
		this.has_synonyms = has_synonyms;
	}
	public boolean isIs_moderator_only() {
		return is_moderator_only;
	}
	public void setIs_moderator_only(boolean is_moderator_only) {
		this.is_moderator_only = is_moderator_only;
	}
	public boolean isIs_required() {
		return is_required;
	}
	public void setIs_required(boolean is_required) {
		this.is_required = is_required;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	
	
	

}
