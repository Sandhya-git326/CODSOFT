import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        final int MIN = 1;
        final int MAX = 100;
        final int MAX_ATTEMPTS = 10;
        int totalRounds = 0;
        int roundsWon = 0;
        int totalScore = 0;
        System.out.println("---------------------------------");
        System.out.println("       NUMBER GUESSING GAME");
        System.out.println("---------------------------------");
        boolean playAgain = true;
        while (playAgain) {
            totalRounds++;
            int secretNumber = random.nextInt(MAX - MIN + 1) + MIN;
            int attempts = 0;
            boolean guessedCorrectly = false;
            System.out.println("\nRound " + totalRounds);
            System.out.println("Guess the number between " + MIN + " and " + MAX);
            System.out.println("You have " + MAX_ATTEMPTS + " attempts.\n");
            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Enter your guess: ");
                if (!sc.hasNextInt()) {
                    System.out.println("Please enter a valid number.");
                    sc.next();
                    continue;
                }
                int guess = sc.nextInt();
                attempts++;
                if (guess == secretNumber) {
                    guessedCorrectly = true;
                    roundsWon++;
                    int roundScore = (MAX_ATTEMPTS - attempts + 1) * 10;
                    totalScore += roundScore;
                    System.out.println("\nCongratulations!");
                    System.out.println("You guessed the correct number: " + secretNumber);
                    System.out.println("Attempts used: " + attempts);
                    System.out.println("Round Score: " + roundScore);
                    break;
                } else if (guess < secretNumber) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }
                System.out.println("Attempts remaining: " + (MAX_ATTEMPTS - attempts));
            }
            if (!guessedCorrectly) {
                System.out.println("\nYou lost this round.");
                System.out.println("The correct number was: " + secretNumber);
            }
            System.out.println("\n---------- SCOREBOARD -----------");
            System.out.println("Rounds Played : " + totalRounds);
            System.out.println("Rounds Won    : " + roundsWon);
            System.out.println("Total Score   : " + totalScore);
            System.out.println("---------------------------------");
            System.out.print("\nDo you want to play again? (Y/N): ");
            String choice = sc.next();
            if (!choice.equalsIgnoreCase("Y")) {
                playAgain = false;
            }
        }
        System.out.println("\n-------------------------------");
        System.out.println("          GAME OVER");
        System.out.println("---------------------------------");
        System.out.println("Total Rounds Played : " + totalRounds);
        System.out.println("Total Rounds Won    : " + roundsWon);
        System.out.println("Final Score         : " + totalScore);
        System.out.println("Thank you for playing!");
        sc.close();
    }
}
