package FordFulkersonAlgorithmus;

import java.util.List;
import java.util.Map;

public class FordF {

    private List<Node> graph;
    private int numberOfNodes;

    public FordF(List<Node> graph) {
        this.graph = graph;
    }

    public void run() {
        //FF-Algo

    }

    //Methode zum überprüfen ob voll?




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

}
