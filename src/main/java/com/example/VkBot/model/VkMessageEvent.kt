package com.example.VkBot.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Data-класс для представления события, получаемого от VK Callback API.
 * Поле "obj" десериализуется из JSON-поля "object" и содержит данные о сообщении.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class VkMessageEvent(
    var type: String? = null,
    var event_id: String? = null,
    var v: String? = null,
    @JsonProperty("object")
    var obj: MessageContainer? = null,
    var group_id: Int? = null,
    var secret: String? = null
)