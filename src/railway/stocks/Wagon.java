/*
 * Wagon
 *
 * Version 1
 */
package railway.stocks;

/**
 * A piece of railway rolling stock that is designed to carry cargo
 * 
 * @author Tanya Domchenko
 * @version 1, 07 May 2016
 */
public class Wagon extends UnpoweredStock {
    private Type type;	// the type of rolling stock
    
    public enum Type {
        COVEREDCAR(26.0f, 68.0f), 	// designed to transport goods and fully enclosed by sides and a fixed roof
        TANKCAR(27.0f, 67.0f), 		// designed to transport liquid and gaseous commodities
        FLATCAR(23.5f, 71.0f);		// designed to carry extra heavy or extra large loads 
									// and consists of an open, flat deck mounted
        
        private final float weight;		// the lightweight
        private final float payload;	// the load weight
        
		/**
	     * Constructor specifying the weight 
	     * 
	     * @param weight the lightweight
		 * @param payload the load weight
	     */
        private Type(float weight, float payload) {
            this.weight = weight;
            this.payload = payload;
        }
        
		/**
		 * Gets the lightweight
		 */
        public float getWeight() {
            return weight;
        }

		/**
		 * Gets the load weight
		 */
        public float getPayload() {
            return payload;
        }
    }    

	/**
	 * Constructor specifying the type of rolling stock 
	 * 
	 * @param type the type of rolling stock 
	 */
    public Wagon(Wagon.Type type) {
        this.type = type;
        setWeight( type.getWeight() );
    }
    
	/**
	 * Constructor that initializes the object by parameters of the passed object  
	 * 
	 * @param w the pattern object
	 */
    public Wagon(Wagon w) {
        this.type = w.getType();
        setWeight( w.getWeight() );
    }
    
	/**
	 * Gets the type of the wagon
	 */
    public Wagon.Type getType() {
        return type;
    }
    
	/**
	 * Gets the amount of the passengers seats in the wagon
	 */
    @Override
    public int getPassengerCapacity() {
        return 0;
    }

	/**
	 * Gets the load weight of the wagon
	 */
    @Override
    public float getPayloadCapacity() {        
        return type.getPayload();
    }

	/**
	 * Gets the total weight of the wagon
	 */
    @Override
    public float getGrossWeight() {
        return getWeight() + getPayloadCapacity();
    }
    
    @Override
    public String toString() {
        return getNumber() + " " + type.name() + ": " + getPayloadCapacity() + " t";
    }
}
