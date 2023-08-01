package com.codingwitherik.library.book

import com.codingwitherik.library.author.Author
import com.codingwitherik.library.author.AuthorResponse
import com.codingwitherik.library.library.LibraryResponse

class BookResponse() {
    var id: Long? = null
    var name: String? = null
    var authors: List<AuthorResponse>? = null
    var isbn: String? = null
    var libraries: Set<LibraryResponse> = emptySet()

    constructor(book: Book) : this() {
        this.id = book.id
        this.name = book.name
        this.isbn = book.isbn
        this.libraries =
            book.libraryBooks.distinctBy { it.library }
                .mapNotNull { libraryBook -> libraryBook.library?.let { LibraryResponse(it) } }.toSet()
        this.authors = book.authors.map { AuthorResponse(it) }
    }
}

class BookParam {
    fun toEntity(): Book {
        val entity = Book()
        entity.isbn = this.isbn
        entity.name = this.name
        entity.authors = this.authorIds?.map { Author().apply { this.id = it } }.orEmpty().toSet()
        return entity
    }

    var name: String? = null
    var authorIds: List<Long>? = null
    var isbn: String? = null
}