/*
 * MotorCoach
 *
 * Version 1
 */
package railway.stocks;

/**
 * A rail transport vehicle that provides the motive power for a train
 * and at the same time transport passengers or luggage
 *
 * @author Tanya Domchenko
 * @version 1, 07 May 2016
 */
public class MotorCoach extends PoweredStock {
    private final float w = 48.0f; 	// the lightweight
    private final float carryingCapacity = 8.0f;	// the number or quantity of people or things 
													// that can be conveyed or held by a vehicle or container
    
    private Type type;	// the class of service of the coach
    
	/**
	 * Declares classes of service of the motor coach
	 */
    public static enum Type {
        PASSENGER, 	// to transport passengers
		WEIGHT;		// to transport cargo
    }

	/**
	 * Constructor specifying the motor coach service class
	 * 
	 * @param type the class of service
	 */
    public MotorCoach(MotorCoach.Type type) {
        this.type = type;
        if ( type == Type.PASSENGER ) {
            getEngine().setPower( 180 );        
        }
        else {
            getEngine().setPower( 250 );
        }
        setWeight( w );
    }

	/**
	 * Gets the amount of the passengers seats in the motor coach
	 */
    @Override
    public int getPassengerCapacity() {
        if ( type == Type.PASSENGER ) {
            return 54;
        }
        else {
            return 0;
        }
    }

	/**
	 * Gets the load weight of the motor coach
	 */
    @Override
    public float getPayloadCapacity() {
        if ( type == Type.WEIGHT ) {
            return 35;
        }
        else {
            return 0;
        }
    }

	/**
	 * Gets the total weight of the motor coach
	 */
    @Override
    public float getGrossWeight() {
        switch ( type ) {
            case PASSENGER:
                return getWeight() + carryingCapacity;
            case WEIGHT:
                 return getWeight() + getPayloadCapacity();
            default: return getWeight();
        }
    }
    
    @Override
    public String toString() {
        String str = "Motor coach (for " + getMaxTrainWeight() + " t): ";
        if ( type == Type.PASSENGER ) {
            str += getPassengerCapacity() + " places";
        }
        else {
            str += getPayloadCapacity() + " t";
        }
        return str; 
    }

}
