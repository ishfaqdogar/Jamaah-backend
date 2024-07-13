package com.rg.jamaah.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.UUID

@Document(collection = "users")
data class User(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val email: String,
    val password: String,
    val regDt: LocalDateTime = LocalDateTime.now(),
    val status: String ="Active",
    val deviceId: String
    //val role: com.rg.jamaah.model.Role
)

enum class Role {
    USER, ADMIN
}

data class UserRequest(
    val firstName: String,
    val lastName: String,
    val gender: String,
    val email: String,
    val password: String,
    val deviceId: String
)

data class UserResponse(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val email: String,
    val password: String,
    val status: String,
    val regDt: LocalDateTime,
    val deviceId: String
)
