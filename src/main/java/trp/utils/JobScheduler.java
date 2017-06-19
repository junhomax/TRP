package trp.utils;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by junhomax on 2017-06-20.
 */
public class JobScheduler{
    private static final Comparator< Pair< String, Integer > > jobComparator = new PriorityComparator();
    private PriorityQueue< Pair< String, Integer > > jobQueue;

    public JobScheduler() {
        jobQueue = new PriorityQueue< Pair< String, Integer > >( 5, jobComparator );
    }

    public Pair< String, Integer > getNextJob() {
        return jobQueue.poll();
    }

    public void addJob( Pair< String, Integer > newJob_ ) {
        jobQueue.add( newJob_ );
    }




}
