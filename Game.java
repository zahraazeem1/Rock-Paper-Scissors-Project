import java.util.*;
/**
 * This is where the main game will be played
 *
 */
public class Game
{
    private int numRounds;  //Random number 
    private Player [] players;
    private int score1;
    private int score2;
    private String [] movesP1;
    private String [] movesP2;
    private boolean print;
    private int [] scores;
    /**
     * Constructor for objects of class Game
     */
    public Game(Player[] players, boolean print) {
        this.players = players;
        this.numRounds = (int) (Math.random() * 101) + 100; // Random 100 - 200; change to 50 for testing.
        this.score1 = 0;
        this.score2 = 0;
        this.print = print;
        this.scores = new int[players.length];
    }

    /**
     * Plays the game with all player combinations.
     */
    public void play() {
        for (int one = 0; one < players.length - 1; one++) {
            Player pOne = players[one];
            for (int two = one + 1; two < players.length; two++) {
                Player pTwo = players[two];

                score1 = 0;
                score2 = 0;
                movesP1 = new String[numRounds];
                movesP2 = new String[numRounds];

                if (print) System.out.println("Match: " + pOne.getName() + " vs " + pTwo.getName());

                for (int round = 1; round <= numRounds; round++) {
                    int points = oneRound(pOne, pTwo, round);

                    if (points < 0) { // Handle illegal moves.
                        if (points == -1) { // Player 1 Illegal
                            score1 -= 10;
                            score2 += 1;
                        } else if (points == -2) { // Player 2 Illegal
                            score1 += 1;
                            score2 -= 10;
                        } else { // Both Players Illegal
                            score1 -= 10;
                            score2 -= 10;
                        }
                    } else { // Handle legal moves.
                        if (points == 0 || points == 1) {
                            score1 += points;
                            score2 -= points;
                        } else {
                            score1 -= 1;
                            score2 += 1;
                        }
                    }
                }

                scores[one] += score1;
                scores[two] += score2;

                if (print) {
                    System.out.println(pOne.getName() + ": " + score1);
                    System.out.println(pTwo.getName() + ": " + score2);
                }
            }
        }
    }

    /**
     * Displays the final scores.
     */
    public void displayScore() {
        System.out.println("Final Scores:");
        for (int i = 0; i < scores.length; i++) {
            System.out.println(players[i].getName() + ": " + scores[i]);
        }
    }

    /**
     * Executes one round of the game between two players.
     */
    private int oneRound(Player one, Player two, int round) {
        String move1 = one.move(movesP1, movesP2, score1, score2);
        String move2 = two.move(movesP2, movesP1, score2, score1);

        int illegal = 0;

        // Validate moves.
        if (move1 == null || (!move1.equals("r") && !move1.equals("p") && !move1.equals("s"))) {
            illegal -= 1;
        }
        if (move2 == null || (!move2.equals("r") && !move2.equals("p") && !move2.equals("s"))) {
            illegal -= 2;
        }

        if (illegal < 0) return illegal; // Return negative for illegal moves.

        // Store valid moves.
        movesP1[round - 1] = move1;
        movesP2[round - 1] = move2;

        // Determine round result.
        String move = move1 + move2;
        if (print) System.out.println("Round " + round + ": " + move);

        if (move.equals("rr") || move.equals("pp") || move.equals("ss")) {
            return 0; // Tie
        } else if (move.equals("rs") || move.equals("sp") || move.equals("pr")) {
            return 1; // Player 1 wins
        } else {
            return 2; // Player 2 wins
        }
    }
}