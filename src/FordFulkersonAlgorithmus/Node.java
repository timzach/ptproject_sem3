package FordFulkersonAlgorithmus;

import java.util.*;

public class Node implements Comparable {

    private String label = null;
    private Map<Node, Edge> edges = new HashMap<>();
    private Map<Node, Edge> residualEdges;

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

    public void addResidualEdge(Node node, Edge edge) {
        if (this.residualEdges.containsKey(node)) {
            int addCapacity = edge.getCapacity();
            int currentCapacity = this.residualEdges.get(node).getCapacity();
            edge.setCapacity(currentCapacity + addCapacity);
            this.residualEdges.replace(node, edge);
        } else {
            this.residualEdges.put(node, edge);
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

    public int getEdgeCapacity(Node target) {
        if (this.residualEdges.containsKey(target)) {
            return residualEdges.get(target).getCapacity();
        }
        return 0;

    }

    public void reduceCapacity(Node target, int value) {
        if (this.residualEdges.containsKey(target)) {
            boolean tmpBoolean = residualEdges.get(target).reduceCapacity(value);
            if (!tmpBoolean) {
                this.residualEdges.remove(target);
            }
            return; //damit es nicht in die Exception läuft
        }
        throw new RuntimeException("no Edge found when trying to reduceCapacity");
    }

    public Map<Node, Edge> getResidualEdges() {
        return residualEdges;
    }

    public void createResidualEdges() {
        residualEdges = new HashMap<Node, Edge>(edges);
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

    public Optional<List<Node>> path_bfs(Node target, Set<Node> visited) {
        createResidualEdges();
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

    public void reset() {
        edges.forEach((k, v) -> {
            v.reset();
        });
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Node) {
            Node tmp = (Node) o;
            return tmp.label.compareTo(this.label);
        }
        return 0;
    }

    public String residualToString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Node, Edge> pair : residualEdges.entrySet()) {
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
}
