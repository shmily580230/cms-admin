//package com.mm.common.crud;
//
//import com.mm.common.annotation.SysLog;
//import com.mm.common.util.R;
//import com.mybatisflex.core.paginate.Page;
//import com.mybatisflex.core.service.IService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.constraints.NotNull;
//import java.util.List;
//
//@Validated
//@RestController
//@RequiredArgsConstructor
//public class CrudController<T> {
//
//    final IService<T> service;
//
//    /**
//     * 角色列表
//     */
//    @GetMapping
//    public R<List<T>> list(@NotNull(message = "page is null") Integer page,
//                           @NotNull(message = "limit is null") Integer limit) {
//        Page<T> iPage = service.page(new Page<>(page, limit));
//        return R.ok(iPage);
//    }
//
//    /**
//     * 保存角色
//     */
//    @SysLog("保存角色")
//    @PostMapping
//    public R post(@RequestBody T entity) {
//        service.saveOrUpdate(entity);
//        return R.ok();
//    }
//
//    /**
//     * 删除角色
//     */
//    @SysLog("删除角色")
//    @DeleteMapping
//    public R del(@RequestBody List<Long> ids) {
//        service.removeByIds(ids);
//        return R.ok();
//    }
//}
