package com.peter.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserQueryCondition {
	private String username;
	@ApiModelProperty(value="用户年龄起始值")
	private int age;
	private int ageTo;
	private String others;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAgeTo() {
		return ageTo;
	}
	public void setAgeTo(int ageTo) {
		this.ageTo = ageTo;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
}
