import FordFulkersonAlgorithmus.*;
import FordFulkersonAlgorithmus.Node;
import PrimAlgorithmus.*;
import Probleme.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserEingabe {

    public static void main(String[] args) {

        int ProblemID = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welches Problem möchten Sie lösen?:");
        System.out.println("Problem 1: Strassenbau");
        System.out.println("Problem 2: Wasserversorgung");
        System.out.println("Problem 3: Feuerwerk");
        System.out.println("Problem 4: Hochzeitspaare");
        System.out.println("Problem 5: Einladungen");
        System.out.println("Problem 6: Verteilung auf die Straßen");
        System.out.println("Problem 7: Kompetenz");
        System.out.print("Geben sie die Nummer Ihres gewünschten Problems ein:");

        boolean correctInput = true;
        while(correctInput) {
            if (scanner.hasNextInt()) {
                ProblemID = scanner.nextInt();
            } else {
                System.out.println("Bitte geben Sie eine ProblemID ein: ");
                scanner.next();
                continue;
            }
            correctInput = false;
        }


        System.out.println("Gewähltes Problem:" + ProblemID);

        switch (ProblemID) {
            case 1:
                System.out.println("Strassenbau");
                strassenbau();
                break;
            case 2:
                System.out.println("Wasserversorgung");
                wasserVersorgung();
                break;
            case 3:
                System.out.println("Feuerwerk");
                break;
            case 4:
                System.out.println("Hochzeitspaare");
                //FordFulkerson
                break;
            case 5:
                System.out.println("Einladungen");
                //euler
                break;
            case 6:
                System.out.println("StrassenVerteilung");
                strassenVerteilung();
                break;
            case 7:
                System.out.println("Kompetenz");
                kompetenzErmittlung();
                break;
        }
    }

    /**
     * Programm für die Loesung des Wasserversorgung Problems.
     */
    private static void wasserVersorgung() {
        List<Node> graph = null;

        int wahl = decide();

        switch (wahl) {
            case 1:
                graph = createGraphFF();
            case 2:
                graph = Problem2.createProblemGraph();
        }

        FordF fordF = new FordF(graph);
        System.out.println(fordF.graphToString());
        System.out.println("-------------------");
        fordF.resetPrintHistory();
        int maxFlow = fordF.run(graph.get(0), graph.get(graph.size() - 1));
        System.out.println(fordF.graphToString());
        System.out.println("-------------------");
        System.out.println("Maximaler Fluss in Ihrem Graphen: " + maxFlow);
        System.out.println("-------------------");
    }

    /**
     * Programm für die Loesung des Kompetenz Problems.
     */
    private static void kompetenzErmittlung() {
        List<Node> graph = null;

        int wahl = decide();

        switch (wahl) {
            case 1:
                graph = createGraphFF();
            case 2:
                graph = Problem7.createProblemGraph();
        }

        FordF fordF = new FordF(graph);
        System.out.println(fordF.graphToString());
        System.out.println("-------------------");
        fordF.resetPrintHistory();
        int maxFlow = fordF.run(getNodeWithLabelFF("s", graph), getNodeWithLabelFF("t", graph));
        System.out.println(fordF.graphToString());
        System.out.println("-------------------");
        System.out.println("Maximaler Fluss in Ihrem Graphen: " + maxFlow);
        System.out.println("-------------------");
    }

    /**
     * Generiert den Graphen fuer einen MaxFlow Algorithmus
     * @return Graphen des MaxFlow Algo
     */
    public static List<FordFulkersonAlgorithmus.Node> createGraphFF() {

        List<Node> graph = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        int anzahlNodes = 0;

        System.out.println("Anzahl Nodes: ");

        boolean correctInputNodes = true;
        while(correctInputNodes) {
            if (scanner.hasNextInt()) {
                anzahlNodes = scanner.nextInt();
            } else {
                System.out.println("Bitte geben Sie die Anzahl der Nodes ein: ");
                scanner.next();
                continue;
            }
            correctInputNodes = false;
        }

        System.out.println("Benennen Sie Ihren Startknoten mit s und ihren Endknoten mit t.");


        for (int i = 0; i < anzahlNodes; i++) {
            System.out.println("Name der " + (i + 1) + "ten Node: ");
            String nodeLabel;
            nodeLabel = scanner.next();
            graph.add(new Node(nodeLabel));
        }

        int anzahlEdges = 0;

        System.out.println("Anzahl Edges: ");

        boolean correctInputEdges = true;
        while(correctInputEdges) {
            if (scanner.hasNextInt()) {
                anzahlEdges = scanner.nextInt();
            } else {
                System.out.println("Bitte geben Sie die Anzahl der Edges ein: ");
                scanner.next();
                continue;
            }
            correctInputEdges = false;
        }

        String startNode;
        Node Start = null;
        String endNode;
        Node End = null;

        int cap;

        for (int i = 0; i < anzahlEdges; i++) {
            System.out.println((i + 1) + "te Edge");
            System.out.println("Startknoten:");
            startNode = scanner.next();
            Start = getNodeWithLabelFF(startNode, graph);

            System.out.println("Endknoten:");
            endNode = scanner.next();
            End = getNodeWithLabelFF(endNode, graph);

            System.out.println("Kapazität: ");
            cap = scanner.nextInt();


            Start.addEdge(End, new FordFulkersonAlgorithmus.Edge(cap));
        }

        return graph;

    }

    /**
     * Programm fuer die Loesung des Strassenverteilung Problems.
     */
    public static void strassenVerteilung() {

        List<Node> graph = null;

        int wahl = decide();

        switch (wahl) {
            case 1:
                graph = createGraphFF();
            case 2:
                graph = Problem6.createProblemGraph();
        }

        FordF fordF = new FordF(graph);
        System.out.println(fordF.graphToString());
        System.out.println("-------------------");
        fordF.resetPrintHistory();
        int maxFlow = fordF.run(getNodeWithLabelFF("s", graph), getNodeWithLabelFF("t", graph));
        System.out.println(fordF.graphToString());
        System.out.println("-------------------");
        System.out.println("Maximaler Fluss in Ihrem Graphen: " + maxFlow);
        System.out.println("-------------------");
    }

    /**
     * Programm fuer die Loesung des Strassenbau Problems.
     */
    public static void strassenbau() {

        List<PrimAlgorithmus.Node> graph = null;

        int wahl = decide();

        switch (wahl) {
            case 1:
                graph = createGraphPrim();
            case 2:
                graph = Problem1.createProblemGraph();
        }

        Prim prim = new Prim(graph);
        System.out.println(prim.originalGraphToString());
        System.out.println("----------------");
        prim.run();
        System.out.println();
        prim.resetPrintHistory();
        System.out.println(prim.minimumSpanningTreeToString());
        System.out.println("Gesamtsumme: " + PrimAlgorithmus.Edge.getSumWeight());
    }

    /**
     * Generiert den Graphen fuer einen Prim Algorithmus
     * @return Graphen des Prim Algo
     */
    private static List<PrimAlgorithmus.Node> createGraphPrim() {
        List<PrimAlgorithmus.Node> graph = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        int anzahlNodes = 0;

        System.out.println("Anzahl Nodes: ");

        boolean correctInputNodes = true;
        while(correctInputNodes) {
            if (scanner.hasNextInt()) {
                anzahlNodes = scanner.nextInt();
            } else {
                System.out.println("Bitte geben Sie die Anzahl der Nodes ein: ");
                scanner.next();
                continue;
            }
            correctInputNodes = false;
        }



        for (int i = 0; i < anzahlNodes; i++) {
            System.out.println("Name der " + (i + 1) + "ten Node: ");
            String nodeLabel;
            nodeLabel = scanner.next();
            graph.add(new PrimAlgorithmus.Node(nodeLabel));
        }

        int anzahlEdges = 0;

        System.out.println("Anzahl Edges: ");

        boolean correctInputEdges = true;
        while(correctInputEdges) {
            if (scanner.hasNextInt()) {
                anzahlEdges = scanner.nextInt();
            } else {
                System.out.println("Bitte geben Sie die Anzahl der Edges ein: ");
                scanner.next();
                continue;
            }
            correctInputEdges = false;
        }

        String startNode;
        PrimAlgorithmus.Node Start = null;
        String endNode;
        PrimAlgorithmus.Node End = null;

        int cap;

        for (int i = 0; i < anzahlEdges; i++) {
            System.out.println((i + 1) + "te Edge");
            System.out.println("Startknoten:");
            startNode = scanner.next();
            System.out.println("Endknoten:");
            endNode = scanner.next();
            System.out.println("Kapazität: ");
            cap = scanner.nextInt();

            for (PrimAlgorithmus.Node node : graph) {
                if (node.getLabel().equals(startNode)) {
                    Start = node;
                }
                if (node.getLabel().equals(endNode)) {
                    End = node;
                }
            }

            if (Start == null) {
                throw new RuntimeException("Start nicht gefunden");
            }

            if (End == null) {
                throw new RuntimeException("EndKnoten nicht gefunden");
            }

            Start.addEdge(End, new PrimAlgorithmus.Edge(cap));
        }

        return graph;

    }

    /**
     * Beinhaltet die Wahl zwischen den 2 Graphtypen
     * @return Integer der Wahl
     */
    public static int decide() {
        Scanner scanner = new Scanner(System.in);

        int wahl = 0;

        System.out.println("Möchten Sie einen eigenen Graph erstellen oder den Graphen des Problems testen?");
        System.out.println("Eigener Graph [1]");
        System.out.println("Graph des Problems [2]");

        boolean correctInput = true;
        while(correctInput) {
            if (scanner.hasNextInt()) {
                wahl = scanner.nextInt();
                if (wahl < 1 || wahl > 2) {
                    System.out.println("Bitte wählen Sie [1] oder [2]. ");
                    continue;
                }
            } else {
                System.out.println("Bitte geben Sie eine Zahl ein.");
                scanner.next();
                continue;
            }
            correctInput = false;
        }
        return wahl;
    }

    /**
     * Findet den Knoten mit dem passenden Label.
     * @param name Name des Knoten
     * @param graph Graph in dem der Knoten gesucht wird
     * @return Knoten mit dem passenden Namen
     */
    public static FordFulkersonAlgorithmus.Node getNodeWithLabelFF(String name, List<FordFulkersonAlgorithmus.Node> graph) {

        FordFulkersonAlgorithmus.Node tmpNode = null;

        for (FordFulkersonAlgorithmus.Node node : graph) {
            if (node.getLabel().equals(name)) {
                tmpNode = node;
                return tmpNode;
            }
        }

        if (tmpNode == null) {
            throw new RuntimeException("Knoten " + name +" nicht im Graphen gefunden");
        }
        return tmpNode;
    }
}

