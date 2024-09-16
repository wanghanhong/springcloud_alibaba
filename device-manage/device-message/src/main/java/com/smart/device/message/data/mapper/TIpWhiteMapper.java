package com.smart.device.message.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.entity.TIpWhite;
import java.util.List;

public interface TIpWhiteMapper extends BaseMapper<TIpWhite> {

    List<String> queryTIpWhiteList();

}
