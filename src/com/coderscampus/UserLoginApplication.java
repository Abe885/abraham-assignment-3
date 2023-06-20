package com.coderscampus;

import java.util.Scanner;

public class UserLoginApplication {

	public static void main(String[] args) {
		UserLoginService service = new UserLoginService();	
		service.readFileUserData();							

		Scanner scanner = new Scanner(System.in);
		int loginAttempts = 0;
		boolean successfulLogin = false;

		while (loginAttempts < 5 && !successfulLogin) {		//the bulk of my code here revolves around this while loop printing out my prompts to the console
			System.out.println("Enter your Username: ");	// and taking in each input then using the verifyLogin method to verify user input against the csv
			String usernameInput = scanner.nextLine();		// info from the array.
															// it loops until the user has attempted to login unsuccessfully 5 times or has successfully 
			System.out.println("Enter your Password: ");	// logged in.
			String passwordInput = scanner.nextLine();

			User loggedinUser = service.verifyLogin(usernameInput, passwordInput);
			if (loggedinUser != null) {
				successfulLogin = true;
				System.out.println("Welcome: " + loggedinUser.getName());
			} else {
				loginAttempts++;
				System.out.println("Invalid login, please try again.");
			}
		}

		if (!successfulLogin) {
			System.out.println("Too many failed attempts, you are now locked out.");
		}

		scanner.close();
	}
}