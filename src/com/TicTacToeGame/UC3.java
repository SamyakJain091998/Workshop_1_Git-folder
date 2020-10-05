package com.TicTacToeGame;

import java.util.Scanner;

public class UC3 {
	private static char[] boardArray;

	private static char[] Board() {
		char[] boardArray = new char[10];
		for (int i = 0; i < boardArray.length; i++) {
			boardArray[i] = ' ';
		}
		return boardArray;
	}

	private static void ChooseLetter() {

		char firstCharacterInput = ' ';
		char computerCharacterInput = ' ';
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter input Mr. player");
		System.out.println("Enter 'X' or 'O' : ");
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

		System.out.println(
				"User input is : " + firstCharacterInput + " and computer input is : " + computerCharacterInput);
		sc.close();
	}

	private static void showBoard(char[] boardArray) {
		System.out.println("-----THE BOARD-----");
		System.out.println("__________________");
		System.out.print("|  ");
		for (char i = 1; i < 3; i++) {
			System.out.print(boardArray[i] + "  |  ");
		}
		System.out.print(boardArray[3] + "  ");
		System.out.print("|");
		System.out.println();
		System.out.println("__________________");
		System.out.print("|  ");
		for (char i = 4; i < 6; i++) {
			System.out.print(boardArray[i] + "  |  ");
		}
		System.out.print(boardArray[6] + "  ");
		System.out.print("|");
		System.out.println();
		System.out.println("__________________");
		System.out.print("|  ");
		for (char i = 7; i < 9; i++) {
			System.out.print(boardArray[i] + "  |  ");
		}
		System.out.print(boardArray[9] + "  ");
		System.out.print("|");
		System.out.println();
		System.out.println("__________________");
	}

	public static void main(String[] args) {
		boardArray = Board();
		// ChooseLetter();
		showBoard(boardArray);
	}
}
