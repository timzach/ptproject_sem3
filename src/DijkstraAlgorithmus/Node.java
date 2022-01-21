package DijkstraAlgorithmus;

import java.util.HashMap;
import java.util.Map;

public class Node {

    private String label;
    private Map<Node, Edge> edges = new HashMap<>(); //Hashmap in der Liste
    private boolean isVisited = false;

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

    public void addEdge(Node node, Edge edge) {
        if (this.edges.containsKey(node)) {
            if (edge.getWeight() < this.edges.get(node).getWeight()) {
                this.edges.replace(node, edge);
            }
        } else {
            this.edges.put(node, edge);
        }
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public String toString() {
        return getLabel();
    }

}