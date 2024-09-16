package com.smart.device.message.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.message.entity.TMsgDict;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author
 */

public interface TBaseMsgMapper extends BaseMapper<TMsgDict> {

    List<TMsgDict> dictList(@Param("dictTypeId") String dictTypeId);

}
