package com.lease.web.app.controller.apartment;

import com.lease.common.result.Result;
import com.lease.web.app.service.ApartmentInfoService;
import com.lease.web.app.vo.apartment.ApartmentDetailVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "公寓信息")
@RequestMapping("/app/apartment")
@RequiredArgsConstructor
public class ApartmentController {

    private final ApartmentInfoService apartmentInfoService;

    @Operation(summary = "根据id获取公寓信息")
    @GetMapping("getDetailById")
    public Result<ApartmentDetailVo> getDetailById(@RequestParam Long id) {
        ApartmentDetailVo apartmentDetailVo = apartmentInfoService.getDetailById(id);
        return Result.ok(apartmentDetailVo);
    }
}
