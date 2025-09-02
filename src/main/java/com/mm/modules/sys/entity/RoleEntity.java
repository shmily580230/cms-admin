package com.mm.modules.sys.entity;


import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色
 *
 * @author lwl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table("role")
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String name;

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

    /**
     * 菜单id
     */
    @Column(ignore = true)
    private List<Long> menuIds;
}
