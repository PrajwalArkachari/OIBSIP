import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int maxAttempts = 3;
        int round = 1;
        boolean playAgain = true;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I am thinking of a number between 1 and 100.");

        while (playAgain) {
            int secretNumber = random.nextInt(100) + 1;
            int tries = 0;
            boolean guessed = false;

            System.out.println("Round " + round);

            while (!guessed && tries < maxAttempts) {
                System.out.print("Guess the number: ");
                int guess = scanner.nextInt();
                tries++;

                if (guess == secretNumber) {
                    System.out.println("Congratulations! You guessed the number in " + tries + " tries.");
                    guessed = true;
                } else if (guess < secretNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!guessed) {
                System.out.println("You have used up all your attempts. The number was " + secretNumber + ".");
            }

            System.out.print("Do you want to play again? (y/n) ");
            String playAgainInput = scanner.next();

            if (playAgainInput.equalsIgnoreCase("n")) {
                playAgain = false;
                System.out.println("Thank you for playing the game.\n Have a nice day!!!....(*__*)");
            } else {
                round++;
            }
        }

//        scanner.close();
    }
}
