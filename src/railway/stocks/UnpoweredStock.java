/*
 * UnpoweredStock
 *
 * Version 1
 */
package railway.stocks;

/**
 * The vehicle that is designed to carry passengers or cargo
 *
 * @author Tanya Domchenko
 * @version 1, 07 May 2016
 */
public abstract class UnpoweredStock extends RollingStock implements INumbered {
    private int number;	// the sequence number from the front of the train
    
	/**
	 * Returns the number from the front of the train
	 */
    public int getNumber() {
        return number;
    }
    
	/**
	 * Sets the sequence number from the front of the train
	 * 
	 * @param number the number of the van to set
	 */
    protected void setNumber(int number) {
        this.number = number;
    }

}