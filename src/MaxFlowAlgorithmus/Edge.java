package MaxFlowAlgorithmus;

public class Edge {

    private int capacity;


    private boolean isPrinted = false;
    private boolean isFull = false;
    private boolean isResidual = false;
    /**
     * Wird für das Bearbeiten des Graphens beim Matching genutzt.
     */
    private boolean notNeeded = false;

    /**
     * Konstruktor fuer eine Edge beim ersten Erstellen
     * @param capacity Kapazitaet der Kante
     */
    public Edge(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Konstruktor fuer das Kopieren einer Edge
     * @param edge Die zu kopierende Kante
     */
    public Edge(Edge edge) {
        this.capacity = edge.getCapacity();
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
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

    public boolean isNotNeeded() {
        return notNeeded;
    }

    public void setNotNeeded(boolean notNeeded) {
        this.notNeeded = notNeeded;
    }

    /**
     * Reduziert die Kapazitaet einer Kante.
     * @param value Wie viel die Kapazitaet reduziert wird
     * @return false wenn die Kapazitaet nach der Subtraktion gleich null ist
     * <p>true wenn noch Kapazitaet verbleibt</p>
     */
    public boolean reduceCapacity(int value) {
        setCapacity(capacity-value);

        if (capacity == 0) {
            return false;
        }
        if (capacity < 0) {
            throw new RuntimeException("Kapazitaet im Minus");
        }
        return true;
    }
}
