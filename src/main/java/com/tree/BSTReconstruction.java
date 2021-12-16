package com.tree;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;

public class BSTReconstruction
{

    static BinaryTreeNode<Integer> reconstruct(final int[] values)
    {
        // recursive termination
        if (values.length == 0)
        {
            return null;
        }

        final int midIdx = values.length / 2;

        final int midValue = values[midIdx];
        final BinaryTreeNode<Integer> newNode = new BinaryTreeNode<>(midValue);

        // recursive termination
        if (values.length == 1)
        {
            return newNode;
        }

        // recursive descent
        final int[] leftPart = Arrays.copyOfRange(values, 0, midIdx);
        final int[] rightPart = Arrays.copyOfRange(values, midIdx + 1,
                values.length);

        newNode.left = reconstruct(leftPart);
        newNode.right = reconstruct(rightPart);

        return newNode;
    }

    static <T> BinaryTreeNode<T> reconstruct(final List<T> preorderValues, final List<T> inorderValues)
    {
        if (preorderValues.size() != inorderValues.size())
            throw new IllegalStateException("inputs differ in length");

        // recursive termination
        if (preorderValues.size() == 0 || inorderValues.size() == 0)
            return null;

        final T rootValue = preorderValues.get(0);
        final BinaryTreeNode<T> root = new BinaryTreeNode<>(rootValue);

        // recursive termination
        if (preorderValues.size() == 1 && inorderValues.size() == 1)
        {
            return root;
        }

        // recursive descent
        final int index = inorderValues.indexOf(rootValue);

        // left and right part for preorder
        root.left = reconstruct(preorderValues.subList(1, index + 1), inorderValues.subList(0, index));
        root.right = reconstruct(preorderValues.subList(index + 1, preorderValues.size()),
                inorderValues.subList(index + 1, inorderValues.size()));

        return root;
    }

    static <T> BinaryTreeNode<T> reconstructClearer(final List<T> preorderValues, final List<T> inorderValues)
    {
        // recursive termination
        if (preorderValues.size() == 0 || inorderValues.size() == 0)
        {
            return null;
        }

        final T rootValue = preorderValues.get(0);
        final BinaryTreeNode<T> root = new BinaryTreeNode<>(rootValue);

        // recursive termination
        if (preorderValues.size() == 1 && inorderValues.size() == 1)
        {
            return root;
        }

        // recursive descent
        final int index = inorderValues.indexOf(rootValue);

        // left and right part for inorder
        final List<T> leftInoder = inorderValues.subList(0, index);
        final List<T> rightInoder = inorderValues.subList(index + 1, inorderValues.size());

        // left and right part for preorder
        final List<T> leftPreorder = preorderValues.subList(1, 1 + index);
        final List<T> rightPreorder = preorderValues.subList(index + 1, preorderValues.size());

        root.left = reconstruct(leftPreorder, leftInoder);
        root.right = reconstruct(rightPreorder, rightInoder);

        return root;
    }

    private static void printInfo(final BinaryTreeNode<Integer> root)
    {
        TreePrinter.nicePrint(root);
        System.out.println("Root:  " + root);
        System.out.println("Left:  " + root.left);
        System.out.println("Right: " + root.right);
        System.out.println();
    }
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

    public static void main(final String[] args) throws Exception {
        System.out.println("Welcome to the Java Tree Drawing Service!");
        Thread.sleep(1000);
        System.out.println("This program will take in a series of integers from the user and draw a Binary Balanced " +
                "Search Tree!");
        Thread.sleep(1000);
        while(true) {
            // Program
            System.out.print("Enter the total number of integers (nodes) that you want in the tree: ");
            int arrayLength = 0;
            Scanner sc = new Scanner(System.in);
            if(!sc.hasNextInt()) {
                throw new NullPointerException("You must enter an integer!");
            } else {
                arrayLength = sc.nextInt();
            }

            System.out.println("Now enter the integers (one at a time followed each time by the return key): ");
            int [] inputArray = new int[arrayLength];
            for(int i=0; i<arrayLength; i++)
            {
                if(!sc.hasNextInt()) {
                    throw new Exception("You must enter an integer!");
                } else {
                    inputArray[i]=sc.nextInt();
                }

            }
            if(!duplicates(inputArray)){
                RestClient.postInput(inputArray);
                Arrays.sort(inputArray);
                final int[][] inputs = {inputArray};
                System.out.println("Here is your tree:");
                for (int[] values : inputs)
                {
                    final BinaryTreeNode<Integer> root = reconstruct(values);
                    printInfo(root);
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
}
