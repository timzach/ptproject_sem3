public class Edge {


    //<editor-fold desc="Prim Variablen">
    private int weight;
    private boolean isIncluded = false;
    private boolean isPrinted = false;
    //</editor-fold>


    //<editor-fold desc="Ford-Fulkerson Variablen">
    private int flow;
    private int capacity;
    //</editor-fold>


    //<editor-fold desc="Prim">
    public Edge(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isIncluded() {
        return isIncluded;
    }

    public void setIncluded(boolean included) {
        isIncluded = included;
    }

    public boolean isPrinted() {
        return isPrinted;
    }

    public void setPrinted(boolean printed) {
        isPrinted = printed;
    }
    //</editor-fold>



    //<editor-fold desc="Ford-Fulkerson">
    public Edge(int flow, int capacity) {
        this.flow = flow;
        this.capacity = capacity;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    //</editor-fold>
}