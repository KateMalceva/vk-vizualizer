package com.courseproject.kate.vkvisualizer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "vk-visualizer")
public class VkVisualizerConfig {
    private String token;
    private String access_token;
    private String version;
}
