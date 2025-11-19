package com.lease.web.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lease.model.entity.LeaseTerm;

import java.util.List;

/**
* @author liubo
* @description 针对表【lease_term(租期)】的数据库操作Service
* @createDate 2023-07-26 11:12:39
*/
public interface LeaseTermService extends IService<LeaseTerm> {
	List<LeaseTerm> getByRoomId(Long id);
}
