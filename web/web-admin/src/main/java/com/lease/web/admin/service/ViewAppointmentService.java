package com.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lease.model.entity.ViewAppointment;
import com.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.lease.web.admin.vo.appointment.AppointmentVo;

/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */
    
public interface ViewAppointmentService extends IService<ViewAppointment>{


	IPage<AppointmentVo> findAppointmentVo(IPage<AppointmentVo> appointmentVoPage, AppointmentQueryVo queryVo);
}
