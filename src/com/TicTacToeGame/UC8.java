package com.TicTacToeGame;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class UC8 {

	private static char[] boardArray;
	private static int[] boardArrayIndex = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private static String[] tossString = { "heads", "tails" };
	private static int positionOfMove;
	private static char playerCharacterInput;
	private static char computerCharacterInput;
	private static boolean turnIndex = true;
	private static int turnNumber = 1;
	private static boolean flagIndexForPlayerFirstInputCharacter = true;

	// Returns the board in the form of a 1D array
	private static char[] Board() {
		char[] dummyBoardArray = new char[10];
		for (int i = 0; i < dummyBoardArray.length; i++) {
			dummyBoardArray[i] = '$';
		}
		return dummyBoardArray;
	}

	/*
	 * Checks if the position, user wants to feed input in is empty or not using
	 * boardArrayIndex array
	 */
	private static boolean IsEmpty(int index) {
		if (boardArrayIndex[index] == 0) {
			return true;
		} else {
			return false;
		}
	}

	/* Returns a random string between "heads" & "tails" */
	private static String TossMaker() {
		int index = new Random().nextInt(tossString.length);
		String randomString = (tossString[index]);
		return randomString;
	}

	/*
	 * Checks for the winning conditions for the player or the computer using the
	 * boardArray
	 */
	private static boolean winningCondition() {

		// -----------------Horizontal conditions-----------------
		int i = 1;
		if (boardArray[i] == boardArray[i + 1] && boardArray[i + 1] == boardArray[i + 2]) {
			if (boardArray[i] != '$')
				return true;
		}
		i = 4;
		if (boardArray[i] == boardArray[i + 1] && boardArray[i + 1] == boardArray[i + 2]) {
			if (boardArray[i] != '$')
				return true;
		}
		i = 7;
		if (boardArray[i] == boardArray[i + 1] && boardArray[i + 1] == boardArray[i + 2]) {
			if (boardArray[i] != '$')
				return true;
		}

		// -----------------Vertical conditions-----------------
		i = 1;
		if (boardArray[i] == boardArray[i + 3] && boardArray[i + 6] == boardArray[i + 3]) {
			if (boardArray[i] != '$')
				return true;
		}
		i = 2;
		if (boardArray[i] == boardArray[i + 3] && boardArray[i + 6] == boardArray[i + 3]) {
			if (boardArray[i] != '$')
				return true;
		}
		i = 3;
		if (boardArray[i] == boardArray[i + 3] && boardArray[i + 6] == boardArray[i + 3]) {
			if (boardArray[i] != '$')
				return true;
		}

		// -----------------Diagonal conditions-----------------
		i = 1;
		if (boardArray[i] == boardArray[i + 4] && boardArray[i + 4] == boardArray[i + 8]) {
			if (boardArray[i] != '$')
				return true;
		}
		i = 3;
		if (boardArray[i] == boardArray[i + 2] && boardArray[i + 4] == boardArray[i + 2]) {
			if (boardArray[i] != '$')
				return true;
		}

		return false;
//		else {
//			System.out.println("The match is drawn");
//			System.exit(0);
//		}
	}

	/*
	 * Function to assign a character 'X' or 'O' to whomsoever gets the first move
	 */
	private static void assign_Input_Character_To_Player_Or_Computer(int playerType) {
		Scanner sc = new Scanner(System.in);
		while (flagIndexForPlayerFirstInputCharacter && playerType == 1) {
			System.out.println("Enter 'X' or 'O' down below : ");
			playerCharacterInput = sc.next().charAt(0);
			playerCharacterInput = Character.toUpperCase(playerCharacterInput);
			switch (playerCharacterInput) {
			case 'X': {
				computerCharacterInput = 'O';
				flagIndexForPlayerFirstInputCharacter = false;
				break;
			}
			case 'O': {
				computerCharacterInput = 'X';
				flagIndexForPlayerFirstInputCharacter = false;
				break;
			}
			default: {
				System.out.println("Please enter a valid input");
				continue;
			}
			}
		}
		while (flagIndexForPlayerFirstInputCharacter && playerType == 2) {
			System.out.println("Enter 'X' or 'O' down below for the computer : ");
			computerCharacterInput = sc.next().charAt(0);
			computerCharacterInput = Character.toUpperCase(computerCharacterInput);
			switch (computerCharacterInput) {
			case 'X': {
				playerCharacterInput = 'O';
				flagIndexForPlayerFirstInputCharacter = false;
				break;
			}
			case 'O': {
				playerCharacterInput = 'X';
				flagIndexForPlayerFirstInputCharacter = false;
				break;
			}
			default: {
				System.out.println("Please enter a valid input");
				continue;
			}
			}
		}
	}

	/*
	 * Function to Choose a correct position
	 */
	private static int choosingValidPositionForPlayer() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			
			try {
				System.out.println("Enter the position you want to give input in (from 1-9) : ");
				positionOfMove = sc.nextInt();
				if (positionOfMove >= 1 && positionOfMove <= 9) {
					if (IsEmpty(positionOfMove) == false) {
						System.out.println("The position is not empty. Please give other position input.");
						continue;
					}
					break;
				} else {
					System.out.println("Please enter a valid position between 1-9.");
					continue;
				}
			} catch (InputMismatchException e) {
				// TODO: handle exception
				System.out.println("Invalid input");
				positionOfMove = choosingValidPositionForPlayer();
				return positionOfMove;
			}
		}
		return positionOfMove;
	}

	/*
	 * Function to let player choose a valid character input for a valid position
	 */
	private static void choosing_Valid_Character_For_Player_Position() {
		Scanner sc = new Scanner(System.in);
		positionOfMove = choosingValidPositionForPlayer();
		assign_Input_Character_To_Player_Or_Computer(1);
		boardArray[positionOfMove] = playerCharacterInput;
		turnIndex = false;
		turnNumber++;
		boardArrayIndex[positionOfMove] = 1;
		ShowBoard(boardArray);
		if (winningCondition()) {
			System.out.println("Congrats! You won..");
			System.exit(0);
		}
	}

	/*
	 * Function to dynamically choose a valid character input for a valid position
	 * for a computer
	 */
	private static void choosing_Valid_Character_For_Computer_Position() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Computer's turn");
		assign_Input_Character_To_Player_Or_Computer(2);
		while (true) {
			int randomPositionForComputer = new Random().nextInt(9);
			if (randomPositionForComputer == 0) {
				continue;
			}
			if (IsEmpty(randomPositionForComputer) == true) {
				boardArray[randomPositionForComputer] = computerCharacterInput;
				turnIndex = true;
				turnNumber++;
				boardArrayIndex[randomPositionForComputer] = 1;
				System.out.println("Computer chose position : " + randomPositionForComputer);
				ShowBoard(boardArray);
				if (winningCondition()) {
					System.out.println("Oops! The computer has won the game.");
					System.exit(0);
				}
				break;
			}
			System.out.println(
					"The position is already occupied. Checking for a new position for the computer. Hold back...");
			continue;
		}
	}

	/*
	 * Allows the user to input 'X' or 'O' in a specified & desired location. Also
	 * checks whether the player will play first or the computer
	 */
	private static void ChooseLetter() {
		Scanner sc = new Scanner(System.in);

		if (TossMaker().equals("heads")) {
			while (turnNumber < 10) {
				if (turnIndex == true) {
					System.out.println("Time to give input Mr. Player : ");
					choosing_Valid_Character_For_Player_Position();
				} else {
					choosing_Valid_Character_For_Computer_Position();
				}
			}
			System.out.println("The game is drawn!");
			System.exit(0);
		} else {
			turnIndex = false;
			while (turnNumber < 10) {
				if (turnIndex == false) {
					choosing_Valid_Character_For_Computer_Position();
				} else {
					choosing_Valid_Character_For_Player_Position();
				}
			}
			System.out.println("The game is drawn!");
			System.exit(0);
		}
	}

	// main function
	public static void main(String[] args) {
		boardArray = Board();
		ChooseLetter();
	}

	// Prints the updated board every time it's called
	private static void ShowBoard(char[] boardArray) {
		System.out.println("-----THE BOARD-----");
		System.out.println("___________________");
		System.out.print("|  ");
		for (char i = 1; i < 3; i++) {
			System.out.print(boardArray[i] + "  |  ");
		}
		System.out.print(boardArray[3] + "  ");
		System.out.println("|");
		System.out.println("___________________");
		System.out.print("|  ");
		for (char i = 4; i < 6; i++) {
			System.out.print(boardArray[i] + "  |  ");
		}
		System.out.print(boardArray[6] + "  ");
		System.out.println("|");
		System.out.println("___________________");
		System.out.print("|  ");
		for (char i = 7; i < 9; i++) {
			System.out.print(boardArray[i] + "  |  ");
		}
		System.out.print(boardArray[9] + "  ");
		System.out.println("|");
		System.out.print("___________________");
		System.out.println();
	}
}