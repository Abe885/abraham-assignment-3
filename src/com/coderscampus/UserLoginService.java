package com.coderscampus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UserLoginService {
	private User users[];

	public UserLoginService() {
		users = new User[4];
	}

	public void readFileUserData() {   // this method reads the info in the csv file, splits the info using "," as a delimiter
		BufferedReader fileReader = null; // then sets each split element into an index in a User array with the corresponding value. 
		int i = 0;							// then using a loop iterates it for every line. 
		String line = "";

		try {
			fileReader = new BufferedReader(new FileReader("data.txt"));
			while ((line = fileReader.readLine()) != null) {
				String[] userData = line.split(",");

				if (userData.length == 3) {
					String username = userData[0];
					String password = userData[1];
					String name = userData[2];
					users[i] = new User(username, password, name);
					i++;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("There was a FileNotFound exception error");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("There was some kind of IO exception error");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("There was an IO exception error");
				e.printStackTrace();
			}
		}
	}

	public User verifyLogin(String usernameInput, String passwordInput) {		// second method cross references the user's input with the stored User info.
		for (User user : users) {												// taking into account case sensitivity only for the password and not the username
			if (user != null && user.getUsername().equalsIgnoreCase(usernameInput)
					&& user.getPassword().equals(passwordInput)) {
				return user;
			}
		}
		return null;
	}

	public User[] getUsers() {
		return users;
	}
}
