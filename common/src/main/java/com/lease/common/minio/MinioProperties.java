package com.lease.common.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/16
 */
@ConfigurationProperties(prefix = "minio")
@Data
public class MinioProperties {

	private String endpoint;
	private String accessKey;
	private String secretKey;
	private String bucketName;

}
