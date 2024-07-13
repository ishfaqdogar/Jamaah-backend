package com.rg.jamaah.service

import com.rg.jamaah.controller.member.MemberRequest
import com.rg.jamaah.model.Member
import com.rg.jamaah.model.Role2
import com.rg.jamaah.repository.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val encoder: PasswordEncoder
) {

    /*init {
        val initialUsers = listOf(
            Member(
                id = UUID.randomUUID(),
                email = "email-1@gmail.com",
                password = encoder.encode("pass1"),
                role = Role2.USER
            ),
            Member(
                id = UUID.randomUUID(),
                email = "email-2@gmail.com",
                password = encoder.encode("pass2"),
                role = Role2.ADMIN
            ),
            Member(
                id = UUID.randomUUID(),
                email = "email-3@gmail.com",
                password = encoder.encode("pass3"),
                role = Role2.USER
            )
        )
        memberRepository.saveAll(initialUsers)
    }

     */

    /*fun save(member: Member): Member {
        //return member
        return memberRepository.save(member)

    }

     */
    /*fun save(member: Member): Member {
        return member
        //val updated = member.copy(password = encoder.encode(member.password))
        //return memberRepository.save(updated)
    }

     */
    fun save(memberRequest: MemberRequest): Member {
        val member = Member(
            id = UUID.randomUUID(),
            email = memberRequest.email,
            password = encoder.encode(memberRequest.password),
            role = Role2.USER
        )

        return memberRepository.save(member)

    }

    fun findByEmail(email: String): Member? =
        memberRepository.findByEmail(email)

    fun findAll(): List<Member> =
        memberRepository.findAll()

    fun findByUUID(uuid: UUID): Member? =
        memberRepository.findById(uuid).orElse(null)

    fun deleteByUUID(uuid: UUID): Boolean {
        val memberExists = memberRepository.existsById(uuid)
        if (memberExists) {
            memberRepository.deleteById(uuid)
        }
        return memberExists
    }
}
