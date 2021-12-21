package FordFulkersonAlgorithmus;

import java.util.*;

public class FordF {

    private List<Node> graph;


    public FordF(List<Node> graph) {
        this.graph = graph;
    }

    public int run(Node source, Node target) {
        int maxFlow = 0;

        //while Kanten nicht voll
        while (!source.checkEdgesFull()) {
            //bfs machen
            Optional<List<Node>> tmp = source.path_dfs(target, new HashSet<>());
            if (tmp.isEmpty()) {
                return maxFlow;
            }
            List<Node> path = tmp.get();
            Collections.reverse(path);
                    //minimum Kapazität finden
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < path.size()-1; i++) {
                int tempMin = path.get(i).getRemainingCapacity(path.get(i + 1));
                if (tempMin < min) {
                    min = tempMin;
                }
            }
                    //alle Kanten mit minKap füllen
            for (int i = 0; i < path.size()-1; i++) {
                path.get(i).fill(path.get(i + 1), min);
            }
                    //maxFlow aktualisieren
            maxFlow += min;
//            resetPrintHistory();
//            printPath(path);
//            System.out.println("-------------------");
//            System.out.println(originalGraphToString());
//            System.out.println("-------------------");
        }
        return maxFlow;
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

    public static void printPath(List<Node> path) {
        for (Node node : path) {
            System.out.print(node.getLabel()+"->");
        }
    }

}
