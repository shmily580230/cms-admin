package com.mm.modules.sys.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mm.common.annotation.RestPathController;
import com.mm.common.annotation.SysLog;
import com.mm.common.util.R;
import com.mm.modules.sys.entity.RoleEntity;
import com.mm.modules.sys.entity.RoleMenuEntity;
import com.mm.modules.sys.service.RoleMenuService;
import com.mm.modules.sys.service.RoleService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;

/**
 * 角色管理
 *
 * @author lwl
 */
@Validated
@RestPathController("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 角色列表
     */
    @GetMapping
    public R<List<RoleEntity>> list(Integer page, Integer limit) {
        if (Objects.isNull(page) || Objects.isNull(limit)) {
            List<RoleEntity> list = roleService.list();
            return R.ok(list, Convert.toLong(list.size()));
        }
        Page<RoleEntity> res = roleService.page(new Page<>(page, limit));
        if (res.getTotalRow() > 0) {
            List<Long> ids = res.getRecords().stream().map(RoleEntity::getId).collect(Collectors.toList());
            List<RoleMenuEntity> rms = roleMenuService.list(QueryWrapper.create().in(RoleMenuEntity::getRoleId, ids));
            for (RoleEntity r : res.getRecords()) {
                List<Long> menuIds = rms.stream().filter(e -> NumberUtil.equals(e.getRoleId(), r.getId()))
                        .map(RoleMenuEntity::getMenuId).collect(Collectors.toList());
                r.setMenuIds(menuIds);
            }
        }
        return R.ok(res);
    }

    /**
     * 保存角色
     */
    @SysLog("保存角色")
    @PostMapping
    public R post(@RequestBody RoleEntity role) {
        roleService.saveOrUpdate(role);
        return R.ok();
    }

    /**
     * 删除角色
     */
    @SysLog("删除角色")
    @DeleteMapping
    public R del(@RequestBody List<Long> ids) {
        roleService.removeByIds(ids);
        return R.ok();
    }
}
