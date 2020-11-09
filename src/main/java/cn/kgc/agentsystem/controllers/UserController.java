package cn.kgc.agentsystem.controllers;

import cn.kgc.agentsystem.VO.ResultVO;
import cn.kgc.agentsystem.biz.UserService;
import cn.kgc.agentsystem.common.ResultVoUtil;
import cn.kgc.agentsystem.enums.ResultEnum;
import cn.kgc.agentsystem.exception.MyException;
import cn.kgc.agentsystem.pojo.User;
import lombok.extern.slf4j.Slf4j;
import oracle.jrockit.jfr.jdkevents.ThrowableTracer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    //登录页面
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(){
        return "/pages/login.jsp";
    }

    //错误页面
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error(){
        return "/pages/error.jsp";
    }

    //用户登录:参数:userCode和userPassword
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, @RequestParam("userCode") String userCode,
                          @RequestParam("userPassword") String userPassword) {
        User user = new User();
        user.setUserCode(userCode);
        user.setUserPassword(userPassword);
        User loginUser = userService.login(user);

        //将用户放入session
        request.getSession().setAttribute("result",ResultVoUtil.success(loginUser));
        return "/pages/main.jsp";
    }

    //用户退出
    @RequestMapping(value = "/quit", method = RequestMethod.GET)
    public ResultVO quit(HttpServletRequest request) {
        request.getSession().setAttribute("result", null);
        return ResultVoUtil.relogin();
    }

    //修改密码
    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    @ResponseBody
    public String modifyPassword(HttpServletRequest request,
                                   @RequestParam("userCode") String userCode,
                                   @RequestParam("oldPassword") String oldPassword,
                                   @RequestParam("newPassword") String newPassword) {
        //验证用户是否登录
        User user=null;
        try {
            ResultVO result = (ResultVO) request.getSession().getAttribute("result");
            user = (User) result.getData();
        }catch (Exception e){
            log.error("[数据转换]result-->User:{}",request.getSession().getAttribute("result"));
            request.getSession().setAttribute("result",null);
            throw new MyException(ResultEnum.DATA_CAST_ERROR);
        }
        if (user == null) {
            log.warn("[修改密码]session中user为空");
            throw new MyException(ResultEnum.LOGIN_USER_ERRER);
        }
        //验证输入的用户名密码是否正确
        User target=userService.getUserByIdOrUserCode(user);
        if(!userService.checkOver(target,userCode,oldPassword)){
            request.getSession().setAttribute("result",null);
            throw new MyException(ResultEnum.LOGIN_INFO_WRONG);
        }else{
            //修改密码
            target.setUserPassword(newPassword);
            userService.modifyUser(target);
        }
        //退出登录
        return "success";
    }
}
