package com.example.bookstore.controller;

import com.example.bookstore.entity.User;
import com.example.bookstore.service.UserService;
import com.example.bookstore.util.msgutils.Msg;
import com.example.bookstore.util.msgutils.MsgUtil;
import com.example.bookstore.util.request.RegisterForm;
import com.example.bookstore.util.response.UserInfoForm;
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
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public UserInfoForm getUserByAccount(@PathVariable("UserName") String UserName) {
        User user =  userService.getUserByUserName(UserName);
        if(user == null){
            return null;
        }else{
            UserInfoForm userInfoForm = new UserInfoForm();
            userInfoForm.setUsername(user.getUsername());
            userInfoForm.setId(user.getId());
            userInfoForm.setAvatar(user.getAvatar());
            userInfoForm.setEmail(user.getEmail());
            userInfoForm.setNotes(user.getNotes());
            System.out.println(userInfoForm);
            return userInfoForm;
        }
    }

    @RequestMapping(value = "/User/ChangeInfo/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public Msg ChangeInfo(@PathVariable("id") Integer id, @RequestBody @NotNull RegisterForm registerForm) {
        User user = userService.changeInfo(id, registerForm);
        if(user == null){
            return MsgUtil.makeMsg(MsgUtil.ERROR, "User not found");
        }else{
            JSONObject ret_data = new JSONObject();
            ret_data.put("username", user.getUsername());
            ret_data.put("id", user.getId());
            ret_data.put("role", user.getRole());
            ret_data.put("avatar", user.getAvatar());
            return MsgUtil.makeMsg(MsgUtil.SUCCESS, "Change user info success", ret_data);
        }
    }

}
