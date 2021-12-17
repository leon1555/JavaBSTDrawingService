package com.tree;
import java.util.*;

public class BSTReconstruction
{
    // takes in a sorted array of integers and arranges them into nodes of a balanced binary search tree (preorder traversal)
    // adapted from M. Inden, Java Challenges (O'Reilly)
    static BinaryTreeNode<Integer> reconstruct(final int[] values)
    {
        if (values.length == 0)
        {
            return null;
        }

        final int midIdx = values.length / 2;

        final int midValue = values[midIdx];
        final BinaryTreeNode<Integer> newNode = new BinaryTreeNode<>(midValue);

        if (values.length == 1)
        {
            return newNode;
        }

        final int[] leftPart = Arrays.copyOfRange(values, 0, midIdx);
        final int[] rightPart = Arrays.copyOfRange(values, midIdx + 1,
                values.length);

        newNode.left = reconstruct(leftPart);
        newNode.right = reconstruct(rightPart);

        return newNode;
    }
}
