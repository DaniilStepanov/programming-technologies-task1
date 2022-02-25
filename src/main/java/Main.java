public class Main {

    public static void main(String[] args){
        Graph graph = new Graph(15);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addArc(0,1,3, true);
        graph.addArc(1,2,3, true);
        graph.addArc(2,3,3, true);
        graph.addArc(0,4,3, true);
        graph.addArc(4,5,3, true);

        graph.goDeep(2);
    }
}
