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

    public void run(Node source) {
        //Distanz wird fuer alle Knoten auf unendlich gesetzt. Die Vorgaengerknoten werden auf null gesetzt.
        //Lediglich der Startknoten bekommt 0 als Distanz und wird als besucht markiert.
        for(Node node: graph) {
            distance.put(node, Integer.MAX_VALUE);
            prevNode.put(node, null);
            unsettledNodes.add(node);
        }
        distance.put(source, 0);

        //Solange es unbesuchte Knoten gibt, wird die while-Schleife wiederholt.
        while(!unsettledNodes.isEmpty()) {
            Node u = getMinimum(unsettledNodes);
            unsettledNodes.remove(u);
            for(Node nodes: getAdjacentNodes(u)) {
                //Nun werden die Distanzen aktualisiert, wenn der neue Weg kuerzer ist als der alte.
                if (unsettledNodes.contains(nodes)) {
                    int weight = distance.get(u) + nodes.getEdges().get(u).getWeight();
                    if (weight < distance.get(nodes)) {
                        distance.put(nodes, weight);
                        prevNode.put(nodes, u);
                    }
                }
            }
        }
    }

    public List<Node> getAdjacentNodes(Node node) {
        //Hier werden die adjazenten Knoten vom uebergebenen Knoten zurueckgegeben.
        return new ArrayList<>(node.getEdges().keySet());
    }

    public Node getMinimum(List<Node> unsettledNodes) {
        //Hier wird der naechste nicht besuchte Knoten mit der geringsten Distanz zurueckgegeben.
        Node minimum = unsettledNodes.get(0);
        for (Node node: unsettledNodes) {
            if(distance.get(minimum) > distance.get(node)) {
                minimum = node;
            }
        }
        return minimum;
    }

    public Object[] getDistance() {
        return distance.entrySet().stream().sorted(Map.Entry.comparingByValue()).toArray();
    }

}
