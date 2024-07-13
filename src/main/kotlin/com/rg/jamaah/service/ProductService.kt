package com.rg.jamaah.service


import com.rg.jamaah.model.Product
import com.rg.jamaah.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun findAll(): List<Product> = productRepository.findAll()

    fun findById(id: String): Product? = productRepository.findById(id).orElse(null)

    fun save(product: Product): Product = productRepository.save(product)

    fun deleteById(id: String) = productRepository.deleteById(id)
}
