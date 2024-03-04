package net.gemini.domain.system.user.pojo;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.gemini.common.base.AbstractPageQuery;

/**
 * @author edison
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQuery extends AbstractPageQuery<User> {

    private String username;
    private String nickname;
    private Integer status;
    private Integer orgId;

    @Override
    public QueryWrapper<User> addQueryCondition() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(status != null, "u.status", status)
                .eq(orgId != null, "o.org_id", orgId)
                .like(StrUtil.isNotEmpty(username), "u.username", username)
                .like(StrUtil.isNotEmpty(nickname), "u.nickname", nickname)
                .eq("u.deleted", 0);
        this.timeRangeColumn = "u.create_time";
        return wrapper;
    }
}
