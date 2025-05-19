
import java.util.Scanner;

public class Quizgame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        System.out.println("Welcome to the Quiz Game!\n");

        // Question 1
        System.out.println("1. What is the capital of India?");
        System.out.println("a) Mumbai");
        System.out.println("b) New Delhi");
        System.out.println("c) Bengaluru");
        System.out.print("Your answer: ");
        String answer1 = scanner.nextLine().trim().toLowerCase();
        if (answer1.equals("b") || answer1.contains("new delhi")) {
            score++;
            System.out.println("Correct!\n");
        } else {
            System.out.println("Wrong! The correct answer is New Delhi.\n");
        }

        // Question 2
        System.out.println("2. Who invented Java?");
        System.out.println("a) James Gosling");
        System.out.println("b) Dennis Ritchie");
        System.out.println("c) Bjarne Stroustrup");
        System.out.print("Your answer: ");
        String answer2 = scanner.nextLine().trim().toLowerCase();
        if (answer2.equals("a") || answer2.contains("james gosling")) {
            score++;
            System.out.println("Correct!\n");
        } else {
            System.out.println("Wrong! The correct answer is James Gosling.\n");
        }

        // Question 3
        System.out.println("3. What is 5 * 6?");
        System.out.println("a) 30");
        System.out.println("b) 20");
        System.out.println("c) 25");
        System.out.print("Your answer: ");
        String answer3 = scanner.nextLine().trim().toLowerCase();
        if (answer3.equals("a") || answer3.equals("30")) {
            score++;
            System.out.println("Correct!\n");
        } else {
            System.out.println("Wrong! The correct answer is 30.\n");
        }

        System.out.println("Quiz Over!");
        System.out.println("Your final score: " + score + "/3");

        scanner.close();
    }
}
