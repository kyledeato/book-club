package com.kyle.bookClub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyle.bookClub.models.Book;
import com.kyle.bookClub.repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	//returns all books
	public List<Book> allBooks() {
		return bookRepository.findAll();
	}
	
	//create a book
	public Book createBook(Book b) {
		return bookRepository.save(b);
	}
	
	// retrieves a book
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
    
    public Book updateBook(Book b) {
    	return bookRepository.save(b);
    }
    
    public void deleteBook(Long b) {
    	bookRepository.deleteById(b);
    }
}
