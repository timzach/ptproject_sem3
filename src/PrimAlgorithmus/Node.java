package PrimAlgorithmus;

import org.apache.commons.math3.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class Node {

    private String label = null;
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

    /**
     * Fuegt eine Kante hinzu, wenn bereits eine Kante mit hoeherem Gewicht existiert wird diese durch die neue Kante ersetzt.
     * @param node Zielknoten der Kante
     * @param edge Die Kante zum hinzufuegen
     */
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


    //Note that since nextMinimum() iterates through the edges, the time complexity of this implementation is O(V2).
    // If we store the edges in a priority queue (sorted by weight) instead, the algorithm will perform in O(E log V).

    /**
     * Sucht die kuerzeste ausgehende Kante
     * @return Gibt die kuerzeste Kante sowie dessen Zielknoten zurück
     */
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

    /**
     * Generiert die Ausgabe fuer den Start-Graphen
     * @return String der Ausgabe
     */
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

    /**
     * Generiert die Ausgabe fuer den End-Graphen
     * @return String der Ausgabe
     */
    public String includedToString() {
        StringBuilder sb = new StringBuilder();
        if (isVisited()) {
            for (Map.Entry<Node, Edge> pair : edges.entrySet()) {
                if (pair.getValue().isIncluded()) {
                    if (!pair.getValue().isPrinted()) {
                        sb.append(getLabel());
                        sb.append(" --- ");
                        sb.append(pair.getValue().getWeight());
                        Edge.setSumWeight(Edge.getSumWeight() + pair.getValue().getWeight());
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