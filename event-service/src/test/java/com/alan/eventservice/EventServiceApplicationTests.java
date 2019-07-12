package com.alan.eventservice;

import com.alan.eventservice.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServiceApplicationTests {
@Autowired
    EventService eventService;
    @Test
    public void contextLoads() {

        //System.out.println("start");
        //while (true);
        //eventService.start();
    }

}
