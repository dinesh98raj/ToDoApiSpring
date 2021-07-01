package com.example.springsecurityjwt.models;


public class MyListItem {
	private Integer taskNo;
	private String listItem;
	private boolean status;
	private boolean toedit;
	
	
	public MyListItem() {
	}

	public MyListItem(Integer taskNo, String listItem, boolean status, boolean toedit) {
		this.taskNo = taskNo;
		this.listItem = listItem;
		this.status = status;
		this.toedit = toedit;
	}

	public MyListItem(ListItem lstitm) {
		this.taskNo = lstitm.getTaskNo();
		this.listItem = lstitm.getListItem();
		this.status = lstitm.isStatus();
		this.toedit = lstitm.isToedit();
	}
	
	public Integer getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(Integer taskNo) {
		this.taskNo = taskNo;
	}

	public String getListItem() {
		return listItem;
	}

	public void setListItem(String listItem) {
		this.listItem = listItem;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isToedit() {
		return toedit;
	}

	public void setToedit(boolean toedit) {
		this.toedit = toedit;
	}
}
