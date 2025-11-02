package com.lease.web.admin.controller.lease;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lease.common.result.Result;
import com.lease.model.entity.LeaseAgreement;
import com.lease.model.enums.LeaseStatus;
import com.lease.web.admin.service.LeaseAgreementService;
import com.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.lease.web.admin.vo.agreement.AgreementVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Tag(name = "租约管理")
@RestController
@RequestMapping("/admin/agreement")
@RequiredArgsConstructor
public class LeaseAgreementController {

    private final LeaseAgreementService leaseAgreementService;

    @Operation(summary = "保存或修改租约信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody LeaseAgreement leaseAgreement) {
        leaseAgreementService.saveOrUpdate(leaseAgreement);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询租约列表")
    @GetMapping("page")
    public Result<IPage<AgreementVo>> page(@RequestParam long current, @RequestParam long size, AgreementQueryVo queryVo) {
        IPage<AgreementVo> agreementVoPage = new Page<>(current, size);
        IPage<AgreementVo> resultPage = leaseAgreementService.pageItem(agreementVoPage, queryVo);
        return Result.ok(resultPage);
    }

    @Operation(summary = "根据id查询租约信息")
    @GetMapping(name = "getById")
    public Result<AgreementVo> getById(@RequestParam Long id) {
        AgreementVo agreementVo = leaseAgreementService.getAgreementVoById(id);
        return Result.ok(agreementVo);
    }

    @Operation(summary = "根据id删除租约信息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        leaseAgreementService.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "根据id更新租约状态")
    @PostMapping("updateStatusById")
    public Result updateStatusById(@RequestParam Long id, @RequestParam LeaseStatus status) {
        LambdaUpdateWrapper<LeaseAgreement> updateWrapper = new LambdaUpdateWrapper<LeaseAgreement>()
                .eq(LeaseAgreement::getId, id)
                .set(LeaseAgreement::getStatus, status);
        leaseAgreementService.update(updateWrapper);
        return Result.ok();
    }

}

