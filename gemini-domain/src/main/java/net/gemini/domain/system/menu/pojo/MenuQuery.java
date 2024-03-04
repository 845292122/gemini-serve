package net.gemini.domain.system.menu.pojo;

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
public class MenuQuery extends AbstractPageQuery<Menu> {

    private String menuName;
    private Integer status;

    @Override
    public QueryWrapper<Menu> addQueryCondition() {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotEmpty(menuName), "menu_name", menuName)
                .eq(Objects.nonNull(status), "status", status);
        return wrapper;
    }
}
