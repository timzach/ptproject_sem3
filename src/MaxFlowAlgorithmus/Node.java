package MaxFlowAlgorithmus;

import java.util.*;

public class Node {

        private String label = null;
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

    public void setLabel(String label) {
        this.label = label;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public Map<Node, Edge> getEdges() {
        return edges;
    }

    public void setEdges(Map<Node, Edge> edges) {
        this.edges = edges;
    }

    /**
     * Fuegt eine Kante hinzu, wenn bereits eine Kante mit geringer Kapazitaet existiert wird diese durch die neue Kante ersetzt.
     * @param node Zielknoten der Kante
     * @param edge Die Kante zum hinzufügen
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
     * Fuegt eine Rueckfluss-Kante hinzu oder erhoeht die Kapazität wenn diese Kante bereits existiert.
     * @param node Zielknoten der Kante
     * @param edge Die Kante zum hinzufuegen
     */
    public void addResidualEdge(Node node, Edge edge) {
        if (this.residualEdges.containsKey(node)) {
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
     * Wenn der Knoten die Kante besitzt gibt es die Kapazitaet zurueck
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
     * Wenn der Knoten die Kante hat reduziert sie dessen Kapazitaet.
     * <p>Wenn die Kapazitaet gleich O ist wird die Kante geloescht</p>
     * @param target Zielknoten der Kante
     * @param value Wert wie viel die Kapazitaet reduziert wird
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

    public Map<Node, Edge> getResidualEdges() {
        return residualEdges;
    }

    /**
     * Erstellt eine Kopie der Hashmap edges in residualEdges.
     */
    public void createResidualEdges() {
        residualEdges = new HashMap<>();

        for (Map.Entry<Node, Edge> pair : edges.entrySet()) {
            residualEdges.put(pair.getKey(), new Edge(pair.getValue()));
        }
    }

    public Optional<List<Node>> path_dfs(Node target, Set<Node> visited) {

        if (edges.containsKey(target) && !edges.get(target).isFull()) {
            List<Node> path = new ArrayList<>();
            path.add(target);
            path.add(this);

            return Optional.of(path);
        }
        visited.add(this);
        //Set<Node> nodeSet = edges.keySet();
        //List<Node> nodeList = new LinkedList<Node>(nodeSet);
        //Collections.shuffle(nodeList);
        for (Node node : edges.keySet()) {
            if (!visited.contains(node)) {
                if (!edges.get(node).isFull()) {
                    Optional<List<Node>> tmp = node.path_dfs(target, visited);

                    if (tmp.isPresent()) {
                        tmp.get().add(this);
                        return tmp;
                    }
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Sucht einen Weg zu einem Knoten mit der Breitensuche
     * @param target Zielknoten
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

        while (!nodeQueue.isEmpty()) {
            Node currentNode = nodeQueue.poll();
            for (Node node : currentNode.getResidualEdges().keySet()) {
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
            while (!path.contains(source)) {
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
     * @return String der Ausgabe
     */
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

    /**
     * Generiert die Ausgabe fuer den Graphen mit den Rueckflusskanten
     * @return String der Ausgabe
     */
    public String residualToString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Node, Edge> pair : residualEdges.entrySet()) {
            if (pair.getValue().isResidual()) {
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

        }
        return sb.toString();
    }

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
                            sb.append("/");
                            sb.append(pair.getValue().getFlow());
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
     * Ueberprueft den Inhalt in residualEdges
     * @return true, wenn die Hashmap Inhalt hat <p>false, wenn keine Rueckflusskanten vorhanden sind</p>
     */
    public boolean checkResidualHasContent() {
        if (this.residualEdges == null) {
            return false;
        } else {
            return true;
        }
    }

    public void searchEdgeReturn() {
       for (Node node : edges.keySet()) {
            if (node.getEdges().containsKey(this)) {
                node.edges.remove(this);
            } else {
                this.edges.get(node).setNotNeeded(true);
            }
        }
    }

    public void removeNotNeeded() {
        edges.entrySet().removeIf(entry -> entry.getValue().isNotNeeded());
    }
}
