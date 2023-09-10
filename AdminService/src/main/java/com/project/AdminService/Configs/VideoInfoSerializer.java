package com.project.AdminService.Configs;


import com.project.AdminService.Objects.VideoInfo;
import org.apache.commons.lang.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;

public class VideoInfoSerializer implements Serializer<VideoInfo> {
    @Override
    public byte[] serialize(String topic, VideoInfo videoInfo) {
        if (videoInfo == null) {
            return null;
        }

        try {
            // Serialize the fields of VideoInfo into a byte array
            byte[] buffer = videoInfo.getBuffer();
            int bytesRead = videoInfo.getBytesRead();

            ByteBuffer bufferBytes = ByteBuffer.allocate(Integer.BYTES + buffer.length);
            bufferBytes.putInt(bytesRead);
            bufferBytes.put(buffer);

            return bufferBytes.array();
        } catch (Exception e) {
            throw new SerializationException("Error serializing VideoInfo", e);
        }
    }
}
