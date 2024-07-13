package com.rg.jamaah.service

import com.rg.jamaah.model.User
import com.rg.jamaah.model.UserRequest
import com.rg.jamaah.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService (
    private val userRepository : UserRepository,
    private val encoder: PasswordEncoder
) {
    fun createUser(userRequest: UserRequest) : Any? {
        val found = userRepository.findByEmail(userRequest.email)

        return if(found == null){
           val user =  User(
                id = UUID.randomUUID(),
                firstName = userRequest.firstName,
                lastName = userRequest.lastName,
                email = userRequest.email,
                password = encoder.encode(userRequest.password),
                gender = userRequest.gender,
                deviceId = userRequest.deviceId

            )
            userRepository.save(user)
            user
        } else null
    }

    fun findALl(): List<User> =
        userRepository.findAll()
    fun findByUUID(uuid : UUID) : User ? =
        userRepository.findById(uuid).orElse(null)
       // userRepository.findByUUID(uuid)


    fun deleteByUUID(uuid: UUID): Boolean {
        val memberExists = userRepository.existsById(uuid)
        if (memberExists) {
            userRepository.deleteById(uuid)
        }
        return memberExists
    }
   // fun deleteByUUID(uuid: UUID) : Boolean =
       // userRepository.deleteByUUID(uuid)


}