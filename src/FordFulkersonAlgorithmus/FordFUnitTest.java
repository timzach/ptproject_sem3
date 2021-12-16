package FordFulkersonAlgorithmus;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FordFUnitTest {

    @Test
    public void giveGraph(){
        FordF fordF = new FordF(createGraph());
        System.out.println(fordF.originalGraphToString());
        System.out.println("-------------------");
        fordF.resetPrintHistory();

    }

    public static List<Node> createGraph() {

        List<Node> graph = new ArrayList<>();

        Node s = new Node("s");
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node t = new Node("t");

        Edge sa = new Edge(14);
        s.addEdge(a, sa);

        Edge sb = new Edge(16);
        s.addEdge(b, sb);

        Edge ba = new Edge(6);
        b.addEdge(a, ba);

        Edge ac = new Edge(15);
        a.addEdge(c, ac);

        Edge bd = new Edge(15);
        b.addEdge(d, bd);

        Edge cb = new Edge(4);
        c.addEdge(b, cb);

        Edge cd = new Edge(7);
        c.addEdge(d, cd);

        Edge ct = new Edge(10);
        c.addEdge(t, ct);

        Edge dt = new Edge(17);
        d.addEdge(t, dt);

        graph.add(s);
        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.add(d);
        graph.add(t);

        return graph;

    }

}
