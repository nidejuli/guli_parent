package com.huatian.servicebase.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor//生成有参数构造方法
@NoArgsConstructor//无参数构造方法
public class GuLiException extends RuntimeException {

    private Integer code;//返回码

    private String msg;//异常信息

}
