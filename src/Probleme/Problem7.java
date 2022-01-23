package Probleme;

import MaxFlowAlgorithmus.*;

import java.util.ArrayList;
import java.util.List;
/**
 * Klasse fuer das Kompetenz-Problem
 */
public class Problem7 {
    /**
     * Die Methode generiert den Problem-Graphen
     * @return den Problem-Graphen
     */
    public static List<Node> createProblemGraph() {

        List<Node> graph = new ArrayList<>();

        Node s = new Node("s");
        Node t = new Node("t");

        Node maier = new Node("Maier", 1);
        Node mueller = new Node("Mueller", 1);
        Node augst = new Node("Augst", 1);
        Node schmidt = new Node("Schmidt", 1);
        Node kunze = new Node("Kunze", 1);
        Node hof = new Node("Hof", 1);
        Node lustig = new Node("Lustig", 1);

        Node strassenbau = new Node("Strassenbau", 2);
        Node verkehrsplanung = new Node("Verkehrsplanung", 2);
        Node archaeologie = new Node("Archaeologie", 2);
        Node modeberatung = new Node("Modeberatung", 2);
        Node hochzeitsplanung = new Node("Hochzeitsplanung", 2);
        Node wasserversorgung = new Node("Wasserversorgung", 2);
        Node wettkampfausrichtung = new Node("Wettkampfausrichtung", 2);

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
        graph.add(t);

        return graph;
    }
}
