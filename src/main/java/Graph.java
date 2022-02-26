import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int maxVertexCount;
    private int[][] connectionMatrix;
    //    Vertex[] vertexList;
    List<Vertex> vertexList;
    private int currentVertexIndex;
    private GraphStack graphStack;

    public Graph(int maxVertexCount) {
//        this.vertexList = new Vertex[maxVertexCount];
        this.vertexList = new ArrayList<>();
        this.maxVertexCount = maxVertexCount;
        this.connectionMatrix = new int[maxVertexCount][maxVertexCount];
        this.currentVertexIndex = 0;
        this.graphStack = new GraphStack(maxVertexCount);
    }

    public void addVertex(String name) {
//        vertexList[currentVertexIndex++] = new Vertex(name);
        vertexList.add(new Vertex(name));
    }

    public void addArc(String start, String end, int weight, boolean goBack) {
        Integer startIndex = 0;
        Integer endIndex = 0;
        for (Vertex vertex: vertexList){
            if(vertex.getName().equals(start)){
                startIndex = vertexList.indexOf(vertex);
            }else if(vertex.getName().equals(end)){
                endIndex = vertexList.indexOf(vertex);
            }
        }
        connectionMatrix[startIndex][endIndex] = weight;
        connectionMatrix[endIndex][startIndex] = goBack ? weight : -1;
    }

    public void deleteVertex(String name) {
        for (Vertex vertex : vertexList) {
            if (vertex.getName().equals(name)) {
                vertexList.remove(vertex);
            }
        }
    }

    public int check(int vertex) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (connectionMatrix[vertex][i] != -1 && vertexList.get(i).isChecked() == false) {
                return i;
            }
        }
        return -1;
    }

    public void goDeep(int index) {
        System.out.println(vertexList.get(index).getName());
        vertexList.get(index).setChecked(true);
        graphStack.push(index);

        while (!graphStack.isEmpty()) {
            int neighbour = check(graphStack.peek());

            if (neighbour == -1) {
                neighbour = graphStack.pop();
            } else {
                System.out.println(vertexList.get(neighbour).getName());
                vertexList.get(neighbour).setChecked(true);
                graphStack.push(neighbour);
            }
        }
        for (int i = 0; i < vertexList.size(); i++) {
            vertexList.get(i).setChecked(false);
        }
    }
}
