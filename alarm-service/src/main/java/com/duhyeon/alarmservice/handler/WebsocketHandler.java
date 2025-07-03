package com.duhyeon.alarmservice.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Slf4j
public class WebsocketHandler extends TextWebSocketHandler {

    private static final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 클라이언트가 연결되었을 때 호출되는 메서드
        super.afterConnectionEstablished(session);
        sessions.add(session); // 세션을 리스트에 추가
        InetAddress clientIp = null;
        if (session.getRemoteAddress() != null) {
            clientIp = session.getRemoteAddress().getAddress();
        }

        log.info("WebSocket connection established: session={}, ip={}", session.getId(), clientIp);
    }

    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        log.info("WebSocket connection closed: {}", session.getId());
    }


    public void alarmSend(WebSocketSession session, String alarm) throws Exception {
        session.sendMessage(new TextMessage(alarm));
    }

    public void allSend(String alarm) {
        for(WebSocketSession session : sessions){
            try{
                alarmSend(session, alarm);
            } catch (Exception e){
                log.error(String.valueOf(e));
            }
        }
    }
}
