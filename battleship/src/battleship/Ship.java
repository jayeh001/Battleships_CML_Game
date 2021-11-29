
package battleship;

public abstract class Ship {
	/**
	 * row of bow
	 */
	private int bowRow;
	
	/**
	 * column of bow
	 */
	private int bowColumn;
	
	/**
	 * length of ship
	 */
	private int length;
	
	/**
	 * sets ship to horizontal or vertical
	 */
	private boolean horizontal;
	
	/**
	 * array of booleans indicating if part of ship was hit
	 */
	private boolean [] hit;
	
	/*
	 * creates a ship with given length and sets hit array length 
	 */
	public Ship(int length) {
		this.length = length;
		this.hit = new boolean[length];
		
		// initialize to false given length of ship
			// All parts of ship has not been hit yet, mark them as false
		for (int i = 0; i < length; i ++) {
			hit[i] = false;
		}
	}
	
	/**
	 * gets length of ship
	 * @return ship length
	 */
	public int getLength() {
		return this.length;
	}
	
	/**
	 * gets row of bow
	 * @return row of bow
	 */
	 public int getBowRow() {
		 return this.bowRow;
	 }
	
	 /**
	 * gets column of bow
	 * @return bow column
	 */
	public int getBowColumn() {
		return this.bowColumn;
	}
	
	/**
	 * gets hit array
	 * @return hit array
	 */
	public boolean[] getHit() {
		return this.hit;
	}
	
	/**
	 * gets if ship was horizontal or vertical
	 * @return horizontal or not
	 */
	public boolean isHorizontal() {
		return this.horizontal;
	}
	
	/**
	 * sets row of bow
	 * @param row to set the bow to
	 */
	public void setBowRow(int row) {
		this.bowRow = row;
	}
	
	/**
	 * sets column of bow
	 * @param column to set bow
	 */
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}
	
	/**
	 * sets ship to horizontal or not
	 * @param horizontal
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	/**
	 * gets ship type
	 * @return ship type
	 */
	public abstract String getShipType();
	
	/**
	 * checks if given row and column is too far left or too far up to place bow of ship at. Also,
	 * checks if other ships are within the perimeter of current ship
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return false if not valid or true if valid
	 */
	 boolean okToPlaceShipAt(int row, int column, boolean horizontal, 
			 Ocean ocean) {
		 
		 boolean isValidLeft = horizontal && column < this.getLength() - 1;
		 boolean isValidTop = !horizontal && row < this.getLength() - 1;
		 if ( isValidLeft || isValidTop) {// poking out on left side or at the very top
			 	return false;
		 }
		 
		 //iterates 3 layers of rows
		 if (!horizontal) {
			 for (int i = row - this.length; i < row + 2; i++) {
				 for (int j = column - 1; j < column + 2; j++) {//iterates through columns
					 if (i < 0 || i > 9 ||
						 j < 0 || j > 9 ) {//skip out of bounds
						 continue;
					 } 
					 
					if (ocean.isOccupied(i,j)) {//if ship detects returns true, exit function and return false
						return false;
					}
				 }
			 }
			 //return true;
		 } else {
			 for (int i = row - 1; i < row + 2; i++) {
				 for (int j = column - this.length; j < column + 2; j++) {//iterates through columns
					 if (i < 0 || i > 9 ||
						 j < 0 || j > 9 ) {//skip out of bounds
						 continue;
					 } 
					 
					if (ocean.isOccupied(i,j)) {//if ship detects returns true, exit function and return false
						return false;
					}
				 }
			 }
		 }
		 return true;	 
	}
	 
	 /**
	  * places ship bow at given row and column with orientation of ship
	  * @param row
	  * @param column
	  * @param horizontal
	  * @param ocean
	  */
	 void placeShipAt(int row, int column, boolean horizontal, Ocean 
			 ocean) {
		 //depends on okToPlaceShitAt () method
		 setBowRow(row);
		 setBowColumn(column);
		 setHorizontal(horizontal);
		 
		 Ship [][] shipArray = ocean.getShipArray();
		 
		 if (horizontal) {
			 for (int i = 0; i < length; i ++) {
				 shipArray[row][column - i] = this;
			 }
		 } else if (!horizontal) {
			 for (int i = 0; i < length; i ++) {
				 shipArray[row - i][column] = this;
			 }
		 }
		 
	 }

	 /**
	  * shoots at given row and column
	  * @param row
	  * @param column
	  * @return true if shot lands on segment of ship and ship has not been sunk yet
	  */
	 boolean shootAt(int row, int column) {
		 boolean isTooFarRight = column > getBowColumn(); 
		 boolean isTooFarLeft = column < getBowColumn() - getLength() + 1;
		 if (isHorizontal() && !isTooFarRight && !isTooFarLeft && row == getBowRow() && !isSunk()) {
			 hit[getBowColumn() - column] = true;
			 return true;
		 }
		 boolean isTooFarDown = row > getBowRow(); 
		 boolean isTooFarUp = row < getBowRow() - getLength() + 1;
		 if (!isHorizontal() && !isTooFarDown && !isTooFarUp && column == getBowColumn() && !isSunk()) {
			 hit[getBowRow() - row] = true;
			 return true;
		 }
		 return false;
	 }
	 
	 /**
	  * checks if ship has sunk
	  * @return true if all index of hit array is true
	  */
	 boolean isSunk() {
		 for (int i = 0; i < this.length; i ++) {
			 if (hit[i] == false) {
				 return false;
			 }
		 }
		 return true;
	 }
	/**
	 * prints "s" if ship has sunk, "x" if ship was only hit 
	 */
	 @Override
	 public String toString() {
		 if (isSunk()) {
			 return "s";
		 }
		 return "x";
	 }

}
