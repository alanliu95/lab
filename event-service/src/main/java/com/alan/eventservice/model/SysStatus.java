package com.alan.eventservice.model;

public class SysStatus {
	private String token;
	private String ts;
	private Float cpu;
	private Float mem;

	public SysStatus(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "SysStatus{" +
				"token='" + token + '\'' +
				", ts='" + ts + '\'' +
				", cpu=" + cpu +
				", mem=" + mem +
				'}';
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public Float getCpu() {
		return cpu;
	}

	public void setCpu(Float cpu) {
		this.cpu = cpu;
	}

	public Float getMem() {
		return mem;
	}

	public void setMem(Float mem) {
		this.mem = mem;
	}

	public void readStatus() {} //有问题

}
