package com.tree;

public class TreeHeight {

    // helper method for TreePrinter
    static <T> int getHeight(final BinaryTreeNode<T> parent)
    {
        if (parent == null)
            return 0;

        final int leftHeight = getHeight(parent.left);
        final int rightHeight = getHeight(parent.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
