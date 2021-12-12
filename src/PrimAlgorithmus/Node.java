package PrimAlgorithmus;

import org.apache.commons.math3.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class Node {

    private String label = null;
    private Map<Node, Edge> edges = new HashMap<>(); //Hashmap wegen den Key Values
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

    public Pair<Node, Edge> nextMinimum() {
        Edge nextMinimum = new Edge(Integer.MAX_VALUE);
        Node nextNode = this;
        for (Map.Entry<Node, Edge> pair : edges.entrySet()) {
            if (!pair.getKey().isVisited()) {
                if (!pair.getValue().isIncluded()) {
                    if (pair.getValue().getWeight() < nextMinimum.getWeight()) {
                        nextMinimum = pair.getValue();
                        nextNode = pair.getKey();
                    }
                }
            }
        }
        return new Pair<>(nextNode, nextMinimum);
    }

    public String originalToString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Node, Edge> pair : edges.entrySet()) {
            if (!pair.getValue().isPrinted()) {
                sb.append(getLabel());
                sb.append(" --- ");
                sb.append(pair.getValue().getWeight());
                sb.append(" --- ");
                sb.append(pair.getKey().getLabel());
                sb.append("\n");
                pair.getValue().setPrinted(true);
            }
        }
        return sb.toString();
    }

    public String includedToString() {
        StringBuilder sb = new StringBuilder();
        if (isVisited()) {
            for (Map.Entry<Node, Edge> pair : edges.entrySet()) {
                if (pair.getValue().isIncluded()) {
                    if (!pair.getValue().isPrinted()) {
                        sb.append(getLabel());
                        sb.append(" --- ");
                        sb.append(pair.getValue().getWeight());
                        sb.append(" --- ");
                        sb.append(pair.getKey().getLabel());
                        sb.append("\n");
                        pair.getValue().setPrinted(true);
                    }
                }
            }
        }
        return sb.toString();
    }

}