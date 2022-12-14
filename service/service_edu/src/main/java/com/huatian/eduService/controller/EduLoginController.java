package com.huatian.eduService.controller;

import com.huatian.commonUtils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(description = "登陆管理")
@RequestMapping("eduService/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("/login")
    public Result login(){
        HashMap<String,Object> resultMap=new HashMap<>();
        resultMap.put("token","admin");
        return Result.ok().data(resultMap);
    }


    @GetMapping("/info")
    public Result info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", "[admin]");
        map.put("name", "admin");
        map.put("avatar", "http://yhstore.top/oneblog/20210823181042333.png");
        map.put("introduction","hahahhah");
        return Result.ok().data(map);
    }

    // 登出
    @PostMapping("/logout")
    public Result logOut() {
        return Result.ok();
    }
}
