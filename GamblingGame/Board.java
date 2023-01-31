import java.util.Scanner;

public class Board {

    // The Cell for every game space
    // NOTE: this never had to be a Cell, but from the previous game idea I had this
    // was faster to implement[I could C&P this :skull:]
    public class Cell {
        public Cell(char cellType) {
            this.cellType = cellType;
        }

        public char getCellType() {
            return this.cellType;
        }

        private final char cellType;
    }

    /*
     * @param boardSize, This is the size and starting board.
     * 
     * @param scanner, The scanner for inputs in the game loop
     */
    public Board(Cell[][] boardSize, Scanner scanner) {
        this.boardPieces = new Cell[boardSize.length][boardSize[0].length];
        this.visibleBoardPieces = new Cell[boardSize.length][boardSize[0].length];
        this.scanner = scanner;
    }

    /*
     * Clears the console
     */
    private void clearConsole() {
        System.out.println("\033c");
    }

    /*
     * @return Return's the height of the game board in array format
     */
    public int getBoardHeight() {
        return this.boardPieces.length;
    }

    /*
     * @return Return's the width of the game board in array format
     */
    public int getBoardWidth() {
        return this.boardPieces[0].length;
    }

    /*
     * The method that handles all the game logic
     */
    public void runGame() {

        // Start our game by adding all of the game pieces to the board
        for (int c = 0; c < this.boardPieces.length; c++) {
            for (int r = 0; r < this.boardPieces[c].length; r++) {
                // This value will be overriten by the char for the piece
                char boardPieceType;
                switch ((int) (Math.random() * 6) + 1) {
                    case 1:
                        boardPieceType = '*';
                        break;
                    case 2:
                        boardPieceType = '@';
                        break;
                    case 3:
                        boardPieceType = 'O';
                        break;
                    case 4:
                        boardPieceType = '%';
                        break;
                    case 5:
                        boardPieceType = '$';
                        break;
                    default:
                        boardPieceType = 'O';
                        break;
                }
                // All of the visible pieces start out as a blank char '#' and become the real
                // values when the input is valid
                this.visibleBoardPieces[c][r] = new Cell('#');
                // This sets the board piece to the random char from earlier
                this.boardPieces[c][r] = new Cell(boardPieceType);
            }
        }

        // Our game loop
        while (true) {

            // Check if our health is gone
            if (this.healthRemaning == 0) {
                System.out.println("You have lost, final score: " + this.score);
                break;
            }

            // Clear the console to make it look nicer
            this.clearConsole();

            // Draw all the visible chars to the console
            for (int c = 0; c < this.boardPieces.length; c++) {

                // Draw the guide Letters
                if (c == 0) {
                    System.out.print("   ");
                    for (int i = 0; i < this.boardPieces[0].length; i++)
                        System.out.print(alphabet[i] + " ");
                    System.out.println();
                }

                for (int r = 0; r < this.boardPieces[0].length; r++) {
                    // Draw the guide numbers
                    if (r == 0)
                        System.out.print((c + 1 < 10) ? ((c + 1) + "  ") : ((c + 1) + " "));

                    // Draw the visible piece char
                    System.out.print(this.visibleBoardPieces[c][r].getCellType() + " ");
                }
                System.out.println();
            }

            // The display values for the user
            System.out.println("\n\nHealth: " + this.healthRemaning + "\nScore: " + this.score);
            System.out.print("Enter a coordinate to guess(Capital Letter + Number): ");

            // This handles the input for the coordinate
            String input = scanner.nextLine();
            if (input.length() <= 3 && input.length() >= 2) {

                // 64 is 1 lower than A, so if we subtract it from the input we can get the
                // width
                int width = input.charAt(0) - 64;

                // Heigh is just parsing the input for the number
                int height = Integer.parseInt("" + (input.length() == 3 ? input.substring(1, 3) : input.charAt(1)));

                // Set the piece to the new value, -1 because arrays start at 0
                this.visibleBoardPieces[height - 1][width - 1] = this.boardPieces[height - 1][width - 1];

                // Handle the score for the new piece
                switch (this.visibleBoardPieces[height - 1][width - 1].getCellType()) {
                    case '*':
                        this.score += 5;
                        break;
                    case '@':
                        this.score += 3;
                        break;
                    case 'O':
                        this.healthRemaning--;
                        break;
                    case '%':
                        this.score += 2;
                        break;
                    case '$':
                        this.score += 10;
                        break;
                }
            }
        }
    }

    private Cell[][] visibleBoardPieces;
    private final Cell[][] boardPieces;
    private final Scanner scanner;
    private int score = 0;
    private int healthRemaning = 3;
    private final char[] alphabet = {
            'A',
            'B',
            'C',
            'D',
            'E',
            'F',
            'G',
            'H',
            'I',
            'J',
            'K',
            'L',
            'M',
            'N',
            'O',
            'P',
            'Q',
            'R',
            'S',
            'T',
            'U',
            'V',
            'W',
            'X',
            'Y',
            'Z'
    };
}
