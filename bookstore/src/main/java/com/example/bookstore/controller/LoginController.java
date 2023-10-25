package com.example.bookstore.controller;

import com.example.bookstore.entity.User;
import com.example.bookstore.entity.UserAuth;
import com.example.bookstore.service.TimerService;
import com.example.bookstore.service.UserService;
import com.example.bookstore.util.msgutils.Msg;
import com.example.bookstore.util.msgutils.MsgCode;
import com.example.bookstore.util.msgutils.MsgUtil;
import com.example.bookstore.util.request.CheckForm;
import com.example.bookstore.util.request.LoginForm;
import com.example.bookstore.util.request.RegisterForm;
import com.example.bookstore.util.response.UserInfoForm;
import com.example.bookstore.util.sessionutils.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import net.sf.json.JSON;
import org.jetbrains.annotations.NotNull;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@RestController
@Transactional
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private TimerService timerService;

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public Msg Login(@RequestBody @NotNull LoginForm loginForm){
        UserAuth userAuth = userService.handleLogin(loginForm);
        if(userAuth != null){
            User user = userAuth.getUser();
            if(user.isBlocked()) {
                return MsgUtil.makeMsg(MsgCode.ERROR, "User is blocked");
            }
            JSONObject obj = new JSONObject();
            obj.put("username", user.getUsername());
            obj.put("id", user.getId());
            obj.put("role", user.getRole());
            SessionUtil.setSession(obj);

            JSONObject ret_data = new JSONObject();
            ret_data.put("username", user.getUsername());
            ret_data.put("id", user.getId());
            ret_data.put("role", user.getRole());
            ret_data.put("avatar", user.getAvatar());

            timerService.timerStart();
            return MsgUtil.makeMsg(MsgCode.SUCCESS, "Login success", ret_data);
        }
        else{
            return MsgUtil.makeMsg(MsgCode.ERROR, "Login failed");
        }
    }

    @RequestMapping(value = "/checkSession", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public Msg checkSession(){
        JSONObject auth = SessionUtil.getAuth();
        if (auth.size() == 0) {
            return MsgUtil.makeMsg(MsgCode.NOT_LOGGED_IN_ERROR);
        } else {
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, auth);
        }
    }

    @RequestMapping(value = "/Logout", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public Msg Logout(HttpServletRequest request){
        Boolean status = SessionUtil.removeSession();
        if(status){
            request.getSession(false);
            String timer = timerService.timerStop();
            JSONObject data = new JSONObject();
            data.put("timer", timer);
            System.out.println(timer);
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGOUT_SUCCESS_MSG, data);
        }
        return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.LOGOUT_ERR_MSG);
    }

    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public Msg Register(@RequestBody @NotNull RegisterForm registerForm){
        //username already exist
        if(userService.checkUserExist(registerForm.getUsername())){
            return MsgUtil.makeMsg(MsgCode.REGISTER_NAMEEXIST_ERROR, MsgUtil.REGISTER_NAMEEXIST_ERROR_MSG);
        }
        //email already exist
        if(userService.checkUserExistByEmail(registerForm.getEmail())){
            return MsgUtil.makeMsg(MsgCode.REGISTER_EMAILEXIST_ERROR, MsgUtil.REGISTER_EMAILEXIST_ERROR_MSG);
        }
        //register success
        if(userService.handleRegister(registerForm) != null){
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.REGISTER_SUCCESS_MSG);
        }else{
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.REGISTER_ERR_MSG);
        }
    }
}
