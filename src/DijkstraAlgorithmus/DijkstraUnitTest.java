package DijkstraAlgorithmus;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DijkstraUnitTest {

    @Test
    public void testDijkstra() {
        //Create nodes
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");

        //Create edges
        Edge ab = new Edge(2, a, b);
        Edge ba = new Edge(2, b, a);
        Edge ac = new Edge(4, a, c);
        Edge ca = new Edge(4, c, a);
        Edge bd = new Edge(3, b, d);
        Edge db = new Edge(3, d, b);
        Edge ce = new Edge(6, c, e);
        Edge ec = new Edge(6, e, c);
        Edge df = new Edge(7, d, f);
        Edge fd = new Edge(7, f, d);
        Edge ef = new Edge(1, e, f);
        Edge fe = new Edge(1, f, e);

        //Add nodes and edges to Lists
        List<Node> nodes = new ArrayList<>();
        nodes.add(a);
        nodes.add(b);
        nodes.add(c);
        nodes.add(d);
        nodes.add(e);
        nodes.add(f);

        List<Edge> edges = new ArrayList<>();
        edges.add(ab);
        edges.add(ba);
        edges.add(ac);
        edges.add(ca);
        edges.add(bd);
        edges.add(db);
        edges.add(ce);
        edges.add(ec);
        edges.add(df);
        edges.add(fd);
        edges.add(ef);
        edges.add(fe);

        //Create Graph
        Graph testGraph = new Graph(nodes, edges);
        System.out.println(testGraph.toString());
        Dijkstra algorithm = new Dijkstra(testGraph);
        algorithm.execute(a);
        LinkedList<Node> path = algorithm.getPath(f);
        System.out.println("--------");
        for (Node node: path) {
            System.out.println(node);
        }

    }
}
