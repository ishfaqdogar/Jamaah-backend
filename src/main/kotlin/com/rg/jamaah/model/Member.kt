package com.rg.jamaah.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document(collection = "members")
data class Member(
    @Id
    val id: UUID = UUID.randomUUID(),
    val email: String,
    val password: String,
    val role : com.rg.jamaah.model.Role2
)

enum class Role2 {
    USER, ADMIN
}
