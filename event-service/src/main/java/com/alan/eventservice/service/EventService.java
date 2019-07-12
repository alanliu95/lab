package com.alan.eventservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class EventService {
    @Autowired
    private EventHandler eventHandler;
    private Thread thread;
    @PostConstruct
    public void start(){
        this.thread= new Thread(this.eventHandler);
        thread.start();
        //Device device=deviceMapper.getOneByToken("123");
        //System.out.println(device);
    }

    public String getRecord(String devName) throws Exception{
         return this.eventHandler.getCurrRecord(devName);
    }
    public String getCurrDevOnline(String devName) throws Exception{
        return this.eventHandler.getCurrDevOnline(devName);
    }
    @PreDestroy
    public void stop(){
        this.eventHandler.shutdown();
        //System.out.println("close resource");
    }
}
