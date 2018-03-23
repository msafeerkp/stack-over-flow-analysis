package org.stackoverflow.analysis.model;

public class SOFMetaModel {
	
	private String meta_type;
	private long creation_time;
	private long page_number;
	private int batch_size;
	private long message_time;
	public String getMeta_type() {
		return meta_type;
	}
	public void setMeta_type(String meta_type) {
		this.meta_type = meta_type;
	}
	public long getCreation_time() {
		return creation_time;
	}
	public void setCreation_time(long creation_time) {
		this.creation_time = creation_time;
	}
	public long getPage_number() {
		return page_number;
	}
	public void setPage_number(long page_number) {
		this.page_number = page_number;
	}
	public int getBatch_size() {
		return batch_size;
	}
	public void setBatch_size(int batch_size) {
		this.batch_size = batch_size;
	}
	public long getMessage_time() {
		return message_time;
	}
	public void setMessage_time(long message_time) {
		this.message_time = message_time;
	}
	
	

}
