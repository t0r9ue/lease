package com.lease.common.minio;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/16
 */
@Configuration
//@ConfigurationPropertiesScan("com.lease.common.minio")
@EnableConfigurationProperties(MinioProperties.class)
@RequiredArgsConstructor
public class MinioConfiguration {

    private final MinioProperties properties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(properties.getEndpoint())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();
    }

}
