package com.smart.fire.platform.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.install.entity.vo.ScreenVo;


/**
 * @author
 */

public interface ScreenMapper extends BaseMapper<ScreenVo> {

    Integer queryUserNum();

}
