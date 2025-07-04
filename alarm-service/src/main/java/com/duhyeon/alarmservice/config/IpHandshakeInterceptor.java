package com.duhyeon.alarmservice.config;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class IpHandshakeInterceptor implements HandshakeInterceptor {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        String nickname = generateNickname();
        attributes.put("nickname", nickname);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception exception) {

    }

    public String generateNickname() {
        String url = "https://nickname.hwanmoo.kr/?format=json&max_length=8&type=animal";
        try {
            String response = restTemplate.getForObject(url, String.class);
            JSONObject json = new JSONObject(response);
            JSONArray words = json.getJSONArray("words");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < words.length(); i++) {
                sb.append(words.getString(i));
                if (i < words.length() - 1) sb.append(" ");
            }
            return sb.toString();
        } catch (Exception e) {
            // 실패하면 기본 닉네임 반환
            return "익명 유저";
        }
    }
}
