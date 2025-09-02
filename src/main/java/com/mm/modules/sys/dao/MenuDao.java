package com.mm.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mm.modules.sys.entity.MenuEntity;
import com.mybatisflex.core.BaseMapper;

/**
 * 菜单管理
 *
 * @author lwl
 */
public interface MenuDao extends BaseMapper<MenuEntity> {

    @Select("SELECT * FROM menu WHERE id in ( " +
            " SELECT menu_id FROM role_menu WHERE role_id IN ( " +
            "   SELECT role_id FROM user_role WHERE user_id = ${userId} " +
            " )) ORDER BY sort,id DESC")
    List<MenuEntity> listByUserId(@Param("userId") Long userId);

}
