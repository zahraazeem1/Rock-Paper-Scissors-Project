import java.util.*;
/**
 * The Player interface defines the API used by the game engine to interace with a player.
 */
public interface Player
{
    public String move(String [] myMoves, String [] opponentMoves, int myScore, int opponentScore);
    public String getName();
}
