package com.alan.eventservice.web;

import com.alan.eventservice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class RecordController{
    @Autowired
    private EventService eventService;
    @GetMapping("/devices/{devName}")
    public String record(@PathVariable("devName") String devName)throws Exception{
        String str;
        //try{
            str=eventService.getRecord(devName);
       // }catch (Exception e){
       //     e.printStackTrace();
        //    str=null;
       // }
//        System.out.println("返回值是："+str);
         return str;
    }

    @GetMapping("/online/{devName}")
    public String online(@PathVariable("devName") String devName)throws Exception{
        String str=eventService.getCurrDevOnline(devName);
        return str;
    }
}
