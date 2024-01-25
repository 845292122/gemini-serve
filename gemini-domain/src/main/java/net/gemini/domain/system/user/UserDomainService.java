package net.gemini.domain.system.user;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import net.gemini.common.base.PageDTO;
import net.gemini.common.constant.Constants;
import net.gemini.common.exception.BusinessException;
import net.gemini.common.exception.BusinessStatus;
import net.gemini.domain.system.user.ability.UserService;
import net.gemini.domain.system.user.pojo.User;
import net.gemini.domain.system.user.pojo.UserVO;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author edison
 */
@Service
@RequiredArgsConstructor
public class UserDomainService {

    private final UserService userService;

    public PageDTO<UserVO> getUserList(UserVO userVO) {
        Page<UserVO> page = userService.getUserList(userVO);
        return new PageDTO<>(page.getRecords(), page.getTotal());
    }


    public UserVO getUserInfo(Long userId) {
        User user = userService.getById(userId);
        return new UserVO(user);
    }

    public void addUser(UserVO userVO) {
        userService.checkUsernameUnique(userVO);
        userService.checkPhoneUnique(userVO);
        userService.checkEmailUnique(userVO);
        String initPwd = userService.initPassword(Constants.INIT_PASSWORD);
        userVO.setPassword(initPwd);
        userService.save(new User(userVO));
    }

    public void updateUser(UserVO userVO) {
        userService.checkPhoneUnique(userVO);
        userService.checkEmailUnique(userVO);
        userService.updateById(new User());
    }

    public void removeUser(Long userId) {
        long loginId = StpUtil.getLoginIdAsLong();
        userService.checkCanBeDeleted(loginId, userId);
        userService.removeById(userId);
    }
}
