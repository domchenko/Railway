/*
 * Транспорт. 
 * Определить иерархию подвижного состава железнодорожного транспорта. 
 * Создать пассажирский поезд. 
 * Посчитать общую численность пассажиров и багажа. 
 * Провести сортировку вагонов поезда на основе уровня комфортности. 
 * Найти вагоны в поезде, соответствующие заданному диапазону параметров числа пассажиров.
 */
package railway.main;

import java.util.List;
import railway.stocks.Carriage;
import railway.stocks.RollingStock;
import railway.stocks.Train;
import railway.stocks.TrainType;

/**
 *
 * @author Tanya Domchenko
 */
public class Railway {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Train train = new Train( TrainType.PASSENGER, 500 );
        boolean b;
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
        System.out.println( "Total payload is " + train.calculatePayload() + " t" );
        System.out.println( "Number of passengers' places is " + train.countPassengers() );
        System.out.println( train.toString() );
        train.sortCarriagesByClass();
        System.out.println( "After sorting by comfortability class");
        System.out.println( train.toString() );
        
        searchByCapacity( train, 20, 40 );
        
        Train suburbanTrain = new Train( TrainType.PASSENGER, 40 );
        suburbanTrain.addVans( Carriage.Type.INTERREGIONAL, 5, true );
        System.out.println( "#2. Suburban train has " + suburbanTrain.getVansCount() + " vans" );
        System.out.println( suburbanTrain.toString() );
        
        searchByCapacity( suburbanTrain, 20, 55 );
    }    
    
    public static void searchByCapacity(Train t, int aFrom, int aTo) {
        System.out.println( "Searching stocks with " +
                aFrom + "-" + aTo + " passenger places" );
        List<RollingStock> l = t.filterByCapacity( aFrom, aTo );
        for ( RollingStock ps: l ) {    
            System.out.println( ps );            
        }
    }
}
