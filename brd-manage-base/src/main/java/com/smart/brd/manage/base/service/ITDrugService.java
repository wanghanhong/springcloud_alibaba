package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TDrug;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 
 */
public interface ITDrugService extends IService<TDrug> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TDrug> tDrugList(PageDomain page,TDrug entity);

    TDrug tDrugAdd(TDrug entity);

    TDrug tDrugUpdate(TDrug entity);

    int tDrugDel(Long id);

    TDrug tDrugDetail(TDrug entity);

    /**------基本方法结束-----------------------------------------*/

    List<TDrug> queryTDrugList(HttpServletRequest request, TDrug entity);

    void importTDrug(MultipartFile path);
}
