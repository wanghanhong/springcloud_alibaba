package com.smart.card.sys.job.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.card.sys.job.domain.Job;

import java.util.List;

public interface JobMapper extends BaseMapper<Job> {

    List<Job> queryList();
}