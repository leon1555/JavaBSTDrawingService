package com.tree;

public class TreeHeight {

    static <T> int getHeight(final BinaryTreeNode<T> parent)
    {
        // recursive termination
        if (parent == null)
            return 0;

        // recursive descent
        final int leftHeight = getHeight(parent.left);
        final int rightHeight = getHeight(parent.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
