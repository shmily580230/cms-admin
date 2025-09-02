package com.mm.common.aspect;

import cn.hutool.core.convert.Convert;
import cn.hutool.extra.servlet.ServletUtil;
import com.mm.common.annotation.SysLog;
import com.mm.common.util.UserUtil;
import com.mm.modules.sys.entity.OptLogEntity;
import com.mm.modules.sys.service.OptLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * 系统日志，切面处理类
 *
 * @author lwl
 */
@Lazy
@Slf4j
@Component
@RequiredArgsConstructor
public class SysLogAspect {

    final HttpServletRequest request;
    final OptLogService optLogService;

    @Pointcut("@annotation(com.mm.common.annotation.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        sw.stop();
        //保存日志
        saveSysLog(point, sw.getTotalTimeMillis());

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        OptLogEntity sysLog = new OptLogEntity();
        SysLog syslog = method.getAnnotation(SysLog.class);
        if (syslog != null) {
            //注解上的描述
            sysLog.setOperation(syslog.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        //请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = Convert.toStr(args);
            if (params.length() > 2500) {
                params = params.substring(0, 2500);
            }
            sysLog.setParams(params);
        } catch (Exception e) {
            log.warn("获取参数异常");
        }
        //设置IP地址
        sysLog.setIp(ServletUtil.getClientIP(request));
        //用户名
        sysLog.setUserId(Optional.ofNullable(UserUtil.getUserId()).orElse(0L));
        sysLog.setTime(time);
        //保存系统日志
        optLogService.save(sysLog);
    }
}
