package com.smart.device.install.service.inspection.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import com.smart.device.common.install.entity.TBaseFirefighter;
import com.smart.device.install.mapper.inspection.TBaseFirefighterMapper;
import com.smart.device.install.feign.SmokeFirePlatformFeignClient;
import com.smart.device.install.service.inspection.ITBaseFirefighterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author f
 */
@Service
public class TBaseFirefighterServiceImpl extends ServiceImpl<TBaseFirefighterMapper, TBaseFirefighter> implements ITBaseFirefighterService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TBaseFirefighterMapper tBaseFirefighterMapper;
    @Resource
    private SmokeFirePlatformFeignClient smokeFirePlatformFeignClient;

    @Override
    public IPage<TBaseFirefighter> baseFirefighterList(PageDomain page, TBaseFirefighter vo) {
        Page<TBaseFirefighter> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TBaseFirefighter> iPage = tBaseFirefighterMapper.baseFirefighterList(pg,vo);
        return iPage;
    }

    @Override
    public TBaseFirefighter baseFirefighterAdd(TBaseFirefighter entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        //生成id
        entity.setId(idWorker.nextId());
        entity.setCreateTime(LocalDateTime.now());
        tBaseFirefighterMapper.insert(entity);
        return entity;
    }
    @Override
    public void checkUserAndSave(String phone,Long deptId) {
        if(phone != null){
            Map<String,Object> map = smokeFirePlatformFeignClient.checkUserName(phone);
            String result = String.valueOf(map.get("Data"));
            if("true".equals(result)){
                smokeFirePlatformFeignClient.addXCXUser(phone,phone,deptId);
            }else{
                log.error("aa-yicunzai");
            }
        }
    }
    @Override
    public int baseFirefighterDel(Long id) {
        return tBaseFirefighterMapper.deleteById(id);
    }

    @Override
    public TBaseFirefighter baseFirefighterUpdate(TBaseFirefighter entity) {
        entity.setUpdateTime(LocalDateTime.now());
        tBaseFirefighterMapper.updateById(entity);
        return entity;
    }

    @Override
    public TBaseFirefighter selectBaseFirefighterByID(Long id) {
        TBaseFirefighter entity = tBaseFirefighterMapper.selectById(id);
        return entity;
    }
    /**------通用方法开始结束-----------------------------------------*/

    @Override
    public TBaseFirefighter selectBaseFirefighter(TBaseFirefighter vo) {
        TBaseFirefighter entity = tBaseFirefighterMapper.selectBaseFirefighter(vo);
        return entity;
    }

}
