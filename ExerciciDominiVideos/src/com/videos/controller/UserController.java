package com.videos.controller;

import com.videos.model.Response;
import com.videos.model.User;
import com.videos.repository.UserRepository;


public class UserController {
	private final UserRepository userRepository = new UserRepository();


	// Checks if given user name is already taken in user repository.
	// Returns an array of two booleans. The first boolean is true if 
	// given user name is already in database. The second boolean is 
	// true if any exception is returned from database.
	public boolean[] isExistingUser(String userName) throws Exception {
		Response<Boolean> response = userRepository
				.isExistingUser(userName);
		boolean[] output = new boolean[2];
		if (response.isExceptionThrown()) {
			output[0] = false;
			output[1] = true;
		} else {
			output[0] = response.get();
			output[1] = false;
		}
		return output;
	}

	// Creates new user, retrieves created user from data json file,
	// and checks if retrieved user equals the one added in first place
	public String[] registerNewUser(String[] userData) {
		User user = new User(userData[0], userData[1], userData[2],
				userData[3]);
		Response<User> responseCreate = userRepository.create(user);
		Response<User> responseFind = userRepository.find(user.getUserName());
		String[] output = new String[2];
		if (responseCreate.isExceptionThrown()
				|| responseFind.isExceptionThrown()
				|| !user.equals(responseFind.get())) {
			output[0] = "";
			output[1] = "\nSorry, unknown error, back to main menu!";
			userRepository.delete(user.getUserName());
		} else {
			output[0] = user.getUserName();
			output[1] = "\nUser successfully created";
		}
		return output;
	}

	
	public String[] login(String[] loginData) {
		Response<Boolean> response = userRepository.login(loginData);
		String outputMessage = "";
		if (response.get() != null && response.get()) {
			outputMessage = "\nUser successfully logged\n";
		} else if (!response.isExceptionThrown()) {
			outputMessage = "\nUser name or password not valid!\n";
		} else {
			outputMessage = "\nSorry, unknown error, try later!\n";
		}
		String[] output = { response.getResponseMessage(),
				outputMessage };
		return output;
	}

}
