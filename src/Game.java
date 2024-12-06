// importing my arraylist and scanner
import java.util.ArrayList;
import java.util.Scanner;

// creating the game class
public class Game {

    // creating two static arraylists to store the positions for the players
    static ArrayList<Integer> player1Positions = new ArrayList<>();
    static ArrayList<Integer> player2Positions = new ArrayList<>();
    // scanner to read players input
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        // while-loop to let the players play more rounds + clear last games positions
        while (true) {
            player1Positions.clear();
            player2Positions.clear();

            // new board + show the current board
            Board board = new Board();
            board.display();

            // string variable to store the result
            String result = "";

            // looping the game until someone won or exits
            while (true) {
                // Player 1 turn, calling the GameLogic to handle p1's turn
                System.out.println("Where do you want to place? Choose between box1-9: ");
                result = GameLogic.playerTurn(scan, board, player1Positions, "player1");
                // if the player make a choice then print it
                if (!result.isEmpty()) {
                    System.out.println(result);
                    break; // round done
                }

                // Player 2's turn, same code
                System.out.println("Enter your choice player 2: ");
                result = GameLogic.playerTurn(scan, board, player2Positions, "player2");
                if (!result.isEmpty()) {
                    System.out.println(result);
                    break;
                }
            }

            // asking the players if they wanna go again
            System.out.println("Would you like to play again? (yes/no)");
            String playAgain = scan.next();
            // if the answer is yes then keep going, if its no then stop the loop/program
            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }
        }
        scan.close();
    }
}