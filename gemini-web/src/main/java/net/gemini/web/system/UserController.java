package net.gemini.web.system;

import cn.hutool.core.collection.ListUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.gemini.common.base.PageDTO;
import net.gemini.common.base.ResponseDTO;
import net.gemini.domain.system.user.UserDomainService;
import net.gemini.domain.system.user.pojo.UserQuery;
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
    public ResponseDTO<PageDTO<UserVO>> list(UserQuery userQuery) {
        PageDTO<UserVO> page = userDomainService.getUserList(userQuery);
        return ResponseDTO.ok(page);
    }

    @ApiOperation(value = "用户详情")
    @GetMapping("{userId}")
    public ResponseDTO<UserVO> info(@PathVariable("userId") Long userId) {
        UserVO userVO = userDomainService.getUserInfo(userId);
        return ResponseDTO.ok(userVO);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping
    public ResponseDTO<Void> add(@Validated @RequestBody UserVO userVO) {
        userDomainService.addUser(userVO);
        return ResponseDTO.ok();
    }

    @ApiOperation(value = "修改用户")
    @PutMapping
    public ResponseDTO<Void> edit(@Validated @RequestBody UserVO userVO) {
        userDomainService.updateUser(userVO);
        return ResponseDTO.ok();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("{userId}")
    public ResponseDTO<Void> remove(@PathVariable("userId") Long userId) {
        userDomainService.removeUser(userId);
        return ResponseDTO.ok();
    }

    @ApiOperation(value = "导入用户")
    @PostMapping("-import")
    public ResponseDTO<Void> importUser(MultipartFile file) {
        List<UserVO> userExcel = GeminiExcelUtil.readFromRequest(UserVO.class, file);
        userExcel.forEach(ue -> userDomainService.addUser(ue));
        return ResponseDTO.ok();
    }

    @ApiOperation(value = "导出用户")
    @GetMapping("-export")
    public void exportUser(HttpServletResponse response, UserQuery userQuery) {
        PageDTO<UserVO> userList = userDomainService.getUserList(userQuery);
        GeminiExcelUtil.writeToResponse(userList.getRecords(), UserVO.class, response);
    }

    @ApiOperation(value = "用户导入模板")
    @GetMapping("template")
    public void excelTemplate(HttpServletResponse response) {
        GeminiExcelUtil.writeToResponse(ListUtil.toList(new UserVO()), UserVO.class, response);
    }
}
