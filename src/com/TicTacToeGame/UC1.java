package com.TicTacToeGame;

public class UC1 {
	private static char[] boardArray;
	
	private static char[] Board() {
		char[] boardArray = new char[11];
		for(char c : boardArray) {
			c = ' ';
		}
		return boardArray;
	}
	
	public static void main(String[] args) {
		boardArray = Board();
	}
}
