package com.ianxc.trie;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class TrieNode {

    char value;

    @NotNull
    private TrieNode[] children = new TrieNode[26];

    static TrieNode of(char value) {
        return new TrieNode(value);
    }

    static TrieNode ofDummy() {
        return new TrieNode('*');
    }

    private TrieNode(char value) {
        this.value = value;
    }

    @Nullable
    TrieNode child(char c) {
        return children[c - 'a'];
    }

}

public class Trie {
    @NotNull
    private TrieNode[] children = new TrieNode[26];

    @NotNull
    Trie insert(String word) {
        return this;
    }
}