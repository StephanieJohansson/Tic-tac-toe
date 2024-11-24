// importing my arraylist, my arrays, my wincon list to be able to use them and scanner to read input from users
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// defines the game class
public class Game {

    // making two static arraylists for player 1 and 2's positions
    static ArrayList<Integer> player1Positions = new ArrayList<>();
    static ArrayList<Integer> player2Positions = new ArrayList<>();
    // creating a scanner to read the input from the players
    static Scanner scan = new Scanner(System.in);

    // my main method
    public static void main(String[] args) {

        // creating a while-loop that allows the players to play more than just once
        while (true) {
            // Reset players positions from last game played
            player1Positions.clear();
            player2Positions.clear();

            // creating the game board
            char[][] board = {{' ', '|', ' ', '|', ' '},
                    {'-', '+', '-', '+', '-'},
                    {' ', '|', ' ', '|', ' '},
                    {'-', '+', '-', '+', '-'},
                    {' ', '|', ' ', '|', ' '}};

            theBoard(board);  // showing my board in the terminal
            String result = ""; // defines a string variable to store the result of the game

            // creating another while-loop that controls every individual round
            while (true) {
                // player 1 starts the round and get this message
                System.out.println("Player 1, where do you want to place? Choose between box 1-9:");
                int pos1 = scan.nextInt(); // a scan for ints that lets player one chose a position to play
                // checking if the position is already taken or not
                while (player1Positions.contains(pos1) || player2Positions.contains(pos1)) {
                    // sending this message out to the player if it's true
                    System.out.println("That position is occupied! Please enter an empty place");
                    pos1 = scan.nextInt(); // chance 2 to choose a position
                }
                placeChoise(board, pos1, "player1"); // placing player 1 choice of position on the board
                theBoard(board); // and showing it
                // creating at string to help check the result
                result = checkTheWinner();
                // if statement that sends out the result of the game
                if (!result.isEmpty()) {
                    System.out.println(result);
                    break;
                }

                // player 2's turn to play, same code as for player 1
                System.out.println("Player 2, where do you want to place? Choose between box 1-9:");
                int pos2 = scan.nextInt();
                while (player1Positions.contains(pos2) || player2Positions.contains(pos2)) {
                    System.out.println("That position is occupied! Please enter an empty place");
                    pos2 = scan.nextInt();
                }
                placeChoise(board, pos2, "player2");
                theBoard(board);
                result = checkTheWinner();
                if (!result.isEmpty()) {
                    System.out.println(result);
                    break;
                }
            }

            // asking the payers if they wanna go again
            System.out.println("Would you like to play again? (yes/no)");
            String playAgain = scan.next(); // allowing the player to put in an answer
            // if they would write in "yes" then ignore break and restart the game, otherwise exit the game
            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }
        }

        scan.close(); // closing the scan object when the game is done
    }

    // creating a method that shows the board
    private static void theBoard(char[][] board) {
        for (char[] row : board) { // a for loop that goes through every row on the board
            for (char c : row) { // a for loop that goes through every column of the board
                System.out.print(c); // printing it out
            }
            System.out.println(); // printing out a line break
        }
    }

    // creating a method to place the symbols on the board
    public static void placeChoise(char[][] board, int pos, String user) {
        char symbol = 'X'; // defines a char string to X for player 1 as a standard
        if (user.equals("player1")) { // IF it's player 1 placing on a position then put X
            player1Positions.add(pos); // adds player 1ns position
            symbol = 'X'; // putting X
        } else if (user.equals("player2")) { // or if it's player 2 placing on a position then put O
            player2Positions.add(pos); // adds players 2s position
            symbol = 'O'; // putting O as the symbol instead
        }

        switch (pos) { // creating the positions in the board and where the
                      // program should place the X or O depending on what tha players chose with a switch
            case 1 -> board[0][0] = symbol;
            case 2 -> board[0][2] = symbol;
            case 3 -> board[0][4] = symbol;
            case 4 -> board[2][0] = symbol;
            case 5 -> board[2][2] = symbol;
            case 6 -> board[2][4] = symbol;
            case 7 -> board[4][0] = symbol;
            case 8 -> board[4][2] = symbol;
            case 9 -> board[4][4] = symbol;
            // creating a default for the players not to be able to overwrite an already chosen spot
            default -> throw new IllegalArgumentException("Invalid position: " + pos);
        }
    }

    // creating a method with an array list to control the win conditions
    public static String checkTheWinner() {
        // defines the winning combinations with my array list
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

        // putting in the integer list in a for-loop that loops through the win combinations
        for (List<Integer> l : winCons) {
            if (player1Positions.containsAll(l)) { // if statement to control if player 1 has one win combo,
                                                   // and if it has then print the message
                return "Congratulations player 1 you won!";
            } else if (player2Positions.containsAll(l)) { // or if player 2 wins then return message
                return "Congratulations player 2 you won!";
            }
        }

        // another if statement in case the game is even/there's no places left. Then return the message
        if (player1Positions.size() + player2Positions.size() == 9) {
            return "Bummer, it's a tie!";
        }

        return ""; // no winner yet
    }
}