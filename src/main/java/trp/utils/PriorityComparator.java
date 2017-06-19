package trp.utils;

import java.util.Comparator;

/**
 * Created by junhomax on 2017-06-20.
 */
public class PriorityComparator implements Comparator< Pair< String, Integer > > {
    public int compare( Pair< String, Integer > leftPair_, Pair< String, Integer > rightPair_ ) {
        if( leftPair_.second() < rightPair_.second() ) return -1;
        if( leftPair_.second() > rightPair_.second() ) return 1;
        return 0;
    }
}
