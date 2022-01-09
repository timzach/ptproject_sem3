package FordFulkersonAlgorithmus;

import org.junit.jupiter.api.Test;

import java.util.*;
//import java.util.stream.IntStream;


public class FordFUnitTest {

    @Test
    public void giveGraph(){
        List<Node> graph = createGraph();
//        testForest(graphen,"s","t");
        FordF fordF = new FordF(graph);
        //System.out.println(fordF.originalGraphToString());
        System.out.println("-------------------");
        fordF.resetPrintHistory();
        for (int i = 0; i < 200; i++) {
            int maxFlow = fordF.run(graph.get(0),graph.get(graph.size()-1));
            //System.out.println(fordF.originalGraphToString());
            System.out.println("-------------------");
            System.out.println("Maximaler Fluss: " + maxFlow);
            System.out.println("-------------------");
            fordF.resetPrintHistory();

            for (Node node: graph) {
                node.reset();
                //node.shuffle();
            }

        }
    }

/*    public static void testForest( List<Node> base, String source, String target)
    {
        IntStream.range(0, 500).forEach(i ->
        {
            int index = (int) (Math.random() * 6);
            int index2= (int) (Math.random() * 6);
            Collections.swap(base,index, index2);
            Node sourceNode = null;
            Node targetNode = null;
            for (Node node : base)
            {
                if(node.getLabel().equals(source))
                    sourceNode = node;
                if(node.getLabel().equals(target))
                    targetNode = node;
            }
            FordF alg = new FordF(base);
            printCombination(base);
            System.out.println(alg.run(sourceNode,targetNode));
            System.out.println("-------------------");
            base.forEach( e -> {e.reset();});
        });

    }

    public static void printCombination( List<Node> base)
    {
        for (int i = 0; i < base.size()-1; i++)
        {
            System.out.print(base.get(i).getLabel() + "-" );
        }
        System.out.print(base.get(base.size()-1).getLabel() + "\n" );
    }*/


    public static List<Node> createGraph() {

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

/*        Scanner scanner = new Scanner(System.in);

        String startNode;
        String endNode;
        String nameNode;
        int cap;

        System.out.println("Startknoten:");
        startNode = scanner.nextLine();
        System.out.println("Endknoten:");
        endNode = scanner.nextLine();
        nameNode = startNode+endNode;
        System.out.println("Kapazität");
        cap = scanner.nextInt();*/



        Edge dt = new Edge(17);
        d.addEdge(t, dt);

        graph.add(s);
        graph.add(d);
        graph.add(c);
        graph.add(b);
        graph.add(a);
        graph.add(t);



        return graph;

    }

}
