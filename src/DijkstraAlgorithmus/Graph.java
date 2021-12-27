package DijkstraAlgorithmus;

import java.util.List;

public class Graph {
    private List<Node> nodes;
    private List<Edge> edges;

    public Graph(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    @Override
    public String toString() {
        if (!edges.isEmpty()) {
            for (Edge edges: edges) {
                System.out.println(edges.toString());
            }
            return "Fertig!";
        } else {
            return "Es existiert kein Graph!";
        }
    }
}
