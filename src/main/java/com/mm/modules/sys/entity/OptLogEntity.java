package com.mm.modules.sys.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 操作日志
 *
 * @author lwl
 */
@Data
@Table("opt_log")
public class OptLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    @Column(ignore = true)
    private String username;
    /**
     * 用户操作
     */
    private String operation;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求参数
     */
    private String params;
    /**
     * 执行时长(毫秒)
     */
    private Long time;
    /**
     * IP地址
     */
    private String ip;

    /**
     * 创建时间
     */
    @Column(onInsertValue = "now()")
    private Date createdAt;

}
