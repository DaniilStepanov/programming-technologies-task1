public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph(15);

        graph.addVertex("A");
        graph.addVertex("B");

        graph.addArc("A", "B", 1, false);

        System.out.println(graph.getConnectionMatrix());
    }
}
