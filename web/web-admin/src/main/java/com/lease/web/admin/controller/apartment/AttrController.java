package com.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lease.common.result.Result;
import com.lease.model.entity.AttrKey;
import com.lease.model.entity.AttrValue;
import com.lease.web.admin.service.AttrKeyService;
import com.lease.web.admin.service.AttrValueService;
import com.lease.web.admin.vo.attr.AttrKeyVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "房间属性管理")
@RestController
@RequestMapping("/admin/attr")
@RequiredArgsConstructor
public class AttrController {

    private final AttrKeyService attrKeyService;
    private final AttrValueService attrValueService;

    @Operation(summary = "新增或更新属性名称")
    @PostMapping("key/saveOrUpdate")
    public Result saveOrUpdateAttrKey(@RequestBody AttrKey attrKey) {
        boolean b = attrKeyService.saveOrUpdate(attrKey);
        return Result.ok();
    }

    @Operation(summary = "新增或更新属性值")
    @PostMapping("value/saveOrUpdate")
    public Result saveOrUpdateAttrValue(@RequestBody AttrValue attrValue) {
        boolean b = attrValueService.saveOrUpdate(attrValue);
        return Result.ok();
    }


    @Operation(summary = "查询全部属性名称和属性值列表")
    @GetMapping("list")
    public Result<List<AttrKeyVo>> listAttrInfo() {
        List<AttrKeyVo> attrKeyVos = attrKeyService.listAttrInfo();
        return Result.ok(attrKeyVos);
    }

    @Operation(summary = "根据id删除属性名称")
    @DeleteMapping("key/deleteById")
    public Result removeAttrKeyById(@RequestParam Long attrKeyId) {
        //1、逻辑删除key表
        boolean b = attrKeyService.removeById(attrKeyId);
        //2、逻辑删除value表中对应的字段
        LambdaQueryWrapper<AttrValue> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AttrValue::getAttrKeyId, attrKeyId);
        boolean remove = attrValueService.remove(queryWrapper);
        return Result.ok();
    }

    @Operation(summary = "根据id删除属性值")
    @DeleteMapping("value/deleteById")
    public Result removeAttrValueById(@RequestParam Long id) {
        boolean b = attrValueService.removeById(id);
        return Result.ok();
    }

}
