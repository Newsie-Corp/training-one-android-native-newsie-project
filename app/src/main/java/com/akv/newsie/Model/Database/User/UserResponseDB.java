package com.akv.newsie.Model.Database.User;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.GsonBuilder;

@Entity
public class UserResponseDB {

	@PrimaryKey(autoGenerate = true)
	private Integer userResponseId;

	private String message;

	private boolean status;

	private String token;

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