package com.videos;

import java.util.Scanner;

import com.videos.controller.UserController;
import com.videos.controller.VideoController;
import com.videos.tools.Tools;

public class Main {

	public static void main(String[] args) {
		UserController userController = new UserController();
		VideoController videoController = new VideoController();
		boolean exitFlag = false;
		int screen = 0;
		Scanner scanner = new Scanner(System.in);
		String currentUserName = "";

		// Program flow logic
		while (!exitFlag) {
			switch (screen) {
			// Welcome screen
			case 0:
				if (!currentUserName.isEmpty()) {
					System.out.println(
							"\nUser '" + currentUserName + "' logged out");
					currentUserName = "";
				}
				screen = welcomeScreen(scanner);
				break;
			// Register screen
			case 1:
				currentUserName = registerScreen(scanner, userController);
				if (currentUserName.isEmpty()) {
					screen = 0;
				} else {
					screen = 3;
				}
				break;
			// Login screen
			case 2:
				currentUserName = loginScreen(scanner, userController);
				if (currentUserName.isEmpty()) {
					screen = 6;
				} else if (currentUserName.equals("e")) {
					currentUserName = "";
					screen = 0;
				} else {
					screen = 3;
				}
				break;
			// User screen
			case 3:
				screen = userScreen(scanner, currentUserName);
				break;
			// Add video screen
			case 4:
				System.out.println(addVideoScreen(scanner, videoController,
						currentUserName));
				screen = 3;
				break;
			// List videos screen
			case 5:
				System.out.println(
						listVideoScreen(videoController, currentUserName));
				screen = 3;
				break;
			// Exit screen
			case 6:
				System.out.println("\nBye!");
				exitFlag = true;
				break;
			}
		}
		scanner.close();
		System.out.println("\nExit successfully");
	}

	// Welcome screen
	public static int welcomeScreen(Scanner scanner) {
		System.out.println(
				"\n\nWelcome to videoApp\n---------------------------------------");
		String promptMessage = "To register as new user, enter '1'\nTo log in as existing user,"
				+ " enter '2'\nTo quit program, enter '3'\nEnter choice: ";
		System.out.println(promptMessage);
		String userInputStr;
		while (((userInputStr = scanner.nextLine()).isEmpty())
				|| ((!userInputStr.equals("1")) && (!userInputStr.equals("2"))
						&& (!userInputStr.equals("3")))) {
			System.out
					.println("\nNot valid, input must be '1', '2', or '3'!\n");
			System.out.println(promptMessage);
		}
		if (userInputStr.equals("3")) {
			return 6;
		} else {
			return Integer.parseInt(userInputStr);
		}
	}

	// Register screen
	private static String registerScreen(Scanner scanner,
			UserController userController) {
		System.out.println(
				"\n\nRegister user\n---------------------------------------");
		String[] userData = new String[4];
		String[] promptMessage = { "Enter username: ", "Enter first name: ",
				"Enter last name: ", "Enter password: " };
		int j = 0;
		while (true) {
			tryBlock: try {
				for (int i = j; i < 4; i++) {
					userData[i] = userInput(scanner, promptMessage[i]);
					if (i == 0) {
						boolean[] isExistingUserOutput = userController
								.isExistingUser(userData[i]);
						if (isExistingUserOutput[0]) {
							System.out.println(
									"\nUser already exists, pick an other one!\n");
							break tryBlock;
						} else if (isExistingUserOutput[1]) {
							System.out.println(
									"\nSorry, unknown error, back to main menu!\n");
							return "";
						}
					}
					j++;
				}
				userData[1] = Tools.firstUppercase(userData[1]);
				userData[2] = Tools.firstUppercase(userData[2]);
				String[] registrationOutput = userController
						.registerNewUser(userData);
				System.out.println(registrationOutput[1]);
				System.out.println("\nHi, " + registrationOutput[0] + "!");
				return registrationOutput[0];
			} catch (Exception e) {
				System.out.println(Tools.exceptionToString(e));
			}
		}
	}

	// Login screen
	private static String loginScreen(Scanner scanner,
			UserController userController) {
		System.out.println(
				"\n\nUser login\n---------------------------------------");
		String[] loginData = new String[2];
		String[] loginOutput = new String[2];
		String[] promptMessage = { "Enter username: ", "Enter password: " };

		// three chances to log in, if wrong user name or password exit program
		for (int i = 0; i < 3; i++) {
			int j = 0;
			while (j < 2) {
				for (int k = j; k < loginData.length; k++) {
					System.out.println(promptMessage[k]);
					while ((loginData[k] = scanner.nextLine()).isEmpty()) {
						System.out.println(
								"\nNot valid, input can not be empty!\n"
										+ promptMessage[k]);
					}
					j++;
				}
			}
			loginOutput = userController.login(loginData);
			System.out.println(loginOutput[1]);
			if (!loginOutput[0].isEmpty()) {
				System.out.println("Hi, " + loginOutput[0] + "!");
				return loginOutput[0];
			}
		}
		System.out.println("Sorry, no more attempts left!");
		return loginOutput[0];
	}

	// User screen
	public static int userScreen(Scanner scanner, String currentUserName) {
		System.out.println(
				"\n\nUser menu\n---------------------------------------");
		String promptMessage = "To add new video, enter '1'\nTo list user videos,"
				+ " enter '2'\nTo go back to main menu, enter '3'\nEnter choice: ";
		System.out.println(promptMessage);
		String userInputStr;
		while (((userInputStr = scanner.nextLine()).isEmpty())
				|| ((!userInputStr.equals("1")) && (!userInputStr.equals("2"))
						&& (!userInputStr.equals("3")))) {
			System.out
					.println("\nNot valid, input must be '1', '2', or '3'!\n");
			System.out.println(promptMessage);
		}
		switch (userInputStr) {
		case "1":
			return 4;
		case "2":
			return 5;
		case "3":
			return 0;
		default:
			return 6;
		}
	}

	// Add video screen
	private static String addVideoScreen(Scanner scanner,
			VideoController videoController, String userName) {
		System.out.println(
				"\n\nAdd video\n---------------------------------------");
		String[] videoData = new String[3];
		videoData[2] = userName;
		String[] promptMessage = { "Enter video title: ",
				"Enter video tags, separated by spaces: " };
		int j = 0;
		while (true) {
			try {
				for (int i = j; i < 2; i++) {
					videoData[i] = userInput(scanner, promptMessage[i]);
					j++;
				}
				videoData[0] = Tools.firstUppercase(videoData[0]);
			} catch (Exception e) {
				System.out.println(Tools.exceptionToString(e));
			}
			return videoController.addVideo(videoData);
		}
	}

	// List user videos screen
	private static String listVideoScreen(VideoController videoController,
			String userName) {
		System.out.println("\n\n" + userName
				+ " video list\n---------------------------------------");

		return videoController.getUserVideos(userName);
	}

	// Generic user input method
	public static String userInput(Scanner scanner, String promptMessage)
			throws Exception {
		System.out.println(promptMessage);
		String output = scanner.nextLine();
		if (output.isEmpty()) {
			throw (new Exception("Input can not be empty!"));
		}
		return output;
	}

}
