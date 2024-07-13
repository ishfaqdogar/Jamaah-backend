package com.rg.jamaah.service

import com.rg.jamaah.model.Article
import com.rg.jamaah.repository.ArticleRepository
import org.springframework.stereotype.Service


@Service
class ArticleService (
    private val articleRepository: ArticleRepository
) {
    fun findAll(): List<Article> =
        articleRepository.findAll()
}