package com.lease.common.mybatislpus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/11
 */
@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {
	@Override
	public void insertFill(MetaObject metaObject) {
		this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
	}
}
