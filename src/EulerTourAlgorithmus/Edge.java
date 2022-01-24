package EulerTourAlgorithmus;

public class Edge {

    private final String label;

    public Edge(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}