package MaxFlowAlgorithmus;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MaxFlowMatchingUnitTest {
    @Test
    public void giveGraph(){
        List<Node> graph = createGraph();
//        testForest(graphen,"s","t");
        MaxF maxF = new MaxF(graph);
        System.out.println(maxF.graphToString());
        System.out.println("-------------------");
        maxF.resetPrintHistory();
        maxF.checkMatching();
        System.out.println(maxF.graphToString());
        System.out.println("-------------------");
        maxF.resetPrintHistory();
        int maxFlow = maxF.run(graph.get(0),graph.get(graph.size()-1));
        System.out.println("-------------------");
        System.out.println("Maximaler Fluss: " + maxFlow);
        System.out.println("-------------------");
        maxF.resetPrintHistory();
        System.out.println(maxF.residualGraphToStringMatching());

    }

    private List<Node> createGraph() {
        List<Node> graph = new ArrayList<>();

        Node s = new Node("s");
        Node marie = new Node("Marie", 1);
        Node susanne = new Node("Susanne",1);
        Node antonia = new Node("Antonia",1);
        Node lena = new Node("Lena",1);
        Node ida = new Node("Ida",1);
        Node anna = new Node("Anna",1);
        Node peter = new Node("Peter",2);
        Node jonas = new Node("Jonas",2);
        Node felix = new Node("Felix",2);
        Node mats = new Node("Mats",2);
        Node aaron = new Node("Aaron",2);
        Node tom = new Node("Tom",2);
        Node t = new Node("t");

        Edge sMarie = new Edge(1);
        s.addEdge(marie, sMarie);

        Edge sSusanne = new Edge(1);
        s.addEdge(susanne, sSusanne);

        Edge sAntonia = new Edge(1);
        s.addEdge(antonia, sAntonia);

        Edge sLena = new Edge(1);
        s.addEdge(lena, sLena);

        Edge sIda = new Edge(1);
        s.addEdge(ida, sIda);

        Edge sAnna = new Edge(1);
        s.addEdge(anna, sAnna);

        Edge peterT = new Edge(1);
        peter.addEdge(t, peterT);

        Edge jonasT = new Edge(1);
        jonas.addEdge(t, jonasT);

        Edge felixT = new Edge(1);
        felix.addEdge(t, felixT);

        Edge matsT = new Edge(1);
        mats.addEdge(t, matsT);

        Edge aaronT = new Edge(1);
        aaron.addEdge(t, aaronT);

        Edge tomT = new Edge(1);
        tom.addEdge(t, tomT);

        Edge mariePeter = new Edge(1);
        marie.addEdge(peter, mariePeter);

        Edge marieMats = new Edge(1);
        marie.addEdge(mats, marieMats);

        Edge susanneJonas = new Edge(1);
        susanne.addEdge(jonas, susanneJonas);

        Edge susanneFelix = new Edge(1);
        susanne.addEdge(felix, susanneFelix);

        Edge antoniaJonas = new Edge(1);
        antonia.addEdge(jonas, antoniaJonas);

        Edge antoniaTom = new Edge(1);
        antonia.addEdge(tom, antoniaTom);

        Edge lenaPeter = new Edge(1);
        lena.addEdge(peter, lenaPeter);

        Edge lenaMats = new Edge(1);
        lena.addEdge(mats, lenaMats);

        Edge lenaAaron = new Edge(1);
        lena.addEdge(aaron, lenaAaron);

        Edge idaTom = new Edge(1);
        ida.addEdge(tom, idaTom);

        Edge annaAaron = new Edge(1);
        anna.addEdge(aaron, annaAaron);

        Edge peterLena = new Edge(1);
        peter.addEdge(lena, peterLena);

        Edge peterIda = new Edge(1);
        peter.addEdge(ida, peterIda);

        Edge jonasMarie = new Edge(1);
        jonas.addEdge(marie, jonasMarie);

        Edge jonasAntonia = new Edge(1);
        jonas.addEdge(antonia, jonasAntonia);

        Edge felixLena = new Edge(1);
        felix.addEdge(lena, felixLena);

        Edge felixSusanne = new Edge(1);
        felix.addEdge(susanne, felixSusanne);

        Edge matsIda = new Edge(1);
        mats.addEdge(ida, matsIda);

        Edge matsSusanne = new Edge(1);
        mats.addEdge(susanne, matsSusanne);

        Edge matsMarie = new Edge(1);
        mats.addEdge(marie, matsMarie);

        Edge matsLena = new Edge(1);
        mats.addEdge(lena, matsLena);

        Edge aaronAntonia = new Edge(1);
        aaron.addEdge(antonia, aaronAntonia);

        Edge aaronAnna = new Edge(1);
        aaron.addEdge(anna, aaronAnna);

        Edge tomAnna = new Edge(1);
        tom.addEdge(anna, tomAnna);

        Edge tomIda = new Edge(1);
        tom.addEdge(ida, tomIda);

        graph.add(s);
        graph.add(marie);
        graph.add(susanne);
        graph.add(antonia);
        graph.add(lena);
        graph.add(ida);
        graph.add(anna);
        graph.add(peter);
        graph.add(jonas);
        graph.add(felix);
        graph.add(mats);
        graph.add(aaron);
        graph.add(tom);
        graph.add(t);

        return graph;
    }
}
