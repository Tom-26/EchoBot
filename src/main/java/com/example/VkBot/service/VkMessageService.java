package com.example.VkBot.service;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiExtendedException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class VkMessageService {
    private static final Logger logger = LoggerFactory.getLogger(VkMessageService.class);

    private final VkApiClient vk;
    private final GroupActor actor;

    public VkMessageService(
            @Value("${vk.groupId}") Integer groupId,
            @Value("${vk.communityToken}") String communityToken
    ) {
        TransportClient transportClient = HttpTransportClient.getInstance();
        this.vk = new VkApiClient(transportClient);
        // Преобразуем groupId в Long и создаем GroupActor
        this.actor = new GroupActor(groupId.longValue(), communityToken);
    }

    /**
     * Отправляет эхо-сообщение в чат с указанным peerId.
     *
     * @param peerId      идентификатор чата или пользователя
     * @param eventId     идентификатор события (из Callback API)
     * @param userId      идентификатор пользователя, от которого пришло сообщение
     * @param messageText текст входящего сообщения
     */
    public void sendMessage(Integer peerId, String eventId, Integer userId, String messageText) {
        // Проверки входных параметров
        if (peerId == null) {
            logger.error("Ошибка: peerId равен null. Невозможно отправить сообщение.");
            throw new IllegalArgumentException("peerId не может быть null");
        }
        if (messageText == null || messageText.trim().isEmpty()) {
            // Вместо выброса исключения логируем короткое сообщение и возвращаемся
            logger.warn("Нет текстового запроса: возможно, был отправлен стикер, фото, видео или другой тип медиа.");
            return;
        }
        if (eventId == null || eventId.trim().isEmpty()) {
            logger.warn("Предупреждение: eventId пустой или null. Это может повлиять на логирование событий.");
        }
        if (userId == null) {
            logger.warn("Предупреждение: userId равен null.");
        }

        try {
            Object response = vk.messages().sendDeprecated(actor)
                    .peerId(peerId.longValue())
                    .message("Вы сказали: " + messageText)
                    .randomId(generateRandomId())
                    .execute();
            logger.info("Ответ отправлен, id: {}", response);
        } catch (ApiExtendedException e) {
            int errorCode = e.getCode(); // Метод getCode() возвращает код ошибки
            if (errorCode == 901) {
                logger.warn("Невозможно отправить сообщение пользователю {}: нет разрешения.", userId);
                return;
            } else {
                logger.error("Ошибка при отправке сообщения: ", e);
                throw new RuntimeException("Ошибка отправки сообщения", e);
            }
        } catch (Exception ex) {
            logger.error("Общая ошибка при отправке сообщения: ", ex);
            throw new RuntimeException("Ошибка отправки сообщения", ex);
        }
    }

    // Метод для генерации случайного идентификатора запроса
    private int generateRandomId() {
        return new Random().nextInt(Integer.MAX_VALUE);
    }
}