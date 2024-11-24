package com.ianxc.tree;

import java.util.ArrayDeque;
import java.util.function.Function;

import org.jetbrains.annotations.Nullable;

class TreeNode<T> {
    T data;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T data) {
        this(data, null, null);
    }

    public TreeNode(T data, @Nullable TreeNode<T> left, @Nullable TreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

public class BinaryTree<T> {
    private TreeNode<T> root = null;
    private int size = 0;

    public int size() {
        return this.size;
    }

    public void insertAtNextLeft(T data) {
        var newNode = new TreeNode<T>(data);

        if (root == null) {
            root = newNode;
            this.size++;
            return;
        }

        Function<TreeNode<T>, Boolean> inserter = current -> {
            if (current.left == null) {
                current.left = newNode;
                this.size++;
                return false;
            }
            if (current.right == null) {
                current.right = newNode;
                this.size++;
                return false;
            }
            return true;
        };

        bfs(inserter);
    }

    public void bfs(Function<TreeNode<T>, Boolean> processor) {
        if (root == null) {
            return;
        }

        var bfsQueue = new ArrayDeque<TreeNode<T>>();
        bfsQueue.offer(root);
        while (!bfsQueue.isEmpty()) {
            var current = bfsQueue.poll();

            var shouldContinue = processor.apply(current);
            if (!shouldContinue) {
                return;
            }

            if (current.left != null) {
                bfsQueue.offer(current.left);
            }
            if (current.right != null) {
                bfsQueue.offer(current.right);
            }
        }
    }

    /**
     * Print contents in BFS order
     */
    @Override
    public String toString() {
        var sb = new StringBuilder("BinaryTree[");
        Function<TreeNode<T>, Boolean> stringifier = current -> {
            sb.append(current.data);
            sb.append(' ');
            return true;
        };
        bfs(stringifier);

        if (sb.charAt(sb.length() - 1) == '[') {
            sb.append(']');
        } else {
            sb.setCharAt(sb.length() - 1, ']');
        }
        return sb.toString();
    }
}