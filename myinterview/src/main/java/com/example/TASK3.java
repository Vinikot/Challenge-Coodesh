package com.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * Write a list and add an aleatory number of Strings. In the end, print out how
 * many distinct itens exists on the list.
 *
 */
public class TASK3 {

    public static void printDistinctItens(){
        HashSet<String> distinctItems = new HashSet<>();
        ArrayList<String> list = generateStringList();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            distinctItems.add(iterator.next());
        }

        int numberOfDistinctItems = distinctItems.size();
        int listSize = list.size();

        System.out.println("List size: " + listSize);
        System.out.println("The list contains " + numberOfDistinctItems + " distinct items.");
    }

    private static String generateRandomWord(Random random) {
        String[] words = {"apple", "banana", "orange", "grape", "strawberry", "blueberry", "mango", "pineapple", "kiwi", "watermelon"};
        return words[random.nextInt(words.length)];
    }

    private static ArrayList<String> generateStringList(){
        Random random = new Random();
        int numOfItens = random.nextInt(20);
        ArrayList<String> stringList = new ArrayList<>();
        for (int i = 0; i < numOfItens; i++) {
            stringList.add(generateRandomWord(random));
        }
        return stringList;
    }

}
