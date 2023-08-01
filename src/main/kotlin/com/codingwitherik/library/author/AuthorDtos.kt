package com.codingwitherik.library.author

class AuthorResponse() {
    var id: Long? = null
    var name: String? = null

    constructor(author: Author) : this() {
        this.id = author.id
        this.name = author.name
    }
}

class AuthorParam {
    fun toEntity(): Author {
        val entity = Author()
        entity.name = this.name
        return entity
    }

    var name: String? = null
}