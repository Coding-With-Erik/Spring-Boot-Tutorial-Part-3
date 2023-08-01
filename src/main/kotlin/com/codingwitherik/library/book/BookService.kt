package com.codingwitherik.library.book

import com.codingwitherik.library.author.AuthorService
import org.springframework.stereotype.Service

@Service
class BookService(
    private val repository: BookRepository,
    private val authorService: AuthorService
) {
    fun save(book: Book): Book {
        book.authors = book.authors.mapNotNull { author -> author.id?.let { authorService.findOneOrThrow(it) } }.toSet()
        return repository.save(book)
    }

    fun findOneOrThrow(id: Long): Book {
        return repository.findById(id).orElseThrow { NoSuchElementException("No Book with id $id found") }
    }

    fun update(id: Long, book: Book): Book {
        val toUpdate = findOneOrThrow(id)
        toUpdate.name = book.name
        toUpdate.isbn = book.isbn
        toUpdate.authors = book.authors
        return save(toUpdate)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }
}