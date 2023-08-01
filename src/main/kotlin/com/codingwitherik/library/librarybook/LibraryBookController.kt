package com.codingwitherik.library.librarybook

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("library/{libraryId}/book")
@Tag(name = "LibraryBook")
class LibraryBookController(
    private val service: LibraryBookService
) {
    @GetMapping
    @Operation(summary = "Search for Library Book", operationId = "searchLibraryBook")
    fun search(
        @PathVariable libraryId: Long,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) author: String?,
        @RequestParam(required = false) isbn: String?,
        @ParameterObject pageable: Pageable
    ): Page<LibraryBookResponse> {
        return service.search(libraryId, name, author, isbn, pageable).map {
            LibraryBookResponse(it)
        }
    }

    @PostMapping
    @Operation(summary = "Add Book to Library", operationId = "addBookToLibrary")
    fun addBook(@PathVariable libraryId: Long, @RequestBody libraryBookParam: LibraryBookParam): LibraryBookResponse {
        return LibraryBookResponse(service.addBook(libraryId, libraryBookParam.bookId, libraryBookParam.id))
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remove Book from Library", operationId = "removeBookFromLibrary")
    fun deleteBook(@PathVariable libraryId: Long, @PathVariable id: String) {
        service.removeBook(libraryId, id)
    }
}