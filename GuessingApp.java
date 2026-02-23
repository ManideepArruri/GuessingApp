/*
MAIN CLASS

Use Case 4 : Error Handling & Validation

This class coordinates the game execution while ensuring
all user inputs are safely validated before processing.

Responsibilites : 
-Initialize game configuration
-Accept user input
-Validate input using ValidationService
-Handle game flow without crashing on invalid input

@author Developer
@version 4.0
 */

import java.util.Scanner;

public class GuessingApp{
    public static void main(String[] args) throws InvalidInputException {
        System.out.println("Welcome to the Guessing App");
        GameConfig gameConfig = new GameConfig();
        gameConfig.showRules();

        Scanner scanner = new Scanner(System.in);
        int attempts = 0, hintsUsed = 0;
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
        scanner.close();
    }
}