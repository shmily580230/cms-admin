package com.mm.modules.sys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;

import com.mm.common.annotation.RestPathController;
import com.mm.common.util.R;
import com.mm.modules.sys.entity.OptLogEntity;
import com.mm.modules.sys.entity.UserEntity;
import com.mm.modules.sys.service.OptLogService;
import com.mm.modules.sys.service.UserService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;

import cn.hutool.core.lang.Opt;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;

/**
 * 系统日志
 *
 * @author lwl
 */
@RequiredArgsConstructor
@RestPathController("/opt_log")
public class OptLogController {
    final OptLogService optLogService;
    final UserService userService;

    /**
     * 列表
     */
    @GetMapping
    public R<List<OptLogEntity>> get(Integer page, Integer limit, String key) {
        QueryWrapper qw = QueryWrapper.create().like(OptLogEntity::getOperation, key, StrUtil.isNotBlank(key));
        Page<OptLogEntity> res = optLogService.page(new Page<>(page, limit), qw);
        List<Long> userIds = res.getRecords().stream().map(OptLogEntity::getUserId).collect(Collectors.toList());
        List<UserEntity> users = Opt.ofEmptyAble(userIds).map(e -> userService.listByIds(e)).orElse(new ArrayList<>());
        for (OptLogEntity sysLog : res.getRecords()) {
            sysLog.setUsername(users.stream()
                    .filter(e -> NumberUtil.equals(sysLog.getUserId(), e.getId()))
                    .findFirst().map(UserEntity::getUsername).orElse(""));
        }
        return R.ok(res);
    }

}
