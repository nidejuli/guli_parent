package com.huatian.servicebase.exceptionHandler;

import com.huatian.commonUtils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice//全局异常处理
@Slf4j//把日志输出在文件中
public class GlobalExceptionHandler {

    /**
     * 出现异常时执行的方法
     *
     * @param e
     * @return
     * @ExceptionHandler 指定出现什么异常才执行这个方法
     * @ResponseBody 为了能够返回数据
     */
    @ExceptionHandler(Exception.class)//指定出现什么异常执行这个方法
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了全局异常处理！");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)//指定出现什么异常执行这个方法
    @ResponseBody
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行了特定异常处理！");
    }


    //自定义异常
    @ExceptionHandler(GuLiException.class)//指定出现什么异常执行这个方法
    @ResponseBody
    public R error(GuLiException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }


}

