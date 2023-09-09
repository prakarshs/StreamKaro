package com.project.FrontendService.Configs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Data
@Log4j2
public class KafkaConfig {

    private final List<byte[]> videoPackets = new ArrayList<>();
    @KafkaListener(topics = "Video-Stream", groupId = "stream-group")
    public void receiveStream(byte[] packet) {
        log.info("receiving now..");
        // Adding Packets To List
        videoPackets.add(packet);
    }

    // Method to retrieve the video packets for streaming
    public List<byte[]> getVideoPackets() {
        return videoPackets;
    }
}
