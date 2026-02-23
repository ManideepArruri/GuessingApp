/*
MAIN CLASS

Use Case 5 : Game Result Storage

This class coordinates the complete game flow
and persists the fianl result after completion.

Responsibilites : 
-Initialize game configuration
-Accept and validate user guesses
-Generate hints when applicable
-Store game result at end

@author Developer
@version 5.0
 */

import java.util.Scanner;

public class GuessingApp{
    public static void main(String[] args) throws InvalidInputException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("====================");
        System.out.println("Welcome to the Guessing App");
        System.out.println("====================\n");
        /*
        Player Name is capture once 
        and stored along with game results.
         */
        System.out.print("Enter Player Name: ");
        String player = scanner.nextLine();
        GameConfig gameConfig = new GameConfig();
        gameConfig.showRules();
        int attempts = 0, hintsUsed = 0;
        //Tracks whether the player successfully guessed the number.
        boolean win = false;
        //Game loop runs until the player exhausts the maximum attempts.
        while(attempts < gameConfig.getMaxAttempts()){
            System.out.print("Enter your guess: ");
            int guess = ValidationService.validateInput(scanner.nextLine());
            attempts++;
            String result = GuessValidator.validateGuess(guess,gameConfig.getTargetNumber());
            /*
            A hint is generated only after
            an incorrect guess and within 
            the allowed hint limit.
            */
            if(!"CORRECT".equals(result) && hintsUsed < gameConfig.getMaxHints()){
                hintsUsed++;
                System.out.println(HintService.generateHint(gameConfig.getTargetNumber(),hintsUsed));
            }
            System.out.println(result);
            /*
            Stop the loop immediately
            if correct number is guessed.
             */
            if("CORRECT".equals(result)){
                break;
            }
        }
        StorageService.saveResult(player,attempts,win);
        scanner.close();
    }
}