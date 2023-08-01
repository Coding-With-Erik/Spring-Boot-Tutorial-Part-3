package com.codingwitherik.library.author

import com.codingwitherik.library.book.BookParam
import com.codingwitherik.library.book.BookResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("author")
@Tag(name = "Author")
class AuthorController(
    private val service: AuthorService
) {
    @PostMapping
    @Operation(summary = "Create Author", operationId = "createAuthor")
    fun create(@RequestBody authorParam: AuthorParam): AuthorResponse {
        return AuthorResponse(service.create(authorParam.toEntity()))
    }

    @GetMapping("{id}")
    @Operation(summary = "Get Author", operationId = "getAuthor")
    fun get(@PathVariable id: Long): AuthorResponse {
        return AuthorResponse(service.findOneOrThrow(id))
    }

    @PutMapping("{id}")
    @Operation(summary = "Update Author", operationId = "updateAuthor")
    fun update(@PathVariable id: Long, @RequestBody authorParam: AuthorParam): AuthorResponse {
        return AuthorResponse(service.update(id, authorParam.toEntity()))
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete Author", operationId = "deleteAuthor")
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }
}