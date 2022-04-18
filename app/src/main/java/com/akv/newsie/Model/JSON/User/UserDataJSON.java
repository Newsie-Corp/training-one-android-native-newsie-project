package com.akv.newsie.Model.JSON.User;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class UserDataJSON {

	@SerializedName("remember_exp")
	private String rememberExp;

	@SerializedName("company_id")
	private String companyId;

	@SerializedName("last_login")
	private String lastLogin;

	@SerializedName("date_created")
	private String dateCreated;

	@SerializedName("oauth_uid")
	private String oauthUid;

	@SerializedName("avatar")
	private String avatar;

	@SerializedName("ip_address")
	private String ipAddress;

	@SerializedName("forgot_exp")
	private String forgotExp;

	@SerializedName("remember_time")
	private String rememberTime;

	@SerializedName("full_name")
	private String fullName;

	@SerializedName("last_activity")
	private String lastActivity;

	@SerializedName("top_secret")
	private String topSecret;

	@SerializedName("oauth_provider")
	private String oauthProvider;

	@SerializedName("id")
	private String id;

	@SerializedName("banned")
	private String banned;

	@SerializedName("verification_code")
	private String verificationCode;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
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

	public String getId(){
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

	public void setId(String id) {
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
		return new GsonBuilder().setPrettyPrinting().create().toJson(this, UserDataJSON.class);
	}
}