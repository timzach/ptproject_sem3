import java.util.List;

public class Prim {

    private List<Vertex> graph;

    public void run() {
        if (graph.size() > 0) {
            graph.get(0).setVisited(true);
        }
    }
}
