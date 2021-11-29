package battleship;

import java.util.Scanner;

public class BattleshipGame {

	public static void main(String[] args) {
		/**
		 * create Ocean instance
		 */
		Ocean ocean = new Ocean();
		
		ocean.placeAllShipsRandomly();
		
  		System.out.println("####### WELCOME TO BATTLESHIPS!! ##########");
		ocean.print();

		
		/**
		 * get user input of 2 integer values with comma separated to indicate shoot location
		 */
		while (!ocean.isGameOver()) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter row, column (ex: 1,2):\n");
			String answer = scan.next();
//			System.out.println("answer: " + answer);
			int[] values = new int[5];
			
			int valuesIndex = 0;
			String curr_number = "";
			
			// Parsing string for only int
			for (int i = 0; i < answer.length(); i++) {
//				if (answer.charAt(i) >= '0' && answer.charAt(i) <= '9') {
				if (Character.isDigit(answer.charAt(i))) {
//					values[valuesIndex++] = (int)(answer.charAt(i) - '0');
					curr_number += answer.charAt(i);
				} else if (!Character.isDigit(answer.charAt(i)) && curr_number.length() > 0) {
//					System.out.println("curr_number: " + curr_number);
					values[valuesIndex++] = Integer.parseInt(curr_number);
					curr_number = "";
				}
			} 
			
			if (curr_number.length() > 0) {
//				System.out.println("curr_number: " + curr_number);
				values[valuesIndex] = Integer.parseInt(curr_number);
			}
			
//			for (int val : values) {
//				System.out.println("val: " + val);
//			}
			
			int row = values[0];
			int column = values[1];
			
//			System.out.println("row: " + row + ", col: " + column);
			
			if (row < 0 || row > 9 || column < 0 || column > 9) {
				System.out.println("invalid input. please try again");
				continue;
			}
			
			Ship curr_ship = ocean.getShipArray()[row][column];
			
			/**
			 * computer responds with "hit" or "miss"
			 * 
			 */
			if (ocean.shootAt(row, column)) {
				Ship[][] ship = ocean.getShipArray();
				ship[row][column].shootAt(row, column);
				System.out.println("Hit!");
			} else {
				System.out.println("Miss!");
			}
			
			/**
			 * if a ship is sunk, program prints "You just sank a ship - (type)"
			 */
			if (curr_ship.isSunk()) {
				System.out.println("You just sank a ship - " + curr_ship.getShipType());
			}
			
			/**
			 * print current state of the ocean every time
			 */
			System.out.println("Total shots fired: " + ocean.getShotsFired());
			System.out.println("Total hits landed: " + ocean.getHitCount());
			System.out.println("Total ships sunk out of ten: " + ocean.getShipsSunk());
			ocean.print();
		}
		
		/**
		 * when all the ships are sunk, print out message that the game is over, and print how many shots were required
		 */
		System.out.println("Game over! Number of shots: " + ocean.getShotsFired());
	}
}
