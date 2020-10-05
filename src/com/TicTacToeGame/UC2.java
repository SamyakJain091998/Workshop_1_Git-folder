package com.TicTacToeGame;

import java.util.Scanner;

public class UC2 {

	private static char[] boardArray;
	
	private static char[] Board() {
		char[] boardArray = new char[11];
		for(char c : boardArray) {
			c = ' ';
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
		
		System.out.println("User input is : " + firstCharacterInput + " and computer input is : " + computerCharacterInput);
		sc.close();
	}
	
	public static void main(String[] args) {		
		boardArray = Board();
		ChooseLetter();
	}
}