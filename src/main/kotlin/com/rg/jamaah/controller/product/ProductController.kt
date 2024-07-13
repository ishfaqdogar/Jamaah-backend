package com.rg.jamaah.controller.product

import com.rg.jamaah.model.Product
import com.rg.jamaah.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/product")
class ProductController(private val productService: ProductService) {

    @GetMapping
    fun getAllProducts(): List<Product> = productService.findAll()
    /*fun getAllProducts(): String {
        return "Hello"
    }

     */

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: String): ResponseEntity<Product> {
        val product = productService.findById(id)
        return if (product != null) {
            ResponseEntity.ok(product)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createProduct(@RequestBody product: Product): Product = productService.save(product)

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: String, @RequestBody product: Product): ResponseEntity<Product> {
        return if (productService.findById(id) != null) {
            ResponseEntity.ok(productService.save(product.copy(id = id)))
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: String): ResponseEntity<Void> {
        return if (productService.findById(id) != null) {
            productService.deleteById(id)
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
