package com.lease.web.app.controller.login;


import com.lease.common.exception.LeaseException;
import com.lease.common.login.LoginUserHolder;
import com.lease.common.result.Result;
import com.lease.common.result.ResultCodeEnum;
import com.lease.web.app.service.LoginService;
import com.lease.web.app.vo.user.LoginVo;
import com.lease.web.app.vo.user.UserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Tag(name = "登录管理")
@RestController
@RequestMapping("/app/")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("login/getCode")
    @Operation(summary = "获取短信验证码")
    public Result getCode(@RequestParam String phone) {
        loginService.getSMSCode(phone);
        return Result.ok();
    }

    @PostMapping("login")
    @Operation(summary = "登录")
    public Result<String> login(@RequestBody LoginVo loginVo) {
        if(!StringUtils.hasText(loginVo.getPhone())) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_PHONE_EMPTY);
        }
        if(!StringUtils.hasText(loginVo.getCode())) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EMPTY);
        }
        String jwt = loginService.login(loginVo);
        return Result.ok(jwt);
    }

    @GetMapping("info")
    @Operation(summary = "获取登录用户信息")
    public Result<UserInfoVo> info() {
        Long userId = LoginUserHolder.getLoginUser().getUserId();
        UserInfoVo userInfoVo = loginService.getUserInfoByUserId(userId);
        return Result.ok(userInfoVo);
    }
}

