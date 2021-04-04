package com.github.dhiraj072.kafkastarter;

import java.util.Properties;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    public Producer() {

    }

    @PostConstruct
    public void init() {

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");  
    }
}
