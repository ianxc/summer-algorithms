package com.ianxc.graph;

import static com.ianxc.core.CoreUtil.dbg;
import static com.ianxc.core.CoreUtil.println;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GraphBfs {

    static <T> List<T> traverseGraph(AdjacencyListGraph<T> g) {
        var processed = new LinkedHashSet<T>();
        // Handle disconnected components of the graph too.
        // Start from a deterministic vertex by getting them in sorted order
        for (var vertex : g.sortedVertices()) {
            if (!processed.contains(vertex)) {
                dbg(() -> println("traverse component begin"));
                traverseGraph(g, processed, vertex);
                dbg(() -> println("traverse component end"));

            }
        }
        dbg(() -> System.out.printf("BFS processed order %s\n", processed));
        return List.copyOf(processed);
    }

    static <T> void traverseGraph(AdjacencyListGraph<T> g, Set<T> processed, T start) {
        // discovered does not need to be lifted up to caller function as it
        // does not need to be shared across disconnected components.
        var discovered = new HashSet<T>();
        discovered.add(start);
        var queue = new ArrayDeque<T>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            var current = queue.poll();
            dbg(() -> System.out.printf("process_vertex_early %s\n", current));
            for (var edge : g.neighbors(current)) {
                var dest = edge.destination();
                if (!processed.contains(dest) || g.isDirected()) {
                    dbg(() -> System.out.printf("process_edge %s\n", edge));
                }
                if (!discovered.contains(dest)) {
                    queue.offer(dest);
                    discovered.add(dest);
                }
            }
            dbg(() -> System.out.printf("process_vertex_late %s\n", current));
            processed.add(current);
        }
    }
}