package com.tree;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BSTTest {
    final int[] input1 = { 1, 2, 3, 4, 5, 6, 7 };
    boolean expected1 = false;
    final int [] input2 = { 1, 1, 2, 3, 5, 8, 13};
    boolean expected2 = true;

    @Test
    public void duplicatesTest() {
        Assert.assertTrue(MainMenu.duplicates(input1) == expected1);
        Assert.assertTrue(MainMenu.duplicates(input2) == expected2);
    }
}
