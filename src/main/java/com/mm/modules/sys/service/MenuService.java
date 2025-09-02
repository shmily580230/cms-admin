package com.mm.modules.sys.service;


import com.mm.modules.sys.entity.MenuEntity;
import com.mybatisflex.core.service.IService;

import java.util.List;


/**
 * 菜单管理
 *
 * @author lwl
 */
public interface MenuService extends IService<MenuEntity> {

    /**
     * 获取用户菜单列表
     */
    List<MenuEntity> listByUserId(Long userId);

    boolean removeByIds(List<Long> ids);
}
