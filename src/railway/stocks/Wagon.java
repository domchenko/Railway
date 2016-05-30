package railway.stocks;

/**
 * Uses for shipping goods
 * 
 * @author Tanya Domchenko
 */
public class Wagon extends UnpoweredStock {
    private Type type;
    
    public enum Type {
        COVEREDCAR(26.0f, 68.0f), 
        TANKCAR(27.0f, 67.0f), 
        FLATCAR(23.5f, 71.0f);
        
        private final float weight;
        private final float payload;
        
        private Type(float weight, float payload) {
            this.weight = weight;
            this.payload = payload;
        }
        
        public float getWeight() {
            return weight;
        }

        public float getPayload() {
            return payload;
        }
    }    

    public Wagon(Wagon.Type type) {
        this.type = type;
        setWeight( type.getWeight() );
    }
    
    public Wagon(Wagon w) {
        this.type = w.getType();
        setWeight( w.getWeight() );
    }
    
    public Wagon.Type getType() {
        return type;
    }
    
    @Override
    public int getPassengerCapacity() {
        return 0;
    }

    @Override
    public float getPayloadCapacity() {        
        return type.getPayload();
    }

    @Override
    public float getGrossWeight() {
        return getWeight() + getPayloadCapacity();
    }
    
    @Override
    public String toString() {
        return getNumber() + " " + type.name() + ": " + getPayloadCapacity() + " t";
    }
}
