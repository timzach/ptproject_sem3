package FordFulkersonAlgorithmus;

public class Edge {

    private int capacity;
    private int flow = 0;


    private boolean isPrinted = false;
    private boolean isFull = false;
    private boolean isResidual = false;

    public Edge(int capacity) {
        this.capacity = capacity;
        //this.flow = flow;
    }

    public Edge(Edge edge) {
        this.capacity = edge.getCapacity();
    }

    public void setCapacity(int newCapacity) {
        this.capacity = newCapacity;
    }

    public int getCapacity() {
        return capacity;
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

    public boolean isPrinted() {
        return isPrinted;
    }

    public void setPrinted(boolean printed) {
        isPrinted = printed;
    }

    public boolean isResidual() {
        return isResidual;
    }

    public void setResidual(boolean residual) {
        isResidual = residual;
    }

    public int getRemainingCapacity() {
        return capacity - flow;
    }

    public boolean reduceCapacity(int value) {
        setCapacity(capacity-value);

        if (capacity == 0) {
            return false;
        }
        if (capacity < 0) {
            throw new RuntimeException("Kapazität im Minus");
        }
        return true;
    }
    public void reset(){
        flow = 0;
        isFull = false;
    }
}
