package Probleme;

import FordFulkersonAlgorithmus.Edge;
import FordFulkersonAlgorithmus.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse fuer das Wasserversorgung-Problem
 */
public class Problem2 {
    /**
     * Die Methode generiert den Problem-Graphen
     * @return den Problem-Graphen
     */
    public static List<Node> createProblemGraph() {
        List<Node> graph = new ArrayList<>();

        Node w = new Node("w");
        Node t = new Node("t");
        Node i = new Node("i");
        Node c = new Node("c");
        Node d = new Node("d");
        Node a = new Node("a");
        Node b = new Node("b");
        Node h = new Node("h");
        Node s = new Node("s");

        Edge wt = new Edge(15000);
        w.addEdge(t, wt);

        Edge wi = new Edge(6);
        w.addEdge(i, wi);

        Edge wc = new Edge(12);
        w.addEdge(c, wc);

        Edge da = new Edge(5);
        d.addEdge(a, da);

        Edge db = new Edge(6);
        d.addEdge(b, db);

        Edge ib = new Edge(3);
        i.addEdge(b, ib);

        Edge ih = new Edge(1);
        i.addEdge(h, ih);

        Edge ca = new Edge(5);
        c.addEdge(a, ca);

        Edge ch = new Edge(5);
        c.addEdge(h, ch);

        Edge as = new Edge(10);
        a.addEdge(s, as);

        Edge bs = new Edge(10);
        b.addEdge(s, bs);

        Edge hs = new Edge(7);
        h.addEdge(s, hs);

        Edge td = new Edge(8);
        t.addEdge(d, td);

        graph.add(w);
        graph.add(t);
        graph.add(i);
        graph.add(c);
        graph.add(d);
        graph.add(a);
        graph.add(b);
        graph.add(h);
        graph.add(s);

        return graph;
    }
}
