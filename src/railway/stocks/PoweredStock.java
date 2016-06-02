/*
 * PoweredStock
 *
 * Version 1
 */
package railway.stocks;

/**
 * The vehicle that's sole sole purpose is to move the train along the tracks
 * 
 * @author Tanya Domchenko
 * @version 1, 07 May 2016
 */
public abstract class PoweredStock extends RollingStock {
    private Engine engine;	// the engine that generates or receives an energy
    
	/**
	 * Produces a power for the vehicle
	 */
    protected class Engine implements Cloneable {        
        private int power;		// the engine power
        private boolean isOn;   // the engine state: true - if it's on, false - if it's off 

		/**
		 * Returns the engine power characteristic
		 */
        public int getPower() {
            return power;
        }

		/**
		 * Sets the engine power 
		 * 
		 * @param power the engine characteristic to set
		 */
        public void setPower(int power) {
            this.power = power;
        }

		/**
		 * Shows the condition of the engine: true - if engine is working and false - otherwise
		 */
        public boolean getIsOn(){
            return isOn;
        }

		/**
		 * The engine start
		 */
        private void on() {
            isOn = true;
        }

		/**
		 * The engine cut-off
		 */
        private void off() {
            isOn = false;
        }

		/**
		 * Energy transformation that causing a train motion
		 */
        private void run() {
            // produce energy
        }

		/**
		 * Gets a copy of the engine 
		 * 
		 * @return 
		 * @throws java.lang.CloneNotSupportedException 
		 */
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

	/**
	 * Default class constructor
	 */
    public PoweredStock() {
        engine = new Engine();
    }
    
	/**
	 * Gets the vehicle engine
	 */
    protected Engine getEngine() {
        return engine;
    }
    
	/**
	 * Sets the vehicle engine
	 * 
	 * @param engine the engine
	 */
    protected void setEngine(Engine engine) {
        this.engine = engine;
    }
    
	/**
	 * Provides a train moving
	 */
    public void move() {
        if ( !engine.getIsOn() ) {
            engine.on();
        }
        engine.run();
    }
    
	/**
	 * Provides a train stop
	 */
    public void stop() {
        if ( engine.getIsOn() ) {
            engine.off();
        }
    }
    
	/**
	 * Defines the biggest weight of the vans that can be moved
	 */
    public int getMaxTrainWeight() {
        int power = engine.getPower();
        if ( isBetween( power, 0, 200 ) ) {
            return 2000;                
        }
        if ( isBetween( power, 201, 400 ) ) {
            return 4000;                
        }
        if ( isBetween( power, 401, 600 ) ) {
            return 6000;                
        }
        else {
            return 8000;
        }
    }
    
	/**
	 * Checks if value <code>x</code> is in a range <code>[a, b]</code>
	 */
    private boolean isBetween(int x, int a, int b) {
        return x <= a && x <= b;
    }
    
	/**
	 * Gets a copy of the vehicle 
	 *
	 * @return 
     * @throws java.lang.CloneNotSupportedException 
	 */
    @Override
    public Object clone() throws CloneNotSupportedException {
        PoweredStock copy = (PoweredStock) super.clone();
        copy.setEngine( (Engine) copy.getEngine().clone() );
        return copy;
    }
    
}
