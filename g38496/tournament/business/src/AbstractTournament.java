package g38496.tournament.business;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a tournament.
 *
 * @author g38496
 */
abstract class AbstractTournament {

    /* list of the tournament's players */
    protected ArrayList<Player> players;

    /* list of the matchs for the current turn */
    protected ArrayList<Match> matchs;

    /**
     * Constructs a <code>SingleEliminationTournament</code> with a list of
     * players.
     * @param players the list of players
     */
    public AbstractTournament(ArrayList<Player> players) {
        this.players = players;
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
     * Sets the list of the players.
     * @param players the list of the players
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
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
     * Generates a list of matchs.
     */
    protected abstract void generateMatchs();
}
