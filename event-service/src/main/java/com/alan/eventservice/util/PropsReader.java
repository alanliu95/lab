package com.alan.eventservice.util;

import java.io.IOException;
import java.util.Properties;


public class PropsReader {
    public static Properties read(String path) throws IOException {
        Properties props=new Properties();
        props.load(PropsReader.class.getResourceAsStream("/"+path));
        return props;
    }
}
