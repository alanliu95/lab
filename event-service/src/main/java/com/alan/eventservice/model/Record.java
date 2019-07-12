package com.alan.eventservice.model;

public class Record {
    private int id;
    private int devId;
    private String devToken;
    private String ts;
    private float cpu;
    private float mem;

    public Record newCopy(){
        Record r=new Record();
        r.id=id;
        r.devId=devId;
        r.devToken=devToken;
        r.ts=ts;
        r.cpu=cpu;
        r.mem=mem;
        return r;
    }
    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", devId=" + devId +
                ", devToken=" + devToken +
                ", ts='" + ts + '\'' +
                ", cpu=" + cpu +
                ", mem=" + mem +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }

    public String getDevToken() {
        return devToken;
    }

    public void setDevToken(String devToken) {
        this.devToken = devToken;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public float getCpu() {
        return cpu;
    }

    public void setCpu(float cpu) {
        this.cpu = cpu;
    }

    public float getMem() {
        return mem;
    }

    public void setMem(float mem) {
        this.mem = mem;
    }
}
