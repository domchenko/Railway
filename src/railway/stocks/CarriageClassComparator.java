package railway.stocks;

import java.util.Comparator;

/**
 * Comparator for sorting carriages by their comfortability
 * 
 * @author Tanya Domchenko
 */
public class CarriageClassComparator implements Comparator<UnpoweredStock> {
    private Carriage.Type[] types;
    
    public CarriageClassComparator() {
        types = new Carriage.Type[4];
        types[0] = Carriage.Type.SLEEPING;
        types[1] = Carriage.Type.COMPARTMENT;
        types[2] = Carriage.Type.ECONOMCLASS;
        types[3] = Carriage.Type.INTERREGIONAL;
    }

    @Override
    public int compare(UnpoweredStock o1, UnpoweredStock o2) {
        Integer c1, c2;
        if ( o1 instanceof Carriage ) {
            c1 = getCarriageLevel( ( (Carriage) o1 ).getType() );            
        }
        else {
            c1 = -1;
        }
        if ( o2 instanceof Carriage ) {
            c2 = getCarriageLevel( ( (Carriage) o2 ).getType() );            
        }
        else {
            c2 = -1;
        }
        return c1.compareTo( c2 );
    }
    
    private int getCarriageLevel(Carriage.Type type) {
        for ( int i = 0; i < types.length; i++ ) {
            if ( types[i] == type ) {
                return i;
            }
        }
        return -1;
    }
}
