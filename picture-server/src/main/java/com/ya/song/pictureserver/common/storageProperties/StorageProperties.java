package com.ya.song.pictureserver.common.storageProperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {
    private String system;
    private String rootLocation;
    private String serverLocation;
    private String requestLocation;
}
