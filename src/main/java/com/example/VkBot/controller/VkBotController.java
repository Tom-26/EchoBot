package com.example.VkBot.controller;

import com.example.VkBot.model.Message;
import com.example.VkBot.model.MessageContainer;
import com.example.VkBot.model.VkMessageEvent;
import com.example.VkBot.service.VkMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vk")
public class VkBotController {
    private final ObjectMapper objectMapper;
    private final VkMessageService messageService;
    private final String confirmationToken = "ff3e9e92";

    public VkBotController(VkMessageService messageService, ObjectMapper objectMapper) {
        this.messageService = messageService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String payload) {
        try {
            System.out.println("Raw payload: " + payload);
            VkMessageEvent event = objectMapper.readValue(payload, VkMessageEvent.class);

            // Проверяем, что тип запроса "confirmation" и group_id соответствует ожидаемому
            if ("confirmation".equals(event.getType()) && event.getGroup_id() != null && event.getGroup_id() == 229884287) {
                return ResponseEntity.ok(confirmationToken);
            }

            if ("message_new".equals(event.getType())) {
                MessageContainer container = event.getObj();
                Message message = container.getMessage();
                if (message == null) {
                    message = new Message();
                    message.setText(container.getBody());
                    message.setFrom_id(container.getUser_id());
                    message.setPeer_id(container.getUser_id());
                }
                String text = message.getText();
                Integer peerId = message.getPeer_id();
                if (peerId == null) {
                    peerId = message.getFrom_id();
                }
                String eventId = event.getEvent_id();
                Integer userId = message.getFrom_id();

                System.out.println("Получено сообщение: " + text + ", peer_id: " + peerId);
                messageService.sendMessage(peerId, eventId, userId, text);
                return ResponseEntity.ok("ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("ok");
    }
}