/*
 * Train
 *
 * Version 1
 */
package railway.stocks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A rail transport consisting of a series of vehicles that transport cargo or passengers
 * 
 * @author Tanya Domchenko
 * @version 1, 07 May 2016
 */
public class Train {
    private final TrainType type;	// the type of the service
    
    private final PoweredStock head;	// the self-propelled rolling stock at the front of the train
    private final PoweredStock tail;	// the self-propelled rolling stock at the end of the train
    private LinkedList<UnpoweredStock> vans = new LinkedList();	// the list of vans 
    private int length = 0;    			// the amount of the vans
    
	/**
	 * Constructor that builds the train by its service type and the distance of the route
	 * 
	 * @param type the type of the service
	 * @param distance the route length
	 */
    public Train(TrainType type, int distance) {
        this.type = type;
        if ( distance <= 150 ) {
            MotorCoach.Type coachType = ( type == TrainType.PASSENGER ) ? 
                    MotorCoach.Type.PASSENGER : MotorCoach.Type.WEIGHT;
            head = new MotorCoach( coachType );
            tail = new MotorCoach( coachType );
            length = 2;
        }
        else {
            int power = ( type == TrainType.PASSENGER ) ? 300 : 500;
            head = new Locomotive( power );
            tail = new Locomotive( power );
        }
    }
	
    /**
	 * Gets the type of the train
	 */
    public TrainType getType() {
        return type;
    }
    
	/**
	 * Adds the passenger coaches at the beginning or at the end of the train
	 *
	 * @param vanType the class of the van
     * @param count the amount of the vans
	 * @param addToEnd the direction where to add this vans
	 */
    public boolean addVans(Carriage.Type vanType, int count, boolean addToEnd) {
        if ( type != TrainType.PASSENGER ) {
            return false;
        }
        if ( count < 1 ) {
            return false;
        }
        UnpoweredStock van = new Carriage( vanType );
        if ( isPermissibleLoad( van, count ) ) {
            insertVan( van, count, addToEnd );
            return true;
        }        
        else {
            return false;
        }
    }
    
	/**
	 * Checks whether train could move such amount of vans
	 * 
	 * @param van the pattern rolling stock
	 * @param count the amount of the vans
	 */
    private boolean isPermissibleLoad(UnpoweredStock van, int count) {
        float w = head.getGrossWeight() + tail.getGrossWeight();         
        return w + van.getGrossWeight() * count <= head.getMaxTrainWeight();
    }
    
	/**
	 * Adds some amount of the vans at the front or at the end of the train
	 *
	 * @param van the pattern rolling stock
	 * @param count the amount of the vans
	 * @param addToEnd the sign to add vans at the front of the train, if false - at the end of the train
	 */
    private void insertVan(UnpoweredStock van, int count, boolean addToEnd) {
        do {                
            van.setNumber( generateIndexNumber() );
            if ( addToEnd ) {                     
                vans.add( van );
            }
            else {
                vans.addFirst( van );
            }
            length++;
            count--;
            if ( count > 0 ) {
                van = buildVan( van );
            }
        } while ( count > 0 );
    }
    
	/**
	 * Defines the sequence number of the van in the train
	 */
    private int generateIndexNumber() {
        int num = 0;
        if ( vans.isEmpty() ) {
            if ( head instanceof Locomotive ) {
                num = 1; // locomotives hasn't any number
            }
            else {
                num = 2; // motor coach has number 1
            }
        }
        else {
            int max = vans.getLast().getNumber();
            Iterator it = vans.iterator();
            while ( it.hasNext() ) {
                UnpoweredStock ps = (UnpoweredStock) it.next();
                if ( ps.getNumber() > max ) {
                    max = ps.getNumber();
                }
            }
            num = max + 1;
        }
        return num;
    }
    
	/**
	 * Creates a new rolling stock by defined pattern van
	 *
	 * @param origin the pattern object
	 */
    private UnpoweredStock buildVan(UnpoweredStock origin) {
        UnpoweredStock newVan = null;
        if ( origin instanceof Carriage ) {
            newVan = new Carriage( (Carriage) origin);
        }
        else {
            newVan = new Wagon( (Wagon) origin);
        }
        return newVan;
    }
    
	/**
	 * Adds the wagons at the beginning or at the end of the train
	 *
	 * @param vanType the class of the van
     * @param count the amount of the vans
	 * @param addToEnd the direction where to add this vans
	 */
    public boolean addVans(Wagon.Type vanType, int count, boolean addToEnd) {
        if ( type != TrainType.WEIGHT ) {
            return false;
        }
        if ( count < 1 ) {
            return false;
        }
        UnpoweredStock van = new Wagon( vanType );
        if ( isPermissibleLoad( van, count ) ) {
            insertVan( van, count, addToEnd );
            return true;
        }        
        else {
            return false;
        }
    }
    
    /**
     * Returns the rolling stocks of the train
     * 
     * @return 
     */
    public List<RollingStock> getVans() {
        List<RollingStock> l = new ArrayList<>();
        RollingStock copy = null;
        if ( ( head.getPassengerCapacity() > 0 || head.getPayloadCapacity() > 0 ) 
                && ( copy = getCopy( head ) ) != null ) {
            l.add( copy );
        }        
        Iterator it = vans.iterator();
        while ( it.hasNext() ) {
            UnpoweredStock ps = (UnpoweredStock) it.next();
            if ( ( copy = getCopy( ps ) ) != null ) {
                l.add( copy );
            }
        }
        if ( ( tail.getPassengerCapacity() > 0 || tail.getPayloadCapacity() > 0 )
                && ( copy = getCopy( tail ) ) != null ) {
            l.add( copy );
        }
        return l;
    }
    
	/**
     * Returns the count of the rolling stocks
     */
    public int getVansCount() {
        return length;
    }
    
	/**
	 * Returns rolling stocks by the defined amount of passengers
	 *
	 * @param aFrom the lower range
	 * @param aFrom the upper range
	 */
    public List<RollingStock> filterByCapacity(int aFrom, int aTo) {
        List<RollingStock> l = new ArrayList<>();        
        copyIfCapacityBetween( head, aFrom, aTo, l );        
        for ( UnpoweredStock ps: vans ) {
            copyIfCapacityBetween( ps, aFrom, aTo, l );
        }
        copyIfCapacityBetween( tail, aFrom, aTo, l );
        return l;
    }
    
	/**
	 * Returns a copy of van which capacity is in the defined range
	 *
	 * @param st the origin
	 * @param aFrom the lower range
	 * @param aFrom the upper range
	 */
    private void copyIfCapacityBetween(RollingStock st, int aFrom, int aTo,
            List<RollingStock> list) {
        RollingStock copy = null;
        if ( st != null 
                && st.getPassengerCapacity() >= aFrom
                && st.getPassengerCapacity() <= aTo ) {
            copy = getCopy( st );
        }
        if ( copy != null ) {
            list.add( copy );
        }
    }
    
	/**
	 * Copies a rolling stock
	 *
	 * @param st the origin
	 */
    private RollingStock getCopy(RollingStock st) {
        RollingStock copy = null;
        try {
            copy = (RollingStock) st.clone();
        } catch (CloneNotSupportedException ex) {
        }
        return copy;
    }
    
	/**
	 * Calculates the total amount of passengers in the train
	 */
    public int countPassengers() {
        int count = head.getPassengerCapacity() + tail.getPassengerCapacity();
        for ( UnpoweredStock st: vans ) {
            count += st.getPassengerCapacity();
        }
        return count;
    }
    
	/**
	 * Calculates the total payload of the train
	 */
    public float calculatePayload() {
        float sum = head.getPayloadCapacity() + tail.getPayloadCapacity();
        for ( UnpoweredStock st: vans ) {
            sum += st.getPayloadCapacity();
        }
        return sum;
    }
    
	/**
	 * Sorts passenger coaches by their comfortability class
	 */
    public void sortCarriagesByClass() {
        CarriageClassComparator comparator = new CarriageClassComparator();
        vans.sort( comparator );
        enumerate();
    }
    
	/**
	 * Renumber the vans of the train
	 */
    private void enumerate() {
        int num = ( head instanceof MotorCoach ) ? 2 : 1;
        for ( UnpoweredStock st: vans ) {
            st.setNumber( num++ );
        }
    }

    @Override
    public String toString() {
        String str = "Head " + head.toString();
        for ( RollingStock ps: vans ) {    
            str += "\n" + ps.toString();            
        }
        if ( tail != null ) {
            str += "\nTail " + tail.toString();
        }
        return str;
    }
}
