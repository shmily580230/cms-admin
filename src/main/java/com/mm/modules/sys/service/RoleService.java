package com.mm.modules.sys.service;


import com.mm.modules.sys.entity.RoleEntity;
import com.mybatisflex.core.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 角色
 *
 * @author lwl
 */
public interface RoleService extends IService<RoleEntity> {

    @Transactional(rollbackFor = Exception.class)
    boolean removeByIds(List<Long> ids);
}
