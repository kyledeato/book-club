package com.kyle.bookClub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyle.bookClub.models.Book;
import com.kyle.bookClub.models.User;
import com.kyle.bookClub.services.BookService;
import com.kyle.bookClub.services.UserService;



@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
//	HOME ROUTE
	@RequestMapping("")
	public String home(HttpSession s, Model m) {
//		retrieve the user from session
		Long userId = (Long) s.getAttribute("user_id");
//		check if userId is null
		if (userId == null) {
			return "redirect:/";
		} else {
//			go to the db to retrieve the user using the id from session
			User thisUser = userService.findOne(userId);
			m.addAttribute("thisUser", thisUser);			
//			m.addAttribute("thisUser", thisUser.getUserName());	
			List<Book> books = bookService.allBooks();
			m.addAttribute("books", books);
			return "books.jsp";
		}
	}
	
//	CREATE A BOOK
	@GetMapping("/new")
	public String createBook(@ModelAttribute("book") Book book, HttpSession s) {
//		retrieve the user from session
		Long userId = (Long) s.getAttribute("user_id");
//		check if userId is null
		if (userId == null) {
			System.out.println();
			return "redirect:/";
		} 
		return "create.jsp";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id")Long id, Model model) {
		Book book = bookService.findBook(id);
		model.addAttribute("book", book);
		return "show.jsp";
	}
	
	//RENDER EDIT
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id")Long id, Model model) {
		Book book = bookService.findBook(id);
		model.addAttribute("book",book);
		return "edit.jsp";
	}
	
// =========== ACTION ROUTES =================
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("book") Book book, HttpSession s, BindingResult res) {
		System.out.println("create route ========");
//		retrieve the user from session
		Long userId = (Long) s.getAttribute("user_id");
//		check if userId is null
		if (userId == null) {
			return "redirect:/";
		} 
		if (res.hasErrors()) {
			System.out.println("error creating a new book");
			return "create.jsp";
		} else {
			bookService.createBook(book);
			System.out.println("creating book successful");
			return "redirect:/books";
		}
	}
	
	@PutMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("book") Book book,HttpSession s, BindingResult result) {
//		retrieve the user from session
		Long userId = (Long) s.getAttribute("user_id");
//		check if userId is null
		if (userId == null) {
			return "redirect:/";
		}
		if (result.hasErrors()) {
			return "edit.jsp";
		} else {
			bookService.createBook(book);
			return "redirect:/books";
		}
	}
	
	//DELETE
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		System.out.println("deleting");
		bookService.deleteBook(id);
		return "redirect:/books";
	}
}
