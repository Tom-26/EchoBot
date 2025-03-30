package com.example.VkBot.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Data-класс для представления сообщения, полученного от VK.
 * Все поля объявлены как var, чтобы Java-код мог использовать сеттеры.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Message(
    var date: Long? = null,
    var from_id: Int? = null,
    var id: Int? = null,
    var version: Long? = null,
    var out: Int? = null,
    var fwd_messages: List<Any>? = null,
    var important: Boolean? = null,
    var is_hidden: Boolean? = null,
    var attachments: List<Any>? = null,
    var conversation_message_id: Int? = null,
    var text: String? = null,
    var is_unavailable: Boolean? = null,
    var peer_id: Int? = null,
    var random_id: Int? = null
)