package net.gemini.domain.system.role.ability;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import net.gemini.domain.system.role.pojo.RoleMenu;
import org.springframework.stereotype.Service;

/**
* @author edison
*/
@Service
@RequiredArgsConstructor
public class RoleMenuService extends ServiceImpl<RoleMenuMapper, RoleMenu> {

    private final RoleMenuMapper roleMenuMapper;
}




