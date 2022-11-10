import java.util.Scanner;
import java.io.File;

public class Board {

  String loadPhrase() {
    String tempPhrase = "";

    int numOfLines = 0;

    try {
      Scanner sc = new Scanner(new File("Hangman/phrases.txt"));
      while (sc.hasNextLine()) {
        tempPhrase = sc.nextLine().trim();
        numOfLines++;
      }
    } catch (Exception e) {
      System.out.println("Error reading or parsing phrases.txt");
    }

    int randomInt = (int) ((Math.random() * numOfLines) + 1);

    try {
      int count = 0;
      Scanner sc = new Scanner(new File("Hangman/phrases.txt"));
      while (sc.hasNextLine()) {
        count++;
        String temp = sc.nextLine().trim();
        if (count == randomInt) {
          tempPhrase = temp;
        }
      }
    } catch (Exception e) {
      System.out.println("Error reading or parsing phrases.txt");
    }

    return tempPhrase;
  }

  private boolean isValidInput(String input) {
    return input.length() == 1 ? input.matches("[a-zA-Z]*") : false;
  }

  public void run(String word) {
    System.out.println(word);
    String curWord = "";
    for (char c : word.toCharArray()) {
      curWord += c == ' ' ? '*' : '_';
    }

    Scanner sc = new Scanner(System.in);
    System.out.println("Welcome to hangman!");

    while (true) {
      if (totalMistakes == 7) {
        System.out.println("You lost! too many mistakes");
        break;
      }

      System.out.println(curWord + "\nMistakes(" + totalMistakes + "/7)");

      String input = "";
      while (input.length() != 1 || !isValidInput(input)) {
        System.out.print("please enter a character to guess: ");
        input = sc.nextLine();
      }

      for (int i = 0; i < useChars.length; i++) {
        if (useChars[i] == 0 && useChars[i] != input.toCharArray()[0]) {
          useChars[i] = input.toCharArray()[0];
          break;
        }
      }

      char[] curWordArr = curWord.toCharArray();
      for (int i = 0; i < word.length(); i++) {
        char curChar = word.charAt(i);

        for (int j = 0; j < useChars.length; j++) {
          if (useChars[j] != 0 && useChars[j] == curChar)
            curWordArr[i] = curChar;
        }
      }
      curWord = String.valueOf(curWordArr);

      if (!curWord.contains(input)) {
        totalMistakes++;
        System.out.println("Incorrect Guess!");
      }

      if (word.replace(' ', '*').equals(curWord)) {
        System.out.println("You won the game!");
        break;
      }
    }
    sc.close();
  }

  private int totalMistakes = 0;
  private static char useChars[] = new char[26];
}