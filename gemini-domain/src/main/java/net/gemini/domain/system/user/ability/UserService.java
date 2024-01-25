package net.gemini.domain.system.user.ability;

import cn.dev33.satoken.secure.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import net.gemini.common.exception.BusinessException;
import net.gemini.common.exception.BusinessStatus;
import net.gemini.domain.system.user.pojo.User;
import net.gemini.domain.system.user.pojo.UserVO;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
* @author edison
*/
@Service
@RequiredArgsConstructor
public class UserService extends ServiceImpl<UserMapper, User> {

    private final UserMapper userMapper;

    public User loadUserByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getUsername, username);
        return this.getOne(wrapper);
    }

    public Page<UserVO> getUserList(UserVO userVO) {
        return userMapper.getUserList(userVO.toPage(), userVO.addQueryCondition());
    }

    public void checkUsernameUnique(UserVO userVO) {
        if (isUsernameDuplicated(userVO.getUsername())) {
            throw new BusinessException(BusinessStatus.Common.USER_NAME_NOT_UNIQUE, userVO.getUsername());
        }
    }

    public void checkPhoneUnique(UserVO userVO) {
        if (isPhoneDuplicated(userVO.getPhone(), userVO.getUserId())) {
            throw new BusinessException(BusinessStatus.Common.USER_PHONE_NOT_UNIQUE, userVO.getPhone());
        }
    }

    public void checkEmailUnique(UserVO userVO) {
        if (isEmailDuplicated(userVO.getEmail(), userVO.getUserId())) {
            throw new BusinessException(BusinessStatus.Common.USER_EMAIL_NOT_UNIQUE, userVO.getEmail());
        }
    }

    private boolean isUsernameDuplicated(String username) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getUsername, username);
        return userMapper.exists(wrapper);
    }

    private boolean isPhoneDuplicated(String phone, Long userId) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.ne(Objects.nonNull(userId), User::getUserId, userId)
                .eq(User::getPhone, phone);
        return userMapper.exists(wrapper);
    }

    private boolean isEmailDuplicated(String email, Long userId) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.ne(Objects.nonNull(userId), User::getUserId, userId)
                .eq(User::getEmail, email);
        return userMapper.exists(wrapper);
    }

    public String initPassword(String initPassword) {
        return BCrypt.hashpw(initPassword);
    }

    public void checkCanBeDeleted(Long loginId, Long userId) {
        User user = userMapper.selectById(userId);
        if (Objects.equals(loginId, user.getUserId()) || user.getIsAdmin() != 0) {
            throw new BusinessException(BusinessStatus.Common.USER_CURRENT_USER_CAN_NOT_BE_DELETED);
        }
    }
}