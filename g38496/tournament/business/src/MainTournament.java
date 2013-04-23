package g38496.tournament.business;

import java.util.ArrayList;
import java.util.Collections;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * The main class of the <em>business</em> part of the application. This class
 * is an interface with the <em>view</em> part of the application.
 *
 * @author g38496
 */
public class MainTournament {

    /* players list of the tournament */
    private ArrayList<Player> players;

    /* pool tournament */
    private PoolTournament poolTournament;

    /* single-elimination tournament */
    private SingleEliminationTournament singleEliminationTournament;

    /* inscriptions status */
    private boolean inscriptionsOpen;

    /* single-elimination tournament status */
    private boolean turnPlaying;

    /* pool tournament status */
    private boolean poolPlaying;

    /**
     * Opens the inscriptions.
     */
    public void openInscription() {
        this.players = new ArrayList<>();
        this.inscriptionsOpen = true;
        this.poolPlaying = false;
        this.turnPlaying = false;
    }

    /**
     * Closes the inscriptions and creates a new pool tournament.
     */
    public void closeInscription() {
        this.inscriptionsOpen = false;
        this.turnPlaying = false;
        this.poolTournament = new PoolTournament(players);
        this.poolPlaying = true;
    }

    /**
     * Closes the pool tournament and creates a new single-elimination
     * tournament.
     */
    public void closePoolTournament() throws TournamentException {
        this.poolPlaying = false;
        this.singleEliminationTournament =
                new SingleEliminationTournament(this.getRanking());
        this.turnPlaying = true;
    }

    /**
     * Returns <code>true</code> if inscriptions are open.
     * @return <code>true</code> if inscriptions are open; <code>false</code>
     * otherwise
     */
    public boolean isInscriptionsOpen() {
        return this.inscriptionsOpen;
    }

    /**
     * Returns <code>true</code> if the specified player could have been added.
     * @param player the player to be added
     * @return <code>true</code> if the specified player could have been added;
     * <code>false</code> otherwise
     */
    public boolean addPlayer(Player player) {
        if ((players.size() < Config.PLAYER_MAX_NUMBER) 
                && (this.inscriptionsOpen)
                && (player != null)) {
            this.players.add(player);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns <code>true</code> if the specified player could have been
     * removed.
     * @param player the player to be removed
     * @return <code>true</code> if the specified player could have been
     * removed; <code>false</code> otherwise
     */
    public boolean removePlayer(Player player) {
        if ((this.players.size() > 0)
                && (this.inscriptionsOpen)
                && (this.players.contains(player))) {
            this.players.remove(player);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the number of free places left.
     * @return the number of free places left
     */
    public int getFreePlacesNumber() {
        return (Config.PLAYER_MAX_NUMBER - players.size());
    }

    /**
     * Sets the result of a match.
     * @param id the id of the match
     * @param result the result of the match to set
     * @throws NullPointerException if the result is <code>null</code>
     * @throws TournamentException if the result cannot be set
     */
    public void setTurnResult(int id, ResultEnum result)
            throws TournamentException {
        if (result == null) {
            throw new NullPointerException("Result cannot be null");
        } else if (result == ResultEnum.NOT_PLAYED) {
            throw new TournamentException("Result cannot be set to NOT_PLAYED");
        } else if (this.singleEliminationTournament.hasMatchsToPlay()) {
            this.singleEliminationTournament.setResult(id, result);
        } else {
            throw new TournamentException("Cannot set result");
        }

        /*if (!this.singleEliminationTournament.hasMatchsToPlay()
                && this.singleEliminationTournament.hasNextTurn()) {
            this.singleEliminationTournament.nextTurn();
        }*/
        if (!this.hasMatchsToPlay()) {
            this.singleEliminationTournament.nextTurn();
        }
    }

    /**
     * Sets the result of a match.
     * @param id the id of the match
     * @param result the result of the match to set
     */
    public void setPoolResult(int id, ResultEnum result)
            throws TournamentException {
        // FIXME
    }

    /**
     * Returns the list of the players of the tournament.
     * @return the list of the players of the tournament
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * Returns the list of all the matchs.
     * @return the list of all the matchs
     */
    public ArrayList<Match> getMatchs() {
        ArrayList<Match> matchs = new ArrayList<>();
        
        if (poolPlaying) {
            matchs = this.poolTournament.getMatchs();
        } else if (turnPlaying) {
            matchs = this.singleEliminationTournament.getMatchs();
        }

        return matchs;
    }

    /**
     * Returns the list of the matchs already played.
     * @return the list of the matchs already played
     */
    public ArrayList<Match> getMatchsDone() {
        ArrayList<Match> matchs = new ArrayList<>();
        
        if (poolPlaying) {
            matchs = this.poolTournament.getMatchsDone();
        } else if (turnPlaying) {
            matchs = this.singleEliminationTournament.getMatchsDone();
        }

        return matchs;

    }

    /**
     * Returns the list of the matchs not yet played.
     * @return the list of the matchs not yet played
     */
    public ArrayList<Match> getMatchsToPlay() {
        ArrayList<Match> matchs = new ArrayList<>();
        
        if (poolPlaying) {
            matchs = this.poolTournament.getMatchsToPlay();
        } else if (turnPlaying) {
            matchs = this.singleEliminationTournament.getMatchsToPlay();
        }

        return matchs;
    }

    /**
     * Returns the ranking of the players.
     * @return the ranking of the players
     */
    public ArrayList<Player> getRanking() {
        ArrayList<Player> ranking = this.players;
        Collections.sort(ranking);
        Collections.reverse(ranking);
        return ranking;
    }

    /**
     * Returns <code>true</code> if there are still matchs to play.
     * @return <code>true</code> if there are still matchs to play;
     * <code>false</code> otherwise
     */
    public boolean hasMatchsToPlay() {
        boolean hasMatchsToPlay = false;
        
        if (poolPlaying) {
            hasMatchsToPlay = this.poolTournament.hasMatchsToPlay();
        } else if (turnPlaying) {
            hasMatchsToPlay = this.singleEliminationTournament.hasMatchsToPlay();
        }

        return hasMatchsToPlay;
    }

    /**
     * Returns the winner when all the matchs took place.
     * @return the winner when all the matchs took place
     * @throws TournamentException if the last match didn't took place
     */
    public Player getWinner() throws TournamentException {
        Match lastMatch;
        Player winner;
        ArrayList<Match> matchsToPlay;
        matchsToPlay = this.singleEliminationTournament.getMatchsToPlay();

        if (!singleEliminationTournament.hasNextTurn()
                && (matchsToPlay.size() == 1)) {
            lastMatch = this.singleEliminationTournament.getMatchs().get(0);
            winner = lastMatch.getWinner();
        } else {
            throw new TournamentException("Cannot get winner.");
        }

        return winner;
    }
}
