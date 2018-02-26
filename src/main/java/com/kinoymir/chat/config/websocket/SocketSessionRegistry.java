package com.kinoymir.chat.config.websocket;



import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SocketSessionRegistry {

    private final ConcurrentMap<String, String> userSessionIds = new ConcurrentHashMap<String, String>();

    public SocketSessionRegistry() {
    }

    /**
     * 获取sessionId
     *
     * @param user
     * @return
     */
    public String getSessionId(String user) {
        return userSessionIds.getOrDefault(user,null);
    }


    /**
     * 获取所有session
     *
     * @return
     */
    public ConcurrentMap<String, String> getAllSessionIds() {
        return this.userSessionIds;
    }

    /**
     * register session
     *
     * @param user
     * @param sessionId
     */
    public void registerSessionId(String user, String sessionId) {
        userSessionIds.putIfAbsent(user, sessionId);
    }

    /**
     * 移除
     * @param userName
     * @param sessionId
     */
    public void unregisterSessionId(String userName, String sessionId) {
        userSessionIds.remove(userName, sessionId);
    }
}
