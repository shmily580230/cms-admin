package com.mm.modules.sys.service.impl;

import com.mm.modules.sys.dao.OptLogDao;
import com.mm.modules.sys.entity.OptLogEntity;
import com.mm.modules.sys.service.OptLogService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * 系统日志
 *
 * @author lwl
 */
@Service("optLogService")
public class OptLogServiceImpl extends ServiceImpl<OptLogDao, OptLogEntity> implements OptLogService {

}
