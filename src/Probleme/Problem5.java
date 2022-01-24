package Probleme;

import EulerTourAlgorithmus.Edge;
import EulerTourAlgorithmus.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse fuer das Einladungs-Problem
 */
public class Problem5 {
    public static List<Node> createProblemGraph() {
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



        Edge ab = new Edge("ab");
        a.addEdge(b, ab);
        b.addEdge(a, ab);

        Edge bc = new Edge("bc");
        b.addEdge(c, bc);
        c.addEdge(b, bc);

        Edge ad = new Edge("ad");
        a.addEdge(d, ad);
        d.addEdge(a, ad);

        Edge ae = new Edge("ae");
        a.addEdge(e, ae);
        e.addEdge(a, ae);

        Edge ed = new Edge("ed");
        e.addEdge(d, ed);
        d.addEdge(e, ed);

        Edge db = new Edge("db");
        d.addEdge(b, db);
        b.addEdge(d, db);

        Edge df = new Edge("df");
        d.addEdge(f, df);
        f.addEdge(d, df);

        Edge bf = new Edge("bf");
        b.addEdge(f, bf);
        f.addEdge(b, bf);

        Edge bj = new Edge("bj");
        b.addEdge(j, bj);
        j.addEdge(b, bj);

        Edge cj = new Edge("cj");
        c.addEdge(j, cj);
        j.addEdge(c, cj);

        Edge fj = new Edge("fj");
        f.addEdge(j, fj);
        j.addEdge(f, fj);

        Edge dg = new Edge("dg");
        d.addEdge(g, dg);
        g.addEdge(d, dg);

        Edge eg = new Edge("eg");
        e.addEdge(g, eg);
        g.addEdge(e, eg);

        Edge eh = new Edge("eh");
        e.addEdge(h, eh);
        h.addEdge(e, eh);

        Edge hg = new Edge("hg");
        h.addEdge(g, hg);
        g.addEdge(h, hg);

        Edge hi = new Edge("hi");
        h.addEdge(i, hi);
        i.addEdge(h, hi);

        Edge gi = new Edge("gi");
        g.addEdge(i, gi);
        i.addEdge(g, gi);

        Edge gf = new Edge("gf");
        g.addEdge(f, gf);
        f.addEdge(g, gf);

        Edge fi = new Edge("fi");
        f.addEdge(i, fi);
        i.addEdge(f, fi);

        Edge ij = new Edge("ij");
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
