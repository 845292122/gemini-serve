package net.gemini.web.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.gemini.common.base.ResponseDTO;
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
    public ResponseDTO<String> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        return ResponseDTO.ok(token);
    }

    @ApiOperation(value = "登录信息")
    @GetMapping("info")
    public ResponseDTO<LoginInfoDto> loginInfo() {
        LoginInfoDto info = authService.getLoginInfo();
        return ResponseDTO.ok(info);
    }

    @ApiOperation(value = "注销登录")
    @PostMapping("logout")
    public ResponseDTO<Void> logout() {
        // 记录退出日志
        authService.logout();
        return ResponseDTO.ok();
    }

}
