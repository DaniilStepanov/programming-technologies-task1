import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int maxVertexCount;
    //    private int[][] connectionMatrix;
    private List<List<Integer>> connectionMatrix;
    //    Vertex[] vertexList;
    private List<Vertex> vertexList;
    private int currentVertexIndex;
    private GraphStack graphStack;

    public Graph(int maxVertexCount) {
//        this.vertexList = new Vertex[maxVertexCount];
        this.vertexList = new ArrayList<>();
        this.maxVertexCount = maxVertexCount;
//        this.connectionMatrix = new int[maxVertexCount][maxVertexCount];
        this.connectionMatrix = new ArrayList<>();
        this.currentVertexIndex = 0;
        this.graphStack = new GraphStack(maxVertexCount);
        for (int t = 0; t < maxVertexCount; t++) {
            connectionMatrix.add(new ArrayList<>());
            for (int i = 0; i < maxVertexCount; i++) {
                connectionMatrix.get(t).add(0);
            }
        }
    }

    public void addVertex(String name) {
//        vertexList[currentVertexIndex++] = new Vertex(name);
        vertexList.add(new Vertex(name));
    }

    public void addArc(String start, String end, int weight, boolean goBack) {
        Integer startIndex = 0;
        Integer endIndex = 0;
        for (Vertex vertex : vertexList) {
            if (vertex.getName().equals(start)) {
                startIndex = vertexList.indexOf(vertex);
            } else if (vertex.getName().equals(end)) {
                endIndex = vertexList.indexOf(vertex);
            }
        }
        connectionMatrix.get(startIndex).set(endIndex, weight);
        if (goBack) {
            connectionMatrix.get(endIndex).set(startIndex, weight);
        } else {
            connectionMatrix.get(endIndex).set(startIndex, -1);
        }

//        connectionMatrix[startIndex][endIndex] = weight;
//        connectionMatrix[endIndex][startIndex] = goBack ? weight : -1;
    }

    public void deleteVertex(String name) {
        int vertexIndex = getVertexIndexByName(name);
        vertexList.remove(getVertexIndexByName(name));
//        int[][] newArr = new int[maxVertexCount][maxVertexCount];
        for (List<Integer> list : connectionMatrix) {
            list.remove(vertexIndex);
            list.add(0);
        }
        connectionMatrix.remove(vertexIndex);
        connectionMatrix.add(new ArrayList<>());
        for (int i = 0; i < maxVertexCount; i++) {
            connectionMatrix.get(maxVertexCount - 1).add(0);
        }
//        for (int t = vertexIndex; t < maxVertexCount - 1; t++) {
//            for (int i = vertexIndex; i < maxVertexCount - 1; i++) {
//                newArr[t][i] = connectionMatrix[t + 1][i + 1];
//            }
//        }

    }

    public void deleteArc(String from, String to) {
        int fromN = getVertexIndexByName(from);
        int toN = getVertexIndexByName(to);
        connectionMatrix.get(fromN).set(toN, 0);
        connectionMatrix.get(toN).set(fromN, 0);
    }

    public int check(int vertex) {
        for (int i = 0; i < vertexList.size(); i++) {
//            if (connectionMatrix[vertex][i] != -1 && vertexList.get(i).isChecked() == false) {
//                return i;
//            }
            if (connectionMatrix.get(vertex).get(i) != -1 && vertexList.get(i).isChecked() == false) {
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

    public List<String> listOfInputArcs(String vertexName) {
        List<String> inputArcs = new ArrayList<>();
        for (int i = 0; i < maxVertexCount; i++) {
//            if (connectionMatrix[i][getVertexIndexByName(vertexName)] > 0) {
//                inputArcs.add(vertexList.get(i).getName());
//            }
            if (connectionMatrix.get(i).get(getVertexIndexByName(vertexName)) > 0) {
                inputArcs.add(vertexList.get(i).getName());
            }
        }
        return inputArcs;
    }

    public List<String> listOfOutputArcs(String vertexName) {
        List<String> outputArcs = new ArrayList<>();
        for (int i = 0; i < maxVertexCount; i++) {
//            if (connectionMatrix[getVertexIndexByName(vertexName)][i] > 0) {
//                outputArcs.add(vertexList.get(i).getName());
//            }
            if (connectionMatrix.get(getVertexIndexByName(vertexName)).get(i) > 0) {
                outputArcs.add(vertexList.get(i).getName());
            }
        }
        return outputArcs;
    }

    public int getVertexIndexByName(String name) {
        for (int i = 0; i < maxVertexCount; i++) {
            if (vertexList.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void renameVertex(String oldName, String newName) {
        vertexList.get(getVertexIndexByName(oldName)).setName(newName);
    }

    public void reWeightArc(String from, String to, int newWeight) {
        int fromN = getVertexIndexByName(from);
        int toN = getVertexIndexByName(to);
        connectionMatrix.get(fromN).set(toN, newWeight);
        if (connectionMatrix.get(toN).get(fromN) > 0) {
            connectionMatrix.get(toN).set(fromN, newWeight);
        }

    }

    public void printConnectionMatrix() {
        for (int i = 0; i < maxVertexCount; i++) {
            for (int t = 0; t < maxVertexCount; t++) {
                System.out.print(connectionMatrix.get(i).get(t) + " ");
//                System.out.print(connectionMatrix[i][t] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public ArrayList<String> listAllVertexes() {
        ArrayList<String> vertexStringList = new ArrayList<>();
        for (Vertex vertex : vertexList) {
            vertexStringList.add(vertex.getName());
        }
        return vertexStringList;
    }

    public List<List<Integer>> getConnectionMatrix() {
        return connectionMatrix;
    }


}
