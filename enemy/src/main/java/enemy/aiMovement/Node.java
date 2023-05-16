package enemy.aiMovement;

public class Node {
    private final int x;
    private final int y;
    private int c; //Cost
    private int h; //Heuristic
    private int f; //Cost+Heuristics
    private Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
//
//    @Override
//    public int compareTo(Node o) {
//        return Integer.compare(f, o.getF());
//    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Node other = (Node) obj;
        return x == other.getX() && y == other.getY();
    }

//    @Override
//    public int hashCode() {
//        return 31 * x + y;
//    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", c=" + c +
                ", h=" + h +
                ", f=" + f +
                ", parent=" + parent +
                '}';
    }
}
