package com.tree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MainMenu {
    public static void main(final String[] args) throws Exception {
        System.out.println("Welcome to the Java Tree Drawing Service!");
        Thread.sleep(1000);
        System.out.println("This program will take in a series of integers from the user and draw a Balanced Binary " +
                "Search Tree!");
        Thread.sleep(1000);
        while(true) {
            // Program
            int arrayLength = 0;
            Scanner sc = new Scanner(System.in);
            while(true) {
                System.out.print("Enter the total number of integers (nodes) that you want in the tree: ");
                if(!sc.hasNextInt()) {
                    System.out.println("You must enter an integer!");
                    sc.next();
                } else {
                    arrayLength = sc.nextInt();
                    break;
                }
            }
            int [] inputArray = new int[arrayLength];
            System.out.println("Now enter the integers (one at a time followed each time by the return key): ");
            for(int i=0; i<arrayLength; i++)
            {
                while(true) {
                    if (!sc.hasNextInt()) {
                        System.out.println("You must enter an integer!");
                        sc.next();
                    } else {
                        inputArray[i] = sc.nextInt();
                        break;
                    }
                }
            }
            if(!duplicates(inputArray)){
                RestClient.postInput(inputArray);
                Arrays.sort(inputArray);
                final int[][] inputs = {inputArray};
                System.out.println("Here is your tree:");
                for (int[] values : inputs)
                {
                    final BinaryTreeNode<Integer> root = BSTReconstruction.reconstruct(values);
                    TreePrinter.nicePrint(root);
                }
                System.out.println("Thank you for using the Java Tree Service!");
                break;
            }
            else {
                System.out.println("Please ensure that there are no duplicate integers.");
                Thread.sleep(1000);
            }
        }
    }
    // checks integers inputted by user for duplicates
    static boolean duplicates(final int[] integers)
    {
        Set<Integer> lump = new HashSet<Integer>();
        for (int i : integers)
        {
            if (lump.contains(i)) return true;
            lump.add(i);
        }
        return false;
    }
}
