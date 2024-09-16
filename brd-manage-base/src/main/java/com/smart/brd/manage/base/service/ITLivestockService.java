package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TLivestock;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.entity.vo.LiveStockVo;
import com.smart.common.core.domain.Result;
import com.smart.common.core.page.PageDomain;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 
 */
public interface ITLivestockService extends IService<TLivestock> {

    /**------基本方法开始-----------------------------------------*/
    IPage<LiveStockVo> tLivestockList(PageDomain page, LiveStockVo entity);

    TLivestock tLivestockAdd(LiveStockVo entity);

    TLivestock tLivestockUpdate(TLivestock entity);

    int tLivestockDel(Long id);

    LiveStockVo tLivestockDetail(TLivestock entity);
    /**------基本方法结束-----------------------------------------*/

    List<LiveStockVo> queryLivestockList(LiveStockVo entity);

    Result livestockImport(MultipartFile path, LiveStockVo entity);

    void livestockExport(HttpServletResponse response);

    // 查询下 仔猪预出栏 日期小于当前日期的（育肥猪），然后将出栏日期更改 空
    void preTransferList();
    // 查询当日的仔猪，然后把仔猪-类型改为育肥猪
    void suitableListSet();


}
