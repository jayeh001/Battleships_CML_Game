package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		//TODO
		//More tests
		ship = new Cruiser();
		assertEquals(3, ship.getLength());
		
		ship = new Submarine();
		assertEquals(1, ship.getLength());
		
	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		//test set submarine to 0, 0
		Ship Submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		Submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, Submarine.getBowRow());
		
		//test set destroyer to 9,9
		Ship destroyer = new Destroyer();
		row = 9;
		column = 9;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, destroyer.getBowRow());
		
		
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		//TODO
		//More tests
		battleship.setBowColumn(3);
		assertEquals(3, battleship.getBowColumn());
		
		Ship submarine = new Submarine();
		row = 1;
		column = 1;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, submarine.getBowColumn());	
		
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		//TODO
		//More tests
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		battleship.shootAt(0, 9);
		
		// test if hit at bow, if hit array will update correctly
		assertTrue(battleship.getHit()[0]);
		
		battleship.shootAt(0, 8);
		
		// test if hit at bow-1, if hit array will update correctly
		assertTrue(battleship.getHit()[1]);
		

		
	}
	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		//TODO
		//More tests
		ship = new Cruiser();
		assertEquals("cruiser", ship.getShipType());
		
		ship = new Destroyer();
		assertEquals("destroyer", ship.getShipType());
		
		ship = new Submarine();
		assertEquals("submarine", ship.getShipType());
		
	}
	
	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests	
		Ship cruiser = new Cruiser();
		row = 6;
		column = 4;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertFalse(cruiser.isHorizontal());
		
		
		Ship destroyer = new Destroyer();
		row = 6;
		column = 4;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(cruiser.isHorizontal());
		
	}
	
	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		//test cruiser set bow row
		Ship cruiser = new Cruiser();
		row = 0;
		column = 4;
		
		cruiser.setBowRow(row);
		assertEquals(row, cruiser.getBowRow());
		
		//test empty ship set bow row
		Ship empty = new EmptySea();
		row = 0;
		column = 4;
		empty.setBowRow(row);
		assertEquals(row, empty.getBowRow());
		
	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		//TODO
		//More tests
		//test cruiser set bow column
		Ship cruiser = new Cruiser();
		row = 0;
		column = 4;
		
		cruiser.setBowColumn(column);
		assertEquals(column, cruiser.getBowColumn());
		
		//test empty ship set bow column
		Ship empty = new EmptySea();
		row = 0;
		column = 4;
		empty.setBowColumn(column);
		assertEquals(column, empty.getBowColumn());
		
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests
		//test cruiser set horizontal
		Ship cruiser = new Cruiser();
		horizontal = true;
		
		cruiser.setHorizontal(horizontal);
		assertTrue(cruiser.isHorizontal());
		
		//test submarine set horizontal
		Ship submarine = new Submarine();
		horizontal = false;
		submarine.setHorizontal(horizontal);
		assertFalse(submarine.isHorizontal());
		
		
		
		
	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		//TODO
		//More tests
		
		//test see if its ok to battleship at 0, 3 which is shorter than its length
		row = 0;
		column = 2;
		horizontal = true;
		boolean ok2 = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship here.");
		
		//test see if its ok to put battleship at 3,0, vertically, which will make the ship stick out
		row = 2;
		column = 0;
		horizontal = false;
		ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "Not OK to place ship here.");
		
	
		
		
		
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		//TODO
		//More tests
	
		
		Battleship battleship3 = new Battleship();
		row = 0;
		column = 8;
		horizontal = true;
		boolean ok3 = battleship3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok3, "Not OK to place ship horizontally adjacent");
		
		
		
		Battleship battleship4 = new Battleship();
		row = 4;
		column = 4;
		horizontal = false;
		boolean ok4 = battleship4.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok4, "Not OK to place ship diagonally adjacent.");
		
		
	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		

		
		//TODO
		//More tests
		
		//put a cruiser and test
		Ship cruiser = new Cruiser();
		row = 1;
		column = 9;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		assertEquals(column, cruiser.getBowColumn());
		assertTrue(cruiser.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][5].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		
		assertEquals(cruiser, ocean.getShipArray()[1][9]);
		assertEquals(cruiser, ocean.getShipArray()[1][7]);
		
		//put another battleship and test 
		Ship battleship1 = new Battleship();
		row = 9;
		column = 0;
		horizontal = false;
		battleship1.placeShipAt(row, column, horizontal, ocean);
		
		assertEquals(row, battleship1.getBowRow());
		assertEquals(column, battleship1.getBowColumn());
		assertFalse(battleship1.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][5].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		
		assertEquals(cruiser, ocean.getShipArray()[1][9]);
		assertEquals(cruiser, ocean.getShipArray()[1][7]);
		
		assertEquals(battleship1, ocean.getShipArray()[9][0]);
		assertEquals(battleship1, ocean.getShipArray()[6][0]);
		
		
		
		
	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//TODO
		//More tests
		//shot at the bow see if its marked
		battleship.shootAt(0, 9);
		boolean[] hitArray1 = {true, false, false, false};
		assertArrayEquals(hitArray1, battleship.getHit());
		
		//shot at all index at this ship
		battleship.shootAt(0, 8);
		boolean[] hitArray2 = {true, true, false, false};
		assertArrayEquals(hitArray2, battleship.getHit());
		
		battleship.shootAt(0, 7);
		
		battleship.shootAt(0, 6);
		boolean[] hitArray3 = {true, true, true, true};
		assertArrayEquals(hitArray3, battleship.getHit());
		
		
		assertTrue(battleship.isSunk());
		
		//empty ship will default to false
		Ship empty = new EmptySea();
		
		empty.placeShipAt(0, 0, horizontal, ocean);
		assertFalse(empty.shootAt(0, 0));
		
		
		
	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		//TODO
		//More tests
		//shot at 3,3, expect to see issunk is true
		assertTrue(submarine.shootAt(3, 3));
		assertTrue(submarine.isSunk());
		
		//test if the ship is placed vertically, if is sunk can detect
		
		Ship destroyer = new Destroyer();
		row = 9;
		column = 9;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(destroyer.isSunk());
		assertTrue(destroyer.shootAt(9, 9));
		assertFalse(destroyer.isSunk());
		assertTrue(destroyer.shootAt(8, 9));
		assertTrue(destroyer.isSunk());
		
		//empty ship will defaut to false
		Ship empty = new EmptySea();
		
		empty.placeShipAt(0, 0, horizontal, ocean);
		assertFalse(empty.isSunk());
		
		
	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		
		//TODO
		//More tests
		
		//sunk submarine expected to return s
		
		Ship submarine = new Submarine();

		submarine.placeShipAt(3, 3, horizontal, ocean);
		
		assertEquals("x", submarine.toString());
	
		submarine.shootAt(3, 3);
		
		assertEquals("s", submarine.toString());
		
		//empty sea expected to return "-"
		
		Ship empty = new EmptySea();
		
		empty.placeShipAt(0, 0, horizontal, ocean);
		
		assertEquals("-", empty.toString());
		
		
		
	}

}
