package com.TicTacToeGame;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class UC13 {

	private static char[] boardArray;
	private static int[] boardArrayIndex = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private static String[] tossString = { "heads", "tails" };
	private static int positionOfMove;
	private static char playerCharacterInput;
	private static char computerCharacterInput;
	private static boolean turnIndex = true;
	private static boolean cornerCheckIndexForComputerMove = false;
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
			System.out.println("------------NEW GAME BEGINS------------");
			ChooseLetter();
		}
	}

	/*
	 * Function to choose a number input for a valid position for the computer, so
	 * as to block player's win
	 */
	private static int blockPlayerWin() {
		cornerCheckIndexForComputerMove = false;
		// TODO Auto-generated method stub
		int randomNumberToReturn;
//		int i = 1;
//		horizontal 1
		if (boardArray[1] == boardArray[2] && IsEmpty(3) && boardArray[1] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 3;
		}

		if (boardArray[2] == boardArray[3] && IsEmpty(1) && boardArray[2] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 1;
		}

		if (boardArray[1] == boardArray[3] && IsEmpty(2) && boardArray[1] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 2;
		}

//		horizontal 2
		if (boardArray[4] == boardArray[5] && IsEmpty(6) && boardArray[4] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 6;
		}

		if (boardArray[5] == boardArray[6] && IsEmpty(4) && boardArray[5] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 4;
		}

		if (boardArray[4] == boardArray[6] && IsEmpty(5) && boardArray[4] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 5;
		}

//		horizontal 3
		if (boardArray[7] == boardArray[8] && IsEmpty(9) && boardArray[7] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 9;
		}

		if (boardArray[8] == boardArray[9] && IsEmpty(7) && boardArray[8] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 7;
		}

		if (boardArray[7] == boardArray[9] && IsEmpty(8) && boardArray[7] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 8;
		}

//		vertical 1
		if (boardArray[1] == boardArray[4] && IsEmpty(7) && boardArray[1] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 7;
		}

		if (boardArray[4] == boardArray[7] && IsEmpty(1) && boardArray[4] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 1;
		}

		if (boardArray[7] == boardArray[1] && IsEmpty(4) && boardArray[1] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 4;
		}

//		vertical 2
		if (boardArray[2] == boardArray[5] && IsEmpty(8) && boardArray[5] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 8;
		}

		if (boardArray[5] == boardArray[8] && IsEmpty(2) && boardArray[5] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 2;
		}

		if (boardArray[2] == boardArray[8] && IsEmpty(5) && boardArray[2] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 5;
		}

//		vertical 3
		if (boardArray[3] == boardArray[6] && IsEmpty(9) && boardArray[3] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 9;
		}

		if (boardArray[6] == boardArray[9] && IsEmpty(3) && boardArray[6] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 3;
		}

		if (boardArray[3] == boardArray[9] && IsEmpty(6) && boardArray[3] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 6;
		}

//		diagonal 1
		if (boardArray[1] == boardArray[5] && IsEmpty(9) && boardArray[1] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 9;
		}

		if (boardArray[5] == boardArray[9] && IsEmpty(1) && boardArray[5] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 1;
		}

		if (boardArray[9] == boardArray[1] && IsEmpty(5) && boardArray[1] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 5;
		}

//		diagonal 2
		if (boardArray[3] == boardArray[5] && IsEmpty(7) && boardArray[3] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 7;
		}

		if (boardArray[5] == boardArray[7] && IsEmpty(3) && boardArray[5] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 3;
		}

		if (boardArray[3] == boardArray[7] && IsEmpty(5) && boardArray[7] != '$') {
			cornerCheckIndexForComputerMove = true;
			return 5;
		}

		return new Random().nextInt(9);
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
			int randomPositionForComputer = blockPlayerWin();
			boolean cornerPositionsEmptyOrNot = IsEmpty(1) || IsEmpty(3) || IsEmpty(7) || IsEmpty(9);

			if (cornerCheckIndexForComputerMove == false && cornerPositionsEmptyOrNot) {
				if (randomPositionForComputer == 1 || randomPositionForComputer == 3 || randomPositionForComputer == 7
						|| randomPositionForComputer == 9) {
					if (IsEmpty(randomPositionForComputer) == true) {
						boardArray[randomPositionForComputer] = computerCharacterInput;
						turnIndex = true;
						turnNumber++;
						boardArrayIndex[randomPositionForComputer] = 1;
						System.out.println("Computer chose position : " + randomPositionForComputer);
						ShowBoard(boardArray);
						if (winningCondition()) {
							System.out.println("Oops! The computer has won the game.");
							System.out.println("------------NEW GAME BEGINS------------");
							ChooseLetter();
						}
						break;
					}
					System.out.println(
							"The position is already occupied. Checking for a new position for the computer. Hold back...");
					continue;
				} else {
					continue;
				}
			}
//			int randomPositionForComputer = new Random().nextInt(9);
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
					System.out.println("------------NEW GAME BEGINS------------");
					ChooseLetter();
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
		boardArray = Board();
		boardArrayIndex = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		positionOfMove = 0;
		playerCharacterInput = ' ';
		computerCharacterInput = ' ';
		turnIndex = true;
		cornerCheckIndexForComputerMove = false;
		turnNumber = 1;
		flagIndexForPlayerFirstInputCharacter = true;
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
			System.out.println("------------NEW GAME BEGINS------------");
			ChooseLetter();
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
			System.out.println("------------NEW GAME BEGINS------------");
			ChooseLetter();
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