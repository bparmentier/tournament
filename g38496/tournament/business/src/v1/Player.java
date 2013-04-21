package g38496.tournament.business;

/**
 * Represents a participant (player). A participant may be an individual or a team.
 *
 * @author g38496
 */
public class Player implements Comparable<Player> {

    private String name;
    private int points;
    private int id;
    private static int nextId = 1;
    
    /**
     * Constructs a <code>Player</code>.
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        this.id = this.nextId;
        this.nextId++;
    }

    /**
     * Returns the points of the player.
     * @return the points of the player
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the points of this player.
     * @param points the points to set to the player
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Adds points to this player.
     * @param points the points to add to the player
     */
    public void addPoints(int points) {
        this.points += points;
    }

    /**
     * Returns the name of the player.
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the id of the player.
     * @return the id of the player
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the string representation of the object.
     * @return the string representation of the object
     */
    @Override
    public String toString() {
        return "\nPlayer id: " + this.id + "\tPoints: " + this.points
                + "\tName: " + this.name;
    }

    /**
     * Compares the points of this player with the points of the specified
     * player for order. Returns a negative integer, zero, or a positive
     * integer as the points of this player are less than, equal to, or greater
     * than the points of the specified player.
     * @param e the player to be compared
     * @return a negative integer, zero, or a positive integer as the points
     * of this player are less than, equal to, or greater than the points of
     * the specified player.
     */
    @Override
    public int compareTo(Player e) { // TournamentException if e is null?
        return this.points - e.getPoints();
    }
}
