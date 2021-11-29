package battleship;

public class Battleship extends Ship {
	

	
	static int NUM_BATTLESHIPS = 1;
	
	static int LENGTHBATTLESHIPS = 4;
	
	static final String SHIP_TYPE = "battleship";
	

	
	
	/**
	 * constructor
	 */
	public Battleship () {
		
		super(LENGTHBATTLESHIPS);

	}
	
	
	
	@Override
	public String getShipType() {
		
		return Battleship.SHIP_TYPE;
		
	}
	
	
	
	
	
}
