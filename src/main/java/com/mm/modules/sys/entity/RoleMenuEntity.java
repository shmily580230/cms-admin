package com.mm.modules.sys.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色与菜单对应关系
 *
 * @author lwl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table("role_menu")
public class RoleMenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 创建时间
     */
    @Column(onInsertValue = "now()")
    private Date createdAt;

}
