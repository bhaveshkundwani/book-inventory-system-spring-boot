package com.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.book.entity.Book;
import com.book.service.BookService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;

	// =========================
	// HOME
	// =========================

	@GetMapping("/")
	public String showIndex(Model model) {
		model.addAttribute("book", new Book());
		return "index";
	}

	// =========================
	// ADD BOOK PAGE
	// =========================

	@GetMapping("/addBook")
	public String showAddBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}

	// =========================
	// ADD BOOK
	// =========================

	@PostMapping("/addBook")
	public String saveBook(@ModelAttribute("book") Book book, Model model) {

		try {
			if (bookService.bookExists(book.getBookid())) {
				model.addAttribute("error", "Book ID already exists.");
				return "addbook";
			}

			bookService.saveBook(book);

			model.addAttribute("message", "Book added successfully.");
			model.addAttribute("book", new Book());

		} catch (Exception e) {
			model.addAttribute("error", "Failed to add book: " + e.getMessage());
		}

		return "addbook";
	}

	// =========================
	// SEARCH PAGE
	// =========================

	@GetMapping("/search")
	public String showSearchBook(Model model) {
		model.addAttribute("book", new Book());
		return "search";
	}

	// =========================
	// SEARCH BOOK
	// =========================

	@PostMapping("/search")
	public String searchBook(@ModelAttribute("book") Book book, Model model) {

		Optional<Book> foundBook = bookService.searchBook(book.getBookid());

		if (foundBook.isPresent()) {
			model.addAttribute("book", foundBook.get());
		} else {
			model.addAttribute("book", null);
			model.addAttribute("errorMessage", "Book not found!");
		}

		return "search";
	}

	// =========================
	// DELETE PAGE
	// =========================

	@GetMapping("/deleteBook")
	public String showDeleteBook(Model model) {
		model.addAttribute("book", new Book());
		return "deletebook";
	}

	// =========================
	// DELETE BOOK
	// =========================

	@PostMapping("/deleteBook")
	public String deleteBook(@ModelAttribute("book") Book book, Model model) {

		boolean deleted = bookService.deleteBook(book.getBookid());

		if (deleted) {
			model.addAttribute("successMessage", "Book deleted successfully.");
		} else {
			model.addAttribute("errorMessage", "Book not found!");
		}

		return "deletebook";
	}

	// =========================
	// BOOK INFORMATION
	// =========================

	@GetMapping("/info")
	public String getInfo(Model model) {

		long totalBooks = bookService.getTotalBooks();
		double totalBooksPrice = bookService.getTotalBooksPrice();
		long totalAuthors = bookService.getTotalAuthors();
		List<Book> books = bookService.getAllBooks();

		model.addAttribute("totalBooks", totalBooks);
		model.addAttribute("totalBooksPrice", totalBooksPrice);
		model.addAttribute("totalAuthors", totalAuthors);
		model.addAttribute("books", books);

		return "info";
	}
}
