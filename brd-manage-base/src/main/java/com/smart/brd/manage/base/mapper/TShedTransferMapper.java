package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TShedTransfer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TShedTransferMapper extends BaseMapper<TShedTransfer> {

    IPage<TShedTransfer> tShedTransferList(Page<TShedTransfer> page, @Param("vo")TShedTransfer vo);


}
