package com.example.VkBot.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Data-класс для представления события присоединения к группе в VK.
 * Используется для десериализации JSON, получаемого от VK Callback API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class GroupJoinEvent(
    var type: String? = null,
    var event_id: Int? = null,
    var v: String? = null,
    @JsonProperty("object")
    var obj: GroupJoinObject? = null, // Переименовано, чтобы избежать конфликта с ключевым словом 'object'
    var group_id: Int? = null
)