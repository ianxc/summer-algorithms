package com.ianxc.graph;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GraphBfsTest {
    @Test
    void testBfsDirected() {
        var g = new AdjacencyListGraph<Character>(true);
        g.addVertex('a');
        g.addEdge('a', 'b', 2);
        g.addEdge('c', 'd', 5);
        g.addEdge('9', '8', 10);
        g.addEdge('c', 'e', 5);
        g.addEdge('a', 'f', 0);
        g.addEdge('a', 'g', 0);
        g.addEdge('a', 'c', 1);
        g.addEdge('d', 'a', -1);
        assertThat(g).asString().isEqualTo("""
                Directed vertices=9 edges=8
                8 -> *
                9 -> 8 (10), *
                a -> b (2), f (0), g (0), c (1), *
                b -> *
                c -> d (5), e (5), *
                d -> a (-1), *
                e -> *
                f -> *
                g -> *""");

        var processedOrder = GraphBfs.traverseGraph(g);
        assertThat(processedOrder)
                .isEqualTo(List.<Character>of('8', '9', 'a', 'b', 'f', 'g', 'c', 'd', 'e'));
    }
}
