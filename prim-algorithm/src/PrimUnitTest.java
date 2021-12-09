

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
        a.addEdge(b, ab);
        b.addEdge(a, ab);

        Edge bc = new Edge(3);
        b.addEdge(c, bc);
        c.addEdge(b, bc);

        Edge ad = new Edge(3);
        c.addEdge(a, ad);
        d.addEdge(d, ad);

        Edge ae = new Edge(4);
        a.addEdge(a, ae);
        e.addEdge(e, ae);

        Edge ed = new Edge(5);
        e.addEdge(e, ed);
        d.addEdge(d, ed);

        Edge db = new Edge(3);
        d.addEdge(d, db);
        b.addEdge(b, db);

        Edge df = new Edge(3);
        d.addEdge(d, df);
        f.addEdge(f, df);

        Edge bf = new Edge(2);
        b.addEdge(b, bf);
        f.addEdge(f, bf);

        Edge bj = new Edge(4);
        b.addEdge(b, bj);
        j.addEdge(j, bj);

        Edge cj = new Edge(2);
        c.addEdge(c, cj);
        j.addEdge(j, cj);

        Edge fj = new Edge(3);
        f.addEdge(f, fj);
        j.addEdge(j, fj);

        Edge dg = new Edge(4);
        d.addEdge(d, dg);
        g.addEdge(g, dg);

        Edge eg = new Edge(4);
        e.addEdge(e, eg);
        g.addEdge(g, eg);

        Edge eh = new Edge(2);
        e.addEdge(e, eh);
        h.addEdge(h, eh);

        Edge hg = new Edge(3);
        h.addEdge(h, hg);
        g.addEdge(g, hg);

        Edge hi = new Edge(4);
        h.addEdge(h, hi);
        i.addEdge(i, hi);

        Edge gi = new Edge(2);
        g.addEdge(g, gi);
        i.addEdge(i, gi);

        Edge gf = new Edge(4);
        g.addEdge(g, gf);
        f.addEdge(f, gf);

        Edge fi = new Edge(3);
        f.addEdge(f, fi);
        i.addEdge(i, fi);

        Edge ij = new Edge(4);
        i.addEdge(i, ij);
        j.addEdge(j, ij);

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