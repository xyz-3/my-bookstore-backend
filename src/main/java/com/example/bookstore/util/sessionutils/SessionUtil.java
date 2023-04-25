package com.example.bookstore.util.sessionutils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {
    public static boolean checkAuth() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // Session
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession(false);

            if (session != null) {
                Integer userType = (Integer) session.getAttribute("role");
                return userType != null && userType >= 0;
            }
        }
        return false;
    }

    public static JSONObject getAuth() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // Session
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession(false);
            Cookie userCookie;
            if (request.getParameter("JSESSIONID") != null) {
                userCookie = new Cookie("JSESSIONID",
                        request.getParameter("JSESSIONID"));
            } else {
                String sessionId = session.getId();
                userCookie = new Cookie("JSESSIONID", sessionId);
            }
//            HttpServletResponse response = null;
//            response.addCookie(userCookie);
//            String ss_id = request.getSession(false).getId();

            if (session != null) {
                JSONObject ret = new JSONObject();
                ret.put("username", (String)session.getAttribute("username"));
                ret.put("password", (String)session.getAttribute("password"));
                return ret;
            }
        }
        return null;
    }

    public static void setSession(JSONObject data) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // Session
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession();
//            String ss_id = session.getId();

            for (Object str : data.keySet()) {
                String key = (String) str;
                Object val = data.get(key);
                session.setAttribute(key, val);
            }

            HttpSession test = request.getSession(false);
            System.out.println(test.getAttribute("username"));
        }
    }

    public static Boolean removeSession() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        // Session
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession(false);

            if (session != null) {
                session.invalidate();
            }
        }
        return true;
    }
}
