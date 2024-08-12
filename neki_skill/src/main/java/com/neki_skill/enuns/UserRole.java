package com.neki_skill.enuns;

public enum UserRole {
	
	ADMIN("admin"),
	USER("user");
	
	String role;

	UserRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	 

}
