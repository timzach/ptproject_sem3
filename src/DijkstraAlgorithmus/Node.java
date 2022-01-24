package DijkstraAlgorithmus;

import java.util.HashMap;
import java.util.Map;

public class Node {

    private final String label;
    private final Map<Node, Edge> edges = new HashMap<>(); //Hashmap in der Liste

    public Node(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
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

    public String toString() {
        return label;
    }

}