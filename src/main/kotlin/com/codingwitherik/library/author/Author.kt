package com.codingwitherik.library.author

import com.codingwitherik.library.book.Book
import jakarta.persistence.*

@Entity
class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String? = null

    @ManyToMany
    @JoinTable(
        name = "book_author",
        joinColumns = [JoinColumn(name = "author_id")],
        inverseJoinColumns = [JoinColumn(name = "book_id")]
    )
    var books: Set<Book> = emptySet()
}