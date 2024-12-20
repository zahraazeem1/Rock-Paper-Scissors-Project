import java.util.*;
/**
 * Driver class, sets up game
 * Look to comments to see what you need to do to test your game
 */
public class Main
{
    public static void main(String [] args)
    {
        Player[] players = new Player[3];  // need to update array based on number of players
        boolean printsRun = false;   // enable/disable debug print statements.

        // Add players to game
        players[0] = new Example1();
        players[1] = new PlayerExample();
        players[2] = new MJ();

        //construct the game with the classes in the players array, calls on play and displays the score
        //DO NOT CHANGE
        Game newGame = new Game(players, printsRun);
        newGame.play();
        newGame.displayScore();
    }
}
