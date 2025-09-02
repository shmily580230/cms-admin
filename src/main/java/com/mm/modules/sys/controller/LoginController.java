package com.mm.modules.sys.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mm.common.annotation.RestPathController;
import com.mm.common.util.Assert;
import com.mm.common.util.R;
import com.mm.common.util.UserUtil;
import com.mm.modules.sys.dto.LoginDTO;
import com.mm.modules.sys.entity.UserEntity;
import com.mm.modules.sys.service.UserService;
import com.mybatisflex.core.query.QueryWrapper;

import cn.hutool.crypto.SecureUtil;

/**
 * 登录
 *
 * @author lwl
 */
@RestPathController("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param dto
     * @return
     */
    @PostMapping
    public R<String> login(@Valid @RequestBody LoginDTO dto) {
        UserEntity user = userService.getOne(QueryWrapper.create()
                .eq(UserEntity::getUsername, dto.getUsername())
                .eq(UserEntity::getPassword, SecureUtil.md5(dto.getPassword())));
        Assert.isNull(user, "用户名或密码错误");
        return R.ok(UserUtil.getToken(user.getId()));
    }

}
