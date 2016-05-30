package railway.stocks;

/**
 * Uses for transportation people
 * 
 * @author Tanya Domchenko
 */
public class Carriage extends UnpoweredStock {
    private Type type;  
    
    public enum Type {
        SLEEPING( 18, 58.5f, 2.0f ), 
        COMPARTMENT( 36, 61.5f, 4.0f ), 
        ECONOMCLASS( 54, 59.0f, 6.0f ), 
        INTERREGIONAL( 64, 38, 9.0f );

        private final int capacity;
        private final float weight;
        private final float carryingCapacity;

        private Type(int capacity, float weight, float carryingCapacity) {
            this.capacity = capacity;
            this.weight = weight;
            this.carryingCapacity = carryingCapacity;
        }

        public int getCapacity() {
            return capacity;
        }
        
        public float getWeight() {
            return weight;
        }
        
        public float getCarryingCapacity() {
            return carryingCapacity;
        }

    }    
    
    public Carriage(Carriage.Type type) {
        this.type = type;
        setWeight( type.getWeight() );
    }
    
    public Carriage(Carriage c) {
        this.type = c.getType();
        setWeight( c.getWeight() );
    }
    
    public Carriage.Type getType() {
        return type;
    }
    
    @Override
    public int getPassengerCapacity() {
        return type.getCapacity();
    }

    @Override
    public float getPayloadCapacity() {
        return type.getCarryingCapacity();
    }
    
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
