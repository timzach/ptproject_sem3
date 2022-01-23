package EulerTourAlgorithmus;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EulerTourUnitTest {

    @Test
    public void testEulerGraph() {
        List<Node> graph = new ArrayList<>(createGraph());
        EulerTour euler = new EulerTour(graph);
        euler.run(graph.get(0));
        List<Node> tour = euler.getTour();
        System.out.print("Weg");
        for (Node entries : tour) {
            System.out.print(" -> " + entries);
        }
        System.out.println();
    }

    @Test
    public void testEulerGraph2() {
        List<Node> graph = new ArrayList<>(createGraph2());
        EulerTour euler = new EulerTour(graph);
        euler.run(graph.get(0));
        List<Node> tour = euler.getTour();
        System.out.print("Weg");
        for (Node entries : tour) {
            System.out.print(" -> " + entries);
        }
        System.out.println();
    }

    @Test
    public void testEulerGraph3() {
        List<Node> graph = new ArrayList<>(createGraph3());
        EulerTour euler = new EulerTour(graph);
        euler.run(graph.get(0));
        List<Node> tour = euler.getTour();
        System.out.print("Weg");
        for (Node entries : tour) {
            System.out.print(" -> " + entries);
        }
        System.out.println();
    }

    @Test
    public void testEulerGraphLoop() {
        for(int i = 0; i < 7; i++) {
            List<Node> graph = new ArrayList<>(createGraph());
            EulerTour euler = new EulerTour(graph);
            euler.run(graph.get(i));
            List<Node> tour = euler.getTour();
            System.out.print("Weg");
            for (Node entries : tour) {
                System.out.print(" -> " + entries);
            }
            System.out.println();
        }
    }

    @Test
    public void testEulerGraphLoop2() {
        for(int i = 0; i < 11; i++) {
            List<Node> graph = new ArrayList<>(createGraph2());
            EulerTour euler = new EulerTour(graph);
            euler.run(graph.get(i));
            List<Node> tour = euler.getTour();
            System.out.print("Weg");
            for (Node entries : tour) {
                System.out.print(" -> " + entries);
            }
            System.out.println();
        }
    }

    @Test
    public void testEulerGraphLoop3() {
        for(int i = 0; i < 9; i++) {
            List<Node> graph = new ArrayList<>(createGraph3());
            EulerTour euler = new EulerTour(graph);
            euler.run(graph.get(i));
            List<Node> tour = euler.getTour();
            System.out.print("Weg");
            for (Node entries : tour) {
                System.out.print(" -> " + entries);
            }
            System.out.println();
        }
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


        Edge ab = new Edge("ab");
        a.addEdge(b, ab);
        b.addEdge(a, ab);

        Edge ac = new Edge("ac");
        a.addEdge(c, ac);
        c.addEdge(a, ac);

        Edge bc = new Edge("bc");
        b.addEdge(c, bc);
        c.addEdge(b, bc);

        Edge bd = new Edge("bd");
        b.addEdge(d, bd);
        d.addEdge(b, bd);

        Edge be = new Edge("be");
        b.addEdge(e, be);
        e.addEdge(b, be);

        Edge cd = new Edge("cd");
        c.addEdge(d, cd);
        d.addEdge(c, cd);

        Edge ce = new Edge("ce");
        c.addEdge(e, ce);
        e.addEdge(c, ce);

        Edge de = new Edge("de");
        d.addEdge(e, de);
        e.addEdge(d, de);

        Edge df = new Edge("df");
        d.addEdge(f, df);
        f.addEdge(d, df);

        Edge eg = new Edge("eg");
        e.addEdge(g, eg);
        g.addEdge(e, eg);

        Edge fg = new Edge("fg");
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

    public static List<Node> createGraph2() {

        //Beispiel aus der Vorlesung
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
        Node k = new Node("K");


        Edge ab = new Edge("ab");
        a.addEdge(b, ab);
        b.addEdge(a, ab);

        Edge ac = new Edge("ac");
        a.addEdge(c, ac);
        c.addEdge(a, ac);

        Edge bc = new Edge("bc");
        b.addEdge(c, bc);
        c.addEdge(b, bc);

        Edge be = new Edge("be");
        b.addEdge(e, be);
        e.addEdge(b, be);

        Edge bf = new Edge("bf");
        b.addEdge(f, bf);
        f.addEdge(b, bf);

        Edge cd = new Edge("cd");
        c.addEdge(d, cd);
        d.addEdge(c, cd);

        Edge cg = new Edge("cg");
        c.addEdge(g, cg);
        g.addEdge(c, cg);

        Edge dh = new Edge("dh");
        d.addEdge(h, dh);
        h.addEdge(d, dh);

        Edge ef = new Edge("ef");
        e.addEdge(f, ef);
        f.addEdge(e, ef);

        Edge ei = new Edge("ei");
        e.addEdge(i, ei);
        i.addEdge(e, ei);

        Edge ek = new Edge("ek");
        e.addEdge(k, ek);
        k.addEdge(e, ek);

        Edge fg = new Edge("fg");
        f.addEdge(g, fg);
        g.addEdge(f, fg);

        Edge fk = new Edge("fk");
        f.addEdge(k, fk);
        k.addEdge(f, fk);

        Edge gh = new Edge("gh");
        g.addEdge(h, gh);
        h.addEdge(g, gh);

        Edge gk = new Edge("gk");
        g.addEdge(k, gk);
        k.addEdge(g, gk);

        Edge hj = new Edge("hj");
        h.addEdge(j, hj);
        j.addEdge(h, hj);

        Edge hk = new Edge("hk");
        h.addEdge(k, hk);
        k.addEdge(h, hk);

        Edge ik = new Edge("ik");
        i.addEdge(k, ik);
        k.addEdge(i, ik);

        Edge jk = new Edge("jk");
        j.addEdge(k, jk);
        k.addEdge(j, jk);

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
        graph.add(k);

        return graph;
    }

    public static List<Node> createGraph3() {

        //Beispiel aus Wikipedia
        List<Node> graph = new ArrayList<>();

        Node a = new Node("1");
        Node b = new Node("2");
        Node c = new Node("3");
        Node d = new Node("4");
        Node e = new Node("5");
        Node f = new Node("6");
        Node g = new Node("7");
        Node h = new Node("8");
        Node i = new Node("9");

        Edge ab = new Edge("ab");
        a.addEdge(b, ab);
        b.addEdge(a, ab);

        Edge ac = new Edge("ac");
        a.addEdge(c, ac);
        c.addEdge(a, ac);

        Edge ag = new Edge("ag");
        a.addEdge(g, ag);
        g.addEdge(a, ag);

        Edge ah = new Edge("ah");
        a.addEdge(h, ah);
        h.addEdge(a, ah);

        Edge bc = new Edge("bc");
        b.addEdge(c, bc);
        c.addEdge(b, bc);

        Edge cd = new Edge("cd");
        c.addEdge(d, cd);
        d.addEdge(c, cd);

        Edge cg = new Edge("cg");
        c.addEdge(g, cg);
        g.addEdge(c, cg);

        Edge de = new Edge("de");
        d.addEdge(e, de);
        e.addEdge(d, de);

        Edge dg = new Edge("dg");
        d.addEdge(g, dg);
        g.addEdge(d, dg);

        Edge di = new Edge("di");
        d.addEdge(i, di);
        i.addEdge(d, di);

        Edge ei = new Edge("ei");
        e.addEdge(i, ei);
        i.addEdge(e, ei);

        Edge fg = new Edge("fg");
        f.addEdge(g, fg);
        g.addEdge(f, fg);

        Edge fi = new Edge("fi");
        f.addEdge(i, fi);
        i.addEdge(f, fi);

        Edge gh = new Edge("gh");
        g.addEdge(h, gh);
        h.addEdge(g, gh);

        Edge gi = new Edge("gi");
        g.addEdge(i, gi);
        i.addEdge(g, gi);

        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.add(d);
        graph.add(e);
        graph.add(f);
        graph.add(g);
        graph.add(h);
        graph.add(i);

        return graph;
    }
}
