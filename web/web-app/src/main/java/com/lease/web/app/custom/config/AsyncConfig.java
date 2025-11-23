package com.lease.web.app.custom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/11/20
 */
@Configuration
@EnableAsync
public class AsyncConfig {

	@Bean("browseHistoryExecutor")
	public Executor browseHistoryExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		// 根据业务特点配置：
		// - 浏览历史：并发量中等，可接受一定延迟
		executor.setCorePoolSize(3);           // 日常并发水平
		executor.setMaxPoolSize(8);            // 应对流量高峰
		executor.setQueueCapacity(50);         // 适中的缓冲
		executor.setKeepAliveSeconds(60);      // 空闲线程存活时间

		// 拒绝策略选择（根据业务容忍度）：
		// - DiscardPolicy: 直接丢弃（对浏览历史可接受）
		// - CallerRunsPolicy: 由调用线程执行（保证不丢失）
		// - DiscardOldestPolicy: 丢弃队列中最老的任务
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

		executor.setThreadNamePrefix("browse-history-");
		executor.initialize();
		return executor;
	}

}
