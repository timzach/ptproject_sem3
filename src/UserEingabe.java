import MaxFlowAlgorithmus.Edge;
import MaxFlowAlgorithmus.MaxF;
import MaxFlowAlgorithmus.Node;
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
                //dijkstra
                break;
            case 4:
                System.out.println("Hochzeitspaare");
                hochzeitspaare();
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
     * Programm fuer die Loesung des Wasserversorgung Problems.
     */
    private static void wasserVersorgung() {
        List<Node> graph = null;

        int wahl = decide();
        Node startNode = null;
        Node endNode = null;

        switch (wahl) {
            case 1:
                graph = createGraphMaxFlow();
                System.out.println("Geben sie den Startknoten an: ");
                startNode = knotenEingabeMaxFlow(graph);
                System.out.println("Geben sie den Endknoten an: ");
                endNode = knotenEingabeMaxFlow(graph);

                System.out.println("Start: " + startNode.getLabel() + " Ende: " + endNode.getLabel());
            case 2:
                graph = Problem2.createProblemGraph();
                startNode = getNodeWithLabelMaxFlow("w", graph);
                endNode = getNodeWithLabelMaxFlow("s", graph);
        }



        MaxF maxF = new MaxF(graph);
        System.out.println(maxF.graphToString());
        System.out.println("-------------------");
        maxF.resetPrintHistory();
        int maxFlow = maxF.run(startNode, endNode);
        System.out.println("-------------------");
        System.out.println("Maximaler Fluss in Ihrem Graphen: " + maxFlow);
        System.out.println("-------------------");
        //maxF.resetPrintHistory();
        System.out.println(maxF.residualGraphToString());
    }

    /**
     * Programm fuer die Loesung des Kompetenz Problems.
     */
    private static void kompetenzErmittlung() {
        List<Node> graph = null;

        int wahl = decide();

        switch (wahl) {
            case 1:
                graph = createGraphMaxFlowMatching();
            case 2:
                graph = Problem7.createProblemGraph();
        }

        MaxF maxF = new MaxF(graph);
        System.out.println(maxF.graphToString());
        System.out.println("-------------------");
        maxF.resetPrintHistory();
        int maxFlow = maxF.run(getNodeWithLabelMaxFlow("s", graph),getNodeWithLabelMaxFlow("t", graph));
        System.out.println("-------------------");
        System.out.println("Maximaler Fluss: " + maxFlow);
        System.out.println("-------------------");
        maxF.resetPrintHistory();
        System.out.println(maxF.residualGraphToStringMatchingKompetenz());
    }

    /**
     * Programm fuer die Loesung des Hochzeitspaar Problems.
     */
    private static void hochzeitspaare() {
        List<Node> graph = null;

        int wahl = decide();

        switch (wahl) {
            case 1:
                graph = createGraphMaxFlowMatching();
            case 2:
                graph = Problem4.createProblemGraph();
        }

        MaxF maxF = new MaxF(graph);
        System.out.println(maxF.graphToString());
        System.out.println("-------------------");
        maxF.resetPrintHistory();
        maxF.checkMatching();
        System.out.println(maxF.graphToString());
        System.out.println("-------------------");
        maxF.resetPrintHistory();
        int maxFlow = maxF.run(getNodeWithLabelMaxFlow("s", graph),getNodeWithLabelMaxFlow("t", graph));
        System.out.println("-------------------");
        System.out.println("Maximaler Fluss: " + maxFlow);
        System.out.println("-------------------");
        maxF.resetPrintHistory();
        System.out.println(maxF.residualGraphToStringMatchingPaare());
    }

    /**
     * Programm fuer die Loesung des Strassenverteilung Problems.
     */
    public static void strassenVerteilung() {

        List<Node> graph = null;

        int wahl = decide();
        Node startNode = null;
        Node endNode = null;

        switch (wahl) {
            case 1:
                graph = createGraphMaxFlow();
                System.out.println("Geben sie den Startknoten an: ");
                startNode = knotenEingabeMaxFlow(graph);
                System.out.println("Geben sie den Endknoten an: ");
                endNode = knotenEingabeMaxFlow(graph);

                System.out.println("Start: " + startNode.getLabel() + " Ende: " + endNode.getLabel());
            case 2:
                graph = Problem6.createProblemGraph();
                startNode = getNodeWithLabelMaxFlow("s", graph);
                endNode = getNodeWithLabelMaxFlow("t", graph);
        }



        MaxF maxF = new MaxF(graph);
        System.out.println(maxF.graphToString());
        System.out.println("-------------------");
        maxF.resetPrintHistory();
        int maxFlow = maxF.run(startNode, endNode);
        System.out.println("-------------------");
        System.out.println("Maximaler Fluss in Ihrem Graphen: " + maxFlow);
        System.out.println("-------------------");
        maxF.resetPrintHistory();
        System.out.println(maxF.residualGraphToStringParking());
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


        PrimAlgorithmus.Node Start = null;

        PrimAlgorithmus.Node End = null;

        int weight;

        for (int i = 0; i < anzahlEdges; i++) {
            while (true) {
                System.out.println((i + 1) + "te Edge");
                System.out.println("Startknoten:");
                Start = knotenEingabePrim(graph);
                System.out.println("Endknoten:");
                End = knotenEingabePrim(graph);

                if (Start == End) {
                    System.out.println("Sie können nicht zwei Knoten miteinander verbinden, bitte wählen Sie zwei unterschiedliche Knoten!");
                    continue;
                } else {
                    System.out.println("Gewicht: ");
                    weight = scanner.nextInt();
                    break;
                }
            }
            Start.addEdge(End, new PrimAlgorithmus.Edge(weight));
        }
        return graph;
    }

    /**
     * Generiert den Graphen fuer einen MaxFlow Algorithmus
     * @return Graphen des MaxFlow Algo
     */
    public static List<Node> createGraphMaxFlow() {

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

        //System.out.println("Benennen Sie Ihren Startknoten mit s und ihren Endknoten mit t.");


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

        Node Start = null;
        Node End = null;
        int cap;

        for (int i = 0; i < anzahlEdges; i++) {
            while (true) {
                System.out.println((i + 1) + "te Edge");
                System.out.println("Startknoten:");
                Start = knotenEingabeMaxFlow(graph);
                System.out.println("Endknoten:");
                End = knotenEingabeMaxFlow(graph);

                if (Start == End) {
                    System.out.println("Sie können nicht zwei Knoten miteinander verbinden, bitte wählen Sie zwei unterschiedliche Knoten!");
                    continue;
                } else {
                    System.out.println("Kapazität: ");
                    cap = scanner.nextInt();
                    break;
                }
            }



            Start.addEdge(End, new Edge(cap));
        }

        return graph;

    }

    /**
     * Generiert den Graphen fuer einen MaxFlow Matching Algorithmus
     * @return Graphen des MaxFlow Matching Algo
     */
    public static List<Node> createGraphMaxFlowMatching() {

        List<Node> graph = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        int anzahlNodesErsteGruppe = 0;

        System.out.println("Anzahl Nodes in der ersten Gruppe: ");

        boolean correctInputNodes = true;
        while(correctInputNodes) {
            if (scanner.hasNextInt()) {
                anzahlNodesErsteGruppe = scanner.nextInt();
            } else {
                System.out.println("Bitte geben Sie die Anzahl der Nodes ein: ");
                scanner.next();
                continue;
            }
            correctInputNodes = false;
        }

        //s und t initialisieren

        Node s = new Node("s");
        Node t = new Node("t");
        graph.add(s);
        graph.add(t);


        for (int i = 0; i < anzahlNodesErsteGruppe; i++) {
            System.out.println("Name der " + (i + 1) + "ten Node: ");
            String nodeLabel;
            nodeLabel = scanner.next();
            graph.add(new Node(nodeLabel, 1));
        }

        int anzahlNodesZweiteGruppe = 0;

        System.out.println("Anzahl Nodes in der ersten Gruppe: ");

        boolean correctInputNodesTwo = true;
        while(correctInputNodesTwo) {
            if (scanner.hasNextInt()) {
                anzahlNodesZweiteGruppe = scanner.nextInt();
            } else {
                System.out.println("Bitte geben Sie die Anzahl der Nodes ein: ");
                scanner.next();
                continue;
            }
            correctInputNodesTwo = false;
        }

        //System.out.println("Benennen Sie Ihren Startknoten mit s und ihren Endknoten mit t.");


        for (int i = 0; i < anzahlNodesZweiteGruppe; i++) {
            System.out.println("Name der " + (i + 1) + "ten Node: ");
            String nodeLabel;
            nodeLabel = scanner.next();
            graph.add(new Node(nodeLabel, 2));
        }

        //Kanten von s zu Gruppe 1 und von Gruppe 2 zu t

        for (Node node : graph) {
            if (node.getGroup() == 1) {
                s.addEdge(node, new Edge(1));
            }
            if (node.getGroup() == 2) {
                node.addEdge(t, new Edge(1));
            }
        }


        int anzahlEdges = 0;

        System.out.println("Anzahl Edges zwischen den Paaren: ");

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

        Node Start = null;
        Node End = null;
        int cap;

        for (int i = 0; i < anzahlEdges; i++) {
            while (true) {
                System.out.println((i + 1) + "te Edge");
                System.out.println("Startknoten:");
                Start = knotenEingabeMaxFlow(graph);
                System.out.println("Endknoten:");
                End = knotenEingabeMaxFlow(graph);

                if (Start == End) {
                    System.out.println("Sie können nicht zwei Knoten miteinander verbinden, bitte wählen Sie zwei unterschiedliche Knoten!");
                    continue;
                } else {
                    System.out.println("Kapazität: ");
                    cap = scanner.nextInt();
                    break;
                }
            }
            Start.addEdge(End, new Edge(cap));
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
     * @param name Name des gesuchten Knoten
     * @param graph Graph in dem der Knoten gesucht wird
     * @return Knoten mit dem passenden Namen
     */
    public static Node getNodeWithLabelMaxFlow(String name, List<Node> graph) {

        for (Node node : graph) {
            if (node.getLabel().equals(name)) {
                Node tmpNode = node;
                return tmpNode;
            }
        }

//        if (tmpNode == null) {
//            throw new RuntimeException("Knoten " + name +" nicht im Graphen gefunden");
//        }
        return null;
    }

    /**
     * String Eingabe wird in den passenden Knoten im Graphen umgewandelt (MaxFlow)
     * @param graph Graphen in dem der Knoten gespeichert ist.
     * @return Knoten dessen Name eingegeben wurde.
     */
    public static Node knotenEingabeMaxFlow(List<Node> graph) {
        Scanner scanner = new Scanner(System.in);

        String nodeLabel = null;

        boolean correctInput = true;
        while(correctInput) {
            if (scanner.hasNextLine()) {
                nodeLabel = scanner.nextLine();
                if (getNodeWithLabelMaxFlow(nodeLabel, graph) == null) {
                    System.out.println("Bitte geben Sie einen Knoten aus dem Graphen an. ");
                    continue;
                }
            } else {
                System.out.println("Bitte geben Sie einen String ein.");
                scanner.next();
                continue;
            }
            correctInput = false;
        }
        return getNodeWithLabelMaxFlow(nodeLabel, graph);
    }


    /**
     * Findet den Knoten mit dem passenden Label
     * @param name Name des gesuchten Knotens
     * @param graph Grap in dem der Knoten gesucht wird
     * @return Knoten mit dem passenden Namen
     */
    public static PrimAlgorithmus.Node getNodeWithLabelPrim(String name, List<PrimAlgorithmus.Node> graph) {

        for (PrimAlgorithmus.Node node : graph) {
            if (node.getLabel().equals(name)) {
                PrimAlgorithmus.Node tmpNode = node;
                return tmpNode;
            }
        }
        return null;
    }

    /**
     * String Eingabe wird in den passenden Knoten im Graphen umgewandelt (Prim)
     * @param graph Graphen in dem der Knoten gespeichert ist.
     * @return Knoten dessen Name eingegeben wurde.
     */
    public static PrimAlgorithmus.Node knotenEingabePrim(List<PrimAlgorithmus.Node> graph) {
        Scanner scanner = new Scanner(System.in);

        String nodeLabel = null;

        boolean correctInput = true;
        while(correctInput) {
            if (scanner.hasNextLine()) {
                nodeLabel = scanner.nextLine();
                if (getNodeWithLabelPrim(nodeLabel, graph) == null) {
                    System.out.println("Bitte geben Sie einen Knoten aus dem Graphen an. ");
                    continue;
                }
            } else {
                System.out.println("Bitte geben Sie einen String ein.");
                scanner.next();
                continue;
            }
            correctInput = false;
        }
        return getNodeWithLabelPrim(nodeLabel, graph);
    }
}

