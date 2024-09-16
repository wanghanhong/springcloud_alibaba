package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TLivestock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.brd.manage.base.entity.vo.LiveStockVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 
 */

public interface TLivestockMapper extends BaseMapper<TLivestock> {

    IPage<LiveStockVo> tLivestockList(Page<LiveStockVo> page, @Param("vo") LiveStockVo vo);

    List<LiveStockVo> queryLivestockList(@Param("vo") LiveStockVo vo);

    TLivestock queryById(@Param("id") Long id);


    List<TLivestock> preTransferList(@Param("vo") LiveStockVo vo);

    List<TLivestock> suitableList(@Param("vo") LiveStockVo vo);

    void preListingdateNull(@Param("livestockId") Long livestockId);
    @Select("SELECT COUNT(1) FROM t_livestock where shed_id=#{id} AND delete_flag=0")
    int getLivestockNum(@Param("id") Long id);
    @Select("SELECT COUNT(1) FROM t_livestock where shed_id=#{id} AND `status`=151 AND delete_flag=0")
    int getAbnormalNum(@Param("id")Long id);
}
