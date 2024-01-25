package net.gemini.domain.system.log.ability;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.gemini.domain.system.log.pojo.OperationLog;
import org.apache.ibatis.annotations.Mapper;

/**
* @author edison
*/
@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLog> {

}