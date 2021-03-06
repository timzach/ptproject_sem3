package MaxFlowAlgorithmus;

import java.util.*;

public class MaxF {

    private List<Node> graph;

    public MaxF(List<Node> graph) {
        this.graph = graph;
    }

    /**
     * O(n^3) Findet den MaxFlow zwischen einem Start- und Endknoten
     * @param source Startknoten
     * @param target Zielknoten
     * @return MaxFlow als Integer
     */
    public int run(Node source, Node target) {//O(n*e + e(n^3)) = O(n^3 * e)

        createResidualGraph();//O(n*e)

        int maxFlow = 0;
        //while bis es keinen Weg mehr gibt O(e)
        while (true) {
            //in der while Schleife: O(n^3 + n + n + n + n) = O(n^3)
            resetPrintHistory();

            Optional<List<Node>> tmp = source.path_bfs(target, new HashSet<>());//O(n^3)

            if (tmp.isEmpty()) {
                break;
            }
            List<Node> reversePath = tmp.get();
            List<Node> path = new ArrayList<>(reversePath);
            Collections.reverse(path);

            //minimum Kapazität finden
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < path.size()-1; i++) {//O(n)
                int tempMin = path.get(i).getEdgeCapacity(path.get(i + 1));
                if (tempMin < min) {
                    min = tempMin;
                }
            }

            //neue Rückfluss-Kanten mit cap = minKap erstellen

            Node previousNode = reversePath.get(0);

            for (Node node : reversePath) {//O(n)
                if (node == previousNode) {
                    continue;
                }
                previousNode.addResidualEdge(node, new Edge(min));
                previousNode = node;
            }

            for (int i = 0; i < path.size()-1; i++) {//O(n)
                path.get(i).reduceCapacity(path.get(i + 1), min);
            }

            maxFlow += min;
        }
        return maxFlow;
    }

    /**
     * O(n*e) Kopiert fuer jeden Knoten die Rueckflussedges
     */
    public void createResidualGraph() {
        for (Node node : graph) {
            node.createResidualEdges();
        }
    }

    /**
     * O((n*e)^2) Ueberprueft alle Knoten der Gruppe 1 ob es eine Kante hin und zurueck gibt.
     */
    public void checkMatching() {
        for (Node node : graph) {
            if (node.getGroup() == 1) {
                node.searchEdgeReturn();
            }
        }
        for (Node node : graph) {
            node.removeNotNeeded();
        }
    }

    /**
     * @return String des Anfangsgraphen
     */
    public String graphToString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : graph) {
            sb.append(node.originalToString());
        }
        return sb.toString();
    }

    /**
     * @return String des Rueckflussgraphen
     */
    public String residualGraphToString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : graph) {
            sb.append(node.residualToString());
        }
        return sb.toString();
    }

    /**
     * @return String der Rueckflusskanten fuer die Strassenverteilung
     */
    public String residualGraphToStringParking() {
        StringBuilder sb = new StringBuilder();
        for (Node node : graph) {
            sb.append(node.residualToStringParking());
        }
        return sb.toString();
    }

    /**
     * @return String des Rueckflussgraphen ohne die Knoten s&t.
     */
    public String residualGraphToStringMatching() {
        StringBuilder sb = new StringBuilder();
        for (Node node : graph) {
            sb.append(node.residualToStringMatching());
        }
        return sb.toString();
    }

    /**
     * @return String des Rueckflussgraphen ohne die Knoten s&t fuer das Kompetenz Problem.
     */
    public String residualGraphToStringMatchingKompetenz() {
        StringBuilder sb = new StringBuilder();
        for (Node node : graph) {
            sb.append(node.residualToStringMatchingKompetenz());
        }
        return sb.toString();
    }


    /**
     * @return String des Rueckflussgraphen ohne die Knoten s&t fuer das Paar Problem.
     */
    public String residualGraphToStringMatchingPaare() {
        StringBuilder sb = new StringBuilder();
        for (Node node : graph) {
            sb.append(node.residualToStringMatchingPaare());
        }
        return sb.toString();
    }


    /**
     * Setzt den Wert isPrinted bei jedem Knoten im Graphen zurueck.
     */
    public void resetPrintHistory() {
        for (Node node : graph) {
            for (Map.Entry<Node, Edge> pair : node.getEdges().entrySet()) {
                pair.getValue().setPrinted(false);
            }
            if (node.checkResidualHasContent()) {
                for (Map.Entry<Node, Edge> pair : node.getResidualEdges().entrySet()) {
                    pair.getValue().setPrinted(false);
                }
            }
        }
    }
}
