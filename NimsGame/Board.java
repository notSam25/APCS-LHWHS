import java.util.Scanner;
import java.util.Vector;

public class Board {
    public Board() {};
    
    public Entity getCurrentEntity() {
        return null;
    }

    public boolean isValidName(String name) {
        return name.length() > 0 ? name.matches("[a-zA-Z]*") : false;
    }

    public void runGame() {
        while(entities.size() != 2) {
            System.out.print("Input a player name: ");
            String entityName = scanner.nextLine();
            if(!isValidName(entityName)) {
                System.out.print("\nNames can only be alphabetical and > 1 length");
            } else entities.add(new Entity(entityName));
        }
        System.out.print("\nWelcome players:");
        for(Entity ent : entities) {
            System.out.print(" " + ent.getName());
        }

        while(!closeGame) {
            int randomNum = (int)(Math.round((Math.random()) + 1));
            entities.setElementAt(entities.elementAt(randomNum - 1), 0);
    
            System.out.println("\nPlayer one is: " + entities.elementAt(0).getName());
            String diffucultyString = "Diffuculty Scale:\n[3] 50 Pieces\n[2] 25 Pieces\n[1] 10 pieces";
    
            System.out.println(diffucultyString);
            int choice = 4;
            while(choice > 3) {
                System.out.print("Enter a difficulty: ");
                String input = scanner.nextLine();
                if(input.length() == 1 && input.matches("[1-3]")) {
                    choice = Integer.parseInt(input);
                } else System.out.println("\nIncorrect Input");
            }
    
            switch(choice) {
                case 1: totalPieces = 10; break;
                case 2: totalPieces = 25; break;
                case 3: totalPieces = 50; break;
            }
            
            int iteration = 0;
            while(true) {
                Entity tempEntity;
                if(iteration % 2 == 0) {
                    tempEntity = entities.elementAt(0);
                } else {
                    tempEntity = entities.elementAt(1);
                }
                
                if(totalPieces == 1) {
                    System.out.println(tempEntity.getName() + " has lost the game!");   
                    break;
                }
    
                int halfTotal = (int)(Math.ceil(totalPieces / 2));
                System.out.println("Player " + tempEntity.getName() + 
                ", please enter a number of pieces to remove(max " + halfTotal + ", total " + totalPieces + ")");
    
                int numberToRemove = 0;
                while(numberToRemove < 1) {
                    String input = scanner.nextLine();
                    if(input.length() > 0 && input.length() <= halfTotal 
                    && input.matches("[1-9]*") && Integer.parseInt(input) <= halfTotal) {
                        numberToRemove = Integer.parseInt(input);
                    } else System.out.println("Invalid input");
                }
                
                System.out.println(tempEntity.getName() + " has removed " + numberToRemove + " piece(s)");
                totalPieces -= numberToRemove;
                iteration++;
            }
            System.out.println("Would you like to play again[yes]?");
            String input = scanner.nextLine();
            if(!input.toLowerCase().contains("yes") || !input.toLowerCase().contains("y")) {
                System.out.println("Exiting game...");
                closeGame = true;
            }
        }
        scanner.close();
    }
    int totalPieces;
    Vector<Entity> entities = new Vector<Entity>();
    boolean closeGame = false;
    private Scanner scanner = new Scanner(System.in);
}