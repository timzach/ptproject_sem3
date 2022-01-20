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
        //Distanz wird für alle Knoten auf unendlich gesetzt. Die Vorgängerknoten werden auf null gesetzt.
        //Lediglich der Startknoten bekommt 0 als Distanz und wird als besucht markiert.
        for(Node node: graph) {
            distance.put(node, Integer.MAX_VALUE);
            prevNode.put(node, null);
            unsettledNodes.add(node);
            System.out.println("Added node " + node + " to unsettledNodes");
        }
        distance.put(source, 0);
        System.out.println("---------------------");

        //Solange es unbesuchte Knoten gibt, wird die while-Schleife wiederholt.
        while(!unsettledNodes.isEmpty()) {
            Node u = getMinimum(unsettledNodes);
            unsettledNodes.remove(u);
            for(Node nodes: getAdjacentNodes(u)) {
                //Nun werden die Distanzen aktualisiert, wenn der neue Weg kürzer ist als der alte.
                if (unsettledNodes.contains(nodes)) {
                    int weight = distance.get(u) + nodes.getEdges().get(u).getWeight();
                    if (weight < distance.get(nodes)) {
                        distance.put(nodes, weight);
                        prevNode.put(nodes, u);
                    }
                }
            }
        }
        System.out.println("-----------------");
        System.out.println("FINISHED DIJKSTRA");
        System.out.println("-----------------");
        System.out.println("Kürzeste Distanzen: " + distance.toString());
    }

    public List<Node> getAdjacentNodes(Node node) {
        //Hier werden die adjazenten Knoten vom übergebenen Knoten zurückgegeben.
        List<Node> adjacentNodes = new ArrayList<>(node.getEdges().keySet());
        System.out.println("Nachbar Knoten von " + node + ": " + adjacentNodes);
        return adjacentNodes;
    }

    public Node getMinimum(List<Node> unsettledNodes) {
        //Hier wird der nächste nicht besuchte Knoten mit der geringsten Distanz zurückgegeben.
        Node minimum = unsettledNodes.get(0);
        for (Node node: unsettledNodes) {
            if(distance.get(minimum) > distance.get(node)) {
                minimum = node;
            }
        }
        return minimum;
    }
}
