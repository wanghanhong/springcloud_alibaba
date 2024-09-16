package com.smart.brd.sys.job.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.brd.sys.job.domain.Job;

import java.util.List;

public interface JobMapper extends BaseMapper<Job> {

    List<Job> queryList();
}