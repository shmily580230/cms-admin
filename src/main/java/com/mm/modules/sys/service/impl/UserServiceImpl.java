package com.mm.modules.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.mm.modules.sys.dao.UserDao;
import com.mm.modules.sys.entity.UserEntity;
import com.mm.modules.sys.entity.UserRoleEntity;
import com.mm.modules.sys.service.UserRoleService;
import com.mm.modules.sys.service.UserService;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * 系统用户
 *
 * @author lwl
 */
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    final UserRoleService userRoleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdate(UserEntity entity) {
        if (Objects.nonNull(entity.getId())) {
            userRoleService.remove(QueryWrapper.create().eq(UserRoleEntity::getUserId, entity.getId()));
        }
        super.saveOrUpdate(entity);
        if (CollUtil.isNotEmpty(entity.getRoleIds())) {
            List<UserRoleEntity> urs = entity.getRoleIds().stream()
                    .map(e -> UserRoleEntity.builder().userId(entity.getId()).roleId(e).build())
                    .collect(Collectors.toList());
            userRoleService.saveBatch(urs);
        }
        return true;
    }

    @Override
    public boolean removeByIds(List<Long> ids) {
        super.removeByIds(ids);
        userRoleService.remove(QueryWrapper.create().in(UserRoleEntity::getUserId, ids));
        return true;
    }
}
