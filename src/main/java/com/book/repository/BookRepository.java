package com.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	Optional<Book> findByBookid(String bookid);

    boolean existsByBookid(String bookid);

    void deleteByBookid(String bookid);
    
}
