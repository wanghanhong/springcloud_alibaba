package com.smart.brd.manage.base.service.area;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.brd.manage.base.entity.area.TBaseRegion;
import com.smart.brd.manage.base.entity.vo.BrdFieldVo;

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
    // 把 6100，610101 获取相应MAp
    Map<String,String>  mapRegions(String regionCodes);

    List<TBaseRegion> selectRegionsByCode(String province, String city, String county, String town);

    public String getRegionsByTDeptInfoVo(List<BrdFieldVo> list);


}
