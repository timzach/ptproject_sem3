

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

    public static List<Vertex> createGraph() {

/*
        List<Vertex> graph = new ArrayList<>();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Edge ab = new Edge(2);
        a.addEdge(b, ab);
        b.addEdge(a, ab);
        Edge ac = new Edge(3);
        a.addEdge(c, ac);
        c.addEdge(a, ac);
        Edge bc = new Edge(2);
        b.addEdge(c, bc);
        c.addEdge(b, bc);
        Edge be = new Edge(5);
        b.addEdge(e, be);
        e.addEdge(b, be);
        Edge cd = new Edge(1);
        c.addEdge(d, cd);
        d.addEdge(c, cd);
        Edge ce = new Edge(1);
        c.addEdge(e, ce);
        e.addEdge(c, ce);
        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.add(d);
        graph.add(e);
*/

        List<Vertex> graph = new ArrayList<>();


        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");
        Vertex g = new Vertex("G");
        Vertex h = new Vertex("H");
        Vertex i = new Vertex("I");
        Vertex j = new Vertex("J");


        Edge ab = new Edge(5);
        a.addEdge();
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