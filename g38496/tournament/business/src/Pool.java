package g38496.tournament.business;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a pool. It manages matchs and participants of a pool.
 *
 * @author g38496
 */
public class Pool extends AbstractTournament {

    private int id;
    private static int nextId;

    /**
     * Constructs a pool.
     */
    public Pool(ArrayList<Player> players) {
        super(players);
        this.id = this.nextId;
        this.nextId++;
        this.generateMatchs();
    }

    /**
     * Returns the id of the pool.
     * @return the id of the pool
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the ranking.
     * @return the ranking
     */
    public ArrayList<Player> getRanking() {
        ArrayList<Player> ranking = this.players;
        Collections.sort(ranking);
        Collections.reverse(ranking);
        return ranking;
    }

    @Override
    public String toString() {
        return "Pool id: " + this.id + "\n" + this.matchs;
    }

    @Override
    protected void generateMatchs() {
        Match match;
        Collections.shuffle(this.players);

        this.matchs = new ArrayList<>();
        for (int i = 0; i < (this.players.size() - 1); i++) {
            for (int j = (i + 1); j < this.players.size(); j++) {
                match = new Match(this.players.get(i), this.players.get(j));
                this.matchs.add(match);
            }
        }
    }
}
