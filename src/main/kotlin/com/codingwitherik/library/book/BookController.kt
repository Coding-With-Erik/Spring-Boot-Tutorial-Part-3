package com.codingwitherik.library.book

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("book")
@Tag(name = "Book")
class BookController(
    private val service: BookService
) {
    @PostMapping
    @Operation(summary = "Create Book", operationId = "createBook")
    fun create(@RequestBody bookParam: BookParam): BookResponse {
        return BookResponse(service.save(bookParam.toEntity()))
    }

    @GetMapping("{id}")
    @Operation(summary = "Get Book", operationId = "getBook")
    fun get(@PathVariable id: Long): BookResponse {
        return BookResponse(service.findOneOrThrow(id))
    }

    @PutMapping("{id}")
    @Operation(summary = "Update Book", operationId = "updateBook")
    fun update(@PathVariable id: Long, @RequestBody bookParam: BookParam): BookResponse {
        return BookResponse(service.update(id, bookParam.toEntity()))
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete Book", operationId = "deleteBook")
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }
}