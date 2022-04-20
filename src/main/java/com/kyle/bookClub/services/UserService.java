package com.kyle.bookClub.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.kyle.bookClub.models.LoginUser;
import com.kyle.bookClub.models.User;
import com.kyle.bookClub.repositories.UserRepository;

@Service
public class UserService {

@Autowired
private UserRepository userRepo;

// TO-DO: Write register and login methods!
public User register(User newUser, BindingResult result) {
    // TO-DO: Additional validations!
	
// Check if email is taken
	Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
//	if email is taken reject
	if (potentialUser.isPresent()) {
		result.rejectValue("email", "registerErrors", "This email is taken");
	} 
//	if pass does not match confirm password
	if (!newUser.getPassword().equals(newUser.getConfirm())) {
		result.rejectValue("confirm", "registerErrors", "this confirm pass must match the pass");
	}
//	if it has erros return null
    if (result.hasErrors()) {
    	return null;
    } else {
//    	has and set pw
    	String hashedpw = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	newUser.setPassword(hashedpw);
    	//save to db
    	return userRepo.save(newUser);
    }
	
}

public User login(LoginUser newLoginObject, BindingResult result) {
	
//	FIND THE USER IN THE DB
	Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
	if (!potentialUser.isPresent()) {
		result.rejectValue("email", "loginError", "email not found");
	} else {
		
		User user = potentialUser.get();
//	Reject if BCrypt pass match fails
		if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
//		Reject
			result.rejectValue("password", "loginError", "invalid password");
		}
//	return null if result has errors
		if (result.hasErrors()) {
			return null;
		} else {
//		otherwise, return the user object
			return user;
		}
	}
	
	return null;
	
}
	

//Find ONE
public User findOne(Long id) {
	Optional<User> potentialUser = userRepo.findById(id);
	if (potentialUser.isPresent()) {
		return potentialUser.get();
	} else {
		return null;
	}
}





}