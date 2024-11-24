package com.ianxc.graph;

import static com.ianxc.core.CoreUtil.dbg;
import static com.ianxc.core.CoreUtil.println;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

record DfsResult<T>(List<T> preorder, List<T> postorder) {
}

public class GraphDfs {
    static <T> DfsResult<T> traverseGraph(AdjacencyListGraph<T> g) {
        var discovered = new LinkedHashSet<T>();
        var processed = new LinkedHashSet<T>();
        // Handle disconnected components of the graph too.
        // Start from a deterministic vertex by getting them in sorted order
        for (var vertex : g.sortedVertices()) {
            if (!processed.contains(vertex)) {
                dbg(() -> println("traverse component begin"));
                traverseGraph(g, discovered, processed, vertex);
                dbg(() -> println("traverse component end"));

            }
        }
        dbg(() -> System.out.printf("DFS discovered order %s\n", discovered));
        dbg(() -> System.out.printf("DFS processed order %s\n", processed));
        return new DfsResult<T>(List.copyOf(discovered), List.copyOf(processed));

    }

    static <T> void traverseGraph(
            AdjacencyListGraph<T> g, Set<T> discovered, Set<T> processed, T current) {
        discovered.add(current);
        dbg(() -> System.out.printf("process_vertex_early %s\n", current));
        for (var edge : g.neighbors(current)) {
            var dest = edge.destination();
            if (!discovered.contains(dest)) {
                traverseGraph(g, discovered, processed, dest);
            }
        }

        dbg(() -> System.out.printf("process_vertex_late %s\n", current));
        processed.add(current);
    }
}
