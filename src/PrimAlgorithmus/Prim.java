package PrimAlgorithmus;

import org.apache.commons.math3.util.Pair;
import java.util.List;
import java.util.Map;

public class Prim {

    private List<Node> graph; //In jedem Knoten sind alle Verbindungen mit einer Hashmap gespeichert

    public Prim(List<Node> graph) {
        this.graph = graph;
    }

    /**
     * O(n^3 * e)
     * Findet den Minimum Spanning Tree (MST) im Graphen.
     */
    public int run() {

        int sumWeight = 0;
        //O(1)
        if (graph.size() > 0) {
            graph.get(0).setVisited(true);
        }
        //Schleife läuft bis alle Nodes Visted sind
        //O(n*(n*n*e)
        while (isDisconnected()) {//O(n)
            Edge nextMinimum = new Edge(Integer.MAX_VALUE);
            Node nextNode = graph.get(0);
            for (Node node : graph) {//O(n * e)
                if (node.isVisited()) {
                    Pair<Node, Edge> candidate = node.nextMinimum(); //O(e) Sucht das nächste Minimum
                    if (candidate.getValue().getWeight() < nextMinimum.getWeight()) {
                        nextMinimum = candidate.getValue();
                        nextNode = candidate.getKey();
                    }
                }
            }
            sumWeight += nextMinimum.getWeight();
            nextMinimum.setIncluded(true);
            nextNode.setVisited(true);
        }
        return sumWeight;
    }

    /**
     *  O(n) Checkt ob alle alle Knoten im Graphen bereits besucht sind.
     * @return true, wenn Knoten noch nicht besucht wurden<p>false, wenn ale Knoten noch nicht besucht sind</p>
     */
    private boolean isDisconnected() {
        for (Node node : graph) {
            if (!node.isVisited()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return String des Anfangsgraphen
     */
    public String originalGraphToString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : graph) {
            sb.append(node.originalToString());
        }
        return sb.toString();
    }

    /**
     * Setzt den Wert isPrinted bei jedem Knoten im Graphen zurueck
     */
    public void resetPrintHistory() {
        for (Node node : graph) {
            for (Map.Entry<Node, Edge> pair : node.getEdges().entrySet()) {
                pair.getValue().setPrinted(false);
            }
        }
    }

    /**
     * @return String des MST
     */
    public String minimumSpanningTreeToString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : graph) {
            sb.append(node.includedToString());
        }
        return sb.toString();
    }
}