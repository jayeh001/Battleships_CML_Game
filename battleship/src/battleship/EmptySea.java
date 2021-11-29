package battleship;

public class EmptySea extends Ship {

	
	
	static final String SHIP_TYPE = "empty";
	
	
	/**
	 * constructor
	 */
	public EmptySea () {
		
		super(1);

	}
	
	                                               
	/**        
	 */
	@Override
	boolean shootAt(int row, int column) {
		return false;
	}
	
	/**
	 * Override the method to always return false
	 */
	@Override         
	boolean isSunk() {
		return false;
	}
	
	
	/**
	 * Override to string method to display "-"
	 */
	@Override
	public String toString() {
		return "-";
	}
	
	/**
	 * Override the method to return string "empty"
	 */
	@Override
	public String getShipType() {
		
		return EmptySea.SHIP_TYPE;
		
	}
	
	
	
}
