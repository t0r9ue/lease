package com.lease.web.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lease.model.entity.ViewAppointment;
import com.lease.web.app.vo.appointment.AppointmentItemVo;

import java.util.List;

/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/11/13
 */
    
public interface ViewAppointmentMapper extends BaseMapper<ViewAppointment> {
	List<AppointmentItemVo> selectListItemByUserId(Long userId);
}