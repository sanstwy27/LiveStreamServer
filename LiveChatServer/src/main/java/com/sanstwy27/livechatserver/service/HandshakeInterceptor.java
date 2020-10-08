package com.sanstwy27.livechatserver.service;

import com.sanstwy27.livechatserver.entity.UserEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Sanstwy27
 * @create 10/7/2020
 */

public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // before http protocol upgrade to websocket protocol
        // judge user
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            // request.getSession(true): return session if exist else create new one
            // request.getSession(false): return session if exist else return null
            HttpSession session = servletRequest.getServletRequest().getSession();
            // simple security by session
            UserEntity user = (UserEntity) session.getAttribute("user");
            if (user != null) {
                // handshake success
                return super.beforeHandshake(request, response, wsHandler, attributes);
            }else {
                // handshake failed
                return false;
            }
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
