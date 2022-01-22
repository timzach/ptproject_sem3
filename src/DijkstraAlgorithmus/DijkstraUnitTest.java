package DijkstraAlgorithmus;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;


public class DijkstraUnitTest {

    @Test
    public void testDijkstra() {
        List<Node> graph = createGraph();
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.run(graph.get(0));
        int i = 1;
        System.out.println("Reihenfolge des Feuerwerks:");
        for(Object entries: dijkstra.getDistance()) {
            System.out.println(i + ". " + entries);
            i++;
        }
    }

    public static List<Node> createGraph() {

        List<Node> graph = new ArrayList<>();

        Node s = new Node("Streichholz");
        Node a = new Node("Feuerwerk 1");
        Node b = new Node("Feuerwerk 2");
        Node c = new Node("Feuerwerk 3");
        Node d = new Node("Feuerwerk 4");
        Node e = new Node("Feuerwerk 5");
        Node f = new Node("Feuerwerk 6");
        Node g = new Node("Feuerwerk 7");
        Node h = new Node("Feuerwerk 8");

        Edge sa = new Edge(2);
        s.addEdge(a, sa);
        a.addEdge(s, sa);

        Edge ab = new Edge(7);
        a.addEdge(b, ab);
        b.addEdge(a, ab);

        Edge ac = new Edge(2);
        a.addEdge(c, ac);
        c.addEdge(a, ac);

        Edge ae = new Edge(8);
        a.addEdge(e, ae);
        e.addEdge(a, ae);

        Edge bc = new Edge(3);
        b.addEdge(c, bc);
        c.addEdge(b, bc);

        Edge ce = new Edge(5);
        c.addEdge(e, ce);
        e.addEdge(c, ce);

        Edge de = new Edge(6);
        d.addEdge(e, de);
        e.addEdge(d, de);

        Edge df = new Edge(1);
        d.addEdge(f, df);
        f.addEdge(d, df);

        Edge dg = new Edge(6);
        d.addEdge(g, dg);
        g.addEdge(d, dg);

        Edge eh = new Edge(4);
        e.addEdge(h, eh);
        h.addEdge(e, eh);

        Edge fg = new Edge(6);
        f.addEdge(g, fg);
        g.addEdge(f, fg);

        Edge fh = new Edge(5);
        f.addEdge(h, fh);
        h.addEdge(f, fh);

        graph.add(s);
        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.add(d);
        graph.add(e);
        graph.add(f);
        graph.add(g);
        graph.add(h);

        return graph;
    }
}
