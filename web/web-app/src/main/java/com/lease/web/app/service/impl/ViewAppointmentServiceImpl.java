package com.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.common.login.LoginUserHolder;
import com.lease.model.entity.ViewAppointment;
import com.lease.web.app.mapper.ViewAppointmentMapper;
import com.lease.web.app.service.ApartmentInfoService;
import com.lease.web.app.service.ViewAppointmentService;
import com.lease.web.app.vo.apartment.ApartmentItemVo;
import com.lease.web.app.vo.appointment.AppointmentDetailVo;
import com.lease.web.app.vo.appointment.AppointmentItemVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【view_appointment(预约看房信息表)】的数据库操作Service实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
@RequiredArgsConstructor
public class ViewAppointmentServiceImpl extends ServiceImpl<ViewAppointmentMapper, ViewAppointment>
        implements ViewAppointmentService {

	private final ViewAppointmentMapper viewAppointmentMapper;
	private final ApartmentInfoService apartmentInfoService;

	@Override
	public List<AppointmentItemVo> listItem() {
		Long userId = LoginUserHolder.getLoginUser().getUserId();
		return viewAppointmentMapper.selectListItemByUserId(userId);
	}

	@Override
	public AppointmentDetailVo getDetailById(Long id) {
		ViewAppointment viewAppointment = viewAppointmentMapper.selectById(id);
		ApartmentItemVo apartmentItemVo = apartmentInfoService.findApartmentItemById(viewAppointment.getApartmentId());
		AppointmentDetailVo appointmentDetailVo = new AppointmentDetailVo();
		BeanUtils.copyProperties(viewAppointment, appointmentDetailVo);
		appointmentDetailVo.setApartmentItemVo(apartmentItemVo);
		return appointmentDetailVo;
	}
}




