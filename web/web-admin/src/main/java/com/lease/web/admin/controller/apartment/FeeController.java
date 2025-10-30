package com.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lease.common.result.Result;
import com.lease.model.entity.FeeKey;
import com.lease.model.entity.FeeValue;
import com.lease.web.admin.service.FeeKeyService;
import com.lease.web.admin.service.FeeValueService;
import com.lease.web.admin.vo.fee.FeeKeyVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "房间杂费管理")
@RestController
@RequestMapping("/admin/fee")
@RequiredArgsConstructor
public class FeeController {

    private final FeeKeyService feeKeyService;
    private final FeeValueService feeValueService;

    @Operation(summary = "保存或更新杂费名称")
    @PostMapping("key/saveOrUpdate")
    public Result saveOrUpdateFeeKey(@RequestBody FeeKey feeKey) {
        boolean b = feeKeyService.saveOrUpdate(feeKey);
        return Result.ok();
    }

    @Operation(summary = "保存或更新杂费值")
    @PostMapping("value/saveOrUpdate")
    public Result saveOrUpdateFeeValue(@RequestBody FeeValue feeValue) {
        boolean b = feeValueService.saveOrUpdate(feeValue);
        return Result.ok();
    }


    @Operation(summary = "查询全部杂费名称和杂费值列表")
    @GetMapping("list")
    public Result<List<FeeKeyVo>> feeInfoList() {
        List<FeeKeyVo> feeKeyVos = feeKeyService.feeInfoList();
        return Result.ok(feeKeyVos);
    }

    @Operation(summary = "根据id删除杂费名称")
    @DeleteMapping("key/deleteById")
    public Result deleteFeeKeyById(@RequestParam Long feeKeyId) {
        //1、删除FeeKey表中的数据
        boolean b = feeKeyService.removeById(feeKeyId);
        //2、删除FeeValue表中对应的数据
        LambdaQueryWrapper<FeeValue> queryWrapper = new LambdaQueryWrapper<FeeValue>()
                .eq(FeeValue::getFeeKeyId, feeKeyId);
        boolean remove = feeValueService.remove(queryWrapper);
        return Result.ok();
    }

    @Operation(summary = "根据id删除杂费值")
    @DeleteMapping("value/deleteById")
    public Result deleteFeeValueById(@RequestParam Long id) {
        feeValueService.removeById(id);
        return Result.ok();
    }
}
