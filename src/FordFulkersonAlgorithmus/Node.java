package FordFulkersonAlgorithmus;

import java.util.*;

public class Node {

    private String label = null;
    private Map<Node, Edge> edges = new HashMap<>();
    private boolean isVisited = false;

    //Start- und Endpunkt definieren?
    //Oder über das label mit s(start) und t(target)?


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

    public void setEdges(Map<Node, Edge> edges) {
        this.edges = edges;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public void addEdge(Node node, Edge edge) {
        if (this.edges.containsKey(node)) {
            if (edge.getCapacity() < this.edges.get(node).getCapacity()) {
                this.edges.replace(node, edge);
            }
        } else {
            this.edges.put(node, edge);
        }
    }

    /*
    DFS Methode
    Methode die einen Path von s zu t findet mit Edges (!isFull()) oder capacity > 0


     */
    public List<Node> path_bfs() {
        Node lastNode = this;
        Stack<Node> nodeStack = new Stack<>();
        List<Node> path = new ArrayList<>();
        Node[] parent; //geht das?

        //Der erste knoten soll in den Stack gepusht werden
        for (Map.Entry<Node, Edge> pair : edges.entrySet()) {
            if (!pair.getKey().isVisited()) {
                if (pair.getValue().getCapacity() > 0) {
                    nodeStack.pop().getEdges();//Diese Edges sollen dann gepusht werden
                    nodeStack.push(pair.getKey());
                }
            }
        }
        return path;
    }

    public String originalToString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Node, Edge> pair : edges.entrySet()) {
            if (!pair.getValue().isPrinted()) {
                sb.append(getLabel());
                sb.append(" --- ");
                sb.append(pair.getValue().getCapacity());
                sb.append("/");
                sb.append(pair.getValue().getFlow());
                sb.append(" --> ");
                sb.append(pair.getKey().getLabel());
                sb.append("\n");
                pair.getValue().setPrinted(true);
            }
        }
        return sb.toString();
    }
}
