package com.mm.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 返回数据
 *
 * @author lwl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> R<T> error() {
        return code(RCode.SYSTEM_ERR);
    }

    public static <T> R<T> error(String msg) {
        return code(RCode.SYSTEM_ERR, msg);
    }

    public static <T> R<T> code(int code, String msg) {
        return new R<>(code, msg);
    }

    public static <T> R<T> code(ICode iCode) {
        return new R<>(iCode.getCode(), iCode.getMsg());
    }

    public static <T> R<T> code(ICode iCode, String msg) {
        return new R<>(iCode.getCode(), msg);
    }

    public static <T> R<T> ok(T data) {
        R<T> r = code(RCode.SUCCESS);
        r.setData(data);
        return r;
    }

    public static <T> Page<List<T>> ok(com.mybatisflex.core.paginate.Page<T> res) {
        return new Page<>(RCode.SUCCESS.getCode(), RCode.SUCCESS.getMsg(), res.getRecords(), res.getTotalRow());
    }

    public static <T> Page<List<T>> ok(List<T> list, Long total) {
        return new Page<>(RCode.SUCCESS.getCode(), RCode.SUCCESS.getMsg(), list, total);
    }

    public static <T> R<T> ok(T data, ICode iCode) {
        R<T> r = code(iCode);
        r.setData(data);
        return r;
    }

    public static <T> R<T> ok() {
        return code(RCode.SUCCESS);
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Page<T> extends R<T> {
        /**
         * 总数
         */
        private Long total;

        public Page(Integer code, String msg, T data, Long total) {
            super(code, msg, data);
            this.total = total;
        }
    }
}
