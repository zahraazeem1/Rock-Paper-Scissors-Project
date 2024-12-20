import java.util.*;
/**
 * Example1 Player will first play rock, then all scissors
 */
public class Example1 implements Player {
    private static String name = "Example";
    private static String strategy = "Rock then all scissors";

    /**
     * Returns the player's move. The first move is rock ("r"), and all subsequent moves are scissors ("s").
     */
    public String move(String[] myMoves, String[] opponentMoves, int myScore, int opponentScore) {
        // Check if this is the first move by finding the first non-null value in myMoves.
        for (String move : myMoves) {
            if (move != null) {
                return "s"; // If a previous move exists, play scissors.
            }
        }
        return "r"; // First move is rock.
    }

    /**
     * Returns the name of the player.
     */
    public String getName() {
        return name;
    }
}