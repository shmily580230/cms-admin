package com.mm.modules.sys.service.impl;

import com.mm.modules.sys.dao.RoleMenuDao;
import com.mm.modules.sys.entity.RoleMenuEntity;
import com.mm.modules.sys.service.RoleMenuService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色与菜单对应关系
 *
 * @author lwl
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenuEntity> implements RoleMenuService {

}
