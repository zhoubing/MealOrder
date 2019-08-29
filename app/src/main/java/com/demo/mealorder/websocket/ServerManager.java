package com.demo.mealorder.websocket;

import android.util.Log;

import org.java_websocket.WebSocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ServerManager {
    private ServerSocket serverSocket = null;
    private HashMap<WebSocket, String> webSocketStringHashMap = new HashMap<>();

    public void login(String username, WebSocket webSocket) throws IllegalArgumentException {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if (webSocket == null) {
            throw new IllegalArgumentException("websocket不能为空");
        }
        webSocketStringHashMap.put(webSocket, username);
    }

    public void leave(WebSocket webSocket) {
        if (webSocketStringHashMap.containsKey(webSocket)) {
            webSocketStringHashMap.remove(webSocket);
        }
    }

    public void sendMessage(String userId, String message) {
        Set<Map.Entry<WebSocket, String>> entries = webSocketStringHashMap.entrySet();
        for (Map.Entry<WebSocket, String> entry : entries) {
            if (entry.getValue().equals(userId)) {
                entry.getKey().send(message);
                break;
            }
        }
    }

    public void start(int port) {
        if (port <= 0) {
            throw new IllegalArgumentException("port不正确");
        }
        Log.i("ServerManager","Start ServerSocket...");
        serverSocket = new ServerSocket(this, port);
        serverSocket.start();
        Log.i("ServerManager","Start ServerSocket Success...");
    }

    public void stop(int port) throws IOException, InterruptedException {
        serverSocket.stop();
    }
}
