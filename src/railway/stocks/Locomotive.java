package railway.stocks;

/**
 *
 * @author Tanya Domchenko
 */
public class Locomotive extends PoweredStock {
    private final float w = 52.0f;  

    public Locomotive(int power) {
        getEngine().setPower( power );
        setWeight( w );
    }
    
    @Override
    public int getPassengerCapacity() {
        return 0;
    }

    @Override
    public float getPayloadCapacity() {
        return 0;
    }

    @Override
    public float getGrossWeight() {
        return getWeight();
    }

    @Override
    public String toString() {
        return "Locomotive (for " + getMaxTrainWeight() + " t)";
    }
    
}
