package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TBaseDict;
import com.smart.brd.manage.base.entity.TDisease;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.entity.vo.DiseaseVo;
import com.smart.common.core.page.PageDomain;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 
 */
public interface ITDiseaseService extends IService<TDisease> {

    /**------基本方法开始-----------------------------------------*/
    /**
     * 疾病列表
     * @param page pages
     * @param entity disease
     * @return 结果
     */
    IPage<TDisease> tDiseaseList(PageDomain page,TDisease entity);

    /**
     * 添加记录
     * @param entity 记录内容
     * @return 添加后的记录
     */
    TDisease tDiseaseAdd(DiseaseVo entity);

    /**
     * 记录修改
     * @param entity 记录内容
     * @return 修改后记录
     */
    TDisease tDiseaseUpdate(DiseaseVo entity);

    /**
     * 删除记录
     * @param id 记录ID
     * @return 结果
     */
    int tDiseaseDel(Long id);

    DiseaseVo tDiseaseDetail(TDisease entity);
    /**------基本方法结束-----------------------------------------*/

    List<TBaseDict> getType();

    List<TDisease> queryDiseaseList(HttpServletRequest request,TDisease entity);

    TDisease getDisease(HttpServletRequest request, TDisease entity);

    void exportTDisease(MultipartFile path);
}
