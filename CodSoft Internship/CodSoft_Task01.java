import java.util.Random;
import java.util.Scanner;

class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minNum = 1;
        int maxNum = 100;
        int numberOfAttempts = 10;
        int score = 0;

        System.out.println("*** Random Number Guessing Game! ***");
        System.out.println("You have " + numberOfAttempts + " attempts to guess the number between " + minNum + " and "
                + maxNum + ".");

        boolean playAgain = true;

        while (playAgain) {

            int attempts = 0;
            boolean guessedCorrectly = false;
            int targetNumber = random.nextInt(maxNum - minNum + 1) + minNum;

            while (attempts < numberOfAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess < minNum || userGuess > maxNum) {
                    System.out.println("Your guess is out of range.");
                } else if (userGuess < targetNumber) {
                    System.out.println("Your guess is too low :(");
                } else if (userGuess > targetNumber) {
                    System.out.println("Your guess is too high :(");
                } else {
                    System.out.println(
                            "Congratulations!!!! You've guessed the correct number in " + attempts + " attempts :)");
                    score++;
                    guessedCorrectly = true;
                    break;
                }

                if (attempts == numberOfAttempts) {
                    System.out.println("You've run out of attempts :| The correct number was " + targetNumber);
                }
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainChoice = scanner.next().toLowerCase();

            if (!playAgainChoice.equals("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Thanks for playing :p Your score is: " + score);
        scanner.close();
    }
}
