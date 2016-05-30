package railway.stocks;

/**
 * Locomotives, carriages, wagons, or other vehicles used on a railroad
 * 
 * @author Tanya Domchenko
 */
public abstract class RollingStock implements Cloneable {
    private float weight;
    
    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public abstract int getPassengerCapacity();
    
    public abstract float getPayloadCapacity();    

    public abstract float getGrossWeight();
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
