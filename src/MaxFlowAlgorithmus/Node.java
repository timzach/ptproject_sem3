package MaxFlowAlgorithmus;

import java.util.*;

public class Node {

    private final String label;
    private int group = 0;
    /**
     * Hashmap "edges" speichert den Zielknoten als Key und die Kante als Value
     */
    private Map<Node, Edge> edges = new HashMap<>();
    private Map<Node, Edge> residualEdges;


    public Node(String label) {
        this.label = label;
    }

    public Node(String label, int group) {
        this.label = label;
        this.group = group;
    }

    public String getLabel() {
        return label;
    }

    public int getGroup() {
        return group;
    }

    public Map<Node, Edge> getEdges() {
        return edges;
    }

    /**
     * O(1) Fuegt eine Kante hinzu, wenn bereits eine Kante mit geringer Kapazitaet existiert wird diese durch die neue Kante ersetzt.
     *
     * @param node Zielknoten der Kante
     * @param edge Die Kante zum hinzufuegen
     */
    public void addEdge(Node node, Edge edge) {
        if (this.edges.containsKey(node)) {
            if (edge.getCapacity() > this.edges.get(node).getCapacity()) {
                this.edges.replace(node, edge);
            }
        } else {
            this.edges.put(node, edge);
        }
    }

    /**
     * O(1) Fuegt eine Rueckfluss-Kante hinzu oder erhoeht die Kapazitaet wenn diese Kante bereits existiert.
     *
     * @param node Zielknoten der Kante
     * @param edge Die Kante zum hinzufuegen
     */
    public void addResidualEdge(Node node, Edge edge) {
        if (this.residualEdges.containsKey(node)) {
            edge.setResidual(true);
            int addCapacity = edge.getCapacity();
            int currentCapacity = this.residualEdges.get(node).getCapacity();
            edge.setCapacity(currentCapacity + addCapacity);
            this.residualEdges.replace(node, edge);
        } else {
            edge.setResidual(true);
            this.residualEdges.put(node, edge);
        }
    }

    /**
     * O(1) Wenn der Knoten die Kante besitzt gibt es die Kapazitaet zurueck
     *
     * @param target Zielknoten der Kante
     * @return Kapazitaet als Integer
     */
    public int getEdgeCapacity(Node target) {
        if (this.residualEdges.containsKey(target)) {
            return residualEdges.get(target).getCapacity();
        }
        return 0;

    }

    /**
     * O(1)Wenn der Knoten die Kante hat reduziert sie dessen Kapazitaet.
     * <p>Wenn die Kapazitaet gleich O ist wird die Kante geloescht</p>
     *
     * @param target Zielknoten der Kante
     * @param value  Wert wie viel die Kapazitaet reduziert wird
     */
    public void reduceCapacity(Node target, int value) {
        if (this.residualEdges.containsKey(target)) {
            if (!residualEdges.get(target).reduceCapacity(value)) {
                if (this.residualEdges.remove(target) == null) {
                    throw new RuntimeException("hat nichts entfernt");
                }

            }
        } else {
            throw new RuntimeException("no Edge found when trying to reduceCapacity");
        }
    }

    /**
     * O(1)
     */
    public Map<Node, Edge> getResidualEdges() {
        return residualEdges;
    }

    /**
     * O(e)
     * Erstellt eine Kopie der Hashmap edges in residualEdges.
     */
    public void createResidualEdges() {
        residualEdges = new HashMap<>();

        for (Map.Entry<Node, Edge> pair : edges.entrySet()) {
            residualEdges.put(pair.getKey(), new Edge(pair.getValue()));
        }
    }

    /**
     * O(n^2 + n) Sucht einen Weg zu einem Knoten mit der Breitensuche
     *
     * @param target  Zielknoten
     * @param visited Leeres Set um besuchte Knoten zu speichern
     * @return Den Weg zum Zielknoten, wenn keiner mehr vorhanden dann gibt es eine leere Liste zurueck.
     */
    public Optional<List<Node>> path_bfs(Node target, Set<Node> visited) {

        Queue<Node> nodeQueue = new LinkedList<>();
        //Hashmap<Child Node, Parent Node>
        Map<Node, Node> parents = new HashMap<>();
        List<Node> path = new ArrayList<>();


        Node source = this;
        //Source Node in Queue
        nodeQueue.add(this);
        visited.add(this);
        //O(n^2)
        while (!nodeQueue.isEmpty()) {//O(n)
            Node currentNode = nodeQueue.poll();
            for (Node node : currentNode.getResidualEdges().keySet()) {//O(e)
                if (!visited.contains(node)) {
                    //if (!currentNode.getEdges().get(node).isFull()) {
                    nodeQueue.add(node);
                    parents.put(node, currentNode);
                    visited.add(node);
                    if (visited.contains(target)) {
                        break;
                    }
                    //}
                }
            }
        }
        //Überprüfen ob target gefunden wurde:
        if (visited.contains(target)) {
            //Den weg zurück über die parents verknüpfungen
            //target in path schreiben
            path.add(target);
            //target aus parents nehmen und den parent knoten ausfindig machen
            Node parentNode = parents.get(target);
            //schleife bis path contains source:
            while (!path.contains(source)) {//O(n)
                //parentknoten in path schreiben
                path.add(parentNode);
                //parentknoten in tmp speichern
                Node tmpNode = parentNode;
                //parentknoten von tmp in parentknoten schreiben
                parentNode = parents.get(tmpNode);
            }
            return Optional.of(path);
        } else
            return Optional.empty();
    }

    /**
     * Generiert die Ausgabe fuer den Basis-Graphen
     *
     * @return String der Ausgabe
     */
    public String originalToString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Node, Edge> pair : edges.entrySet()) {
            if (!pair.getValue().isPrinted()) {
                sb.append(getLabel());
                sb.append(" --- ");
                sb.append(pair.getValue().getCapacity());
                sb.append(" --> ");
                sb.append(pair.getKey().getLabel());
                sb.append("\n");
                pair.getValue().setPrinted(true);
            }
        }
        return sb.toString();
    }

    /**
     * Generiert die Ausgabe fuer den Graphen mit den Rueckflusskanten
     *
     * @return String der Ausgabe
     */
    public String residualToString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Node, Edge> pair : residualEdges.entrySet()) {
            //if (pair.getValue().isResidual()) {
            if (!pair.getValue().isPrinted()) {
                sb.append(getLabel());
                sb.append(" --- ");
                sb.append(pair.getValue().getCapacity());
                sb.append(" --> ");
                sb.append(pair.getKey().getLabel());
                sb.append("\n");
                pair.getValue().setPrinted(true);
            }
            //}

        }
        return sb.toString();
    }

    /**
     * Generiert die Ausgabe fuer den Graphen mit Rueckflusskanten ohne die Knoten s&t.
     *
     * @return String der Ausgabe
     */
    public String residualToStringMatching() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Node, Edge> pair : residualEdges.entrySet()) {
            if (pair.getValue().isResidual()) {
                if (!pair.getValue().isPrinted()) {
                    if (!getLabel().equals("s") && !getLabel().equals("t")) {
                        if (!pair.getKey().getLabel().equals("s") && !pair.getKey().getLabel().equals("t")) {
                            sb.append(getLabel());
                            sb.append(" --- ");
                            sb.append(pair.getValue().getCapacity());
                            sb.append(" --> ");
                            sb.append(pair.getKey().getLabel());
                            sb.append("\n");
                            pair.getValue().setPrinted(true);
                        }
                    }

                }
            }

        }
        return sb.toString();
    }

    /**
     * Generiert die Ausgabe nur mit den Rueckfluss kanten fuer das Problem der Strassenverteilung
     *
     * @return String der Ausgabe
     */
    public String residualToStringParking() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Node, Edge> pair : residualEdges.entrySet()) {
            if (pair.getValue().isResidual()) {
                if (!pair.getValue().isPrinted()) {
                    sb.append("Auf der Straße von ");
                    sb.append(getLabel());
                    sb.append(" nach ");
                    sb.append(pair.getKey().getLabel());
                    sb.append(" koennen ");
                    sb.append(pair.getValue().getCapacity());
                    sb.append(" Autos parken.");

                    sb.append("\n");
                    pair.getValue().setPrinted(true);
                }
            }

        }
        return sb.toString();
    }


    /**
     * Generiert die Ausgabe fuer den Graphen mit Rueckflusskanten ohne die Knoten s&t fuer die Hochzeitspaare.
     *
     * @return String der Ausgabe
     */
    public String residualToStringMatchingPaare() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Node, Edge> pair : residualEdges.entrySet()) {
            if (pair.getValue().isResidual()) {
                if (!pair.getValue().isPrinted()) {
                    if (!getLabel().equals("s") && !getLabel().equals("t")) {
                        if (!pair.getKey().getLabel().equals("s") && !pair.getKey().getLabel().equals("t")) {
                            sb.append(pair.getKey().getLabel());
                            sb.append(" heiratet ");
                            sb.append(getLabel());
                            sb.append(". ");
                            sb.append("\n");
                            pair.getValue().setPrinted(true);
                        }
                    }
                }
            }
        }
        return sb.toString();
    }


    /**
     * Generiert die Ausgabe fuer den Graphen mit Rueckflusskanten ohne die Knoten s&t fuer die Kompetenzen.
     *
     * @return String der Ausgabe
     */
    public String residualToStringMatchingKompetenz() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Node, Edge> pair : residualEdges.entrySet()) {
            if (pair.getValue().isResidual()) {
                if (pair.getKey().getGroup() != 2) {
                    if (!pair.getValue().isPrinted()) {
                        if (!getLabel().equals("s") && !getLabel().equals("t")) {
                            if (!pair.getKey().getLabel().equals("s") && !pair.getKey().getLabel().equals("t")) {
                                sb.append(pair.getKey().getLabel());
                                sb.append(" uebernimmt ");
                                sb.append(getLabel());
                                sb.append(". ");
                                sb.append("\n");
                                pair.getValue().setPrinted(true);
                            }
                        }
                    }
                }
            }

        }
        return sb.toString();
    }


    /**
     * O(1)
     * Ueberprueft den Inhalt in residualEdges
     * @return true, wenn die Hashmap Inhalt hat <p>false, wenn keine Rueckflusskanten vorhanden sind</p>
     */
    public boolean checkResidualHasContent() {
        return this.residualEdges != null;
    }

    /**
     * O(e) Checkt ob es eine Kante zurück zum Ausgangsknoten gibt, löscht die Kante zurück zum Ausgangsknoten.
     * Wenn der Knoten aus der anderen Gruppe kein Edge zurück zum Ausgangsknoten hat setzt es notNeeded auf true.
     */
    public void searchEdgeReturn() {
        for (Node node : edges.keySet()) {
            if (node.getEdges().containsKey(this)) {
                node.edges.remove(this);
            } else {
                this.edges.get(node).setNotNeeded(true);
            }
        }
    }

    /**
     * O(e) Entfernt alle Kanten die nicht gebraucht werden.
     */
    public void removeNotNeeded() {
        edges.entrySet().removeIf(entry -> entry.getValue().isNotNeeded());
    }
}
