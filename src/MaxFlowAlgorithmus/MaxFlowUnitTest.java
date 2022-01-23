package MaxFlowAlgorithmus;

import org.junit.jupiter.api.Test;

import java.util.*;

public class MaxFlowUnitTest {

    @Test
    public void giveGraph(){
        List<Node> graph = createGraph();
//        testForest(graphen,"s","t");
        MaxF maxF = new MaxF(graph);
        System.out.println(maxF.graphToString());
        maxF.resetPrintHistory();
        int maxFlow = maxF.run(graph.get(0),graph.get(graph.size()-1));
        System.out.println("-------------------");
        System.out.println("Maximaler Fluss: " + maxFlow);
        System.out.println("-------------------");
        maxF.resetPrintHistory();
        System.out.println(maxF.residualGraphToString());

    }

    private List<Node> createGraph() {

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
        graph.add(d);
        graph.add(c);
        graph.add(b);
        graph.add(a);
        graph.add(t);

        /*List<Node> graph = new ArrayList<>();

        Node s = new Node("s");

        Node maier = new Node("Maier");
        Node mueller = new Node("Mueller");
        Node augst = new Node("Augst");
        Node schmidt = new Node("Schmidt");
        Node kunze = new Node("Kunze");
        Node hof = new Node("Hof");
        Node lustig = new Node("Lustig");

        Node strassenbau = new Node("Strassenbau");
        Node verkehrsplanung = new Node("Verkehrsplanung");
        Node archaeologie = new Node("Archaeologie");
        Node modeberatung = new Node("Modeberatung");
        Node hochzeitsplanung = new Node("Hochzeitsplanung");
        Node wasserversorgung = new Node("Wasserversorgung");
        Node wettkampfausrichtung = new Node("Wettkampfausrichtung");

        Node t = new Node("t");

        Edge smaier = new Edge(1);
        s.addEdge(maier, smaier);

        Edge smueller = new Edge(1);
        s.addEdge(mueller, smueller);

        Edge saugst = new Edge(1);
        s.addEdge(augst, saugst);

        Edge sschmidt = new Edge(1);
        s.addEdge(schmidt, sschmidt);

        Edge skunze = new Edge(1);
        s.addEdge(kunze, skunze);

        Edge shof = new Edge(1);
        s.addEdge(hof, shof);

        Edge slustig = new Edge(1);
        s.addEdge(lustig, slustig);


        Edge a = new Edge(1);
        maier.addEdge(strassenbau, a);

        Edge b = new Edge(1);
        maier.addEdge(verkehrsplanung, b);

        Edge c = new Edge(1);
        maier.addEdge(archaeologie, c);

        Edge d = new Edge(1);
        mueller.addEdge(modeberatung, d);

        Edge e = new Edge(1);
        mueller.addEdge(hochzeitsplanung, e);

        Edge f = new Edge(1);
        augst.addEdge(strassenbau, f);

        Edge g = new Edge(1);
        augst.addEdge(wasserversorgung, g);

        Edge h = new Edge(1);
        schmidt.addEdge(strassenbau, h);

        Edge i = new Edge(1);
        schmidt.addEdge(verkehrsplanung, i);

        Edge j = new Edge(1);
        schmidt.addEdge(wettkampfausrichtung, j);

        Edge k = new Edge(1);
        kunze.addEdge(archaeologie, k);

        Edge l = new Edge(1);
        kunze.addEdge(hochzeitsplanung, l);

        Edge m = new Edge(1);
        hof.addEdge(strassenbau, m);

        Edge n = new Edge(1);
        hof.addEdge(modeberatung, n);

        Edge o = new Edge(1);
        lustig.addEdge(verkehrsplanung, o);

        Edge p = new Edge(1);
        lustig.addEdge(wettkampfausrichtung, p);

        Edge strassenbauT = new Edge(1);
        strassenbau.addEdge(t, strassenbauT);

        Edge verkehrsplanungT = new Edge(1);
        verkehrsplanung.addEdge(t, verkehrsplanungT);

        Edge archaeologieT = new Edge(1);
        archaeologie.addEdge(t, archaeologieT);

        Edge modeberatungT = new Edge(1);
        modeberatung.addEdge(t, modeberatungT);

        Edge hochzeitsplanungT = new Edge(1);
        hochzeitsplanung.addEdge(t, hochzeitsplanungT);

        Edge wasserversorgungT = new Edge(1);
        wasserversorgung.addEdge(t, wasserversorgungT);

        Edge wettkampfausrichtungT = new Edge(1);
        wettkampfausrichtung.addEdge(t, wettkampfausrichtungT);

        graph.add(s);
        graph.add(maier);
        graph.add(mueller);
        graph.add(augst);
        graph.add(schmidt);
        graph.add(kunze);
        graph.add(hof);
        graph.add(lustig);

        graph.add(strassenbau);
        graph.add(verkehrsplanung);
        graph.add(archaeologie);
        graph.add(modeberatung);
        graph.add(hochzeitsplanung);
        graph.add(wasserversorgung);
        graph.add(wettkampfausrichtung);
        graph.add(t);*/

        return graph;
    }


}
