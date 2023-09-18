package com.example.bookstore.util.sessionutils;

import jakarta.servlet.http.*;
import net.sf.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class SessionUtil {

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

            for (Object str : data.keySet()) {
                String key = (String) str;
                Object val = data.get(key);
                session.setAttribute(key, val);
            }
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
