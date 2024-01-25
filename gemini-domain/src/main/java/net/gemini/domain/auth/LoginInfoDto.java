package net.gemini.domain.auth;

import lombok.Data;

import java.util.Set;

/**
 * 登录信息
 * @author edison
 */
@Data
public class LoginInfoDto {

    private Long userId;
    private Long roleId;
    private String roleName;
    private String roleKey;
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private String sex;
    private String avatar;
    private Set<String> permissions;
}
