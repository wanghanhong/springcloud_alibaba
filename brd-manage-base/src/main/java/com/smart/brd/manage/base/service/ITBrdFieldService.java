package com.smart.brd.manage.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.brd.manage.base.entity.TBaseDict;
import com.smart.brd.manage.base.entity.TBrdField;
import com.smart.brd.manage.base.entity.vo.BrdFieldVo;
import com.smart.common.core.domain.Result;
import com.smart.common.core.page.PageDomain;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 
 */
public interface ITBrdFieldService extends IService<TBrdField> {

    /**------基本方法开始-----------------------------------------*/
    IPage<BrdFieldVo> tBrdFieldList(HttpServletRequest request,PageDomain page, TBrdField entity);

    Result tBrdFieldAdd(BrdFieldVo entityVo);

    Result tBrdFieldUpdate(BrdFieldVo entityVo);

    Result tBrdFieldDel(Long id);

    BrdFieldVo tBrdFieldDetail(Long fieldId);
    /**------基本方法结束-----------------------------------------*/

    List<TBaseDict> getType();
}
