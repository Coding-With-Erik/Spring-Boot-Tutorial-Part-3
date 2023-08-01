package com.codingwitherik.library.book

import com.codingwitherik.library.author.Author
import com.codingwitherik.library.librarybook.LibraryBook
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany

@Entity
class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var name: String? = null
    var isbn: String? = null

    @OneToMany(mappedBy = "book")
    var libraryBooks: Set<LibraryBook> = emptySet()

    @ManyToMany
    @JoinTable(
        name = "book_author",
        joinColumns = [JoinColumn(name = "book_id")],
        inverseJoinColumns = [JoinColumn(name = "author_id")]
    )
    var authors: Set<Author> = emptySet()
}