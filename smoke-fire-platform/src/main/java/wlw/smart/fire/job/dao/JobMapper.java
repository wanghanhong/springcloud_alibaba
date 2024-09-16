package wlw.smart.fire.job.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import wlw.smart.fire.job.domain.Job;

import java.util.List;

public interface JobMapper extends BaseMapper<Job> {

    List<Job> queryList();
}