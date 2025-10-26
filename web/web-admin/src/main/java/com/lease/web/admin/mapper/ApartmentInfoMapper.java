package com.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lease.model.entity.ApartmentInfo;
import com.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.lease.web.admin.vo.apartment.ApartmentQueryVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */

public interface ApartmentInfoMapper extends BaseMapper<ApartmentInfo> {
	IPage<ApartmentItemVo> selectByPage(IPage<ApartmentItemVo> page, @Param("queryVo") ApartmentQueryVo queryVo);
}