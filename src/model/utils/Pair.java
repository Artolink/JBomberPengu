package model.utils;

/**
 * A standard generic Pair<X,Y>, with getters, hashCode, equals, and toString
 * well implemented.
 * 
 * @param <X> the first element
 * @param <Y> the second element
 */
public class Pair<X, Y> {

    private final X x;
    private final Y y;

    /**
     * Builder.
     * 
     * @param x the first element
     * @param y the second element
     */
    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @return x
     */
    public X getX() {
        return x;
    }

    /**
     * 
     * @return y
     */
    public Y getY() {
        return y;
    }

    /**
     * HashCode method implemented correctly.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    /**
     * Equals method implemented correctly.
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        if (x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!x.equals(other.x)) {
            return false;
        }
        if (y == null) {
            if (other.y != null) {
                return false;
            }
        } else if (!y.equals(other.y)) {
            return false;
        }
        return true;
    }

    /**
     * toString method implemented correctly.
     */
    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }
}
