package com.example.springsecurityjwt.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="list_item")
public class ListItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="taskno")
	private Integer taskNo;
	
	@Column(name="task")
	private String listItem;
	
	@Column(name="status")
	private boolean status;
	
	@Column(name="edit")
	private boolean toedit;
	
	/*@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userid", referencedColumnName="userid")*/
	
	@Column(name="userid")
	private Integer userid;
	
	public ListItem() {
	}

	public ListItem(String listItem, boolean status, boolean toedit, Integer userid) {
		this.listItem = listItem;
		this.status = status;
		this.toedit = toedit;
		this.userid = userid;
	}
	
	
	public Integer getTaskNo() {
		return taskNo;
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

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
}