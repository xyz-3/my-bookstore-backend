package com.example.bookstore.util.msgutils;
import com.example.bookstore.util.response.UserInfoForm;
import net.sf.json.JSONObject;

public class MsgUtil {

    public static final int SUCCESS = 0;
    public static final int ERROR = -1;
    public static final int LOGIN_USER_ERROR = -100;
    public static final int NOT_LOGGED_IN_ERROR = -101;

    public static final int REGISTER_NAMEEXIST_ERROR = -200;

    public static final int REGISTER_EMAILEXIST_ERROR = -201;


    public static final int UPLOAD_ERROR = -300;


    public static final String SUCCESS_MSG = "success!";
    public static final String LOGIN_SUCCESS_MSG = "login success!";
    public static final String LOGOUT_SUCCESS_MSG = "logout success!";
    public static final String LOGOUT_ERR_MSG = "logout error!";
    public static final String ERROR_MSG = "error!";
    public static final String LOGIN_USER_ERROR_MSG = "username or password input error!";
    public static final String NOT_LOGGED_IN_ERROR_MSG = "login expired, try again";

    public static final String REGISTER_NAMEEXIST_ERROR_MSG = "username already exist!";
    public static final String REGISTER_EMAILEXIST_ERROR_MSG = "email already exist!";
    public static final String REGISTER_SUCCESS_MSG = "register success!";
    public static final String REGISTER_ERR_MSG = "register error!";



    public static Msg makeMsg(MsgCode code, JSONObject data){
        return new Msg(code, data);
    }

    public static Msg makeMsg(MsgCode code, String msg, JSONObject data){
        return new Msg(code, msg, data);
    }

    public static Msg makeMsg(MsgCode code){
        return new Msg(code);
    }

    public static Msg makeMsg(MsgCode code, String msg){
        return new Msg(code, msg);
    }

    public static Msg makeMsg(int status, String msg, JSONObject data){
        return new Msg(status, msg, data);
    }

    public static Msg makeMsg(int status, String msg){
        return new Msg(status, msg);
    }
}