package com.smart.card.manage.service.impl;

import com.smart.card.common.dict.entity.DictDto;
import com.smart.card.common.dict.mapper.TBaseDictMapper;
import com.smart.card.manage.entity.TCardData;
import com.smart.card.manage.entity.TCardHistory;
import com.smart.card.manage.mapper.TCardHistoryMapper;
import com.smart.card.manage.service.ITCardHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import javax.annotation.Resource;
import com.smart.common.utils.IdWorker;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author 
 */
@Service
public class TCardHistoryServiceImpl extends ServiceImpl<TCardHistoryMapper, TCardHistory> implements ITCardHistoryService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TCardHistoryMapper tCardHistoryMapper;
    @Resource
    private TBaseDictMapper tBaseDictMapper;

    @Override
    public IPage<TCardHistory> tCardHistoryList(PageDomain page, TCardHistory vo) {
        Page<TCardHistory> pg = new Page<>(page.getPageNum(), page.getPageSize());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if(StringUtils.isNotBlank(vo.getStartTime())){
            String startStr = vo.getStartTime()+" 00:00:01";
            LocalDateTime startDateTime = LocalDateTime.parse(startStr, dtf);
            vo.setStartTime(startDateTime.toString());
        }
        if(StringUtils.isNotBlank(vo.getStartTime())){
            String endStr = vo.getEndTime()+" 23:59:59";
            LocalDateTime endDateTime = LocalDateTime.parse(endStr, dtf);
            vo.setEndTime(endDateTime.toString());
        }
        IPage<TCardHistory> iPage = tCardHistoryMapper.tCardHistoryList(pg,vo);
        List<TCardHistory> list = iPage.getRecords();
        for (int i=0;i<list.size();i++){
            String res = list.get(i).getResult();
            // 1,0,0   成功: 0 ,失败: 0 ,处理中: 1
            String str ="";
            if(res != null & res.contains(",")){
                String [] arr = res.split(",");
                str = "成功:"+arr[0]+",失败:"+arr[1]+",处理中:"+arr[2];
            }
            list.get(i).setResult(str);
        }
        iPage.setRecords(list);
        return iPage;
    }

    @Override
    public TCardHistory tCardHistoryAdd(TCardHistory entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tCardHistoryUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tCardHistoryDel(Long id) {
        int res = tCardHistoryMapper.deleteById(id);
        return res;
    }

    @Override
    public TCardHistory tCardHistoryUpdate(TCardHistory entity) {
        tCardHistoryMapper.updateById(entity);
        return entity;
    }

    @Override
    public TCardHistory tCardHistoryDetail(TCardHistory entity) {
        TCardHistory detail = tCardHistoryMapper.selectById(entity.getId());
        return detail;
    }

    @Override
    public List<DictDto> tHisType() {
        String dictType = "dict_type_216";
        return tBaseDictMapper.tBaseDictListNopage(dictType);
    }

    @Override
    public List<DictDto> tHisStatus() {
        String dictType = "dict_type_217";
        return tBaseDictMapper.tBaseDictListNopage(dictType);
    }

    @Override
    public List<DictDto> tHisSource() {
        String dictType = "dict_type_218";
        return tBaseDictMapper.tBaseDictListNopage(dictType);
    }

    /**------通用方法开始结束-----------------------------------------*/

}
