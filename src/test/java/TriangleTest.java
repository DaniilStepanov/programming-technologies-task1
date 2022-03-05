import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleTest {

    @Test
    public void testTriangleSquare() {
        Triangle triangle = new Triangle(1, 1, 1);
        double expectedSquare = 5;
        assertEquals(expectedSquare, Math.round(triangle.calcSquare()));
    }

    @Test
    public void testListOfInputArcs() {
        Graph graph = new Graph(15);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        graph.addArc("A", "B", 1, false);
        graph.addArc("A", "C", 2, false);
        graph.addArc("A", "D", 3, false);
        graph.addArc("B", "E", 4, false);

        List<String> arcs = new ArrayList<>();
        arcs.add("A");
        assertEquals(arcs, graph.listOfInputArcs("B"));
    }

    @Test
    public void testListOfOutputArcs() {
        Graph graph = new Graph(15);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        graph.addArc("A", "B", 1, false);
        graph.addArc("A", "C", 2, false);
        graph.addArc("A", "D", 3, false);
        graph.addArc("B", "E", 4, false);

        List<String> arcs = new ArrayList<>();
        arcs.add("B");
        arcs.add("C");
        arcs.add("D");
        assertEquals(arcs, graph.listOfOutputArcs("A"));
    }
    @Test
    public void testAddVertex() {
        Graph graph = new Graph(15);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        graph.addArc("A", "B", 1, false);
        graph.addArc("A", "C", 2, false);
        graph.addArc("A", "D", 3, false);
        graph.addArc("B", "E", 4, false);

        ArrayList<String> vertexes = new ArrayList<>();
        vertexes.add("A");
        vertexes.add("B");
        vertexes.add("C");
        vertexes.add("D");
        vertexes.add("E");
        assertEquals(vertexes, graph.listAllVertexes());
    }
    @Test
    public void testAddArc() {
        Graph graph = new Graph(15);

        graph.addVertex("A");
        graph.addVertex("B");

        graph.addArc("A", "B", 1, false);

List<List<Integer>> matrix = new ArrayList<>();
        for (int t = 0; t < 15; t++) {
            matrix.add(new ArrayList<>());
            for (int i = 0; i < 15; i++) {
                matrix.get(t).add(0);
            }
        }
        matrix.get(0).set(1, 1);
        matrix.get(1).set(0, -1);
        assertEquals(matrix, graph.getConnectionMatrix());
    }

    @Test
    public void testDeleteArc() {
        Graph graph = new Graph(15);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addArc("A", "B", 1, false);
        graph.addArc("B", "C", 1, false);
        graph.deleteArc("B", "C");

        List<List<Integer>> matrix = new ArrayList<>();
        for (int t = 0; t < 15; t++) {
            matrix.add(new ArrayList<>());
            for (int i = 0; i < 15; i++) {
                matrix.get(t).add(0);
            }
        }
        matrix.get(0).set(1, 1);
        matrix.get(1).set(0, -1);
        assertEquals(matrix, graph.getConnectionMatrix());
    }

    @Test
    public void testDeleteVertex() {
        Graph graph = new Graph(15);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addArc("A", "B", 1, false);
        graph.addArc("B", "C", 1, false);
        graph.deleteVertex("C");

        ArrayList<String> vertexes = new ArrayList<>();
        vertexes.add("A");
        vertexes.add("B");
        assertEquals(vertexes, graph.listAllVertexes());
    }
    @Test
    public void testRenameVertex() {
        Graph graph = new Graph(15);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addArc("A", "B", 1, false);
        graph.addArc("B", "C", 1, false);

        graph.renameVertex("C", "E");

        ArrayList<String> vertexes = new ArrayList<>();
        vertexes.add("A");
        vertexes.add("B");
        vertexes.add("E");
        assertEquals(vertexes, graph.listAllVertexes());
    }

    @Test
    public void changeArcWeight(){
        Graph graph = new Graph(15);

        graph.addVertex("A");
        graph.addVertex("B");

        graph.addArc("A", "B", 1, false);
        graph.reWeightArc("A", "B", 2);

        List<List<Integer>> matrix = new ArrayList<>();
        for (int t = 0; t < 15; t++) {
            matrix.add(new ArrayList<>());
            for (int i = 0; i < 15; i++) {
                matrix.get(t).add(0);
            }
        }
        matrix.get(0).set(1, 2);
        matrix.get(1).set(0, -1);
        assertEquals(matrix, graph.getConnectionMatrix());
    }

}
