/*
 * Locomotive
 *
 * Version 1
 */
package railway.stocks;

/**
 * A rail transport vehicle that provides the motive power for a train
 * and has no payload capacity of its own
 *
 * @author Tanya Domchenko
 * @version 1, 07 May 2016
 */
public class Locomotive extends PoweredStock {
    private final float w = 52.0f;  // the lightweight

	/**
	 * Constructor specifying the locomotive engine power
	 * 
	 * @param power the engine power
	 */
    public Locomotive(int power) {
        getEngine().setPower( power );
        setWeight( w );
    }
    
	/**
	 * Gets the amount of the passengers seats in the locomotive
	 */
    @Override
    public int getPassengerCapacity() {
        return 0;
    }

	/**
	 * Gets the load weight of the locomotive
	 */
    @Override
    public float getPayloadCapacity() {
        return 0;
    }

	/**
	 * Gets the total weight of the locomotive
	 */
    @Override
    public float getGrossWeight() {
        return getWeight();
    }

    @Override
    public String toString() {
        return "Locomotive (for " + getMaxTrainWeight() + " t)";
    }
    
}
