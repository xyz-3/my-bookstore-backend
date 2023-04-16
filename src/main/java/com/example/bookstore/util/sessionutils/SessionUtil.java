package com.example.bookstore.util.sessionutils;

//import com.reins.bookstore.constant.Constant;
import net.sf.json.JSONObject;
//import org.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {

//    public static boolean checkAuth(){
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        // Session
//        if(requestAttributes != null) {
//            HttpServletRequest request = requestAttributes.getRequest();
//            HttpSession session = request.getSession(false);
//
//            if(session != null) {
//                Integer userType = (Integer) session.getAttribute(Constant.USER_TYPE);
//                return userType != null && userType >= 0;
//            }
//        }
//        return false;
//    }

    public static JSONObject getAuth(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // Session
        if(requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession(false);

            if(session != null) {
                JSONObject ret = new JSONObject();
                ret.put("username", (String)session.getAttribute("username"));
                ret.put("password", (String)session.getAttribute("password"));
//                ret.put("Id", (String)session.getAttribute("Id"));
//                ret.put(Constant.USER_ID, (Integer)session.getAttribute(Constant.USER_ID));
//                ret.put(Constant.USERNAME, (String)session.getAttribute(Constant.USERNAME));
//                ret.put(Constant.USER_TYPE, (Integer)session.getAttribute(Constant.USER_TYPE));
                return ret;
            }
        }
        return null;
    }

    public static void setSession(JSONObject data){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // Session
        if(requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession();

            for(Object str:data.keySet()){
                String key = (String)str;
                Object val = data.get(key);
                session.setAttribute(key, val);
            }

            System.out.println(session);
        }
    }

    public static Boolean removeSession(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        // Session
        if(requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession(false);

            if(session != null) {
                session.invalidate();
            }
        }
        return true;
    }
}
