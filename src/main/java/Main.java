public class Main {

    public static void main(String[] args){
        Graph graph = new Graph(15);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addArc("A","B",3, true);
        graph.addArc("B","C",3, true);
        graph.addArc("C","D",3, true);
        graph.addArc("A","E",3, true);
        graph.addArc("E","F",3, true);

        graph.goDeep(0);
    }
}
