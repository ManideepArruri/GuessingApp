/*
MAIN CLASS

Cooridinates the game flow: 
1.Initialize game
2.Accept user guesses
3.Validate guesses
4.Stop when game ends

@author Developer
@version 2.0
 */

import java.util.Scanner;

public class GuessingApp{
    public static void main(String[] args) {
        System.out.println("Welcome to the Guessing App");
        GameConfig gameConfig = new GameConfig();
        gameConfig.showRules();

        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        while(attempts < gameConfig.getMaxAttempts()){
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;
            String result = GuessValidator.validateGuess(guess,gameConfig.getTargetNumber());
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