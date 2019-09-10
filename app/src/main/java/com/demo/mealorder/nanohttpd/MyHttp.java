package com.demo.mealorder.nanohttpd;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;

public class MyHttp extends NanoHTTPD {
    public MyHttp() throws IOException {
        super(8081);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    }

    @Override
    public Response serve(IHTTPSession session) {
        return newFixedLengthResponse("Hello from Httpd!");
    }
}
