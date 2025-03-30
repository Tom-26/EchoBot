package com.example.VkBot.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Data-класс, представляющий вложенный объект события присоединения к группе.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class GroupJoinObject(
    var user_id: Int? = null,
    var join_type: String? = null
)