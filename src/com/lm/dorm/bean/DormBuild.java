package com.lm.dorm.bean;

public class DormBuild {
	private Integer id;
	private String name;
	private String remark;
	private Integer disabled;
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(int disabled) {
		this.disabled = disabled;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	public DormBuild() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DormBuild(Integer id, String name, String remark, Integer disabled) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
		this.disabled = disabled;
	}




}
