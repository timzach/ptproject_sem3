package EulerTourAlgorithmus;

import java.util.ArrayList;
import java.util.List;

public class EulerTour {

    private final List<Node> graph;
    List<Node> subtour = new ArrayList<>();
    List<Node> tour = new ArrayList<>();
    Node start;

    public EulerTour(List<Node> graph) {
        this.graph = graph;
    }

    public void run(Node source) {
        if (!checkGraphForEuler()) {
            throw new RuntimeException("Der übergebene Graph ist kein Eulergraph!");
        } else {
            start = source;
            System.out.println("Es handelt sich hier um einen Eulergraph! Führe Algorithmus aus...");
        }
        //subtour = null;
        tour.add(start);
        System.out.print(start);
       /*do {
            start = tour.get(0);
            subtour.add(start);
            Node current = start;
            do {
                Edge unvisitedEdge = current.getEdges().get(current);
                System.out.println(unvisitedEdge);
            } while(start != current);
        } while(true);*/
    }

    public boolean checkGraphForEuler() {
        //Hier wird gecheckt, wie viele Kantenenden ein Knoten hat. Ist der Grad ungerade, wird die Schleife abgebrochen und ein false zurückgegeben.
        //Ist der Grad gerade, wird ein true zurückgegeben, da es sich dann eindeutig um einen Eulergraph handelt.
        boolean value = true;
        for(Node nodes: graph) {
            System.out.println(nodes.getLabel() + " = " + nodes.getEdges().size());
            if(nodes.getEdges().size() % 2 != 0) {
                System.out.println("UNGERADER GRAD");
                value = false;
                break;
            }
        }
        return value;
    }

        public List<Node> getAdjacentNodes(Node node) {
        //Hier werden die adjazenten Knoten vom übergebenen Knoten zurückgegeben.
        List<Node> adjacentNodes = new ArrayList<>(node.getEdges().keySet());
        System.out.println("Nachbar Knoten von " + node + ": " + adjacentNodes);
        return adjacentNodes;
    }
}
