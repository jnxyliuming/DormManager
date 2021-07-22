package com.lm.dorm.bean;

public class User {
	private Integer id;
	private String name;
	private String password;
	private String stuCode;
	private String sex;
	private String tel;
	private Integer dormBuildId;
	private String dormCode;
	private Integer roleId;
	private Integer disabled;
	private Integer createUserId;
	
	private DormBuild dormBuild;
	public User() {
		super();
	}
	
	public User( String name, String password, String sex, String tel, Integer dormBuildId,
			 Integer roleId) {
		super();
		this.password=password;
		this.name = name;
		this.sex = sex;
		this.tel = tel;
		this.dormBuildId = dormBuildId;
		this.roleId = roleId;
	}

	public User(String name, String password, String sex, String tel, Integer dormBuildId, Integer roleId, Integer createUserId, Integer disabled,String stu_code) {
		this.name=name;
		this.password=password;
		this.sex=sex;
		this.tel=tel;
		this.dormBuildId=dormBuildId;
		this.roleId=roleId;
		this.roleId=roleId;
		this.createUserId=createUserId;
		this.disabled=disabled;
		this.stuCode = stu_code;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStuCode() {
		return stuCode;
	}
	public void setStuCode(String stuCode) {
		this.stuCode = stuCode;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getDormBuildId() {
		return dormBuildId;
	}
	public void setDormBuildId(Integer dormBuildId) {
		this.dormBuildId = dormBuildId;
	}
	public String getDormCode() {
		return dormCode;
	}
	public void setDormCode(String dormCode) {
		this.dormCode = dormCode;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}
	public Integer getCeateUserId() {
		return createUserId;
	}
	public void setCeateUserId(Integer ceateUserId) {
		this.createUserId = ceateUserId;
	}
	public DormBuild getDormBuild() {
		return dormBuild;
	}
	public void setDormBuild(DormBuild dormBuild) {
		this.dormBuild = dormBuild;
	}
	
}
