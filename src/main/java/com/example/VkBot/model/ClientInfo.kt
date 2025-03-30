package com.example.VkBot.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Data-класс для представления информации о клиенте (client_info),
 * отправившем сообщение через VK Callback API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class ClientInfo(
    var button_actions: List<String>? = null,
    var keyboard: Boolean? = null,
    var inline_keyboard: Boolean? = null,
    var carousel: Boolean? = null,
    var lang_id: Int? = null
)