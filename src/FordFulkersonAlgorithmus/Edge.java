package FordFulkersonAlgorithmus;

public class Edge {

    private int capacity;
    private int flow;

    private boolean isFull = false;

    public Edge(int capacity, int flow) {
        this.capacity = capacity;
        this.flow = flow;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }
}
