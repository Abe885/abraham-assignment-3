package com.coderscampus;

import java.util.Scanner;

public class UserLoginApplication {

	public static void main(String[] args) {
		UserLoginService service = new UserLoginService();
		service.readFileUserData();

		Scanner scanner = new Scanner(System.in);
		int loginAttempts = 0;
		boolean successfulLogin = false;

		while (loginAttempts < 5 && !successfulLogin) {
			System.out.println("Enter your Username: ");
			String usernameInput = scanner.nextLine();

			System.out.println("Enter your Password: ");
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