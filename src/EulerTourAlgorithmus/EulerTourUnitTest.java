package EulerTourAlgorithmus;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EulerTourUnitTest {

    @Test
    public void testEuler() {
        List<Node> graph = new ArrayList<>(createGraph());
        EulerTour euler = new EulerTour(graph);
        euler.run(graph.get(0));
    }

    public static List<Node> createGraph() {

        //Haus vom Nikolaus mit Kellererweiterung
        List<Node> graph = new ArrayList<>();

        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");
        Node g = new Node("G");


        Edge ab = new Edge(1);
        a.addEdge(b, ab);
        b.addEdge(a, ab);

        Edge ac = new Edge(1);
        a.addEdge(c, ac);
        c.addEdge(a, ac);

        Edge bc = new Edge(1);
        b.addEdge(c, bc);
        c.addEdge(b, bc);

        Edge bd = new Edge(1);
        b.addEdge(d, bd);
        d.addEdge(b, bd);

        Edge be = new Edge(1);
        b.addEdge(e, be);
        e.addEdge(b, be);

        Edge cd = new Edge(1);
        c.addEdge(d, cd);
        d.addEdge(c, cd);

        Edge ce = new Edge(1);
        c.addEdge(e, ce);
        e.addEdge(c, ce);

        Edge de = new Edge(1);
        d.addEdge(e, de);
        e.addEdge(d, de);

        Edge df = new Edge(1);
        d.addEdge(f, df);
        f.addEdge(d, df);

        Edge eg = new Edge(1);
        e.addEdge(g, eg);
        g.addEdge(e, eg);

        Edge fg = new Edge(1);
        f.addEdge(g, fg);
        g.addEdge(f, fg);

        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.add(d);
        graph.add(e);
        graph.add(f);
        graph.add(g);

        return graph;
    }
}
