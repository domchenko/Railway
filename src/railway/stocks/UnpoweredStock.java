package railway.stocks;

/**
 *
 * @author Tanya Domchenko
 */
public abstract class UnpoweredStock extends RollingStock implements INumbered {
    private int number;
    
    public int getNumber() {
        return number;
    }
    
    protected void setNumber(int number) {
        this.number = number;
    }

}
