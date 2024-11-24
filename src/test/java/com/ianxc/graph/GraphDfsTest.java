package com.ianxc.graph;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GraphDfsTest {
    @Test
    void testDfsDirected() {
        var g = new AdjacencyListGraph<Character>(true);
        g.addVertex('a');
        g.addEdge('a', 'b', 2);
        g.addEdge('b', 'h', 0);
        g.addEdge('c', 'd', 5);
        g.addEdge('9', '8', 10);
        g.addEdge('c', 'e', 5);
        g.addEdge('a', 'f', 0);
        g.addEdge('a', 'g', 0);
        g.addEdge('a', 'c', 1);
        g.addEdge('d', 'a', -1);
        g.addEdge('b', 'w', 0);
        g.addEdge('w', 'x', 0);
        g.addEdge('b', 'z', 0);
        g.addEdge('x', 'y', 0);
        g.addEdge('y', 'z', 0);
        assertThat(g).asString().isEqualTo("""
                Directed vertices=14 edges=14
                8 -> *
                9 -> 8 (10), *
                a -> b (2), f (0), g (0), c (1), *
                b -> h (0), w (0), z (0), *
                c -> d (5), e (5), *
                d -> a (-1), *
                e -> *
                f -> *
                g -> *
                h -> *
                w -> x (0), *
                x -> y (0), *
                y -> z (0), *
                z -> *""");

        var result = GraphDfs.traverseGraph(g);
        assertThat(result.preorder())
                .isEqualTo(List.<Character>of('8', '9', 'a', 'b', 'h', 'w', 'x', 'y', 'z', 'f', 'g', 'c', 'd', 'e'));
        assertThat(result.postorder())
                .isEqualTo(List.<Character>of('8', '9', 'h', 'z', 'y', 'x', 'w', 'b', 'f', 'g', 'd', 'e', 'c', 'a'));
    }
}
