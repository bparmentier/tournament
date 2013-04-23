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
    public PoolTournament(ArrayList<Player> players) {
        ArrayList<Player> pools = new ArrayList<>();
        ArrayList<Player> poolPlayers = new ArrayList<>();
        Pool pool;

        for (int i = Config.POOL_MINIMAL_SIZE;
                i <= Config.POOL_MAXIMAL_SIZE; i++) {
            if (players.size() % i = 0) {
                this.poolSize = i;
            }
            //if (players.size() % i = 1) {
            //    this.poolSize = 

        }

        while (poolPlayers.size() <= this.poolSize) {
            for (int i = 0; i < players.size(); i++) {
                poolPlayers.add(players.get(i));
            }
            pool = new Pool(poolPlayers);
            this.pools.add(pool);
        }
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
    public boolean hasMatchsToPlay() {

    }

    /**
     * Returns a list of matchs to play.
     * @return a list of matchs to play
     */
    public ArrayList<Match> getMatchsToPlay() {
        ArrayList<Match> matchsToPlay = new ArrayList<>();
        for (int i = 0; i < this.pools.size(); i++) {
            matchsToPlay.add(this.pools.get(i).getMatchsToPlay());
        }

        return matchsToPlay;
    }

    /**
     * Returns a list of matchs already played.
     * @return a list of matchs already played
     */
    public ArrayList<Match> getMatchsDone() {
        ArrayList<Match> matchsDone = new ArrayList<>();
        for (int i = 0; i < this.pools.size(); i++) {
            matchsDone.add(this.pools.get(i).getMatchsDone());
        }

        return matchsDone;
    }

    /**
     * Returns the list of the matchs.
     * @return the list of the matchs
     */
    public ArrayList<Match> getMatchs() {
        ArrayList<Match> matchs = new ArrayList<>();
        for (int i = 0; i < this.pools.size(); i++) {
            matchs.add(this.pools.get(i).getMatchs());
        }

        return matchs;
    }

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
