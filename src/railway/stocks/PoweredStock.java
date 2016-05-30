package railway.stocks;

/**
 *
 * @author Tanya Domchenko
 */
public abstract class PoweredStock extends RollingStock {
    private Engine engine;
    
    protected class Engine implements Cloneable {        
        private int power;
        private boolean isOn;    

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        public boolean getIsOn(){
            return isOn;
        }

        private void on() {
            isOn = true;
        }

        private void off() {
            isOn = false;
        }

        private void run() {
            // produce energy
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public PoweredStock() {
        engine = new Engine();
    }
    
    protected Engine getEngine() {
        return engine;
    }
    
    protected void setEngine(Engine engine) {
        this.engine = engine;
    }
    
    public void move() {
        if ( !engine.getIsOn() ) {
            engine.on();
        }
        engine.run();
    }
    
    public void stop() {
        if ( engine.getIsOn() ) {
            engine.off();
        }
    }
    
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
    
    private boolean isBetween(int x, int a, int b) {
        return x <= a && x <= b;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        PoweredStock copy = (PoweredStock) super.clone();
        copy.setEngine( (Engine) copy.getEngine().clone() );
        return copy;
    }
    
}
