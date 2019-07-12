package com.alan.eventservice.service;

import java.io.IOException;
import java.util.*;

import com.alan.eventservice.dao.DeviceMapper;
import com.alan.eventservice.dao.RecordMapper;
import com.alan.eventservice.model.DevOnline;
import com.alan.eventservice.model.Device;
import com.alan.eventservice.model.Record;
import com.alan.eventservice.util.PropsReader;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventHandler implements Runnable {
    private final KafkaConsumer<String, String> consumer;
    private final List<String> topics;
    //private Map<String,String> currRecords;
    // map 是否线程安全，是否会出现竞争条件
    private Map<String, Record> currRecords;
    private Record record;

    private Map<String, DevOnline> currDevices;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    public EventHandler() throws IOException {
        Properties props = PropsReader.read("consumer.properties");
        this.consumer = new KafkaConsumer<>(props);
        this.topics = Arrays.asList(props.getProperty("topic").split(","));
        currRecords = new HashMap<>();
        currDevices = new HashMap<>();

    }
    //查询条件是devName
    public String getCurrRecord(String devName)  {
        String res="no";
        try {
            //查询devices表获取token
            Device dev = deviceMapper.getOneByName(devName);
            String token = dev.getToken();
            Record rec=currRecords.get(token);
            if(rec !=null){
                res=this.objectMapper.writeValueAsString(rec);
            //System.out.println("getCurrRecord:"+tmp);
            //if(tmp != null) res=tmp;
            }
        } catch (Exception fromJackson) {
            fromJackson.printStackTrace();
            res="no";
        }
        return res;
    }

    public String getCurrDevOnline(String devName){
        String res="no";
        try {
            DevOnline devOnline=currDevices.get(devName);
            if(devOnline !=null){
                res=this.objectMapper.writeValueAsString(devOnline);
                //System.out.println("getCurrRecord:"+tmp);
                //if(tmp != null) res=tmp;
            }
        } catch (Exception fromJackson) {
            fromJackson.printStackTrace();
            //res="no";
        }
        return res;
    }

    @Override
    public void run() {
        try {
            consumer.subscribe(topics);
            while (true) {
                //消息原始字符串只有token，ts,cpu,mem等字段
                ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
                for (ConsumerRecord<String, String> record : records) {
                    switch (record.topic()){
                        case "lab":
                            //将消息原始字符串转成record对象，devId字段为空
                            this.record = this.objectMapper.readValue(record.value(), Record.class);
                            //为了record对象能入表，必须获取record的devId,检索数据库后，补全devId字段（感觉是比较愚蠢的做法）
                            this.record.setDevId(this.deviceMapper.getOneByToken(this.record.getDevToken()).getId());
                            //System.out.println(this.record);
                            //刷新map 这个map主要为dashboard 服务，为什么key 是设备token 而不直接是设备name, dashboard查询条件时设备name
                            currRecords.put(this.record.getDevToken(), this.record.newCopy());
                            //record对象入表，每条记录为了能入表，都执行一次数据库查询获取devId,方法不理想
                            //this.recordMapper.insertOne(this.record);
                            break;
                        case "online":
                            DevOnline devOnline;
                            //System.out.println(record.value());
                            //将消息原始字符串转成devOnline对象
                            devOnline = this.objectMapper.readValue(record.value(), DevOnline.class);
                            //为了record对象能入表，必须获取record的devId,检索数据库后，补全devId字段（感觉是比较愚蠢的做法）
                            String devName=this.deviceMapper.getOneByToken(devOnline.getDevToken()).getName();
                            //System.out.println(this.record);
                            currDevices.put(devName, devOnline);
                            break;

                    }

                }
            }
        } catch (WakeupException e) {
            // ignore for shutdown
        } catch (IOException e) {
            //jackson 解析失败
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

    public void shutdown() {
        consumer.wakeup();
    }
}

