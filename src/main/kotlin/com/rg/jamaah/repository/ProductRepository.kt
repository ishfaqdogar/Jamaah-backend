package com.rg.jamaah.repository


import com.rg.jamaah.model.Product
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepository : MongoRepository<Product, String>
