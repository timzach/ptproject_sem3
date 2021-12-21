package FordFulkersonAlgorithmus;

import java.util.*;

public class Node {

    private String label = null;
    private Map<Node, Edge> edges = new HashMap<>();
    private boolean isVisited = false;

    //Start- und Endpunkt definieren?
    //Oder über das label mit s(start) und t(target)?


    public Node(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Map<Node, Edge> getEdges() {
        return edges;
    }

    public void setEdges(Map<Node, Edge> edges) {
        this.edges = edges;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public void addEdge(Node node, Edge edge) {
        if (this.edges.containsKey(node)) {
            if (edge.getCapacity() < this.edges.get(node).getCapacity()) {
                this.edges.replace(node, edge);
            }
        } else {
            this.edges.put(node, edge);
        }
    }

    public boolean checkEdgesFull() {
        for (Edge edge : edges.values()) {
            if (!edge.isFull()) {
                return false;
            }
        }
        return true;
    }

    public int getRemainingCapacity(Node target) {
        if (this.edges.containsKey(target)) {
            return edges.get(target).getRemainingCapacity();
        }
        return 0;

    }

    public void fill(Node target, int value) {
        if (this.edges.containsKey(target)) {
            edges.get(target).fill(value);
            return; //damit es nicht in die Exception läuft
        }
        throw new RuntimeException("no Edge found");
    }

    public Optional<List<Node>> path_dfs(Node target, Set<Node> visited) {

        if (edges.containsKey(target) && !edges.get(target).isFull()) {
            List<Node> path = new ArrayList<>();
            path.add(target);
            path.add(this);

            return Optional.of(path);
        }
        visited.add(this);
        for (Node node : edges.keySet()) {
            if (!visited.contains(node)) {
                if (!edges.get(node).isFull()) {
                    Optional<List<Node>> tmp = node.path_dfs(target, visited);

                    if (tmp.isPresent()) {
                        tmp.get().add(this);
                        return tmp;
                    }
                }
            }
        }
        return Optional.empty();
    }

    public Optional<List<Node>> path_bfs(Node target, Set<Node> visited) {

        if (edges.containsKey(target) && !edges.get(target).isFull()) {
            List<Node> path = new ArrayList<>();
            path.add(target);
            path.add(this);

            return Optional.of(path);
        }
        visited.add(this);
        for (Node node : edges.keySet()) {
            if (!visited.contains(node)) {
                if (!edges.get(node).isFull()) {
                    Optional<List<Node>> tmp = node.path_bfs(target, visited);

                    if (tmp.isPresent()) {
                        tmp.get().add(this);
                        return tmp;
                    }
                }
            }
        }
        return Optional.empty();
    }



    public String originalToString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Node, Edge> pair : edges.entrySet()) {
            if (!pair.getValue().isPrinted()) {
                sb.append(getLabel());
                sb.append(" --- ");
                sb.append(pair.getValue().getCapacity());
                sb.append("/");
                sb.append(pair.getValue().getFlow());
                sb.append(" --> ");
                sb.append(pair.getKey().getLabel());
                sb.append("\n");
                pair.getValue().setPrinted(true);
            }
        }
        return sb.toString();
    }
}
