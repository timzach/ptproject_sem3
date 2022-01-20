package FordFulkersonAlgorithmus;

import java.util.*;

public class FordF {

    private List<Node> graph;

    public FordF(List<Node> graph) {
        this.graph = graph;
    }

    public int run(Node source, Node target) {

        createResidualGraph();

        int maxFlow = 0;
        //while Kanten nicht voll
        while (true) {

            //Optional<List<Node>> tmp = source.path_dfs(target, new HashSet<>());
            Optional<List<Node>> tmp = source.path_bfs(target, new HashSet<>());

            if (tmp.isEmpty()) {
                break;
            }
            List<Node> reversePath = tmp.get();
            List<Node> path = new ArrayList<Node>(reversePath);
            Collections.reverse(path);
                    //minimum Kapazität finden
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < path.size()-1; i++) {
                int tempMin = path.get(i).getEdgeCapacity(path.get(i + 1));
                if (tempMin < min) {
                    min = tempMin;
                }
            }
            //neue Rückfluss-Kanten mit cap = minKap erstellen

            Node previousNode = reversePath.get(0);

            for (Node node : reversePath) {
                if (node == previousNode) {
                    continue;
                }
                previousNode.addResidualEdge(node, new Edge(min));
                previousNode = node;
            }
            //fill mit reduce capacity ersetzen
            for (int i = 0; i < path.size()-1; i++) {
                path.get(i).reduceCapacity(path.get(i + 1), min);
            }

            maxFlow += min;
//            resetPrintHistory();
//            printPath(path);
//            System.out.println("-------------------");
//            System.out.println(graphToString());
//            System.out.println("-------------------");
        }
        return maxFlow;
    }

    public String graphToString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : graph) {
            sb.append(node.originalToString());
        }
        return sb.toString();
    }

    public String residualGraphToString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : graph) {
            sb.append(node.residualToString());
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

    public void createResidualGraph() {
        for (Node node : graph) {
            node.createResidualEdges();
        }
    }
}
