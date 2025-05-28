package resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@ServerEndpoint("/ws/history")
@ApplicationScoped
public class ImageSocket {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @OnOpen
    public void onOpen(Session session) {
        sendImagesAsync(session);
    }

    @OnMessage
    public void onMessage(String msg, Session session) {
        sendImagesAsync(session);
    }

    private void sendImagesAsync(Session session) {
        CompletableFuture.runAsync(() -> {
            try {
                String currentTime = LocalDateTime.now().format(FORMATTER);
                Map<String, String> data = Map.of("timestamp", currentTime);
                String json = OBJECT_MAPPER.writeValueAsString(data);
                session.getAsyncRemote().sendText(json);
            } catch (Exception e) {
                session.getAsyncRemote().sendText(e.getMessage());
            }
        });
    }
}
