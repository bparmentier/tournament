package g38496.tournament.business;

/**
 * Thrown when something incoherent happens in the business part of the
 * project.
 *
 * @author g38496
 */
public class TournamentException extends Exception {
    
    /**
     * Constructs a <code>TournamentException</code> with the specified detail
     * message.
     * @param s the detail message
     */
    public TournamentException(String s) {
        super(s);
    }
}
