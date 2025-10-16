package com.lease.web.admin.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/16
 */
public interface FileService {
	String uploadForMinio(MultipartFile file);
}
