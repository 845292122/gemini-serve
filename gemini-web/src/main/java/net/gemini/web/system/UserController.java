package net.gemini.web.system;

import cn.hutool.core.collection.ListUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.gemini.common.base.HttpResult;
import net.gemini.common.base.PageDTO;
import net.gemini.domain.system.user.UserDomainService;
import net.gemini.domain.system.user.pojo.UserVO;
import net.gemini.infrastructure.excel.GeminiExcelUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author edison
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("system/user")
@RequiredArgsConstructor
public class UserController {

    private final UserDomainService userDomainService;

    @ApiOperation(value = "用户列表")
    @GetMapping
    public HttpResult<PageDTO<UserVO>> list(UserVO userVO) {
        PageDTO<UserVO> page = userDomainService.getUserList(userVO);
        return HttpResult.ok(page);
    }

    @ApiOperation(value = "用户详情")
    @GetMapping("{userId}")
    public HttpResult<UserVO> info(@PathVariable("userId") Long userId) {
        UserVO userVO = userDomainService.getUserInfo(userId);
        return HttpResult.ok(userVO);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping
    public HttpResult<Void> add(@Validated @RequestBody UserVO userVO) {
        userDomainService.addUser(userVO);
        return HttpResult.ok();
    }

    @ApiOperation(value = "修改用户")
    @PutMapping
    public HttpResult<Void> edit(@Validated @RequestBody UserVO userVO) {
        userDomainService.updateUser(userVO);
        return HttpResult.ok();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("{userId}")
    public HttpResult<Void> remove(@PathVariable("userId") Long userId) {
        userDomainService.removeUser(userId);
        return HttpResult.ok();
    }

    @ApiOperation(value = "导入用户")
    @PostMapping("-import")
    public HttpResult<Void> importUser(MultipartFile file) {
        List<UserVO> userExcel = GeminiExcelUtil.readFromRequest(UserVO.class, file);
        userExcel.forEach(ue -> userDomainService.addUser(ue));
        return HttpResult.ok();
    }

    @ApiOperation(value = "导出用户")
    @GetMapping("-export")
    public void exportUser(HttpServletResponse response, UserVO userVO) {
        PageDTO<UserVO> userList = userDomainService.getUserList(userVO);
        GeminiExcelUtil.writeToResponse(userList.getRecords(), UserVO.class, response);
    }

    @ApiOperation(value = "用户导入模板")
    @GetMapping("template")
    public void excelTemplate(HttpServletResponse response) {
        GeminiExcelUtil.writeToResponse(ListUtil.toList(new UserVO()), UserVO.class, response);
    }
}
