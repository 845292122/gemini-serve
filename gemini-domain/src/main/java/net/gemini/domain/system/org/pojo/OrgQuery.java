package net.gemini.domain.system.org.pojo;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.gemini.common.base.AbstractPageQuery;

import java.util.Objects;

/**
 * @author edison
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrgQuery extends AbstractPageQuery<Org> {

    private String orgName;
    private Integer status;


    @Override
    public QueryWrapper<Org> addQueryCondition() {
        QueryWrapper<Org> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotEmpty(orgName), "org_name", orgName)
                .eq(Objects.nonNull(status), "status", status);
        return wrapper;
    }
}
