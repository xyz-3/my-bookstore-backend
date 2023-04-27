package com.example.bookstore.controller;

import com.example.bookstore.entity.User;
import com.example.bookstore.service.UserService;
import com.example.bookstore.util.msgutils.Msg;
import com.example.bookstore.util.msgutils.MsgUtil;
import com.example.bookstore.util.request.RegisterForm;
import jakarta.transaction.Transactional;
import net.sf.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //get user info
    @RequestMapping(value = "/user/{UserName}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public Msg getUserByAccount(@PathVariable("UserName") String UserName) {
        User user =  userService.getUserByUserName(UserName);
        if(user == null){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "User not found");
        }else{
            JSONObject ret_data = new JSONObject();
            ret_data.put("username", user.getUsername());
            ret_data.put("id", user.getId());
            ret_data.put("role", user.getRole());
            ret_data.put("email", user.getEmail());
            ret_data.put("notes", user.getNotes());
            ret_data.put("avatar", user.getAvatar());
            return MsgUtil.makeMsg(MsgUtil.SUCCESS, "Get user info success", ret_data);
        }
    }

    @RequestMapping(value = "/User/ChangeInfo/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public Msg ChangeInfo(@PathVariable("id") Integer id, @RequestBody @NotNull RegisterForm registerForm) {
//        System.out.println("id: " + id);
//        System.out.println(registerForm);
        User user = userService.changeInfo(id, registerForm);
        if(user == null){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "User not found");
        }else{
            JSONObject ret_data = new JSONObject();
            ret_data.put("username", user.getUsername());
            ret_data.put("id", user.getId());
            ret_data.put("role", user.getRole());
            ret_data.put("email", user.getEmail());
            ret_data.put("notes", user.getNotes());
            ret_data.put("avatar", user.getAvatar());
            return MsgUtil.makeMsg(MsgUtil.SUCCESS, "Change user info success", ret_data);
        }
    }

}
