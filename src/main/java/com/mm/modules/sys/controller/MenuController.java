package com.mm.modules.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mm.common.annotation.RestPathController;
import com.mm.common.annotation.SysLog;
import com.mm.common.util.Assert;
import com.mm.common.util.R;
import com.mm.modules.sys.entity.MenuEntity;
import com.mm.modules.sys.service.MenuService;
import com.mybatisflex.core.query.QueryWrapper;

import cn.hutool.core.util.NumberUtil;

/**
 * 系统菜单
 *
 * @author lwl
 */
@RestPathController("/api/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 子菜单
     *
     * @param ms
     * @param ps
     * @return
     */
    public static List<MenuEntity> getMenus(List<MenuEntity> ms, List<MenuEntity> ps) {
        List<MenuEntity> sm = new ArrayList<>();
        for (MenuEntity p : ps) {
            List<MenuEntity> cs = new ArrayList<>();
            for (MenuEntity m : ms) {
                if (NumberUtil.equals(m.getPid(), p.getId())) {
                    cs.add(m);
                }
            }
            p.setChildren(getMenus(ms, cs));
            sm.add(p);
        }
        return sm;
    }

    /**
     * 菜单列表
     */
    @GetMapping
    public R<List<MenuEntity>> get() {
        List<MenuEntity> list = menuService.list(QueryWrapper.create().orderBy(MenuEntity::getSort, false));
        List<MenuEntity> pm = list.stream()
                .filter(e -> e.getPid() == 0)
                .collect(Collectors.toList());
        pm = getMenus(list, pm);
        return R.ok(pm);
    }

    /**
     * 保存修改
     */
    @SysLog("菜单保存修改")
    @PostMapping
    public R<Void> post(@Valid @RequestBody MenuEntity menu) {
        menuService.saveOrUpdate(menu);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("菜单删除")
    @DeleteMapping
    public R<Void> del(@RequestBody Long[] ids) {
        for (Long id : ids) {
            // 判断是否有子菜单或按钮
            long mcs = menuService.count(QueryWrapper.create().eq(MenuEntity::getPid, id));
            Assert.bool(mcs > 0, "请先删除子菜单或按钮");
        }
        menuService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
