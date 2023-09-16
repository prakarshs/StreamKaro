package com.project.FrontendService.Configs;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;

import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@Log4j2
public class KafkaConfig {


    @KafkaListener(topics = "Video-Stream", groupId = "group-02")
    public void receiveStream(byte[] videopacket) {

        log.info("received byte: {}",videopacket);
    }
}