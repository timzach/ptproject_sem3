package PrimAlgorithmus;

import org.apache.commons.math3.util.Pair;
import java.util.List;
import java.util.Map;

public class Prim {

    private List<Node> graph;

    public Prim(List<Node> graph) {
        this.graph = graph;
    }

    public void run() {
        if (graph.size() > 0) {
            graph.get(0).setVisited(true);
        }
        //Schleife läuft so lange wie vertex noch nicht besucht wurde
        while (isDisconnected()) {
            Edge nextMinimum = new Edge(Integer.MAX_VALUE);
            Node nextNode = graph.get(0);
            for (Node node : graph) {
                if (node.isVisited()) {
                    Pair<Node, Edge> candidate = node.nextMinimum(); //Momentaufnahme der Hashmap und sucht das nächste Minimum
                    if (candidate.getValue().getWeight() < nextMinimum.getWeight()) {
                        nextMinimum = candidate.getValue();
                        nextNode = candidate.getKey();
                    }
                }
            }
            nextMinimum.setIncluded(true);
            nextNode.setVisited(true);
        }
    }

    private boolean isDisconnected() {
        for (Node node : graph) {
            if (!node.isVisited()) {
                return true;
            }
        }
        return false;
    }

    public String originalGraphToString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : graph) {
            sb.append(node.originalToString());
        }
        return sb.toString();
    }

    public void resetPrintHistory() {
        for (Node node : graph) {
            for (Map.Entry<Node, Edge> pair : node.getEdges().entrySet()) {
                pair.getValue().setPrinted(false);
            }
        }
    }

    public String minimumSpanningTreeToString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : graph) {
            sb.append(node.includedToString());
        }
        return sb.toString();
    }

}