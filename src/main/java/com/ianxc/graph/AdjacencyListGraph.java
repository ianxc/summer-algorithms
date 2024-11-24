package com.ianxc.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

record EdgePair<T>(T source, T destination, int weight) {
    @Override
    public final String toString() {
        return String.format("[%s -> %s, w=%d]", source, destination, weight);
    }
}

class Edge<T> {
    T destination;
    int weight;

    public Edge(T destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("[->%s, w=%d]", this.destination, this.weight);
    }
}

public class AdjacencyListGraph<T> {
    private Map<T, List<Edge<T>>> adjList;
    private boolean isDirected;
    private int numEdges;
    private int numVertices;

    public AdjacencyListGraph(boolean isDirected) {
        this.adjList = new HashMap<>();
        this.isDirected = isDirected;
        this.numEdges = 0;
        this.numVertices = 0;
    }

    public boolean isDirected() {
        return this.isDirected;
    }

    public int numEdges() {
        return this.numEdges;
    }

    public int numVerticies() {
        return this.numVertices;
    }

    public Set<T> vertices() {
        return adjList.keySet();
    }

    public Set<EdgePair<T>> edges() {
        return this.adjList.entrySet()
                .stream()
                .flatMap(entry -> entry.getValue()
                        .stream()
                        .map(edge -> new EdgePair<T>(
                                entry.getKey(), edge.destination, edge.weight)))
                .collect(Collectors.toUnmodifiableSet());
    }

    public void addVertex(T vertex) {
        this.adjList.computeIfAbsent(vertex, (__ -> {
            this.numVertices++;
            return new LinkedList<>();
        }));
    }

    public void addEdgeFast(T source, T destination, int weight) {
        var newEdge = new Edge<T>(destination, weight);
        this.adjList.get(source).add(newEdge);
        this.numEdges++;

        if (!this.isDirected) {
            var reverseEdge = new Edge<T>(source, weight);
            this.adjList.get(destination).add(reverseEdge);
        }
    }

    public void addEdge(T source, T destination, int weight) {
        this.addVertex(source);
        this.addVertex(destination);
        this.addEdgeFast(source, destination, weight);
    }

    public void removeVertex(T vertex) {
        for (var dests : this.adjList.values()) {
            var numBefore = dests.size();
            dests.removeIf(edge -> edge.destination.equals(vertex));

            if (this.isDirected) {
                var numAfter = dests.size();
                var numEdgesWithVertexAsDest = numBefore - numAfter;
                this.numEdges -= numEdgesWithVertexAsDest;
            }
        }

        var numEdgesWithVertexAsSource = this.adjList.get(vertex).size();
        this.numEdges -= numEdgesWithVertexAsSource;
        this.numVertices -= 1;

        this.adjList.remove(vertex);
    }

    /**
     * Assumes no duplicate edges but does not validate this.
     */
    public void removeEdge(T source, T destination) {
        var edgesWithSource = this.adjList.get(source);
        var numEdgesBefore = edgesWithSource.size();
        edgesWithSource.removeIf(edge -> edge.destination.equals(destination));
        var numEdgesAfter = edgesWithSource.size();
        var numEdgesRemoved = numEdgesBefore - numEdgesAfter;
        this.numEdges -= numEdgesRemoved;

        if (!this.isDirected) {
            this.adjList
                    .get(destination)
                    .removeIf(edge -> edge.destination.equals(source));
        }
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append(this.isDirected ? "Directed" : "Undirected");
        sb.append(String.format(" vertices=%d edges=%d\n", this.numVertices, this.numEdges));
        this.adjList
                .keySet()
                .stream()
                .sorted()
                .forEach(source -> {
                    sb.append(source);
                    sb.append(" -> ");
                    this.adjList
                            .get(source)
                            .forEach(edge -> {
                                sb.append(edge.destination);
                                sb.append(" (");
                                sb.append(edge.weight);
                                sb.append("), ");
                            });
                    sb.append("*\n");
                });
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
