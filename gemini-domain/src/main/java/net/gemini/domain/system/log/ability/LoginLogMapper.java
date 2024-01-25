package net.gemini.domain.system.log.ability;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.gemini.domain.system.log.pojo.LoginLog;
import org.apache.ibatis.annotations.Mapper;

/**
* @author edison
*/
@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLog> {

}