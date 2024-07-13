package com.rg.jamaah.repository

import com.rg.jamaah.model.Member
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface MemberRepository : MongoRepository<Member, UUID> {
    fun findByEmail(email: String): Member?
    //fun findByName(password: String): Member?
}
