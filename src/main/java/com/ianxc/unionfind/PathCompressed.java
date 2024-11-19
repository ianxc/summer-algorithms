package com.ianxc.unionfind;

import java.util.Arrays;

public class PathCompressed {
    private int[] root;

    public PathCompressed(int size) {
        this.root = new int[size];
        for (int i = 0; i < size; i++) {
            // Initially, all nodes are disjoint
            this.root[i] = i;
        }
    }

    public int find(int x) {
        while (x != root[x]) {
            x = root[x];
        }
        return x;
    }

    public void union(int x, int y) {
        var rootX = find(x);
        var rootY = find(y);
        if (rootX != rootY) {
            root[rootY] = root[rootX];
        }
    }

    public int findCompressed(int x) {
        if (x == root[x]) {
            return x;
        }

        var actualRoot = findCompressed(root[x]);
        root[x] = actualRoot;
        return actualRoot;
    }

    public void unionCompressed(int x, int y) {
        var rootX = findCompressed(x);
        var rootY = findCompressed(y);

        if (rootX != rootY) {
            root[rootY] = rootX;
        }
    }

    public boolean connected(int x, int y) {
        return findCompressed(x) == findCompressed(y);
    }

    @Override
    public String toString() {
        return String.format("PathCompressed[size=%d, root=%s]",
                root.length, Arrays.toString(root));
    }
}
