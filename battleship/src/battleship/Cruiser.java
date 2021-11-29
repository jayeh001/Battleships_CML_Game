package battleship;

public class Cruiser extends Ship {
	
	static int NUM_CRUISERS = 2;
	static int LENGTHCRUISERS = 3;
	
	static final String SHIP_TYPE = "cruiser";
	
	
	
	/**
	 * constructor
	 */
	public Cruiser () {
		
		super(LENGTHCRUISERS);

	}
	
	
	
	@Override
	public String getShipType() {
		
		return Cruiser.SHIP_TYPE;
		
	}
	
	

}
