package com.smart.device.install.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.install.entity.TBaseBuilding;
import com.smart.device.common.install.entity.TBaseCompany;
import com.smart.device.common.install.entity.TBaseRegion;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author cf
 * @since 2019-09-17
 */
public interface ITBaseRegionService extends IService<TBaseRegion> {

    /**
     * 根据 code 返回下级的机构。
     *
     * @param regionCode
     * @return
     */
    List<TBaseRegion> selectRegions(String regionCode);
    // 获取建筑物中省市县乡的字符
    String geTBaseRegionsBySdBaseBuilding(List<TBaseBuilding> list);
    // 把 6100，610101 获取相应MAp
    Map<String,String>  mapRegions(String regionCodes);

    public String getRegionsByTDeptInfoVo(List<TBaseCompany> list);

    List<TBaseRegion> selectRegionsByCode(String province, String city, String county, String town);

}
