package battleship;

public class Destroyer extends Ship {

	static int NUM_DESTROYERS = 3;
	static int LENGTHDESTROYERS = 2;
	static final String SHIP_TYPE = "destroyer";
	
	
	/**
	 * constructor
	 */
	public Destroyer () {
		
		super(LENGTHDESTROYERS);

	}
	
	
	
	@Override
	public String getShipType() {
		
		return Destroyer.SHIP_TYPE;
		
	}
	
}
