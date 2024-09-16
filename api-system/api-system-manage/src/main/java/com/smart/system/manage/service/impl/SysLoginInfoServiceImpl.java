package com.smart.system.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.system.manage.entity.SysLoginInfo;
import com.smart.system.manage.mapper.SysLogininforMapper;
import com.smart.system.manage.service.ISysLoginInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author sanduo
 * @since 2020-07-10
 */
@Service
public class SysLoginInfoServiceImpl extends ServiceImpl<SysLogininforMapper, SysLoginInfo> implements ISysLoginInfoService {

}
