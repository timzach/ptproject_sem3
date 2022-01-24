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

    public void run(Node source) {
        Node start;
        if (!checkGraphForEuler()) {
            throw new RuntimeException("Der uebergebene Graph ist kein Eulergraph!");
        } else {
            start = source;
        }
        tour.add(start);
        do {
            start = getNodeWithUnfinishedEdges();
            subtour.add(start);
            Node current = start;
            do {
                Node temp = getAdjacentNodes(current).get(0);
                current.getEdges().remove(getAdjacentNodes(current).get(0));
                temp.getEdges().remove(current);
                current = temp;
                subtour.add(current);
            } while (start != current);
            integrateTour(tour, subtour);
        } while (!verifyEulerTour());
        tour.remove(0);
    }

    public boolean checkGraphForEuler() {
        //Hier wird gecheckt, wie viele Kantenenden ein Knoten hat. Ist der Grad ungerade, wird die Schleife abgebrochen und ein false zurueckgegeben.
        //Ist der Grad gerade, wird ein true zurueckgegeben, da es sich dann eindeutig um einen Eulergraph handelt.
        boolean value = true;
        for (Node nodes : graph) {
            if (nodes.getEdges().size() % 2 != 0) {
                value = false;
                break;
            }
        }
        return value;
    }

    public List<Node> getAdjacentNodes(Node node) {
        //Hier werden die adjazenten Knoten vom uebergebenen Knoten zurueckgegeben.
        return new ArrayList<>(node.getEdges().keySet());
    }

    public void integrateTour(List<Node> tour, List<Node> subtour) {
        //Hat die Tour nur einen Knoten, werden alle Knoten der Subtour an das Ende von Tour hinzugefuegt.
        //Befinden sich jedoch bereits mehrere Knoten in der Tour Liste, wird der Index des letzten Auftretens des Startknotes von Subtour innerhalb Tour zurueckgegeben.
        //Dieser Knoten wird dann durch alle Knoten von Subtour ersetzt.
        if (tour.size() == 1) {
            tour.addAll(subtour);
        } else {
            Node firstNodeSubtour = subtour.get(0);
            int index = tour.lastIndexOf(firstNodeSubtour);
            tour.addAll(tour.lastIndexOf(firstNodeSubtour), subtour);
            tour.remove(index + subtour.size());
            }
        subtour.clear();
        }

    public boolean verifyEulerTour() {
        //Hier wird lediglich geprueft, ob noch unbesuchte Kanten im Graph existieren. Besuchte Kanten werden geloescht.
        //Werden unbesuchte Kanten gefunden, wird ein false zurueckgegeben. Andernfalls, wird ein true zurueckgegeben.
        boolean value = true;
        for (Node entries : graph) {
            if (!entries.getEdges().isEmpty()) {
                value = false;
                break;
            }
        }
        return value;
    }

    public Node getNodeWithUnfinishedEdges() {
        //Hier wird der erste Knoten vom Eulerkreis, dessen Grad groeßer 0 ist, gefunden und zurueckgegeben.
        //Befindet sich lediglich ein Element in Tour, dann wird dieses zurueckgegeben.
        Node newNode = null;
        if(tour.size() == 1) {
            newNode = tour.get(0);
        } else {
            for (int i = 1; i < tour.size(); i++) {
                if (!tour.get(i).getEdges().isEmpty()) {
                    newNode = tour.get(i);
                    break;
                }
            }
        }
        return newNode;
    }

    public List<Node> getTour() {
        return this.tour;
    }
}
