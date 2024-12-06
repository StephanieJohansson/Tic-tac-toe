// defines my board class
public class Board {
    // creating a private char array board
    private char[][] board;

    // constructor for the board class with the board layout
    public Board() {
        board = new char[][]{
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };
    }

    // method to show the board in the terminal
    public void display() {
        // loops through the whole board
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println(); // new line
        }
    }

    // a getter method that returns my char array with the board
    public char[][] getBoard() {
        return board;
    }

    // method for the players choice to be put in position
    public void placeChoice(int pos, char symbol) {
        // switch to define the pos on the board
        switch (pos) {
            case 1 -> board[0][0] = symbol;
            case 2 -> board[0][2] = symbol;
            case 3 -> board[0][4] = symbol;
            case 4 -> board[2][0] = symbol;
            case 5 -> board[2][2] = symbol;
            case 6 -> board[2][4] = symbol;
            case 7 -> board[4][0] = symbol;
            case 8 -> board[4][2] = symbol;
            case 9 -> board[4][4] = symbol;
            default -> throw new IllegalArgumentException("Invalid position: " + pos);
        }
    }
}