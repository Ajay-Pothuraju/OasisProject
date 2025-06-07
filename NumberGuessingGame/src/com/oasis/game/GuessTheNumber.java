package com.oasis.game;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        boolean playagain=true;
        int totalscore=0;
        int round=1;

        System.out.println("🎮 Welcome to Guess the Number Game!");
        System.out.println("I have chosen a number between 1 and 100. Try to guess it!");
        while (playagain) {
            int target = rand.nextInt(100) + 1; // random number between 1 and 100
            boolean guessedCorrectly=false;
            int attempts=5;
            
            System.out.println("Round "+round+" begins: ");
            for(int i=1;i<=attempts;i++) {
            	System.out.print("Attempt "+i+" Enter your guess: ");
            	int guess=0;

            try {
                guess = scanner.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
                scanner.next(); // clear invalid input
                i--;
                continue;
            }

            if (guess < 1 || guess > 100) {
                System.out.println("⚠️ Please enter a number between 1 and 100.");
                i--;
                continue;
            }

            if (guess == target) {
                System.out.println("🎉 Congratulations! You guessed the number.");
                int roundscore=(6-i)*10;
                totalscore+=roundscore;
                guessedCorrectly=true;
                break;
            } 
            else if (guess < target) {
                System.out.println("🔻 Too low.");
            } 
            else {
                System.out.println("🔺 Too high.");
            }
            }
            if(!guessedCorrectly) {
            	System.out.println("You used all attempts.The number is: "+target);
            }
            System.out.println("🎯 Total Score: " + totalscore);
            System.out.println("If you want to try again(yes/no): ");
            String answer=scanner.next().toLowerCase();
            
            if(answer.equals("yes")) {
            	round++;
            	System.out.println();
            }
            else {
            	playagain=false;
            	System.out.println("\n👋 Thanks for playing! Final Score: " + totalscore);
            }
        }
        

        scanner.close();
    }
}
