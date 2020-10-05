package com.TicTacToeGame;

import java.util.Scanner;

public class UC4 {
	private static char[] boardArray;
	private static int[] boardArrayIndex = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	// Returns the board in the form of a 1D array
	private static char[] Board() {
		char[] dummyBoardArray = new char[10];
		for (int i = 0; i < dummyBoardArray.length; i++) {
			dummyBoardArray[i] = '$';
		}
		return dummyBoardArray;
	}

	// Checks if the position, user wants to feed input in is empty or not using
	// boardArrayIndex array
	private static boolean IsEmpty(int index) {
		if (boardArrayIndex[index] == 0) {
			return true;
		} else {
			return false;
		}
	}

	// Allows the user to input 'X' or 'O' in a specified & desired location
	private static void ChooseLetter() {

		char firstCharacterInput = ' ';
		char computerCharacterInput = ' ';
		Scanner sc = new Scanner(System.in);
		int positionOfMove;
		System.out.println("Time to give input Mr. Player : ");
		while (true) {
			System.out.println("Enter the position you want to give input in (from 1-9) : ");
			positionOfMove = sc.nextInt();
			if (positionOfMove >= 1 && positionOfMove <= 9) {
				if (IsEmpty(positionOfMove) == false) {
					System.out.println("The position is not empty. Please give other position input.");
					continue;
				}
				break;
			} else {
				System.out.println("The position is not empty. Please give other position input.");
				continue;
			}
		}
		boardArrayIndex[positionOfMove] = 1;
		System.out.println("Enter 'X' or 'O' down below : ");
		firstCharacterInput = sc.next().charAt(0);

		switch (firstCharacterInput) {
		case 'X': {
			firstCharacterInput = 'X';
			computerCharacterInput = 'O';
			break;
		}
		case 'O': {
			firstCharacterInput = 'O';
			computerCharacterInput = 'X';
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + firstCharacterInput);
		}

		System.out.println("User input is : " + firstCharacterInput + " so the computer input will be : "
				+ computerCharacterInput);
		sc.close();
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
		System.out.print("|");
		System.out.println();
		System.out.println("___________________");
		System.out.print("|  ");
		for (char i = 4; i < 6; i++) {
			System.out.print(boardArray[i] + "  |  ");
		}
		System.out.print(boardArray[6] + "  ");
		System.out.print("|");
		System.out.println();
		System.out.println("___________________");
		System.out.print("|  ");
		for (char i = 7; i < 9; i++) {
			System.out.print(boardArray[i] + "  |  ");
		}
		System.out.print(boardArray[9] + "  ");
		System.out.println("|");
		System.out.print("___________________");
	}

	// main function
	public static void main(String[] args) {
		boardArray = Board();
		ChooseLetter();
		// ShowBoard(boardArray);
	}
}
