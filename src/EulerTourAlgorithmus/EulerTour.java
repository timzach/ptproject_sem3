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
            System.out.println("----------------");
        }
        //subtour = null;
        tour.add(start);
        start = tour.get(0);
        Node current = start;
        do {
            Node temp = getAdjacentNodes(current).get(0);
            Edge unvisitedEdge = current.getEdges().remove(getAdjacentNodes(current).get(0));
            temp.getEdges().remove(current);
            System.out.println("Aktueller Knoten: " + current + " Nächster Knoten: " + temp + " Nächste Kante: " + unvisitedEdge);
            current = temp;
            subtour.add(current);
        } while (start != current);
        integrateTour(tour, subtour);
        System.out.println("----------------");
        for (Node entries : tour) {
            System.out.print(entries + " -> ");
        }
        System.out.println();
        System.out.println("----------------");
        System.out.println("TEST: " + graph.size());
        for (int i = 0; i < graph.size(); i++) {
            System.out.println(graph.get(i) + " EDGES: " + graph.get(i).getEdges().toString());
        }


    }

    public boolean checkGraphForEuler() {
        //Hier wird gecheckt, wie viele Kantenenden ein Knoten hat. Ist der Grad ungerade, wird die Schleife abgebrochen und ein false zurückgegeben.
        //Ist der Grad gerade, wird ein true zurückgegeben, da es sich dann eindeutig um einen Eulergraph handelt.
        boolean value = true;
        for (Node nodes : graph) {
            System.out.println("Knoten: " + nodes.getLabel() + " Grad: " + nodes.getEdges().size());
            if (nodes.getEdges().size() % 2 != 0) {
                System.out.println("UNGERADER GRAD");
                value = false;
                break;
            }
        }
        System.out.println("----------------");
        return value;
    }

    public List<Node> getAdjacentNodes(Node node) {
        //Hier werden die adjazenten Knoten vom übergebenen Knoten zurückgegeben.
        List<Node> adjacentNodes = new ArrayList<>(node.getEdges().keySet());
        System.out.println("Nachbar Knoten von " + node + ": " + adjacentNodes);
        return adjacentNodes;
    }

    public List<Node> integrateTour(List<Node> tour, List<Node> subtour) {
        System.out.println("Integriere..");
        for (Node entries : subtour) {
            tour.add(entries);
        }
        return tour;
    }

    /*public void alterCode() {
        do {
            start = tour.get(0);
            Node current = start;
            do {
                Node temp = getAdjacentNodes(current).get(0);
                Edge unvisitedEdge = current.getEdges().remove(getAdjacentNodes(current).get(0));
                temp.getEdges().remove(current);
                System.out.println("Aktueller Knoten: " + current + " Nächster Knoten: " + temp + " Nächste Kante: " + unvisitedEdge);
                current = temp;
                subtour.add(current);
            } while (start != current);
            integrateTour(tour, subtour);
            System.out.println("----------------");
            for (Node entries : tour) {
                System.out.print(entries + " -> ");
            }
            System.out.println("TEST" + graph.remove(1).getEdges().toString());
        } while (true);
    }*/
}
