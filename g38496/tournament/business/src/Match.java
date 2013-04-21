package g38496.tournament.business;

/**
 * Represents a match.
 *
 * @author g38496
 */
public class Match {

    private int id;
    private static int nextId = 1;
    private Player[] players;
    private ResultEnum result;

    /**
     * Constructs a match with two players.
     * @param player1 the first player
     * @param player2 the second player
     */
    public Match(Player player1, Player player2) {
        this.players = new Player[2];
        this.players[0] = player1;
        this.players[1] = player2;
        this.result = ResultEnum.NOT_PLAYED;
        this.id = this.nextId;
        this.nextId++;
    }

    /**
     * Returns the id of the match.
     * @return the id of the match
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the result of the match.
     * @return the result of the match
     */
    public ResultEnum getResult() {
        return result;
    }

    /**
     * Sets the result of the match.
     * @param result the result of the match
     */
    public void setResult(ResultEnum result) {
        this.result = result;
        if (this.result == ResultEnum.PLAYER1) {
            this.players[0].addPoints(Config.POINT_WINNER);
            this.players[1].addPoints(Config.POINT_LOSER);
        } else if (this.result == ResultEnum.PLAYER2) {
            this.players[0].addPoints(Config.POINT_LOSER);
            this.players[1].addPoints(Config.POINT_WINNER);
        } else if (this.result == ResultEnum.DRAW) {
            this.players[0].addPoints(Config.POINT_DRAW);
            this.players[1].addPoints(Config.POINT_DRAW);
        }
    }

    /**
     * Returns <code>true</code> if the match took place.
     * @return <code>true</code> if the match took place;
     * <code>false</code> otherwise
     */
    public boolean isDone() {
        return (this.result != ResultEnum.NOT_PLAYED);
    }

    /**
     * Returns the winner of the match.
     * @return the winner of the match; <code>null</code> if there is no
     * winner
     */
    public Player getWinner() {
        if (this.result == ResultEnum.PLAYER1) {
            return this.players[0];
        } else if (this.result == ResultEnum.PLAYER2) {
            return this.players[1];
        } else {
            return null;
        }
    }

    /**
     * Returns the loser of the match.
     * @return the loser of the match; <code>null</code> if there is no
     * loser
     */
    public Player getLoser() {
        if (this.result == ResultEnum.PLAYER1) {
            return this.players[1];
        } else if (this.result == ResultEnum.PLAYER2) {
            return this.players[0];
        } else {
            return null;
        }
    }
    
    /**
     * Returns the string representation of the object
     * @return the string representation of the object
     */
    @Override
    public String toString() {
        return "\nMatch id: " + this.id + "\tResult: " + this.result + "  \t"
                + players[0].getName() + " vs. " + players[1].getName();
    }
}
