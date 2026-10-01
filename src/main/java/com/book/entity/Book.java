package com.book.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String bookid;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String author;

	private String publisher;

	private String publicationYear;

	private double price;

	private String quantity;

	private String language;

	@Transient
	private String total;

	@Transient
	private String totalauthor;

	@Transient
	private double totalprice;
	
}
