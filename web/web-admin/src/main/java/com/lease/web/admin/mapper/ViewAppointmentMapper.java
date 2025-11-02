package com.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lease.model.entity.ViewAppointment;
import com.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.lease.web.admin.vo.appointment.AppointmentVo;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */

public interface ViewAppointmentMapper extends BaseMapper<ViewAppointment> {
	IPage<AppointmentVo> selectAppointmentVoByPage(IPage<AppointmentVo> appointmentVoPage, AppointmentQueryVo queryVo);
}