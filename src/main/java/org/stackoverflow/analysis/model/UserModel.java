package org.stackoverflow.analysis.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserModel {
	
	private Map<String, Integer> badge_counts;
	private int view_count;
	private int down_vote_count;
	private int up_vote_count;
	private int answer_count;
	private int question_count;
	private long account_id;
	private boolean is_employee;
	private long last_modified_date;
	private long last_access_date;
	private int reputation_change_year;
	private int reputation_change_quarter;
	private int reputation_change_month;
	private int reputation_change_week;
	private int reputation_change_day;
	private int reputation;
	private long creation_date;
	private String user_type;
	private long user_id;
	private String about_me;
	private String location;
	private String website_url;
	private String link;
	private String profile_image;
	private String display_name;
	private int age;
	private int accept_rate;
	
	public Map<String, Integer> getBadge_counts() {
		return badge_counts;
	}
	public void setBadge_counts(Map<String, Integer> badge_counts) {
		this.badge_counts = badge_counts;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
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
	public int getAnswer_count() {
		return answer_count;
	}
	public void setAnswer_count(int answer_count) {
		this.answer_count = answer_count;
	}
	public int getQuestion_count() {
		return question_count;
	}
	public void setQuestion_count(int question_count) {
		this.question_count = question_count;
	}
	public long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}
	public boolean isIs_employee() {
		return is_employee;
	}
	public void setIs_employee(boolean is_employee) {
		this.is_employee = is_employee;
	}
	public long getLast_modified_date() {
		return last_modified_date;
	}
	public void setLast_modified_date(long last_modified_date) {
		this.last_modified_date = last_modified_date;
	}
	public long getLast_access_date() {
		return last_access_date;
	}
	public void setLast_access_date(long last_access_date) {
		this.last_access_date = last_access_date;
	}
	public int getReputation_change_year() {
		return reputation_change_year;
	}
	public void setReputation_change_year(int reputation_change_year) {
		this.reputation_change_year = reputation_change_year;
	}
	public int getReputation_change_quarter() {
		return reputation_change_quarter;
	}
	public void setReputation_change_quarter(int reputation_change_quarter) {
		this.reputation_change_quarter = reputation_change_quarter;
	}
	public int getReputation_change_month() {
		return reputation_change_month;
	}
	public void setReputation_change_month(int reputation_change_month) {
		this.reputation_change_month = reputation_change_month;
	}
	public int getReputation_change_week() {
		return reputation_change_week;
	}
	public void setReputation_change_week(int reputation_change_week) {
		this.reputation_change_week = reputation_change_week;
	}
	public int getReputation_change_day() {
		return reputation_change_day;
	}
	public void setReputation_change_day(int reputation_change_day) {
		this.reputation_change_day = reputation_change_day;
	}
	public int getReputation() {
		return reputation;
	}
	public void setReputation(int reputation) {
		this.reputation = reputation;
	}
	public long getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(long creation_date) {
		this.creation_date = creation_date;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getAbout_me() {
		return about_me;
	}
	public void setAbout_me(String about_me) {
		this.about_me = about_me;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getWebsite_url() {
		return website_url;
	}
	public void setWebsite_url(String website_url) {
		this.website_url = website_url;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAccept_rate() {
		return accept_rate;
	}
	public void setAccept_rate(int accept_rate) {
		this.accept_rate = accept_rate;
	}
	
}
