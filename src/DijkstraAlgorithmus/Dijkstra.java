package DijkstraAlgorithmus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dijkstra {

    private final List<Node> graph;
    Map<Node, Integer> distance = new HashMap<>();
    Map<Node, Node> prevNode = new HashMap<>();
    List<Node> unsettledNodes = new ArrayList<>();

    public Dijkstra(List<Node> graph) {
        this.graph = graph;
    }

    /**
     * O(n^4)
     */
    public void run(Node source) {
        //Distanz wird fuer alle Knoten auf unendlich gesetzt. Die Vorgaengerknoten werden auf null gesetzt.
        //Lediglich der Startknoten bekommt 0 als Distanz.
        for(Node node: graph) { //O(n)
            distance.put(node, Integer.MAX_VALUE);
            prevNode.put(node, null);
            unsettledNodes.add(node);
        }
        distance.put(source, 0);

        //Solange es unbesuchte Knoten gibt, wird die while-Schleife wiederholt.
        while(!unsettledNodes.isEmpty()) { //O(n^4) = n * ( n + n + n^2 * n) = n * (2n + n^3) = n^4 + 2n^2
            Node u = getMinimum(unsettledNodes); //O(1) * O(n) = O(n)
            unsettledNodes.remove(u); //O(n)
            for(Node nodes: getAdjacentNodes(u)) { //O(n) * O(n) = O(n^2)
                //Nun werden die Distanzen aktualisiert, wenn der neu gefundene Weg kuerzer ist als der alte.
                if (unsettledNodes.contains(nodes)) { //O(n)
                    int weight = distance.get(u) + nodes.getEdges().get(u).getWeight(); //O(1)
                    if (weight < distance.get(nodes)) {
                        distance.put(nodes, weight);
                        prevNode.put(nodes, u);
                    }
                }
            }
        }
    }

    /**
     * O(n)
     */
    public List<Node> getAdjacentNodes(Node node) {
        //Hier werden die adjazenten Knoten vom uebergebenen Knoten zurueckgegeben in einer ArrayList.
        return new ArrayList<>(node.getEdges().keySet()); //O(n) * O(1)
    }

    /**
     * O(n)
     */
    public Node getMinimum(List<Node> unsettledNodes) {
        //Hier wird der naechste nicht besuchte Knoten mit der geringsten Distanz zurueckgegeben.
        Node minimum = unsettledNodes.get(0);
        for (Node node: unsettledNodes) { //O(n)
            if(distance.get(minimum) > distance.get(node)) {
                minimum = node;
            }
        }
        return minimum;
    }

    /**
     * O(n*log n)
     */
    public Object[] getDistance() {
        return distance.entrySet().stream().sorted(Map.Entry.comparingByValue()).toArray();
    }

}
