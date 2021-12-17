package com.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// visual rendering of balanced binary search tree
// adapted from M. Inden, Java Challenges (O'Reilly)
public class TreePrinter
{
    static <T> int getHeight(final BinaryTreeNode<T> parent)
    {
        if (parent == null)
            return 0;

        final int leftHeight = getHeight(parent.left);
        final int rightHeight = getHeight(parent.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    static int subtreeWidth(final int height)
    {
        if (height <= 0)
            return 0;

        final int leafWidth = 3;
        final int spacing = 3;

        final int maxNumOfLeaves = (int) Math.pow(2, height - 1);
        final int widthOfTree = maxNumOfLeaves * leafWidth + (maxNumOfLeaves - 1) * spacing;
        final int widthOfSubtree = (widthOfTree - spacing) / 2;

        return widthOfSubtree;
    }


    static <T> String drawNode(final BinaryTreeNode<T> currentNode, final int lineLength)
    {
        String strNode = "   ";
        strNode += spacing(lineLength);
        strNode += stringifyNodeValue(currentNode);
        strNode += spacing(lineLength);

        return strNode;
    }

    static <T> String stringifyNodeValue(final BinaryTreeNode<T> node)
    {
        if (node == null || node.item == null)
            return "   ";

        final String nodeValue = "" + node.item;
        if (nodeValue.length() == 1)
            return " " + nodeValue + " ";
        if (nodeValue.length() == 2)
            return nodeValue + " ";

        return nodeValue.substring(0, 3);
    }

    static String spacing(final int lineLength)
    {
        return " ".repeat(lineLength);
    }

    static <T> String drawConnections(final BinaryTreeNode<T> currentNode,
                                      final int lineLength)
    {
        if (currentNode == null)
            return "   " + spacing(lineLength) + "   " + spacing(lineLength) + "   ";

        String connection = drawLeftConnectionPart(currentNode, lineLength);
        connection += drawJunction(currentNode);
        connection += drawRightConnectionPart(currentNode, lineLength);
        return connection;
    }

    static <T> String drawLeftConnectionPart(final BinaryTreeNode<T> currentNode,
                                             final int lineLength)
    {
        if (currentNode.left == null)
            return "   " + spacing(lineLength);

        return " |-" + drawLine(lineLength);
    }

    static <T> String drawJunction(final BinaryTreeNode<T> currentNode)
    {
        if (currentNode.left == null && currentNode.right == null)
            return "   ";
        else if (currentNode.left == null)
            return " +-";
        else if (currentNode.right == null)
            return "-+ ";

        return "-+-";
    }

    static <T> String drawRightConnectionPart(final BinaryTreeNode<T> currentNode,
                                              final int lineLength)
    {
        if (currentNode.right == null)
            return spacing(lineLength) + "   ";

        return drawLine(lineLength) + "-| ";
    }

    static String drawLine(final int lineLength)
    {
        return "-".repeat(lineLength);
    }

    static String spacingBetweenNodes(final int treeHeight, final int level)
    {
        final int spacingLength = subtreeWidth(treeHeight - level);
        String spacing = " ".repeat(spacingLength);
        if (spacingLength > 0)
            spacing += "   ";
        return spacing;
    }

    static String spacingBetweenConnections(final int treeHeight, final int level)
    {
        final int spacingLength = subtreeWidth(treeHeight - level);
        return " ".repeat(spacingLength);
    }

    static class Pair<T1, T2>
    {
        final T1 first;

        final T2 second;

        public Pair(final T1 left, final T2 right)
        {
            this.first = left;
            this.second = right;
        }
    }

    public static <T> void nicePrint(final BinaryTreeNode<T> startNode)
    {
        if (startNode == null)
            return;

        final int treeHeight = getHeight(startNode);
        final List<String> lines = new ArrayList<>();

        int level = 0;
        String nodeLine = "";
        String connectionLine = "";

        final Queue<Pair<BinaryTreeNode<T>, Integer>> toProcess = new LinkedList<>();
        toProcess.offer(new Pair<>(startNode, 0));

        while (!toProcess.isEmpty() && level < treeHeight)
        {
            // Levelorder
            final Pair<BinaryTreeNode<T>, Integer> current = toProcess.poll();
            final BinaryTreeNode<T> currentNode = current.first;
            final int nodelevel = current.second;

            // action
            int lineLength = subtreeWidth(treeHeight - 1 - level);

            // change in level
            if (level != nodelevel)
            {
                level = nodelevel;
                lineLength = subtreeWidth(treeHeight - 1 - level);

                lines.add(nodeLine.stripTrailing());
                lines.add(connectionLine.stripTrailing());
                nodeLine = "";
                connectionLine = "";

                // Indent previous lines to the right
                for (int i = 0; i < lines.size(); i++)
                {
                    lines.set(i, "   " + spacing(lineLength) + lines.get(i));
                }
            }

            nodeLine += drawNode(currentNode, lineLength);
            nodeLine += spacingBetweenNodes(treeHeight, level);
            connectionLine += drawConnections(currentNode, lineLength);
            connectionLine += spacingBetweenConnections(treeHeight, level);

            // Levelorder
            if (currentNode != null)
            {
                toProcess.offer(new Pair<>(currentNode.left, level + 1));
                toProcess.offer(new Pair<>(currentNode.right, level + 1));
            }
            else
            {
                // artificial placeholders for correct layout
                toProcess.offer(new Pair<>(null, level + 1));
                toProcess.offer(new Pair<>(null, level + 1));
            }
        }

        lines.forEach(System.out::println);
    }
}