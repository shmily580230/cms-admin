package com.mm.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mm.modules.sys.dto.MenuDTO;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 *
 * @author lwl
 */
@Data
@Table("user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态(0=禁用,1=正常)
     */
    private Boolean status;

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
     * 角色ID列表
     */
    @Column(ignore = true)
    private List<Long> roleIds;

    /**
     * 菜单列表
     */
    @Column(ignore = true)
    private List<MenuDTO> menus;

    /**
     * 授权
     */
    @Column(ignore = true)
    private List<String> perms;

}
