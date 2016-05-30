package railway.stocks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Tanya Domchenko
 */
public class Train {
    private final TrainType type;
    
    private final PoweredStock head;
    private final PoweredStock tail;
    private LinkedList<UnpoweredStock> vans = new LinkedList();
    private int length = 0;    
    
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
    
    public TrainType getType() {
        return type;
    }
    
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
    
    private boolean isPermissibleLoad(UnpoweredStock van, int count) {
        float w = head.getGrossWeight() + tail.getGrossWeight();         
        return w + van.getGrossWeight() * count <= head.getMaxTrainWeight();
    }
    
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
     * Returns a copy
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
    
    public int getVansCount() {
        return length;
    }
    
    public List<RollingStock> filterByCapacity(int aFrom, int aTo) {
        List<RollingStock> l = new ArrayList<>();        
        copyIfCapacityBetween( head, aFrom, aTo, l );        
        for ( UnpoweredStock ps: vans ) {
            copyIfCapacityBetween( ps, aFrom, aTo, l );
        }
        copyIfCapacityBetween( tail, aFrom, aTo, l );
        return l;
    }
    
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
    
    private RollingStock getCopy(RollingStock st) {
        RollingStock copy = null;
        try {
            copy = (RollingStock) st.clone();
        } catch (CloneNotSupportedException ex) {
        }
        return copy;
    }
    
    public int countPassengers() {
        int count = head.getPassengerCapacity() + tail.getPassengerCapacity();
        for ( UnpoweredStock st: vans ) {
            count += st.getPassengerCapacity();
        }
        return count;
    }
    
    public float calculatePayload() {
        float sum = head.getPayloadCapacity() + tail.getPayloadCapacity();
        for ( UnpoweredStock st: vans ) {
            sum += st.getPayloadCapacity();
        }
        return sum;
    }
    
    public void sortCarriagesByClass() {
        CarriageClassComparator comparator = new CarriageClassComparator();
        vans.sort( comparator );
        enumerate();
    }
    
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
