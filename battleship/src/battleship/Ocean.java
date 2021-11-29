package battleship;

import java.util.Random;


public class Ocean {
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	
	/**
	 * define 2-dimensional array that represents the ocean
	 * Can only contain "b", "d", etc or " "
	 * private Ship[][] ships = new Ship[10][10];
	 */
	private Ship[][] ships = new Ship[10][10];
	
	/**
	 * total number of shots fired by user
	 */
	private int shotsFired;
	
	/**
	 * the number of times a shot hit a ship
	 */
	private int hitCount;
	
	/**
	 * number of ships sunk
	 */
	private int shipSunk;
	
	/**
	 * returns whether spot has been shot at or not
	 */
	boolean[][] shotMap = new boolean[10][10];
	
	/**
	 * Constructor
	 * 
	 */
	public Ocean() {		
		//Creates an empty ocean		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ships[i][j] = new EmptySea();
				ships[i][j].placeShipAt(i, j, true, this);
			}
		}	
		this.shotsFired = 0;
		this.shipSunk = 0;
		this.hitCount = 0;
		
		// Initialize our shotMap to be all false initially
		for (int i = 0; i < shotMap.length; ++i) {
			for (int j = 0; j < shotMap[0].length; ++j) {
				shotMap[i][j] = false;
			}
		}
	}
	
  /**
	 * placeAllShipsRandomly method to randomly places different kind of ships in the array
	 */
	void placeAllShipsRandomly() {
		Random rand = new Random();
		//randomize row column and horizontal
		int row;  
		int column; 
		boolean horizontal;  
		
		//put battleship first
		//iterate through list of ships here
		Ship [] shiplist = {new Battleship(),  new Cruiser(),  new Cruiser(), new Destroyer(), 
				new Destroyer(),new Destroyer(),new Submarine(), new Submarine(), new Submarine(), new Submarine()};
								
		for (Ship ship: shiplist) {		   
			int ok = 0;
      
			while (ok==0) {
				row = rand.nextInt(OCEAN_SIZE);		
				column = rand.nextInt(OCEAN_SIZE);
				horizontal = rand.nextBoolean();
				
				if (ship.okToPlaceShipAt(row, column, horizontal,this)) {
					ship.placeShipAt(row, column, horizontal, this);
					ok = 1;
				}
//				printWithShips();
			}
		}
	}
	
	void printWithShips() {
//		char [][] oceanArray = new char[11][11];
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (i == 0 && j == 0) { // top left corner blank space
					System.out.print("  ");
					continue;
				}
				if (i == 0 && j > 0) {// x axis header
//					oceanArray[i][j] = (char) (j - 1);
					System.out.print(String.valueOf(j - 1) + " ");
					if (j == 10)
						System.out.println();
					continue;
				}
				if (i > 0 && j == 0) {// y axis header
//					oceanArray[i][j] = (char) (i - 1);
					System.out.print(String.valueOf(i - 1) + " ");
					continue;
				}
				
				if (ships[i-1][j-1].getShipType() != "empty" ) {
//					oceanArray[i][j] = ships[i-1][j-1].getShipType().charAt(0);
					System.out.print(ships[i-1][j-1].getShipType().charAt(0) + " ");
				} else {
//					oceanArray[i][j] = ' ';
					System.out.print("  ");
				}
//				System.out.print(oceanArray[i][j]);
				if (j == 10) {
					System.out.println();
				}
				/*if (ships[i - 1][j - 1].getShipType() == "destroyer") {
					oceanArray[i][j] = 'd';
				}
				if (ships[i - 1][j - 1].getShipType() == "battleship") {
					oceanArray[i][j] = 'b';
				}
				if (ships[i - 1][j - 1].getShipType() == "cruiser") {
					oceanArray[i][j] = 'c';
				}
				if (ships[i - 1][j - 1].getShipType() == "submarine") {
					oceanArray[i][j] = 's';
				}
				if (ships[i - 1][j - 1].getShipType() == "empty") {
					oceanArray[i][j] = ' ';
				}
				*/
			}
		}
	}
	
	/**
	 * return true if the given location contains a ship, false if it does not
	 * @param row
	 * @param column
	 * @return
	 */
	boolean isOccupied(int row, int column) {
		if (ships[row][column].getShipType() == "empty") {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * return true if the given location contains afloat ship
	 * @param row
	 * @param column
	 * @return
	 */
	boolean shootAt(int row, int column) {
		//increase shotsfired count
		this.shotsFired++;
		
		// Keep track of our shots
		shotMap[row][column] = true;
		
		if (this.isOccupied(row, column) && !ships[row][column].isSunk()) {
			//updates hit array of given ship
			ships[row][column].shootAt(row, column);
			this.hitCount++;
			//if the ship is sunk, update shipSunk count
			if (ships[row][column].isSunk()) {
				this.shipSunk++;
			}
			return true;
		} else {// Shot at here, but missed
			return false;
		}
	}
	
	/**
	 * return number of shots fired
	 * @return
	 */
	int getShotsFired() {
		return this.shotsFired;
	}
	
	/**
	 * return number of hits recorded
	 * @return
	 */
	int getHitCount() {
		return this.hitCount;
	}
	
	/**
	 * returns number of ships sunk
	 * @return ships sunk
	 */
	int getShipsSunk() {
		return this.shipSunk;
	}
	
	/**
	 * game over set to true if shipSunk == 10
	 */
	boolean isGameOver() {
		return getShipsSunk() == 10;
	}
	
	/**
	 * return the 10*10 array of ships
	 * @return
	 */
	Ship[][] getShipArray() {
		return ships;
	}
	
	
	/**
	 * print the Ocean
	 */
	void print() {
//		String [][] oceanArray = new String[11][11];
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (i == 0 && j == 0) {
					System.out.print("  ");
					continue;
				}
				if (i == 0 && j > 0) {// if on row 0, prints out x axis numbers
//					oceanArray[i][j] = String.valueOf(j - 1);
//					System.out.print(oceanArray[i][j] + " ");
					System.out.print(String.valueOf(j - 1) + " ");
					if (j == 10)
						System.out.println();
					continue;
				}
				if (i > 0 && j == 0) {
//					oceanArray[i][j] = String.valueOf(i - 1);
//					System.out.print(oceanArray[i][j] + " ");
					System.out.print(String.valueOf(i - 1) + " ");
					continue;
				}
				
				
				// Prints 'x', '-', 's'
				if (shotMap[i - 1][j - 1] == true) {// Calls either emptySea.toString() or Ship.toString()
					System.out.print(ships[i - 1][j - 1].toString() + " ");
				} else {// Gets '.'
					System.out.print(". ");
				}
				
				// At the last column of each row, lets reset to beginning of the next row
				if (j == 10) {
					System.out.println();
				}
			}
		}
		
//		//ask for user input
//		Scanner scan = new Scanner(System.in);
//		
//		System.out.println("Enter row, column:");
//		
//		String answer = scan.next();
//		
//		String [] values = answer.split(",");
//		
//		//need to decide how to use these two variable outside this method
//		int row = Integer.parseInt(values[0]);
//		
//		int column = Integer.parseInt(values[1]);
	}
}