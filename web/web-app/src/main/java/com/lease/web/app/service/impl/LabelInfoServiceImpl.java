package com.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.model.entity.LabelInfo;
import com.lease.web.app.mapper.LabelInfoMapper;
import com.lease.web.app.service.LabelInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【label_info(标签信息表)】的数据库操作Service实现
* @createDate 2023-07-26 11:12:39
*/
@Service
@RequiredArgsConstructor
public class LabelInfoServiceImpl extends ServiceImpl<LabelInfoMapper, LabelInfo>
    implements LabelInfoService {

	private final LabelInfoMapper labelInfoMapper;

	@Override
	public List<LabelInfo> findByApartmentId(Long apartmentId) {
		return labelInfoMapper.selectByApartmentId(apartmentId);
	}

	@Override
	public List<LabelInfo> getByRoomId(Long id) {
		return labelInfoMapper.selectByRoomId(id);
	}
}




