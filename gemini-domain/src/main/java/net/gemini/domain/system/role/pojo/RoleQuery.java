package net.gemini.domain.system.role.pojo;

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
public class RoleQuery extends AbstractPageQuery<Role> {

    private String roleName;
    private String roleKey;
    private Integer status;

    @Override
    public QueryWrapper<Role> addQueryCondition() {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq(status != null, "status", status)
                .like(StrUtil.isNotEmpty(roleKey), "role_key", roleKey)
                .like(StrUtil.isNotEmpty(roleName), "role_name", roleName);
        return wrapper;
    }
}
