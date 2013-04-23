package g38496.tournament.business;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A single-elimination tournament.
 *
 * @author g38496
 */
public class SingleEliminationTournament extends AbstractTournament {

    /* 
     * player who directly goes to the next turn if the number of participants
     * is odd
     */
    private Player theLuckyGuy;

    /* list of the players for the current turn */
    private ArrayList<Player> playersCurrentTurn;

    /* list of the list of the matchs */
    private ArrayList<ArrayList<Match>> matchsHistory;

    /**
     * Constructs a <code>SingleEliminationTournament</code> with a list of
     * players.
     * @param players the list of players
     */
    public SingleEliminationTournament(ArrayList<Player> players) {
        super(players);
        this.players = players;
        this.matchs = new ArrayList<>();
        this.matchsHistory = new ArrayList<>();
        this.playersCurrentTurn = new ArrayList<>(players);
        this.generateMatchs();
    }

    /**
     * Returns <code>true</code> if there is still a turn
     * @return <code>true</code> if there is still a turn; <code>false</code>
     * if it is the last turn
     */
    public boolean hasNextTurn() {
        return ((this.playersCurrentTurn.size() > 1)
                && (this.theLuckyGuy != null));
    }

    /**
     * Regenerates a list of matchs for the next turn.
     * @throws TournamentException if there are still matchs to play
     */
    public void nextTurn() throws TournamentException {
        if (!this.hasMatchsToPlay()) { // && this.hasNextTurn()) {
            this.generateMatchs();
        } else {
            throw new TournamentException("There are still matches to play");
        }
    }

    /**
     * Generates a list of matchs with the players of the current turn.
     */
    @Override
    protected void generateMatchs() {
        Match match;
        Collections.shuffle(this.playersCurrentTurn);

        /* If there were a "lucky guy", add it to playersCurrentTurn. */
        if (this.theLuckyGuy != null) {
            this.playersCurrentTurn.add(this.theLuckyGuy);
            this.theLuckyGuy = null;
            Collections.shuffle(this.playersCurrentTurn);
        }

        /*
         * If the number of players for the current turn is odd, add a
         * random one to the next turn and remove it from playersCurrentTurn.
         */
        if ((this.playersCurrentTurn.size() % 2) != 0) {
            Collections.shuffle(this.playersCurrentTurn);
            this.theLuckyGuy = this.playersCurrentTurn.get(0);
            this.playersCurrentTurn.remove(0);
        }

        /* Reset the list of the matchs and generate a new one. */
        this.matchs = new ArrayList<>();
        for (int i = 0; i < this.playersCurrentTurn.size(); i += 2) {
            match = new Match(this.playersCurrentTurn.get(i),
                    this.playersCurrentTurn.get(i + 1));
            this.matchs.add(match);
        }

        /* Add the newly created list of matchs to the history. */
        this.matchsHistory.add(this.matchs);
    }
}
