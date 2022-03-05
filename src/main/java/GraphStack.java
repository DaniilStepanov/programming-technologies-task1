public class GraphStack {
    private int size;
    private int top;
    private int[] body;


    public GraphStack(int size) {
        this.size = size;
        this.top = -1;
        this.body = new int[size];
    }

    public void push(int vertex) {
        body[++top] = vertex;
    }

    public int pop() {
        return body[top--];
    }

    public int peek() {
        return body[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}
