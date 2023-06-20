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

	public void readFileUserData() {
		BufferedReader fileReader = null;
		int i = 0;
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

	public User verifyLogin(String usernameInput, String passwordInput) {
		for (User user : users) {
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
