package PrimAlgorithmus;

public class Edge {

    private int weight;
    public static int SumWeight;

    private boolean isIncluded = false;
    private boolean isPrinted = false;

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

    public static int getSumWeight() {
        return SumWeight;
    }

    public static void setSumWeight(int sumWeight) {
        SumWeight = sumWeight;
    }
}