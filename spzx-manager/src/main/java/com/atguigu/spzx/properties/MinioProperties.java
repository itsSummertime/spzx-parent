package com.atguigu.spzx.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

//读取spzx.minio开头的所有配置

@ConfigurationProperties(prefix = "spzx.minio")
@Data
public class MinioProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucket;
}
