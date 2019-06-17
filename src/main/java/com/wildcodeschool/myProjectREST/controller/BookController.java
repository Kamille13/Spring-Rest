package com.wildcodeschool.myProjectREST.controller;

import com.wildcodeschool.myProjectREST.model.Book;
import com.wildcodeschool.myProjectREST.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> index(){
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book show(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        return bookRepository.findById(blogId).get();
    }

    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book update(@PathVariable String id, @RequestBody Map<String, String> body){
        int blogId = Integer.parseInt(id);
        // getting book
        Book book = bookRepository.findById(blogId).get();
        book.setTitle(body.get("title"));
        book.setAuthor(body.get("author"));
        book.setDescription(body.get("description"));
        return bookRepository.save(book);
    }

    @DeleteMapping("books/{id}")
    public void delete(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        bookRepository.deleteById(blogId);
    }
}