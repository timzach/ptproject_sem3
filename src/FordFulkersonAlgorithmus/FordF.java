package FordFulkersonAlgorithmus;

import java.util.*;

public class FordF {

    private List<Node> graph;

    public FordF(List<Node> graph) {
        this.graph = graph;
    }

    /**
     * Findet den MaxFlow zwischen einem Start- und Endknoten
     * @param source Startknoten
     * @param target Zielknoten
     * @return MaxFlow als Integer
     */
    public int run(Node source, Node target) {

        createResidualGraph();

        int maxFlow = 0;
        boolean tmpBoolean = false;
        //while Kanten nicht voll
        while (true) {


            System.out.println("-------------------");
            resetPrintHistory();
            //Optional<List<Node>> tmp = source.path_dfs(target, new HashSet<>());
            Optional<List<Node>> tmp = source.path_bfs(target, new HashSet<>());

            if (tmp.isEmpty()) {
                break;
            }
            List<Node> reversePath = tmp.get();
            List<Node> path = new ArrayList<Node>(reversePath);
            Collections.reverse(path);
            int pathSize = path.size();
                    //minimum Kapazität finden
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < path.size()-1; i++) {
//                System.out.print(path.get(i).getLabel());
                int tempMin = path.get(i).getEdgeCapacity(path.get(i + 1));
                if (tempMin < min) {
                    min = tempMin;
                }
            }
//            System.out.print(path.get(pathSize-1).getLabel());
//            System.out.println("\n");
//            System.out.println("-------------------");

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

    /**
     * @return String des Anfangsgraphen
     */
    public String graphToString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : graph) {
            sb.append(node.originalToString());
        }
        return sb.toString();
    }

    /**
     * @return String des Rueckflussgraphen
     */
    public String residualGraphToString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : graph) {
            sb.append(node.residualToString());
        }
        return sb.toString();
    }

    /**
     * Setzt den Wert isPrinted bei jedem Knoten im Graphen zurueck.
     */
    public void resetPrintHistory() {
        for (Node node : graph) {
            for (Map.Entry<Node, Edge> pair : node.getEdges().entrySet()) {
                pair.getValue().setPrinted(false);
            }
            if (node.checkResidualHasContent()) {
                for (Map.Entry<Node, Edge> pair : node.getResidualEdges().entrySet()) {
                    pair.getValue().setPrinted(false);
                }
            }
        }
    }

    /**
     * Kopiert fuer jeden Knoten die Rueckflussedges
     */
    public void createResidualGraph() {
        for (Node node : graph) {
            node.createResidualEdges();
        }
    }
}
