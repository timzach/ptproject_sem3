package FordFulkersonAlgorithmus;

import java.util.*;

public class Node {

    private String label = null;
    private Map<Node, Edge> edges = new HashMap<>();
    private boolean isVisited = false;

    public Node(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Map<Node, Edge> getEdges() {
        return edges;
    }

    public void setEdges(Map<Node, Edge> edges) {
        this.edges = edges;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public void addEdge(Node node, Edge edge) {
        if (this.edges.containsKey(node)) {
            if (edge.getCapacity() < this.edges.get(node).getCapacity()) {
                this.edges.replace(node, edge);
            }
        } else {
            this.edges.put(node, edge);
        }
    }

    public boolean checkEdgesFull() {
        for (Edge edge : edges.values()) {
            if (!edge.isFull()) {
                return false;
            }
        }
        return true;
    }

    public int getRemainingCapacity(Node target) {
        if (this.edges.containsKey(target)) {
            return edges.get(target).getRemainingCapacity();
        }
        return 0;

    }

    public void fill(Node target, int value) {
        if (this.edges.containsKey(target)) {
            edges.get(target).fill(value);
            return; //damit es nicht in die Exception läuft
        }
        throw new RuntimeException("no Edge found when trying to fill");
    }

    //TODO: Überprüfen ob das eine Tiefensuche ist (sollte so sein)

    public Optional<List<Node>> path_dfs(Node target, Set<Node> visited) {

        if (edges.containsKey(target) && !edges.get(target).isFull()) {
            List<Node> path = new ArrayList<>();
            path.add(target);
            path.add(this);

            return Optional.of(path);
        }
        visited.add(this);
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

    //TODO: Breitensuche Implementieren

    public Optional<List<Node>> path_bfs(Node target, Set<Node> visited) {

        //queue initialisieren
        Queue<Node> NodeQueue = new LinkedList<Node>();

        //source in die Queue packen
        NodeQueue.add(this);

        //Queue abarbeiten:
        while (!NodeQueue.isEmpty()) {

            //peek tmp speichern um es am ende wieder in die queue zu packen
            Node tmp = NodeQueue.peek();

            //wenn peek=target ist dann soll alles gelöscht werden bis peek=source ist
            if (tmp == target) {
                while (NodeQueue.peek() != this) {
                    NodeQueue.remove();
                }
                //queue in die liste umwandeln
                List<Node> path = (List<Node>) NodeQueue;
                return Optional.of(path);
            }

            //Nachfolgenden Nodes von Pop in die queue
            Node QueueStart = NodeQueue.poll();
            //TODO: Weg finden damit man die Nodes von den Edges bekommt zum hinzufügen
            for (Node node : edges.keySet()) {
                if (edges.containsKey(tmp)) {
                    if (!visited.contains(tmp)) {
                        if (!edges.get(node).isFull()) {
                            NodeQueue.add(node);
                        }
                    }
                }
            }
            //tmp visited setzen
            visited.add(tmp);
            //tmp wieder in die queue packen
            if (!edges.get(tmp).isFull()) {
                NodeQueue.add(tmp);
            }



        }

        return Optional.empty();

    }



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

    public void reset()
    {
        edges.forEach( (k,v) -> {v.reset();});
    }
}
