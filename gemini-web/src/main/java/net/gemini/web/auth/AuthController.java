package net.gemini.web.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.gemini.common.base.HttpResult;
import net.gemini.domain.auth.AuthService;
import net.gemini.domain.auth.LoginDto;
import net.gemini.domain.auth.LoginInfoDto;
import org.springframework.web.bind.annotation.*;

/**
 * @author edison
 */
@Api(tags = "认证模块")
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public HttpResult<String> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        return HttpResult.ok(token);
    }

    @ApiOperation(value = "登录信息")
    @GetMapping("info")
    public HttpResult<LoginInfoDto> loginInfo() {
        LoginInfoDto info = authService.getLoginInfo();
        return HttpResult.ok(info);
    }

    @ApiOperation(value = "注销登录")
    @PostMapping("logout")
    public HttpResult<Void> logout() {
        // 记录退出日志
        authService.logout();
        return HttpResult.ok();
    }

}
