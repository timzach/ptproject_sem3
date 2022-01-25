package EulerTourAlgorithmus;

import java.util.ArrayList;
import java.util.List;

public class EulerTour {

    private final List<Node> graph;
    List<Node> subtour = new ArrayList<>();
    List<Node> tour = new ArrayList<>();

    public EulerTour(List<Node> graph) {
        this.graph = graph;
    }

    /**
     * O(n^3)
     */
    public void run(Node source) { //O(n^3) = n + n * (n + n * (n + 1 + 1 + 1) + n) + n = n^3 + 5n^2 + 2n = n^3
        Node start;
        if (!checkGraphForEuler()) { //O(n)
            throw new RuntimeException("Der uebergebene Graph ist kein Eulergraph!");
        } else {
            start = source;
        }
        tour.add(start); //O(1)
        do {
            start = getNodeWithUnfinishedEdges(); //O(n)
            subtour.add(start); //O(1)
            Node current = start;
            do {
                Node temp = getAdjacentNodes(current).get(0); //O(n)
                current.getEdges().remove(temp); //O(1)
                temp.getEdges().remove(current); //O(1)
                current = temp;
                subtour.add(current); //O(1)
            } while (start != current); //O(n)
            integrateTour(tour, subtour); //O(n)
        } while (!verifyEulerTour()); //O(n)
        tour.remove(0); //O(n)
    }

    /**
     * O(n)
     */
    public boolean checkGraphForEuler() {
        //Hier wird geprueft, wie viele Kantenenden ein Knoten hat. Ist der Grad ungerade, wird die Schleife abgebrochen und ein false zurueckgegeben.
        //Ist der Grad gerade, wird ein true zurueckgegeben, da es sich dann eindeutig um einen Eulergraph handelt.
        boolean value = true;
        for (Node nodes : graph) { //O(n)
            if (nodes.getEdges().size() % 2 != 0) { //O(1)
                value = false;
                break;
            }
        }
        return value;
    }

    /**
     * O(n)
     */
    public List<Node> getAdjacentNodes(Node node) {
        //Hier werden die adjazenten Knoten vom uebergebenen Knoten zurueckgegeben.
        return new ArrayList<>(node.getEdges().keySet()); //O(n) * O(1)
    }

    /**
     * O(n)
     */
    public void integrateTour(List<Node> tour, List<Node> subtour) {
        //Hat die Tour nur einen Knoten, werden alle Knoten der Subtour an das Ende von Tour hinzugefuegt.
        //Befinden sich jedoch bereits mehrere Knoten in der Tour Liste, wird der Index des letzten Auftretens des Startknotes von Subtour innerhalb Tour zurueckgegeben.
        //Dieser Knoten wird dann durch alle Knoten von Subtour ersetzt.
        if (tour.size() == 1) { //O(1)
            tour.addAll(subtour); //O(1)
        } else {
            Node firstNodeSubtour = subtour.get(0); //O(1)
            int index = tour.lastIndexOf(firstNodeSubtour); //O(n)
            tour.addAll(index, subtour); //O(n)
            tour.remove(index + subtour.size()); //O(n)
            }
        subtour.clear(); //O(1)
        }

    /**
     * O(n)
     */
    public boolean verifyEulerTour() {
        //Hier wird lediglich geprueft, ob noch unbesuchte Kanten im Graph existieren. Besuchte Kanten werden geloescht.
        //Werden unbesuchte Kanten gefunden, wird ein false zurueckgegeben. Andernfalls, wird ein true zurueckgegeben.
        boolean value = true;
        for (Node entries : graph) { //O(n)
            if (!entries.getEdges().isEmpty()) { //O(1)
                value = false;
                break;
            }
        }
        return value;
    }

    /**
     * O(n)
     */
    public Node getNodeWithUnfinishedEdges() {
        //Hier wird der erste Knoten vom Eulerkreis, dessen Grad groesser 0 ist, gefunden und zurueckgegeben.
        //Befindet sich lediglich ein Element in Tour, dann wird dieses zurueckgegeben.
        Node newNode = null;
        if(tour.size() == 1) { //O(1)
            newNode = tour.get(0); //O(1)
        } else {
            for (int i = 1; i < tour.size(); i++) { //O(n)
                if (!tour.get(i).getEdges().isEmpty()) { //O(1)
                    newNode = tour.get(i); //O(1)
                    break;
                }
            }
        }
        return newNode;
    }

    /**
     * O(1)
     */
    public List<Node> getTour() {
        return this.tour;
    }
}
