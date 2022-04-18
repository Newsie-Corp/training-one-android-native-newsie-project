package com.akv.newsie.Model.Database.User;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.GsonBuilder;

@Entity
public class UserDataDB {

	private String rememberExp;

	private String companyId;

	private String lastLogin;

	private String dateCreated;

	private String oauthUid;

	private String avatar;

	private String ipAddress;

	private String forgotExp;

	private String rememberTime;

	private String fullName;

	private String lastActivity;

	private String topSecret;

	private String oauthProvider;

	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "userId")
	private Integer id;

	private String banned;

	private String verificationCode;

	private String email;

	private String username;

	public String getRememberExp(){
		return rememberExp;
	}

	public String getCompanyId(){
		return companyId;
	}

	public String getLastLogin(){
		return lastLogin;
	}

	public String getDateCreated(){
		return dateCreated;
	}

	public String getOauthUid(){
		return oauthUid;
	}

	public String getAvatar(){
		return avatar;
	}

	public String getIpAddress(){
		return ipAddress;
	}

	public String getForgotExp(){
		return forgotExp;
	}

	public String getRememberTime(){
		return rememberTime;
	}

	public String getFullName(){
		return fullName;
	}

	public String getLastActivity(){
		return lastActivity;
	}

	public String getTopSecret(){
		return topSecret;
	}

	public String getOauthProvider(){
		return oauthProvider;
	}

	public Integer getId(){
		return id;
	}

	public String getBanned(){
		return banned;
	}

	public String getVerificationCode(){
		return verificationCode;
	}

	public String getEmail(){
		return email;
	}

	public String getUsername(){
		return username;
	}

	public void setRememberExp(String rememberExp) {
		this.rememberExp = rememberExp;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setOauthUid(String oauthUid) {
		this.oauthUid = oauthUid;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setForgotExp(String forgotExp) {
		this.forgotExp = forgotExp;
	}

	public void setRememberTime(String rememberTime) {
		this.rememberTime = rememberTime;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setLastActivity(String lastActivity) {
		this.lastActivity = lastActivity;
	}

	public void setTopSecret(String topSecret) {
		this.topSecret = topSecret;
	}

	public void setOauthProvider(String oauthProvider) {
		this.oauthProvider = oauthProvider;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setBanned(String banned) {
		this.banned = banned;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return new GsonBuilder().setPrettyPrinting().create().toJson(this, UserDataDB.class);
	}
}