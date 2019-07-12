package com.alan.eventservice.dao;


import com.alan.eventservice.model.Site;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SiteMapper {
    List<Site> getAll();
}
