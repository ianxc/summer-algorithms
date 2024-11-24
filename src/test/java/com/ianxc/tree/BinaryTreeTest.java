package com.ianxc.tree;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

public class BinaryTreeTest {
    @Test
    void testToStringEmpty() {
        var tree = new BinaryTree<Integer>();

        assertThat(tree).asString().isEqualTo("BinaryTree[]");
    }

    @Test
    void testInsert() {
        var tree = new BinaryTree<Integer>();

        var toInsert = Arrays.array(1, 7, 2, 7, 1, 8);
        for (var n : toInsert) {
            tree.insertAtNextLeft(n);
        }

        assertThat(tree).asString().isEqualTo("BinaryTree[1 7 2 7 1 8]");
        assertThat(tree.size()).isEqualTo(6);
    }
}
