package com.rg.jamaah.repository


import com.rg.jamaah.model.User
import org.springframework.data.mongodb.repository.MongoRepository

import java.util.*

interface UserRepository : MongoRepository<User, UUID> {
    fun findByEmail(email: String): User?
    //fun findByName(password: String): Member?
}

//@Repository
/*class UserRepository(
    private val encoder : PasswordEncoder
) {
    private val users = mutableListOf(
        User (
            id = UUID.randomUUID(),
            email = "email-1@gmail.com",
            password = encoder.encode("pass1"),
            role = Role.USER
        ),
        User (
            id = UUID.randomUUID(),
            email = "email-2@gmail.com",
            password = encoder.encode("pass2"),
            role = Role.ADMIN
        ),
        User (
            id = UUID.randomUUID(),
            email = "email-3@gmail.com",
            password = encoder.encode("pass3"),
            role = Role.USER
        )
    )

    fun save(user : User) : Boolean {
        val updated = user.copy(password = encoder.encode(user.password))
        return users.add(updated)
    }


    fun findByEmail(email : String) : User? =
            users
                .firstOrNull { it.email == email}

    fun findAll(): List<User> =
        users

    fun findByUUID(uuid : UUID) : User? =
        users.firstOrNull{it.id == uuid}

    fun deleteByUUID(uuid : UUID) : Boolean {
        val foundUser = findByUUID(uuid)

        return foundUser?.let {
            users.remove(it)
        } ?: false
    }
}

 */