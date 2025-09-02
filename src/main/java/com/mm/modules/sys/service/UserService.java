package com.mm.modules.sys.service;

import com.mm.modules.sys.entity.UserEntity;
import com.mybatisflex.core.service.IService;

import java.util.List;


/**
 * 系统用户
 *
 * @author lwl
 */
public interface UserService extends IService<UserEntity> {

    boolean removeByIds(List<Long> ids);
}
