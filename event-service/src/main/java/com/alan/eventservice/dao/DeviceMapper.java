package com.alan.eventservice.dao;



import com.alan.eventservice.model.Device;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeviceMapper {
//    @Select("select * from devices where dev_token =#{token}")
//    @Results({
//            @Result(property = "id", column = "dev_id"),
//            @Result(property = "siteId", column = "site_id"),
//            @Result(property = "token", column = "dev_token"),
//            @Result(property = "name", column = "dev_name"),
//            @Result(property = "info", column = "dev_info")
//    })
    Device getOneByToken( String token);
    List<Device> getRowsBySite(int siteId);
    Device getOneByName(String name);
}
