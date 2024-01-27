package net.gemini.web.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.gemini.common.base.HttpResult;
import net.gemini.domain.auth.AuthService;
import net.gemini.domain.auth.LoginDto;
import net.gemini.domain.auth.LoginInfoDto;
import org.springframework.web.bind.annotation.*;

/**
 * @author edison
 */
@Tag(name = "登录")
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "登录")
    @PostMapping("login")
    public HttpResult<String> login(@RequestBody LoginDto loginDto) {
        // todo 记录登录日志
        String token = authService.login(loginDto);
        return HttpResult.ok(token);
    }

    @Operation(summary = "登录信息")
    @GetMapping("info")
    public HttpResult<LoginInfoDto> loginInfo() {
        LoginInfoDto info = authService.getLoginInfo();
        return HttpResult.ok(info);
    }

    @Operation(summary = "注销登录")
    @PostMapping("logout")
    public HttpResult<Void> logout() {
        // 记录退出日志
        authService.logout();
        return HttpResult.ok();
    }

}
