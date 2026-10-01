package com.book.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.book.entity.Book;
import com.book.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;
	
	public Book saveBook(Book book) {

        /*
         * Generate Book ID if it is not already provided.
         */
        if (book.getBookid() == null || book.getBookid().isBlank()) {

            String bookId = UUID.randomUUID()
                    .toString()
                    .replace("-", "")
                    .substring(0, 8);

            book.setBookid(bookId);
        }

        return bookRepository.save(book);
    }
	
    public Optional<Book> searchBook(String bookid) {

        return bookRepository.findByBookid(bookid);
    }
    
    public boolean deleteBook(String bookid) {

        Optional<Book> existingBook = bookRepository.findByBookid(bookid);

        if (existingBook.isPresent()) {

        	bookRepository.delete(existingBook.get());

            return true;
        }

        return false;
    }
	
    public List<Book> getAllBooks() {

        return bookRepository.findAll();
    }
    
    public long getTotalBooks() {

        return bookRepository.count();
    }
    
    public double getTotalBooksPrice() {

        return bookRepository.findAll()
                .stream()
                .mapToDouble(Book::getPrice)
                .sum();
    }
    
    public long getTotalAuthors() {

        return bookRepository.findAll()
                .stream()
                .map(Book::getAuthor)
                .filter(author -> author != null && !author.isBlank())
                .distinct()
                .count();
    }
    
    
    public boolean bookExists(String bookid) {

        return bookRepository.existsByBookid(bookid);
    }
    
}
