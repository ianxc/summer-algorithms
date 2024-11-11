package com.ianxc.list;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class Node<T> {

    T value;
    Node<T> next;

    Node(T value) {
        this.value = value;
        this.next = null;
    }
}

public class SinglyLinkedList<T> {

    @Nullable
    private Node<T> head = null;

    private int size = 0;

    boolean isEmpty() {
        return this.size == 0;
    }

    int size() {
        return this.size;
    }

    void pushFront(@NotNull T value) {
        var newHead = new Node<T>(value);
        newHead.next = this.head;
        this.head = newHead;
        this.size++;
    }

    Optional<T> popFront() {
        if (this.size == 0) {
            return Optional.empty();
        }

        assert this.head != null;
        var oldHead = this.head;
        var oldHeadValue = oldHead.value;
        this.head = this.head.next;
        this.size--;
        // delete oldHead;
        return Optional.of(oldHeadValue);
    }

    Optional<T> peek() {
        if (this.size == 0) {
            return Optional.empty();
        }
        assert this.head != null;
        return Optional.of(this.head.value);
    }

    Optional<T> get(int index) {
        if (index < 0 || index >= this.size) {
            return Optional.empty();
        }

        // No need to check for && curr != null in guard if we have the size already.
        assert this.head != null;
        var curr = this.head;
        for (var i = 0; i < index; i++) {
            curr = curr.next;
        }
        return Optional.ofNullable(curr).map(x -> x.value);
    }

    boolean insert(int index, T value) {
        if (index == 0) {
            pushFront(value);
            return true;
        }

        if (index < 0 || index > this.size) {
            return false;
        }

        var curr = this.head;
        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }
        var newNode = new Node<T>(value);
        newNode.next = curr.next;
        curr.next = newNode;
        this.size++;
        return true;
    }

    Optional<T> delete(int index) {
        if (index == 0) {
            return popFront();
        }
        if (index < 0 || index >= this.size) {
            return Optional.empty();
        }
        assert this.head != null;
        var prev = this.head;
        var curr = this.head.next;
        for (int i = 1; i < index; i++) {
            prev = curr;
            curr = curr.next;
        }

        prev.next = curr.next;
        this.size--;
        var value = curr.value;
        curr.next = null; // unnecessary
        return Optional.of(value);

    }

    void clear() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("SinglyLinkedList[");
        var curr = this.head;
        while (curr != null) {
            sb.append(curr.value).append(" > ");
            curr = curr.next;
        }
        sb.append("*]");
        return sb.toString();
    }
}
