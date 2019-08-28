package com.demo.mealorder.websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.server.WebSocketServer;

import java.util.HashMap;

class ServerManager {
    private ServerSocket serverSocket = null;
    private HashMap<WebSocketServer, String> webSocketStringHashMap = new HashMap<>();

    public void login(String username, WebSocketServer webSocketServer) throws IllegalArgumentException{
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if (webSocketServer == null) {
            throw new IllegalArgumentException("websocket不能为空");
        }
        webSocketStringHashMap.put(webSocketServer, username);
    }
}
