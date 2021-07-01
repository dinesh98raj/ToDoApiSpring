package com.example.springsecurityjwt.models;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="user_details")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer userid;
	@Column(name="username")
	private String userName;
	@Column(name="password")
	private String pass;
	
	/*@OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ListItem> lstitm;*/
	
	public User() {
	}

	public User(String userName, String pass) {
		this.userName = userName;
		this.pass = pass;
	}

	public int getUserid() {
		return userid;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String name) {
		this.userName = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	/*public List<ListItem> getLstitm() {
		return lstitm;
	}

	public void setLstitm(List<ListItem> lstitm) {
		this.lstitm = lstitm;
	}*/
	
		
}