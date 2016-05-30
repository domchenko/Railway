package railway.stocks;

/**
 *
 * @author Tanya Domchenko
 */
public class MotorCoach extends PoweredStock {
    private final float w = 48.0f; 
    private final float carryingCapacity = 8.0f;
    
    private Type type;    
    
    public static enum Type {
        PASSENGER, WEIGHT;
    }

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

    @Override
    public int getPassengerCapacity() {
        if ( type == Type.PASSENGER ) {
            return 54;
        }
        else {
            return 0;
        }
    }

    @Override
    public float getPayloadCapacity() {
        if ( type == Type.WEIGHT ) {
            return 35;
        }
        else {
            return 0;
        }
    }

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
