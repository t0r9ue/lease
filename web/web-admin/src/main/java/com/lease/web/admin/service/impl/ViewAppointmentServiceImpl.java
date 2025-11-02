package com.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.model.entity.ViewAppointment;
import com.lease.web.admin.mapper.ViewAppointmentMapper;
import com.lease.web.admin.service.ViewAppointmentService;
import com.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.lease.web.admin.vo.appointment.AppointmentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */
    
@Service
@RequiredArgsConstructor
public class ViewAppointmentServiceImpl extends ServiceImpl<ViewAppointmentMapper, ViewAppointment> implements ViewAppointmentService{

	private final ViewAppointmentMapper viewAppointmentMapper;

	@Override
	public IPage<AppointmentVo> findAppointmentVo(IPage<AppointmentVo> appointmentVoPage, AppointmentQueryVo queryVo) {
		return viewAppointmentMapper.selectAppointmentVoByPage(appointmentVoPage, queryVo);
	}
}
