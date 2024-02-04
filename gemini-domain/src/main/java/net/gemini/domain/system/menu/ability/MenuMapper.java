package net.gemini.domain.system.menu.ability;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.gemini.domain.system.menu.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author edison
*/
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    @Select("select distinct m.*"
            + "from sys_menu m"
            + "left join sys_role_menu rm"
            + "on m.menu_id = rm.menu_id"
            + "left join sys_role r"
            + "on r.role_id = rm.role_id"
            + "where"
            + "m.status = 1 and m.deleted = 0"
            + "and r.status = 1 and r.deleted = 0"
            + "and r.role_id = #{roleId}")
    List<Menu> getMenuListByRoleId(Long roleId);

    @Select(
        "select distinct m.menu_id"
        + "from sys_menu m"
        + "left join sys_role_menu rm"
        + "on m.menu_id = rm.menu_id"
        + "where rm.role_id = #{roleId}"
        + "and m.deleted = 0"
        + "group by m.menu_id"
    )
    List<Long> selectMenuIdsByRoleId(Long roleId);
}