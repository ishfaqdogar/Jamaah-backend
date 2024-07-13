package com.rg.jamaah.controller.member


import com.rg.jamaah.model.Member
import com.rg.jamaah.model.Product
import com.rg.jamaah.model.Role2
import com.rg.jamaah.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/member")
class MemberController(
    private val memberService: MemberService,
    private val encoder: PasswordEncoder
) {



    @PostMapping
    fun createMember(@RequestBody memberRequest: MemberRequest): ResponseEntity<Any> {
        return if (memberService.findByEmail(memberRequest.email) == null) {
            ResponseEntity.ok(memberService.save(memberRequest))
        } else {
            ResponseEntity("Email already exists", HttpStatus.FOUND)
        }
    }



    /*fun createMember(@RequestBody memberRequest: MemberRequest): ResponseEntity<Member> {
        val member = Member(
            id = UUID.randomUUID(),
            email = memberRequest.email,
            password = encoder.encode(memberRequest.password),
            role = Role2.USER
        )
        val createdMember = memberService.save(member)
        return ResponseEntity.ok(createdMember)
    }

     */



    @GetMapping("/{id}")
    fun getMemberById(@PathVariable id: UUID): ResponseEntity<Member> {
        val member = memberService.findByUUID(id)
        return if (member != null) {
            ResponseEntity.ok(member)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/email/{email}")
    //fun getMemberById(): String{
      //return "Hello ID"
    //}
    fun getMemberByEmail(@PathVariable email: String): ResponseEntity<Member> {
        val member = memberService.findByEmail(email)
        return if (member != null) {
            ResponseEntity.ok(member)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getAllMembers(): ResponseEntity<List<Member>> {
        val members = memberService.findAll()
        return ResponseEntity.ok(members)
    }

    @DeleteMapping("/{id}")
    fun deleteMember(@PathVariable id: UUID): ResponseEntity<Void> {
        val deleted = memberService.deleteByUUID(id)
        return if (deleted) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
