package EulerTourAlgorithmus;

import java.util.HashMap;
import java.util.Map;

public class Node {

    private String label;
    private Map<Node, Edge> edges = new HashMap<>(); //Hashmap in der Liste

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
            this.edges.replace(node, edge);
        } else {
            this.edges.put(node, edge);
        }
    }

    public String toString() {
        return label;
    }

}