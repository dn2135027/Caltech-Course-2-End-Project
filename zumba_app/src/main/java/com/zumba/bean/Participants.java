package com.zumba.bean;

public class Participants {
	private int pid;
	private String name;
	private int age;
	private int bid;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public Participants() {
		super();
		
	}
	public Participants(int pid, String name, int age, int bid) {
		super();
		this.pid = pid;
		this.name = name;
		this.age = age;
		this.bid = bid;
	}
	
}
