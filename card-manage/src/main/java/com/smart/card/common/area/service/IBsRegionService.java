package com.smart.card.common.area.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.card.common.area.entity.BsRegion;
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
public interface IBsRegionService extends IService<BsRegion> {

    /**
     * 根据 code 返回下级的机构。
     *
     * @param regionCode
     * @return
     */
    List<BsRegion> selectRegions(String regionCode);
    // 把 6100，610101 获取相应MAp
    Map<String,String>  mapRegions(String regionCodes);

    List<BsRegion> selectRegionsByCode(String province, String city, String county, String town);


}
