// importing everything i need
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

// the GameLogic class
public class GameLogic {
    // method to handle the players turns
    public static String playerTurn(Scanner scan, Board board, ArrayList<Integer> playerPositions, String user) {
        // let the players know which turn it is and print out message
        System.out.println(user.equals("player1") ? "Player 1" : "Player 2" + ", where do you want to place? Choose between box 1-9:");
        // reading players choice
        int pos = scan.nextInt();

        // loop til players chooses an available pos, printing out message if the box is not
        while (playerPositions.contains(pos) || (user.equals("player1") ? Game.player2Positions : Game.player1Positions).contains(pos)) {
            System.out.println("That position is occupied! Please enter an empty place");
            pos = scan.nextInt();
        }

        // defines player 1 to X and player 2 to O
        char symbol = user.equals("player1") ? 'X' : 'O';
        // adding the chosen position
        playerPositions.add(pos);
        // placing the choice
        board.placeChoice(pos, symbol);
        // showing the current board
        board.display();

        // checking for a winner and return the result
        return checkTheWinner();
    }

    // method to check the winner and possible options
    public static String checkTheWinner() {
        List<List<Integer>> winCons = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9),
                Arrays.asList(1, 4, 7),
                Arrays.asList(2, 5, 8),
                Arrays.asList(3, 6, 9),
                Arrays.asList(1, 5, 9),
                Arrays.asList(3, 5, 7)
        );

        // looping every win condition
        for (List<Integer> l : winCons) {
            // checking if player 1 or 2 has a wincon
            if (Game.player1Positions.containsAll(l)) {
                return "Congratulations player 1 you won!";
            } else if (Game.player2Positions.containsAll(l)) {
                return "Congratulations player 2 you won!";
            }
        }

        // if att the positions are chosen but no one has a win con = tie
        if (Game.player1Positions.size() + Game.player2Positions.size() == 9) {
            return "Bummer, it's a tie!";
        }

        return "";
    }
}