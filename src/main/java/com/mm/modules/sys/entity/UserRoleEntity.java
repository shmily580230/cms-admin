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
 * 用户与角色对应关系
 *
 * @author lwl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table("user_role")
public class UserRoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 创建时间
     */
    @Column(onInsertValue = "now()")
    private Date createdAt;

}
