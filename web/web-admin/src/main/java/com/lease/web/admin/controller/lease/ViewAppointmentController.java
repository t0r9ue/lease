package com.lease.web.admin.controller.lease;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lease.common.result.Result;
import com.lease.model.entity.ViewAppointment;
import com.lease.model.enums.AppointmentStatus;
import com.lease.web.admin.service.ViewAppointmentService;
import com.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.lease.web.admin.vo.appointment.AppointmentVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Tag(name = "预约看房管理")
@RequestMapping("/admin/appointment")
@RestController
@RequiredArgsConstructor
public class ViewAppointmentController {

    private final ViewAppointmentService viewAppointmentService;

    @Operation(summary = "分页查询预约信息")
    @GetMapping("page")
    public Result<IPage<AppointmentVo>> page(@RequestParam long current, @RequestParam long size, AppointmentQueryVo queryVo) {
        IPage<AppointmentVo> appointmentVoPage = new Page<>(current, size);
        IPage<AppointmentVo> resultPage = viewAppointmentService.findAppointmentVo(appointmentVoPage, queryVo);
        return Result.ok(resultPage);
    }

    @Operation(summary = "根据id更新预约状态")
    @PostMapping("updateStatusById")
    public Result updateStatusById(@RequestParam Long id, @RequestParam AppointmentStatus status) {
        LambdaUpdateWrapper<ViewAppointment> updateWrapper = new LambdaUpdateWrapper<ViewAppointment>()
                .eq(ViewAppointment::getId, id)
                .set(ViewAppointment::getAppointmentStatus, status);
        viewAppointmentService.update(updateWrapper);
        return Result.ok();
    }

}
