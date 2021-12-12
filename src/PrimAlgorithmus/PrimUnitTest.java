package PrimAlgorithmus;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PrimUnitTest {

    @Test
    public void givenAGraph_whenPrimRuns_thenPrintMST() {
        Prim prim = new Prim(createGraph());
        System.out.println(prim.originalGraphToString());
        System.out.println("----------------");
        prim.run();
        System.out.println();
        prim.resetPrintHistory();
        System.out.println(prim.minimumSpanningTreeToString());
    }

    public static List<Node> createGraph() {

        List<Node> graph = new ArrayList<>();


        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");
        Node g = new Node("G");
        Node h = new Node("H");
        Node i = new Node("I");
        Node j = new Node("J");


        Edge ab = new Edge(5);
        a.addEdge(b, ab);
        b.addEdge(a, ab);

        Edge bc = new Edge(3);
        b.addEdge(c, bc);
        c.addEdge(b, bc);

        Edge ad = new Edge(3);
        a.addEdge(d, ad);
        d.addEdge(a, ad);

        Edge ae = new Edge(4);
        a.addEdge(e, ae);
        e.addEdge(a, ae);

        Edge ed = new Edge(5);
        e.addEdge(d, ed);
        d.addEdge(e, ed);

        Edge db = new Edge(3);
        d.addEdge(b, db);
        b.addEdge(d, db);

        Edge df = new Edge(3);
        d.addEdge(f, df);
        f.addEdge(d, df);

        Edge bf = new Edge(2);
        b.addEdge(f, bf);
        f.addEdge(b, bf);

        Edge bj = new Edge(4);
        b.addEdge(j, bj);
        j.addEdge(b, bj);

        Edge cj = new Edge(2);
        c.addEdge(j, cj);
        j.addEdge(c, cj);

        Edge fj = new Edge(3);
        f.addEdge(j, fj);
        j.addEdge(f, fj);

        Edge dg = new Edge(4);
        d.addEdge(g, dg);
        g.addEdge(d, dg);

        Edge eg = new Edge(4);
        e.addEdge(g, eg);
        g.addEdge(e, eg);

        Edge eh = new Edge(2);
        e.addEdge(h, eh);
        h.addEdge(e, eh);

        Edge hg = new Edge(3);
        h.addEdge(g, hg);
        g.addEdge(h, hg);

        Edge hi = new Edge(4);
        h.addEdge(i, hi);
        i.addEdge(h, hi);

        Edge gi = new Edge(2);
        g.addEdge(i, gi);
        i.addEdge(g, gi);

        Edge gf = new Edge(4);
        g.addEdge(f, gf);
        f.addEdge(g, gf);

        Edge fi = new Edge(3);
        f.addEdge(i, fi);
        i.addEdge(f, fi);

        Edge ij = new Edge(4);
        i.addEdge(j, ij);
        j.addEdge(i, ij);

        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.add(d);
        graph.add(e);
        graph.add(f);
        graph.add(g);
        graph.add(h);
        graph.add(i);
        graph.add(j);

        return graph;
    }

}