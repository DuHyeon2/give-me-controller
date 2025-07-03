package com.duhyeon.alarmservice.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Component
@Slf4j
public class WebsocketHandler extends TextWebSocketHandler {

    private static final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    // 세션마다 ping 스케줄 관리
    private final Map<String, ScheduledFuture<?>> pingTasks = new ConcurrentHashMap<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String nickname = (String) session.getAttributes().get("nickname");

        sessions.add(session);

        log.info("[WebSocket 연결] session={}, nickname={}", session.getId(), nickname);
        log.info("현재 연결 세션 수: {}", sessions.size());

        session.sendMessage(new TextMessage("{\"type\":\"nickname\",\"nickname\":\"" + nickname + "\"}"));

        ScheduledFuture<?> pingTask = scheduler.scheduleAtFixedRate(() -> {
            try {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage("{\"type\":\"ping\"}"));
                }
            } catch (Exception e) {
                log.warn("ping 메시지 전송 실패: {}", e.getMessage());
            }
        }, 30, 30, TimeUnit.SECONDS); // 30초마다 ping

        pingTasks.put(session.getId(), pingTask);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String nickname = (String) session.getAttributes().get("nickname");
        String chatMessage = message.getPayload();
        chatSend(nickname, chatMessage);
    }

    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        log.info("WebSocket connection closed: {}", (String) session.getAttributes().get("nickname"));
    }


    public void alarmSend(WebSocketSession session, String alarm) throws Exception {
        session.sendMessage(new TextMessage(alarm));
    }

    public void noticeSend(String key, String value) {
        String alarm = "{\"type\":\"notice\",\"key\":\"" + key + "\",\"value\":\"" + value + "\"}";
        for(WebSocketSession session : sessions){
            try{
                alarmSend(session, alarm);
            } catch (Exception e){
                log.error(String.valueOf(e));
            }
        }
    }

    public void chatSend(String nickname, String value) {
        String chatMessage = "{\"type\":\"chat\",\"nickname\":\"" + nickname + "\",\"message\":\"" + value + "\"}";
        for(WebSocketSession session : sessions){
            try{
                session.sendMessage(new TextMessage(chatMessage));
            } catch (Exception e){
                log.error("WebSocket 메시지 전송 실패: {}", e.getMessage());
            }
        }
    }
}
