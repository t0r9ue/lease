package com.lease.web.admin.service.impl;

import com.lease.common.minio.MinioProperties;
import com.lease.web.admin.service.FileService;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/16
 */
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

	private final MinioProperties properties;
	private final MinioClient minioClient;

	@Override
	public String uploadForMinio(MultipartFile file) {
		try {
			//1、判断桶是是否存在
			boolean exists = minioClient.bucketExists(BucketExistsArgs.builder()
					.bucket(properties.getBucketName())
					.build());
			//2、不存在就创建桶
			if (!exists) {
				minioClient.makeBucket(MakeBucketArgs
						.builder()
						.bucket(properties.getBucketName())
						.build());

				String bucketPolicy = """
						{
						  "Statement" : [ {
						    "Action" : "s3:GetObject",
						    "Effect" : "Allow",
						    "Principal" : "*",
						    "Resource" : "arn:aws:s3:::%s/*"
						  } ],
						  "Version" : "2012-10-17"
						}""".formatted(properties.getBucketName());

				minioClient.setBucketPolicy(SetBucketPolicyArgs
						.builder()
						.bucket(properties.getBucketName())
						.config(bucketPolicy)
						.build());

			}
			//3、通过stream上传到Minio
			String fileName = new SimpleDateFormat("yyyyMMdd")
					.format(new Date()) + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
			minioClient.putObject(PutObjectArgs
					.builder()
					.bucket(properties.getBucketName())
					// 使用MultipartFile的InputStream上传文件, 指定文件大小后, partSize只需要填写-1
					.stream(file.getInputStream(), file.getSize(), -1)
					// 指定file的类型，避免需要打开图片，而浏览器识别为下载图片
					.contentType(file.getContentType())
					.object(fileName)
					.build());

			return String.join("/", properties.getEndpoint(), properties.getBucketName(), fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("上传文件出现错误");
	}

}
