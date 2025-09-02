package com.mm.modules.sys.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 菜单管理
 *
 * @author lwl
 */
@Data
@Table("menu")
public class MenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 父菜单ID，一级菜单为0
     */
    @NotNull(message = "pid is null")
    private Long pid;

    /**
     * 菜单名称
     */
    @NotBlank(message = "name is null")
    private String name;

    /**
     * 路由路径
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:add)
     */
    private String perms;

    /**
     * 类型(1=目录,2=菜单,3=按钮)
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态(0:禁用,1:启用,2:隐藏)
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(onInsertValue = "now()")
    private Date createdAt;

    /**
     * 修改时间
     */
    @Column(onInsertValue = "now()", onUpdateValue = "now()")
    private Date updatedAt;

    @Column(ignore = true)
    private List<MenuEntity> children;

}
