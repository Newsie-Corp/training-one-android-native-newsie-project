package com.akv.newsie.Model.Database.User;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.GsonBuilder;

@Entity
public class UserResponseDB {

	@PrimaryKey(autoGenerate = true)
	private Integer userResponseId;

//	@Embedded
//	private UserDataDB data;

	private String message;

	private boolean status;

	private String token;

	public UserResponseDB() {
	}

//	public UserResponseDB(Integer userResponseId, UserDataDB data, String message, boolean status, String token) {
//		this.userResponseId = userResponseId;
//		this.data = data;
//		this.message = message;
//		this.status = status;
//		this.token = token;
//	}

//	public UserDataDB getData() {
//		return data;
//	}
//
//	public void setData(UserDataDB data) {
//		this.data = data;
//	}

	public Integer getUserResponseId() {
		return userResponseId;
	}

	public void setUserResponseId(Integer userResponseId) {
		this.userResponseId = userResponseId;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}

	public String getToken(){
		return token;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return new GsonBuilder().setPrettyPrinting().create().toJson(this, UserResponseDB.class);
	}
}