public class Graph {
    private int maxVertexCount;
    private int[][] connectionMatrix;
    Vertex[] vertexList;
    private int currentVertexIndex;
    private GraphStack graphStack;

    public Graph(int maxVertexCount) {
        this.vertexList = new Vertex[maxVertexCount];
        this.maxVertexCount = maxVertexCount;
        this.connectionMatrix = new int[maxVertexCount][maxVertexCount];
        this.currentVertexIndex = 0;
        this.graphStack = new GraphStack(maxVertexCount);
    }

    public void addVertex(String name) {
        vertexList[currentVertexIndex++] = new Vertex(name);
    }

    public void addArc(int start, int end, int weight, boolean goBack) {
        connectionMatrix[start][end] = weight;
        connectionMatrix[end][start] = goBack ? weight : -1;
    }

    public int check(int vertex) {
        for (int i = 0; i < currentVertexIndex; i++) {
            if (connectionMatrix[vertex][i] != -1 && vertexList[i].isChecked() == false) {
                return i;
            }
        }
        return -1;
    }

    public void goDeep(int index) {
        System.out.println(vertexList[index].getName());
        vertexList[index].setChecked(true);
        graphStack.push(index);

        while (!graphStack.isEmpty()) {
            int neighbour = check(graphStack.peek());

            if (neighbour == -1) {
                neighbour = graphStack.pop();
            } else {
                System.out.println(vertexList[neighbour].getName());
                vertexList[neighbour].setChecked(true);
                graphStack.push(neighbour);
            }
        }
        for (int i = 0; i < currentVertexIndex; i++) {
            vertexList[i].setChecked(false);
        }
    }
}
