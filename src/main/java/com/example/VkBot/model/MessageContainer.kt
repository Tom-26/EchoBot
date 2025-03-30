package com.example.VkBot.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Data-класс для представления объекта, содержащего информацию о клиенте и сообщение.
 * Поддерживает два варианта структуры:
 * 1. Стандартный вариант с вложенным объектом "message".
 * 2. Альтернативный вариант, когда поля сообщения приходят напрямую в объекте.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class MessageContainer(
    var client_info: ClientInfo? = null,
    var message: Message? = null,
    // Альтернативные поля, если объект не содержит вложенного "message"
    var id: Int? = null,
    var date: Long? = null,
    var out: Int? = null,
    var user_id: Int? = null,
    var read_state: Int? = null,
    var title: String? = null,
    var body: String? = null
)