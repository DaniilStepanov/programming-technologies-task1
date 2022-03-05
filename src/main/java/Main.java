public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph(15);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
//        graph.addVertex("F");

        graph.addArc("A", "B", 1, false);
        graph.addArc("A", "C", 2, false);
        graph.addArc("A", "D", 3, false);
        graph.addArc("B", "E", 4, false);
//        graph.addArc("E", "F", 5, false);

        graph.goDeep(0);

        graph.printConnectionMatrix();
//        for (String arc : graph.listOfInputArcs("E")) {
//            System.out.println(arc);
//        }
//        for (String arc : graph.listOfOutputArcs("E")) {
//            System.out.println(arc);
//        }
        graph.deleteArc("B", "E");
        graph.printConnectionMatrix();
    }
}
