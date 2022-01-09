package FordFulkersonAlgorithmus;

public class Edge {

    private final int capacity;
    private int flow = 0;

    private boolean isPrinted = false;
    private boolean isFull = false;

    public Edge(int capacity) {
        this.capacity = capacity;
        //this.flow = flow;
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

    public int getRemainingCapacity() {
        return capacity - flow;
    }

    public void fill(int value) {
        flow += value;
        if(flow >= capacity){
            isFull = true;
            if (flow > capacity) {
                throw new RuntimeException("Uebergelaufen");
            }
        }
    }
    public void reset(){
        flow = 0;
        isFull = false;
    }
}
