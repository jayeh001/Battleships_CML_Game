package battleship;

public class Submarine extends Ship {

	static int NUM_SUBMARINES = 4;
	static int LENGTHSUBMARINES = 1;
	static final String SHIP_TYPE = "submarine";
	
	
	
	/**
	 * constructor
	 */
	public Submarine () {
		
		super(LENGTHSUBMARINES);

	}
	
	
	
	@Override
	public String getShipType() {
		
		return Submarine.SHIP_TYPE;
		
	}
	
	
}
