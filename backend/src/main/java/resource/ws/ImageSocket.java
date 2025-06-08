package resource.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ImageDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import mapper.ImageMapper;
import service.ImageService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ServerEndpoint("/ws/images")
@ApplicationScoped
public class ImageSocket {

    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private static volatile boolean schedulerStarted = false;

    @Inject
    ImageMapper imageMapper;

    @Inject
    ImageService imageService;

    @Inject
    ObjectMapper mapper;

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        if (!schedulerStarted) {
            synchronized (ImageSocket.class) {
                if (!schedulerStarted) {
                    scheduler.scheduleAtFixedRate(this::broadcastImages, 0, 3, TimeUnit.SECONDS);
                    schedulerStarted = true;
                }
            }
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    private void broadcastImages() {
        synchronized (sessions) {
            if (sessions.isEmpty()) {
                return;
            }
            try {
                List<ImageDto> images = imageService.getAllImages().stream()
                        .map(imageMapper::toDto)
                        .toList();

                String json = mapper.writeValueAsString(images);

                sessions.stream()
                        .filter(Session::isOpen)
                        .forEach(session -> session.getAsyncRemote().sendText(json));
            } catch (Exception e) {
                sessions.forEach(this::onClose);
            }
        }
    }
}
