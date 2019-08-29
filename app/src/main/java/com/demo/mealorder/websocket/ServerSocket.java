package com.demo.mealorder.websocket;

import android.util.Log;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class ServerSocket extends WebSocketServer {
    private ServerManager serverManager;
    private static final String TAG = "ServerSocket";

    public ServerSocket(ServerManager serverManager, int port) {
        super(new InetSocketAddress(port));
        this.serverManager = serverManager;
        String.class.getCanonicalName();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        Log.i(TAG, "onOpen");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        Log.i(TAG, "onClose: " + reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        Log.i(TAG, message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        Log.i(TAG, ex.getMessage());
    }

    @Override
    public void onStart() {

    }
}
