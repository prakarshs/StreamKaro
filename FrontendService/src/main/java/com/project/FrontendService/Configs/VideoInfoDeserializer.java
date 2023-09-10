package com.project.FrontendService.Configs;

import com.project.FrontendService.Objects.VideoInfo;
import org.apache.commons.lang.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;

public class VideoInfoDeserializer implements Deserializer<VideoInfo> {

    @Override
    public VideoInfo deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }

        try {
            // Deserialize the byte array back into VideoInfo fields
            ByteBuffer bufferBytes = ByteBuffer.wrap(data);
            int bytesRead = bufferBytes.getInt();
            byte[] buffer = new byte[data.length - Integer.BYTES];
            bufferBytes.get(buffer);

            return new VideoInfo(buffer, bytesRead);
        } catch (Exception e) {
            throw new SerializationException("Error deserializing VideoInfo", e);
        }
    }
}
