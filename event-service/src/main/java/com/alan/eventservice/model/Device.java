package com.alan.eventservice.model;

public class Device {
    private int id;
    private int siteId;
    private String name;
    private String token;
    private String info;

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", siteId=" + siteId +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", info='" + info + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
