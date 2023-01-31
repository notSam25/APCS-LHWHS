import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // construct the board and start the game
        Scanner scanner = new Scanner(System.in);
        Board gameBoard = new Board(new Board.Cell[10][26], scanner);

        System.out.print("Input 'Y' if you would like to play the game: ");

        if (scanner.nextLine().equals("Y"))
            gameBoard.runGame();

        scanner.close();
    }
}