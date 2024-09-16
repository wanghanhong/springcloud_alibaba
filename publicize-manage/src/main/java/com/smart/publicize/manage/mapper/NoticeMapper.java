package com.smart.publicize.manage.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.publicize.manage.entity.NoticeEntity;

import java.util.List;

/**
 * USER: gll
 * DATE: 2020/5/22
 * TIME: 16:57
 * Describe:
 * @author l
 */

public interface NoticeMapper extends BaseMapper<NoticeEntity> {

    List<NoticeEntity> queryNoticeListLimit();

}
