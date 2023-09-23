package com.example.bookstore.util.websocket;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/websocket/transfer/{userId}")
@Component
public class WebSocketServer {
    public WebSocketServer(){
        System.out.println("新的连接已经开启");
    }

    private static final AtomicInteger COUNT = new AtomicInteger();
    private static final ConcurrentHashMap<String, Session> SESSIONS = new ConcurrentHashMap<>();

    public boolean sendMessage(Session toSession, String message) {
        if(toSession != null){
            try{
                toSession.getBasicRemote().sendText(message);
                return true;
            }catch(IOException e){
                e.printStackTrace();
            }
        }else{
            System.out.println("对方不在线");
            return false;
        }
        return false;
    }

    public void sendMessageToUser(String user, String message) {
        Session session = SESSIONS.get(user);
        sendMessage(session, message);
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("服务端收到消息:" + message);
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        if (SESSIONS.get(userId) != null) {
            return;
        }
        SESSIONS.put(userId, session);
        COUNT.incrementAndGet();
        System.out.println(userId + "加入,当前在线人数:" + COUNT);
    }

    @OnClose
    public void onClose(@PathParam("userId") String userId) {
        SESSIONS.remove(userId);
        COUNT.decrementAndGet();
        System.out.println(userId + "退出,当前在线人数:" + COUNT);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
}
