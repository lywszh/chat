//package com.kinoymir.chat.config.websocket;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.web.socket.messaging.SessionConnectEvent;
//
//public class STOMPConnectEventListener implements ApplicationListener<SessionConnectEvent> {
//
//    @Autowired
//    private SocketSessionRegistry ssr;
//
//
//    @Override
//    public void onApplicationEvent(SessionConnectEvent event) {
//        String userId = event.getUser().toString();
//        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
//        String sessionId = sha.getSessionId();
//        ssr.registerSessionId(userId,sessionId);
//    }
//}
