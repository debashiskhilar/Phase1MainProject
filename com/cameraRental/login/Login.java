package com.cameraRental.login;

import java.util.Scanner;

public class Login extends Menu{
	Scanner sc = new Scanner(System.in);
	public void userLogin() {

		while(true) {
			System.out.println("+------------------------------------+");
			System.out.println("|    WELCOME TO CAMERA RENTAL APP    |");
			System.out.println("+------------------------------------+");
			System.out.println("PLEASE LOGIN TO CONTINUE");
			System.out.println("USERNAME: ");
			String userid = sc.nextLine();
			System.out.println("PASSWORD: ");
			String password = sc.nextLine();

			if(userid.equals("admin") && password.equals("admin@123")) {
				System.out.println("Login successfully.");
				menuDisplay();
			}
			else {
				System.out.println("You entered wrong userid and password. please enter correct id-password ");
			}
		}
	}

}
