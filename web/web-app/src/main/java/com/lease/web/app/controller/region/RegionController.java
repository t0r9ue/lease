package com.lease.web.app.controller.region;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lease.common.result.Result;
import com.lease.model.entity.CityInfo;
import com.lease.model.entity.DistrictInfo;
import com.lease.model.entity.ProvinceInfo;
import com.lease.web.app.service.CityInfoService;
import com.lease.web.app.service.DistrictInfoService;
import com.lease.web.app.service.ProvinceInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "地区信息")
@RestController
@RequestMapping("/app/region")
@RequiredArgsConstructor
public class RegionController {

    private final ProvinceInfoService provinceInfoService;
    private final CityInfoService cityInfoService;
    private final DistrictInfoService districtInfoService;

    @Operation(summary = "查询省份信息列表")
    @GetMapping("province/list")
    public Result<List<ProvinceInfo>> listProvince() {
        List<ProvinceInfo> provinceInfos = provinceInfoService.list();
        return Result.ok(provinceInfos);
    }

    @Operation(summary = "根据省份id查询城市信息列表")
    @GetMapping("city/listByProvinceId")
    public Result<List<CityInfo>> listCityInfoByProvinceId(@RequestParam Long id) {
        LambdaQueryWrapper<CityInfo> queryWrapper = new LambdaQueryWrapper<CityInfo>()
                .eq(CityInfo::getProvinceId, id);
        List<CityInfo> cityInfoList = cityInfoService.list(queryWrapper);
        return Result.ok(cityInfoList);
    }

    @GetMapping("district/listByCityId")
    @Operation(summary = "根据城市id查询区县信息")
    public Result<List<DistrictInfo>> listDistrictInfoByCityId(@RequestParam Long id) {
        LambdaQueryWrapper<DistrictInfo> queryWrapper = new LambdaQueryWrapper<DistrictInfo>()
                .eq(DistrictInfo::getCityId, id);
        List<DistrictInfo> districtInfoList = districtInfoService.list(queryWrapper);
        return Result.ok(districtInfoList);
    }
}
