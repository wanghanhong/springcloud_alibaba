package com.smart.device.install.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import com.smart.common.utils.StringUtils;
import com.smart.common.utils.upload.FastDfsUtil;
import com.smart.device.common.install.entity.TBaseBuildingSon;
import com.smart.device.common.install.entity.TBaseDict;
import com.smart.device.common.install.entity.TBaseFirehydrant;
import com.smart.device.common.install.entity.TManagerElectric;
import com.smart.device.install.mapper.TBaseFirehydrantMapper;
import com.smart.device.install.service.FdfsServiceImpl;
import com.smart.device.install.service.ITBaseBuildingSonService;
import com.smart.device.install.service.ITBaseDictService;
import com.smart.device.install.service.ITBaseFirehydrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author f
 */
@Service
public class TBaseFirehydrantServiceImpl extends ServiceImpl<TBaseFirehydrantMapper, TBaseFirehydrant> implements ITBaseFirehydrantService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TBaseFirehydrantMapper tBaseFirehydrantMapper;
    @Resource
    private ITBaseBuildingSonService itBaseBuildingSonService;
    @Resource
    private ITBaseDictService itBaseDictService;
    @Resource
    private FdfsServiceImpl fdfsService;
    @Autowired
    private FastDfsUtil fastDfsUtil;
    @Autowired
    private FdfsWebServer fdfsWebServer;
    @Override
    public IPage<TBaseFirehydrant> baseFirehydrantList(PageDomain page, TBaseFirehydrant vo) {
        Page<TBaseFirehydrant> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TBaseFirehydrant> iPage = tBaseFirehydrantMapper.baseFirehydrantList(pg,vo);
        List<TBaseFirehydrant> list = iPage.getRecords();
        list.forEach(e->{
            String codeurl = fdfsService.getResAccessUrl(e.getCodeUrl());
            e.setCodeUrl(codeurl);
        });
        iPage.setRecords(list);
        return iPage;
    }

    @Override
    public TBaseFirehydrant baseFirehydrantAdd(TBaseFirehydrant entity) {
        if(entity.getId() != null){
            baseFirehydrantUpdate(entity);
        }else{
            entity = saveTBaseFirehydrant(entity);
        }
        itBaseBuildingSonService.calAndSetFloorDeviceNum(entity.getBuildingId(),entity.getBuildingFloor());
        itBaseBuildingSonService.calAndSetFloorDeviceNum(entity.getOldbuildingId(),entity.getOldbuildingFloor());
        return entity;
    }

    public TBaseFirehydrant saveTBaseFirehydrant(TBaseFirehydrant entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        //生成id
        Long id = idWorker.nextId();
        entity.setId(id);
        entity.setCreateTime(LocalDateTime.now());

        String codeurl = fastDfsUtil.packageUrlForLink(id+"");
        String url = fdfsWebServer.getWebServerUrl();
        codeurl = codeurl.substring(url.length());
        entity.setCodeUrl(codeurl);
        entity.setIsCode(1);
        tBaseFirehydrantMapper.insert(entity);
        return entity;
    }

    @Override
    public int baseFirehydrantDel(TBaseFirehydrant entity) {
        int res = tBaseFirehydrantMapper.deleteById(entity.getId());
        itBaseBuildingSonService.calAndSetFloorDeviceNum(entity.getBuildingId(),entity.getBuildingFloor());
        itBaseBuildingSonService.calAndSetFloorDeviceNum(entity.getOldbuildingId(),entity.getOldbuildingFloor());
        return res;
    }

    @Override
    public TBaseFirehydrant baseFirehydrantUpdate(TBaseFirehydrant entity) {
        entity.setUpdateTime(LocalDateTime.now());
        tBaseFirehydrantMapper.updateById(entity);
        itBaseBuildingSonService.calAndSetFloorDeviceNum(entity.getBuildingId(),entity.getBuildingFloor());
        itBaseBuildingSonService.calAndSetFloorDeviceNum(entity.getOldbuildingId(),entity.getOldbuildingFloor());
        return entity;
    }

    @Override
    public TBaseFirehydrant selectBaseFirehydrantByID(Long id) {
        TBaseFirehydrant entity = tBaseFirehydrantMapper.selectById(id);
        if(entity != null){
            String codeurl = fdfsService.getResAccessUrl(entity.getCodeUrl());
            String fileurl = fdfsService.getResAccessUrl(entity.getFileUrl());
            entity.setCodeUrlString(codeurl);
            entity.setFileUrlString(fileurl);
            // 把设备材料格式化
            String ids = entity.getMaterialId();
            List<TBaseDict> list = setDictStatus(ids);
            entity.setMaterialList(list);
        }
        return entity;
    }
    /**------通用方法开始结束-----------------------------------------*/

    public List<TBaseDict> setDictStatus(String ids){
        List<TBaseDict> list = itBaseDictService.dictListByType("1");
        if(StringUtils.isNotBlank(ids)){
            String [] arr = ids.split(",");
            Set<String> set = new HashSet<>(Arrays.asList(arr));
             for (int i=0;i<list.size();i++){
                if(set.contains(String.valueOf(list.get(i).getDictId()))){
                    list.get(i).setStatus(1);
                }else {
                    list.get(i).setStatus(0);
                }
            }
        }
        return list;
    }

    public static void main(String [] args){
        String ids = "1";
        List<TBaseDict> list =new ArrayList<>();
        TBaseDict e = new TBaseDict();
        e.setDictId(1);
        list.add(e);
        e = new TBaseDict();
        e.setDictId(2);
        list.add(e);

        String [] arr = ids.split(",");
        Set<String> set = new HashSet<>(Arrays.asList(arr));
        for (int i=0;i<list.size();i++){
            if(set.contains(String.valueOf(list.get(i).getDictId()))){
                list.get(i).setStatus(1);
            }else {
                list.get(i).setStatus(0);
            }
        }
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i).getStatus());
        }
    }


}
