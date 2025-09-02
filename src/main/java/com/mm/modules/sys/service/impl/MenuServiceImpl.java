package com.mm.modules.sys.service.impl;

import com.mm.modules.sys.dao.MenuDao;
import com.mm.modules.sys.entity.MenuEntity;
import com.mm.modules.sys.entity.RoleMenuEntity;
import com.mm.modules.sys.service.MenuService;
import com.mm.modules.sys.service.RoleMenuService;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 菜单
 *
 * @author lwl
 */
@Service("menuService")
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuEntity> implements MenuService {
    final RoleMenuService roleMenuService;

    @Override
    public List<MenuEntity> listByUserId(Long userId) {
        return mapper.listByUserId(userId);
    }

    @Override
    public boolean removeByIds(List<Long> ids) {
        super.removeByIds(ids);
        roleMenuService.remove(QueryWrapper.create().in(RoleMenuEntity::getMenuId, ids));
        return true;
    }
}
