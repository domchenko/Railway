/*
 * Railway
 *
 * Version 1
 */
package railway.main;

import java.util.List;
import railway.stocks.Carriage;
import railway.stocks.RollingStock;
import railway.stocks.Train;
import railway.stocks.TrainType;

/**
 * Determines the hierarchy of rolling railroad transport
 * Builds a passenger train and calculates passengers and luggage in it
 * Sorts vans in the train by their comfortability 
 * Searches vans by defined range of the passengers seats
 * 
 * @author Tanya Domchenko
 * @version 1, 07 May 2016
 */
public class Railway {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		// build a new passenger train
        Train train = new Train( TrainType.PASSENGER, 500 );
        boolean b;
		// add vans of different classes
        b = train.addVans( Carriage.Type.SLEEPING, 4, true );
        if ( !b ) {
            System.out.println( "Error. Van(s) were not added" );
        }
        b = train.addVans( Carriage.Type.ECONOMCLASS, 12, true );
        if ( !b ) {
            System.out.println( "Error. Van(s) were not added" );
        }
        b = train.addVans( Carriage.Type.COMPARTMENT, 10, true );
        if ( !b ) {
            System.out.println( "Error. Van(s) were not added" );
        }
        // add at the beginning
        b = train.addVans( Carriage.Type.COMPARTMENT, 1, false );
        if ( !b ) {
            System.out.println( "Error. Van(s) were not added" );
        }
        
        System.out.println( "#1. Train has " + train.getVansCount() + " vans" );
		// calculates weight of passengers and their luggage 
        System.out.println( "Total payload is " + train.calculatePayload() + " t" );
		// calculates passengers seats in the train
        System.out.println( "Number of passengers' seats is " + train.countPassengers() );
        System.out.println( train.toString() );
		
		// sorts vans by the comfortability
        train.sortCarriagesByClass();
        System.out.println( "After sorting by comfortability class");
        System.out.println( train.toString() );
        
		// searches vans by the range of passengers seats 
        searchByCapacity( train, 20, 40 );
        
		// build a new interregional train with 7 vans: 5 coaches and 2 motor coaches
        Train suburbanTrain = new Train( TrainType.PASSENGER, 40 );
        suburbanTrain.addVans( Carriage.Type.INTERREGIONAL, 5, true );
        System.out.println( "#2. Suburban train has " + suburbanTrain.getVansCount() + " vans" );
        System.out.println( suburbanTrain.toString() );
		
        // searches vans by the range of passengers seats 
        searchByCapacity( suburbanTrain, 20, 55 );
    }    
    
	/**
	 * Searches vans in the train by the range of passengers seats 
	 *
	 * @param t the train
	 * @param aFrom the lower range limit
	 * @param aTo the upper range limit
	 */
    public static void searchByCapacity(Train t, int aFrom, int aTo) {
        System.out.println( "Searching stocks with " +
                aFrom + "-" + aTo + " passenger places" );
        List<RollingStock> l = t.filterByCapacity( aFrom, aTo );
        for ( RollingStock ps: l ) {    
            System.out.println( ps );            
        }
    }
}
