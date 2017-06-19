package trp.utils;

import java.util.Comparator;

/**
 * Created by junhomax on 2017-06-20.
 */
public class Pair< L, R > {
    private final L left;
    private final R right;

    public Pair( L left_, R right_ ) {
        this.left   = left_;
        this.right  = right_;
    }

    public L first() { return this.left; }
    public R second() { return this.right; }

    @Override
    public int hashCode() {
        return this.left.hashCode() ^ this.right.hashCode();
    }

    @Override
    public boolean equals( Object newObj_ ) {
        if( !( newObj_ instanceof Pair ) ) return false;
        Pair newPair_ = ( Pair ) newObj_;
        return this.left.equals( newPair_.first() ) && this.right.equals( newPair_.second() );
    }



}
