package com.example.bookstore.controller;

import com.example.bookstore.entity.User;
import com.example.bookstore.service.UserService;
import com.example.bookstore.util.msgutils.Msg;
import com.example.bookstore.util.msgutils.MsgCode;
import com.example.bookstore.util.msgutils.MsgUtil;
import com.example.bookstore.util.request.RegisterForm;
import com.example.bookstore.util.sessionutils.SessionUtil;
import jakarta.transaction.Transactional;
import net.sf.json.JSON;
import org.jetbrains.annotations.NotNull;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

//    @RequestMapping(value = "/Login", method = RequestMethod.POST)
//    @CrossOrigin(origins = "http://localhost:3000")
//    public Msg Login(@RequestBody @NotNull RegisterForm registerForm) {
//        User auth = userService.checkUser(registerForm);
//        if(auth != null){
//            JSONObject obj = new JSONObject();
//            obj.put("username", auth.getUsername());
//            SessionUtil.setSession(obj);
//
//            JSONObject data = JSONObject.fromObject(auth);
//            //remove password
//            data.remove("password");
//
//            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, data);
//        }
//        else{
//            return MsgUtil.makeMsg(MsgCode.LOGIN_USER_ERROR);
//        }
//    }

//    @RequestMapping(value = "/Logout", method = RequestMethod.POST)
//    @CrossOrigin(origins = "http://localhost:3000")
//    public Msg Logout(){
//        Boolean status = SessionUtil.removeSession();
//
//        if(status){
//            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGOUT_SUCCESS_MSG);
//        }
//        return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.LOGOUT_ERR_MSG);
//    }

//    @RequestMapping(value = "/Register", method = RequestMethod.POST)
//    @CrossOrigin(origins = "http://localhost:3000")
//    public Msg Register(@RequestBody @NotNull RegisterForm registerForm) {
//        //username already exist
//        if(userService.checkUserExist(registerForm.getUsername())){
//            return MsgUtil.makeMsg(MsgCode.REGISTER_NAMEEXIST_ERROR, MsgUtil.REGISTER_NAMEEXIST_ERROR_MSG);
//        }
//        //email already exist
//        if(userService.checkUserExistByEmail(registerForm.getEmail())){
//            return MsgUtil.makeMsg(MsgCode.REGISTER_EMAILEXIST_ERROR, MsgUtil.REGISTER_EMAILEXIST_ERROR_MSG);
//        }
//        //register success
//        if(userService.handleRegister(registerForm) != null){
//            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.REGISTER_SUCCESS_MSG);
//        }else{
//            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.REGISTER_ERR_MSG);
//        }
//    }

//    @RequestMapping(value = "/checkSession", method = RequestMethod.POST)
//    @CrossOrigin(origins = "http://localhost:3000")
//    public Msg checkSession(Long id){
////        JSONObject auth = SessionUtil.getAuth();
//        User user = userService.getUserById(id);
//        System.out.println(user);
//
//        if(user == null){
//            return MsgUtil.makeMsg(MsgCode.NOT_LOGGED_IN_ERROR);
//        }
//        else{
//            //change user to json format
//            JSONObject data = JSONObject.fromObject(user);
//            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, data);
//        }
//    }
}
