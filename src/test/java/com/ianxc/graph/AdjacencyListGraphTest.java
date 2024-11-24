package com.ianxc.graph;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class AdjacencyListGraphTest {
    @Test
    void testDirectedGraph() {
        var g = new AdjacencyListGraph<Character>(true);
        g.addVertex('a');
        assertThat(g).asString().isEqualTo("""
                Directed vertices=1 edges=0
                a -> *""");

        g.addEdge('a', 'b', 2);
        assertThat(g).asString().isEqualTo("""
                Directed vertices=2 edges=1
                a -> b (2), *
                b -> *""");

        g.addEdge('c', 'd', 5);
        assertThat(g).asString().isEqualTo("""
                Directed vertices=4 edges=2
                a -> b (2), *
                b -> *
                c -> d (5), *
                d -> *""");

        g.addEdge('c', 'e', 5);
        assertThat(g).asString().isEqualTo("""
                Directed vertices=5 edges=3
                a -> b (2), *
                b -> *
                c -> d (5), e (5), *
                d -> *
                e -> *""");

        g.removeEdge('c', 'd');
        assertThat(g).asString().isEqualTo("""
                Directed vertices=5 edges=2
                a -> b (2), *
                b -> *
                c -> e (5), *
                d -> *
                e -> *""");

        g.removeVertex('b');
        assertThat(g).asString().isEqualTo("""
                Directed vertices=4 edges=1
                a -> *
                c -> e (5), *
                d -> *
                e -> *""");
    }

    @Test
    void testUndirectedGraph() {
        var g = new AdjacencyListGraph<Character>(false);
        g.addVertex('a');
        assertThat(g).asString().isEqualTo("""
                Undirected vertices=1 edges=0
                a -> *""");

        g.addEdge('a', 'b', 2);
        assertThat(g).asString().isEqualTo("""
                Undirected vertices=2 edges=1
                a -> b (2), *
                b -> a (2), *""");

        g.addEdge('c', 'd', 5);
        assertThat(g).asString().isEqualTo("""
                Undirected vertices=4 edges=2
                a -> b (2), *
                b -> a (2), *
                c -> d (5), *
                d -> c (5), *""");

        g.addEdge('c', 'e', 5);
        assertThat(g).asString().isEqualTo("""
                Undirected vertices=5 edges=3
                a -> b (2), *
                b -> a (2), *
                c -> d (5), e (5), *
                d -> c (5), *
                e -> c (5), *""");

        g.removeEdge('c', 'd');
        assertThat(g).asString().isEqualTo("""
                Undirected vertices=5 edges=2
                a -> b (2), *
                b -> a (2), *
                c -> e (5), *
                d -> *
                e -> c (5), *""");

        g.removeVertex('b');
        assertThat(g).asString().isEqualTo("""
                Undirected vertices=4 edges=1
                a -> *
                c -> e (5), *
                d -> *
                e -> c (5), *""");
    }

}
