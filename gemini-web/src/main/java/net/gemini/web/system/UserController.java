package net.gemini.web.system;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.gemini.common.base.HttpResult;
import net.gemini.common.base.PageDTO;
import net.gemini.domain.system.user.UserDomainService;
import net.gemini.domain.system.user.pojo.UserVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author edison
 */
@Tag(name = "用户模块")
@RestController
@RequestMapping("system/user")
@RequiredArgsConstructor
public class UserController {

    private final UserDomainService userDomainService;

    @Operation(summary = "用户列表")
    @GetMapping
    public HttpResult<PageDTO<UserVO>> list(UserVO userVO) {
        PageDTO<UserVO> page = userDomainService.getUserList(userVO);
        return HttpResult.ok(page);
    }

    @Operation(summary = "用户详情")
    @GetMapping("{userId}")
    public HttpResult<UserVO> info(@PathVariable("userId") Long userId) {
        UserVO userVO = userDomainService.getUserInfo(userId);
        return HttpResult.ok(userVO);
    }

    @Operation(summary = "添加用户")
    @PostMapping
    public HttpResult<Void> add(@Validated @RequestBody UserVO userVO) {
        userDomainService.addUser(userVO);
        return HttpResult.ok();
    }

    @Operation(summary = "修改用户")
    @PutMapping
    public HttpResult<Void> edit(@Validated @RequestBody UserVO userVO) {
        userDomainService.updateUser(userVO);
        return HttpResult.ok();
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("{userId}")
    public HttpResult<Void> remove(@PathVariable("userId") Long userId) {
        userDomainService.removeUser(userId);
        return HttpResult.ok();
    }

}
