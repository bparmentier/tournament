package g38496.tournament.business;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A single-elimination tournament.
 *
 * @author g38496
 */
public class SingleEliminationTournament {

    /* list of the tournament's players */
    private ArrayList<Player> players;

    /* list of the matchs for the current turn */
    private ArrayList<Match> matchs;

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
        this.playersCurrentTurn = new ArrayList<>();
        this.players = players;
        this.matchs = new ArrayList<>();
        this.matchsHistory = new ArrayList<>();
        this.playersCurrentTurn = new ArrayList<>(players);
        this.generateMatchs();
    }

    /**
     * Returns the match corresponding to the <code>id</code>.
     * @param id the id of the match
     * @return the match corresponding to the <code>id</code>;
     * <code>null</code> if the match is not found
     */
    public Match getMatch(int id) {
        Match match; // a match
        for (int i = 0; i < this.matchs.size(); i++) {
            match = this.matchs.get(i);
            if (match.getId() == id) {
                return match;
            }
        }

        return null;
    }

    /**
     * Returns the list of the players.
     * @return the list of the players
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * Returns the list of the matchs.
     * @return the list of the matchs
     */
    public ArrayList<Match> getMatchs() {
        return this.matchs;
    }

    /**
     * Returns the list of all the lists of the matchs
     * @return the list of all the lists of the matchs
     */
    public ArrayList<ArrayList<Match>> getMatchsHistory() {
        return this.matchsHistory;
    }

    /**
     * Sets the result of this match.
     * @param fId the id of the match
     * @param result the result of the match
     * @throws TournamentException if match result is <code>DRAW</code> or
     * <code>null</code>
     */
    public void setResult(int fId, ResultEnum result)
            throws TournamentException {
        Match match; // a match
        
        if ((result == ResultEnum.DRAW) || (result == null)) {
            throw new TournamentException("Match result cannot be drawn");
        } else {
            match = this.getMatch(fId);
            if (this.matchs.contains(match)) {
                match.setResult(result);
                playersCurrentTurn.remove(match.getLoser());
            } else {
                throw new TournamentException("Match id not found.");
            }
        }
    }

    /**
     * Returns a list of matchs to play.
     * @return a list of matchs to play
     */
    public ArrayList<Match> getMatchsToPlay() {
        Match match; // a match
        ArrayList<Match> matchsToPlay = new ArrayList<>();
        for (int i = 0; i < this.matchs.size(); i++) {
            match = this.matchs.get(i);
            if (!match.isDone()) {
                matchsToPlay.add(match);
            }
        }

        return matchsToPlay;
    }

    /**
     * Returns a list of matchs already played.
     * @return a list of matchs already played
     */
    public ArrayList<Match> getMatchsDone() {
        Match match; // a match
        ArrayList<Match> matchsDone = new ArrayList<>();
        for (int i = 0; i < this.matchs.size(); i++) {
            match = this.matchs.get(i);
            if (match.isDone()) {
                matchsDone.add(match);
            }
        }

        return matchsDone;
    }

    /**
     * Returns <code>true</code> if there is an unplayed match.
     * @return <code>true</code> if there is an unplayed match;
     * <code>false</code> otherwise
     */
    public boolean hasMatchsToPlay() {
        return (!this.getMatchsToPlay().isEmpty());
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
        if (!this.hasMatchsToPlay() { // && this.hasNextTurn()) {
            this.generateMatchs();
        } else {
            throw new TournamentException("There are still matches to play");
        }
    }

    /**
     * Generates a list of matchs with the players of the current turn.
     */
    private void generateMatchs() {
        Match match;
        Collections.shuffle(playersCurrentTurn);

        /* If there were a "lucky guy", add it to playersCurrentTurn. */
        if (this.theLuckyGuy != null) {
            this.playersCurrentTurn.add(this.theLuckyGuy);
            this.theLuckyGuy = null;
            Collections.shuffle(playersCurrentTurn);
        }

        /*
         * If the number of players for the current turn is odd, add a
         * random one to the next turn and remove it from playersCurrentTurn.
         */
        if ((this.playersCurrentTurn.size() % 2) != 0) {
            Collections.shuffle(playersCurrentTurn);
            this.theLuckyGuy = this.playersCurrentTurn.get(0);
            this.playersCurrentTurn.remove(0);
        }

        /* Reset the list of the matchs and generate a new one. */
        this.matchs = new ArrayList<>();
        for (int i = 0; i < playersCurrentTurn.size(); i += 2) {
            match = new Match(playersCurrentTurn.get(i),
                    playersCurrentTurn.get(i + 1));
            this.matchs.add(match);
        }

        /* Add the newly created list of matchs to the history. */
        this.matchsHistory.add(this.matchs);
    }
}
