package com.rg.jamaah.controller.user

import com.rg.jamaah.controller.auth.AuthController
import com.rg.jamaah.controller.auth.AuthenticationRequest
import com.rg.jamaah.controller.member.MemberRequest
import com.rg.jamaah.model.Role
import com.rg.jamaah.model.User
import com.rg.jamaah.model.UserRequest
import com.rg.jamaah.model.UserResponse
import com.rg.jamaah.service.AuthenticationService
import com.rg.jamaah.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.*
import kotlin.collections.List




@RestController
@RequestMapping("/api/user")
class UserController(
    private  val userService: UserService,
    private val authController: AuthController
) {
    @PostMapping
    fun createUser(@RequestBody userRequest: UserRequest): Any? {

        val result= userService.createUser(userRequest)
        if(result == null){
            return mapOf(
                "result" to 0,
                "msg" to "Email already exist"

            )

        }else{
            //val authRequest = AuthenticationRequest(email = "john.doe@example.com", password = "password123")
            //val authResponse = authController.anotherFunction(authRequest)
            //abccc


            return mapOf(
                "result" to 1,
                "msg" to "User created successfully"
            )
           /* return mapOf(
                "result" to 1,
                "msg" to "User created successfully",
                "token" to authResponse,
                "email" to userRequest.email,
                "email" to result["email"]

            )

            */
        }

    }


    @GetMapping
    fun listAll() : List<UserResponse> =
        userService.findALl()
            .map { it.toResponse() }

     @GetMapping("/{uuid}")
     fun findByUUID(@PathVariable uuid: UUID) : UserResponse =
         userService.findByUUID(uuid)
             ?.toResponse()
             ?: throw ResponseStatusException(HttpStatus.NOT_FOUND,"Cannot find a user")

     @DeleteMapping("/{uuid}")
     fun deleteByUUID(@PathVariable uuid: UUID) : ResponseEntity<Boolean>{
         val success = userService.deleteByUUID(uuid)

         return if (success)
          ResponseEntity.noContent()
              .build()
         else
             throw ResponseStatusException(HttpStatus.NOT_FOUND,"Cannot find a user")

     }



    /*
    private fun UserRequest.toModel() : User =

        User(
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
            password = this.password,
            gender = this.gender,
            deviceId = this.deviceId

            //role = Role.USER
        )

     */


    private fun User.toResponse() : UserResponse =
        UserResponse(
            id = this.id,
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
            password = this.password,
            gender = this.gender,
            regDt = this.regDt,
            status = this.status,
            deviceId = this.deviceId,
        )


}