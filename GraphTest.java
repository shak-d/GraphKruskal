import java.util.Arrays;
import static org.junit.Assert.*;

public class GraphTest {

    @org.junit.Test
    public void addNode() {
        Graph graph = new Graph(true);
        graph.AddNode("home");
        graph.AddNode("work");
        assertEquals(2, graph.getNodesAmount());
    }

    @org.junit.Test
    public void addNullNode(){
        Graph graph = new Graph(true);
        graph.AddNode(null);
        graph.AddNode(43);
        assertEquals(1, graph.getNodesAmount());
    }

    @org.junit.Test
    public void addEdge(){

        Graph graph = new Graph(true);
        graph.AddEdge("home", "work", 0.4f, "commute");
        assertEquals(2, graph.getNodesAmount());
        assertEquals(1, graph.getEdgesAmount());
    }

    @org.junit.Test
    public void addNullEdge(){

        Graph graph = new Graph(true);
        graph.AddEdge(null, null, .3f, "test");
        assertEquals(0, graph.getEdgesAmount());
        assertEquals(0, graph.getNodesAmount());

    }

    @org.junit.Test
    public void deleteNode(){

        Graph graph = new Graph(true);
        graph.AddNode("t1");
        graph.AddNode("t2");
        graph.AddNode("t3");
        graph.deleteNode("t3");
        assertTrue(graph.getNodesLabels().containsAll(Arrays.asList("t1", "t2")));
        assertEquals(2, graph.getNodesAmount());

    }

    @org.junit.Test
    public void deleteEdge(){

        Graph graph = new Graph(true);
        graph.AddEdge("home", "work", .4f, "commute1");
        graph.AddEdge("home", "school", .3f, "commute2");
        graph.deleteEdge("home", "school");
        assertEquals(1, graph.getEdgesAmount());
        assertTrue(graph.getAdjacentNodes("home").contains("work"));

    }
}