/*
 * RollingStock
 *
 * Version 1
 */
package railway.stocks;

/**
 * The wheeled vehicles collectively used on a railway, 
 * including the locomotives, passenger coaches, freight wagons, guard's vans, etc
 * 
 * @author Tanya Domchenko
 * @version 1, 07 May 2016
 */
public abstract class RollingStock implements Cloneable {
    private float weight;	// the lightweight 
    
	/**
	 * Gets the lightweight of the vehicle
	 */
    public float getWeight() {
        return weight;
    }

	/**
	 * Sets the lightweight of the vehicle
	 *
	 * @param weight the lightweight of the vehicle
	 */
    public void setWeight(float weight) {
        this.weight = weight;
    }

	/**
	 * Gets the amount of the passengers seats of the vehicle
	 */
    public abstract int getPassengerCapacity();
    
	/**
	 * Gets the load weight of the vehicle
	 */
    public abstract float getPayloadCapacity();    

	/**
	 * Gets the total weight of the vehicle
	 */
    public abstract float getGrossWeight();
    
	/**
	 * Gets a copy of the vehicle 
	 * 
	 * @return 
     * @throws java.lang.CloneNotSupportedException 
	 */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

