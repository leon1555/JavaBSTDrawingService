package com.tree;

public class BinaryTreeNode<T>
{
    public final T item;

    public BinaryTreeNode<T> left;

    public BinaryTreeNode<T> right;

    public BinaryTreeNode(final T item)
    {
        this.item = item;
    }

}
