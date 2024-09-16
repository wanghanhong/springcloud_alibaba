package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TVaccine;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 
 */
public interface ITVaccineService extends IService<TVaccine> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TVaccine> tVaccineList(PageDomain page,TVaccine entity);

    TVaccine tVaccineAdd(TVaccine entity);

    TVaccine tVaccineUpdate(TVaccine entity);

    int tVaccineDel(Long id);

    TVaccine tVaccineDetail(TVaccine entity);

    /**------基本方法结束-----------------------------------------*/

    List<TVaccine> queryVaccineList(HttpServletRequest request, TVaccine entity);

    void importTVaccine(MultipartFile path);
}
