package com.kyle.bookClub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.kyle.bookClub.models.LoginUser;
import com.kyle.bookClub.models.User;
import com.kyle.bookClub.services.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	
//	render log/reg page
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}


	 @PostMapping("/register")
	 public String register(@Valid @ModelAttribute("newUser") User newUser, 
	         BindingResult result, Model model, HttpSession session) {
	     
	     // TO-DO Later -- call a register method in the service 
	     // to do some extra validations and create a new user!
//		 execute the Service to Register FIRST
		 userService.register(newUser, result);
	     
	     if(result.hasErrors()) {
	         // Be sure to send in the empty LoginUser before 
	         // re-rendering the page.
	         model.addAttribute("newLogin", new LoginUser());
	         return "index.jsp";
	     } else {
	    	  // No errors! 
	         // TO-DO Later: Store their ID from the DB in session, 
	         // in other words, log them in.
	    	 session.setAttribute("user_id", newUser.getId());
	    	 return "redirect:/books";
	     }
	     
	   
	 
	  
	 }
	 
//		LOGIN USER
		@PostMapping("/login")
		public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
							BindingResult res,
							Model model,
							HttpSession sesh) {
			
			User user = userService.login(newLogin, res);
			
			if (res.hasErrors()) {
				System.out.println("error");
		        model.addAttribute("newUser", new User());
				return "index.jsp";
			} else {
				sesh.setAttribute("user_id", user.getId());
				return "redirect:/books";
			}
			
			
		}
	 
		
		

		
		@GetMapping("/logout")
		public String logout(HttpSession s) {
			s.invalidate();
			return "redirect:/";
		}
	
}
