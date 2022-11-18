
import java.io.File;
import java.util.Scanner;

public class Hangman {
    public Hangman() {
        run();
    }

    private void newPhrase() {
        int fileLineCount = 0;
        try {
            Scanner sc = new Scanner(new File("Hangman/phrases.txt"));
            while (sc.hasNextLine()) {
                sc.nextLine();
                fileLineCount++;
            }
        } catch (Exception e) {
            System.out.println("phrases.txt");
        }
        int randomInt = (int) ((Math.random() * fileLineCount) + 1);
        try {
            int count = 0;
            Scanner sc = new Scanner(new File("Hangman/phrases.txt"));
            while (sc.hasNextLine()) {
                count++;
                String temp = sc.nextLine().trim();
                if (count == randomInt)
                    phrase = temp;
            }
        } catch (Exception e) {
            System.out.println("phrases.txt");
        }
    }

    private String startNewPhrase() {
        newPhrase();
        String currentPhrase = "";
        for (char c : phrase.toCharArray())
            currentPhrase += c == ' ' ? ' ' : '_';
        return currentPhrase;
    }

    private boolean validInput(String input) {
        return input.length() == 1 ? input.matches("[a-zA-Z]") : false;

    }

    private void resetGame() {
        usedChars = new char[26];
        phraseMistakes = 0;
    }

    private void run() {
        System.out.println("Welcome to hangman!");

        // set the phrase we modify to chars we can't see
        String currentPhrase = startNewPhrase();
        Scanner cin = new Scanner(System.in);
        while (true) {

            char currentChar;
            String currentString = "";
            while (!validInput(currentString)) {
                System.out.print(
                        currentPhrase + "\nMistakes(" + this.phraseMistakes + "/7)" + "\nInput a char to guess: ");
                currentString = cin.nextLine();
            }
            currentChar = currentString.toCharArray()[0];

            // add letter to used letters array
            boolean skip = false;
            for (int i = 0; i < usedChars.length; i++) {
                char c = usedChars[i];
                if (c != 0 && c == currentChar && !skip) {
                    System.out.println("Allready used this letter");
                    skip = true;
                    break;
                } else if (c == 0) {
                    usedChars[i] = currentChar;
                    break;
                }
            }

            if (!skip) {
                // append the string with guessed letter
                char tempCharPhrase[] = currentPhrase.toCharArray();
                for (int i = 0; i < this.phrase.length(); i++) {
                    for (char g : this.usedChars) {
                        if (g != 0 && g == phrase.charAt(i)) {
                            tempCharPhrase[i] = g;
                        }
                    }
                }
                currentPhrase = String.valueOf(tempCharPhrase);

                // check if char was added
                if (!currentPhrase.contains(currentString))
                    phraseMistakes++;

                // test if we win or not
                if (currentPhrase.equals(this.phrase)) {
                    System.out.println("You win! play again(y/n)?");
                    if (cin.nextLine().equals("y")) {
                        currentPhrase = startNewPhrase();
                        resetGame();
                    } else
                        break;
                } else if (phraseMistakes == 7) {
                    System.out.println("You lose! the word was: " + this.phrase + "\nplay again(y/n)?");
                    if (cin.nextLine().equals("y")) {
                        currentPhrase = startNewPhrase();
                        resetGame();
                    } else
                        break;
                }

            }
        }
        cin.close();
    }

    private int phraseMistakes = 0;
    private char[] usedChars = new char[26];
    private String phrase;
}
