package com.akv.newsie.Model.JSON.User;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class UserResponseJSON {

	@SerializedName("data")
	private UserDataJSON data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	@SerializedName("token")
	private String token;

	public UserDataJSON getData(){
		return data;
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

	public void setData(UserDataJSON data) {
		this.data = data;
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
		return new GsonBuilder().setPrettyPrinting().create().toJson(this, UserResponseJSON.class);
	}
}