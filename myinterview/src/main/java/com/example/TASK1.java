package com.example;
import java.util.Scanner;
/**
 * 
 *
 * Task here is to implement a function that says if a given string is
 * palindrome.
 * 
 * 
 * 
 * Definition=> A palindrome is a word, phrase, number, or other sequence of
 * characters which reads the same backward as forward, such as madam or
 * racecar.
 */
public class TASK1 {

    public static void isPalindrome() {
        String inputString;
        Scanner read = new Scanner(System.in);
        StringBuilder reverseString;

        try {
            System.out.printf("Enter a string: ");
            inputString = read.next().toLowerCase();

            reverseString = new StringBuilder(inputString);
            reverseString.reverse();
            System.out.println(reverseString);

            if (inputString.equals(reverseString.toString())){
                System.out.println("The given string is a palindrome.");
            }else {
                System.out.println("The given string is not a palindrome.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
 
}