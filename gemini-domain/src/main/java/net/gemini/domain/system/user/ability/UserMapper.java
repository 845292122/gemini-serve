package net.gemini.domain.system.user.ability;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.gemini.domain.system.user.pojo.User;
import net.gemini.domain.system.user.pojo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author edison
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("""
        select u.*, o.org_name, o.leader_name as org_leader
        from sys_user u
        left join sys_org o
        on o.org_id = u.org_id
        ${ew.customSqlSegment}
    """)
    Page<UserVO> getUserList(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> queryWrapper);
}