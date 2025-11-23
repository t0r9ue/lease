package com.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.model.entity.LeaseTerm;
import com.lease.web.app.mapper.LeaseTermMapper;
import com.lease.web.app.service.LeaseTermService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【lease_term(租期)】的数据库操作Service实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
@RequiredArgsConstructor
public class LeaseTermServiceImpl extends ServiceImpl<LeaseTermMapper, LeaseTerm>
        implements LeaseTermService {

	private final LeaseTermMapper leaseTermMapper;

	@Override
	public List<LeaseTerm> getByRoomId(Long id) {
		return leaseTermMapper.selectByRoomId(id);
	}
}




