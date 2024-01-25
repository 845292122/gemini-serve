package net.gemini.domain.system.role.ability;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.gemini.domain.system.role.pojo.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

/**
* @author edison
*/
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    Integer insertBatchSomeColumn(Collection<RoleMenu> roleMenus);
}




