package net.gemini.domain.system.user.pojo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.gemini.common.base.BaseVO;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.sql.Driver;
import java.util.Date;
import java.util.Objects;

/**
 * @author edison
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO extends BaseVO<User> {

    public UserVO (User user) {
        this.userId = user.getUserId();
        this.roleId = user.getRoleId();
        this.orgId = user.getOrgId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.userType = user.getUserType();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.sex = user.getSex();
        this.avatar = user.getAvatar();
        this.password = user.getPassword();
        this.status = user.getStatus();
        this.loginIp = user.getLoginIp();
        this.loginDate = user.getLoginDate();
        this.isAdmin = user.getIsAdmin();
        this.remark = user.getRemark();
        this.createTime = user.getCreateTime();
        this.updateTime = user.getUpdateTime();
        this.creatorId = user.getCreatorId();
        this.updaterId = user.getUpdaterId();
    }

    private Long userId;
    private Long roleId;
    private Long orgId;
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "昵称不能为空")
    private String nickname;
    private Integer userType;
    private String email;
    private String phone;
    private Integer sex;
    private String avatar;
    private String password;
    private Integer status;
    private String loginIp;
    private Date loginDate;
    private Integer isAdmin;
    private String remark;
    private Date createTime;
    private Date updateTime;
    private Long creatorId;
    private Long updaterId;

    /** ----------------- **/
    private String orgName;
    private String orgLeader;

    @Override
    public QueryWrapper<User> addQueryCondition() {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.like(StringUtils.hasText(username), "u.username", username)
                .like(StringUtils.hasText(phone), "u.phone", phone)
                .eq(Objects.nonNull(userId), "u.user_id", userId)
                .eq(Objects.nonNull(status), "u.status", status)
                .eq("u.deleted", 0)
                .and(Objects.nonNull(orgId),
                        o -> o.eq("u.org_id", orgId)
                                .or()
                                .apply("u.org_id in (select t.org_id from sys_org t where find_in_set(" + orgId + ", ancestors)"));
        this.timeRangeColumn = "u.create_time";
        return wrapper;
    }
}
