package DijkstraAlgorithmus;

import org.apache.commons.math3.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dijkstra {

    private List<Node> graph;

    public Dijkstra(List<Node> graph) {
        this.graph = graph;
    }

    public void run(Node source, Node target) {
        Map<Node, Integer> distance = new HashMap<>();
        Map<Node, Node> prevNode = new HashMap<>();

        //Initialisiere Distanz und Vorgängerknoten
        for(Node node: graph) {
            distance.put(node, Integer.MAX_VALUE);
            prevNode.put(node, null);
            if(node == source) {
                distance.put(source, 0);
                source.setVisited(true);

            }
        }

        while (isDisconnected()) {
            Edge nextMinimum = new Edge(Integer.MAX_VALUE);
            Node nextNode = graph.get(0);
            for (Node node : graph) {
                if (node.isVisited()) {
                    Pair<Node, Edge> candidate = node.nextMinimum(); //Sucht das nächste Minimum
                    if (candidate.getValue().getWeight() + distance.get(candidate) < nextMinimum.getWeight()) {
                        nextMinimum = candidate.getValue();
                        nextNode = candidate.getKey();
                    }
                }
            }
            nextMinimum.setIncluded(true);
            nextNode.setVisited(true);
        }

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

    private boolean isDisconnected() {
        for (Node node : graph) {
            if (!node.isVisited()) {
                return true;
            }
        }
        return false;
    }

    public static void printPath(List<Node> path) {
        for (Node node : path) {
            System.out.print(node.getLabel()+"->");
        }
    }
}
