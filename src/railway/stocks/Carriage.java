/*
 * Carriage
 *
 * Version 1
 */
package railway.stocks;

/**
 * A piece of railway rolling stock that is designed to carry passengers
 * 
 * @author Tanya Domchenko
 * @version 1, 07 May 2016
 */
public class Carriage extends UnpoweredStock {
    private Type type;  // the type of rolling stock
    
    public enum Type {
        SLEEPING( 18, 58.5f, 2.0f ), 	// a railway coach provided with beds or berths
        COMPARTMENT( 36, 61.5f, 4.0f ), // a railway coach divided into separate compartments, with no means of moving between compartments
        ECONOMCLASS( 54, 59.0f, 6.0f ), // a railway coach divided into separate compartments, with moving between compartments
        INTERREGIONAL( 64, 38, 9.0f );  // a railway coach in which the seats are arranged in one or more open plan areas with a centre aisle

        private final int capacity;	// the amount of the passenger seats
        private final float weight;	// the lightweight
        private final float carryingCapacity;	// the number or quantity of people or luggage

		/**
	     * Constructor specifying the weight characteristics
	     * 
	     * @param capacity the amount of the passengers
		 * @param weight the lightweight
		 * @param carryingCapacity the number or quantity of people or luggage
	     */
        private Type(int capacity, float weight, float carryingCapacity) {
            this.capacity = capacity;
            this.weight = weight;
            this.carryingCapacity = carryingCapacity;
        }

		/**
		 * Gets the amount of the passengers
		 */
        public int getCapacity() {
            return capacity;
        }
        
		/**
		 * Gets the lightweight
		 */
        public float getWeight() {
            return weight;
        }
        
		/**
		 * Gets the carrying capacity
		 */
        public float getCarryingCapacity() {
            return carryingCapacity;
        }

    }    
    
	/**
	 * Constructor specifying the type of rolling stock 
	 * 
	 * @param type the type of rolling stock 
	 */
    public Carriage(Carriage.Type type) {
        this.type = type;
        setWeight( type.getWeight() );
    }
    
	/**
	 * Constructor that initializes the object by parameters of the passed object  
	 * 
	 * @param c the pattern object
	 */
    public Carriage(Carriage c) {
        this.type = c.getType();
        setWeight( c.getWeight() );
    }
    
	/**
	 * Gets the type of the carriage
	 */
    public Carriage.Type getType() {
        return type;
    }
    
	/**
	 * Gets the amount of the passengers seats in the carriage
	 */
    @Override
    public int getPassengerCapacity() {
        return type.getCapacity();
    }

	/**
	 * Gets the load weight of the carriage
	 */
    @Override
    public float getPayloadCapacity() {
        return type.getCarryingCapacity();
    }
    
	/**
	 * Gets the total weight of the carriage
	 */
    @Override
    public float getGrossWeight() {
        return getWeight() + type.getCarryingCapacity();
    }

    @Override
    public String toString() {
        return getNumber() + " " + type.name() + ": " 
                + getPassengerCapacity() + " places";
    }
}
