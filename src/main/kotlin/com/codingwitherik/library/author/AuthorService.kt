package com.codingwitherik.library.author

import org.springframework.stereotype.Service

@Service
class AuthorService(
    private val repository: AuthorRepository
) {
    fun create(author: Author): Author {
        return repository.save(author)
    }

    fun findOneOrThrow(id: Long): Author {
        return repository.findById(id).orElseThrow { NoSuchElementException("No Author with id $id found") }
    }

    fun update(id: Long, author: Author): Author {
        val toUpdate = findOneOrThrow(id)
        toUpdate.name = author.name
        return repository.save(toUpdate)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }
}