package g38496.tournament.business;

import java.util.ArrayList;

/**
 * Represents a pool tournament.
 *
 * @author g38496
 */
public class PoolTournament {

    private ArrayList<Pool> pools;
    private int poolSize;
    
    /**
     * Constructs a pool tournament.
     */
    public PoolTournament() {
    }

    /**
     * Returns the list of the pools.
     * @return the list of the pools
     */
    public ArrayList<Pool> getPools() {
        return this.pools;
    }

    /**
     * Returns <code>true</code> if there is still matches to play.
     * @return <code>true</code> if there is still matches to play;
     * <code>false</code> otherwise
     */
//    public boolean hasMatchs() {
//    }

    /**
     * Returns a list of matchs to play.
     * @return a list of matchs to play
     */
//    public ArrayList<Match> getMatchsToPlay() {
//    }

    /**
     * Returns a list of matchs already played.
     * @return a list of matchs already played
     */
//    public ArrayList<Match> getMatchsDone() {
//    }

    /**
     * Returns the list of the matchs.
     * @return the list of the matchs
     */
//    public ArrayList<Match> getMatchs() {
//    }

    /**
     * Sets the result of this match.
     * @param fId the id of the match
     * @param result the result of the match
     * @throws TournamentException if match result is <code>DRAW</code> or
     * <code>null</code>
     */
//    public void setResult(int id, ResultEnum result) {
//    }
}
