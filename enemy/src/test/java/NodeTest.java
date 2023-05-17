import enemy.aiMovement.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    private Node node;
    private Node parentNode;
    private Node node2;
    private Node node3;
    @BeforeEach
    void setUp() {
        parentNode = new Node(2,2);

        node = new Node(5,5);
        node.setC(5);
        node.setH(5);
        node.setF(10);
        node.setParent(parentNode);

        node2 = new Node(1,1);
        node2.setF(15);

        node3 = new Node(1,1);
        node2.setF(10);

    }

    @Test
    void getX() {
        assertEquals(5,node.getX());
    }

    @Test
    void getY() {
        assertEquals(5,node.getY());
    }

    @Test
    void setX() {
        node.setX(2);
        assertEquals(2,node.getX());
    }

    @Test
    void setY() {
        node.setY(2);
        assertEquals(2,node.getY());
    }

    @Test
    void getC() {
        assertEquals(5,node.getC());
    }

    @Test
    void setC() {
        node.setC(2);
        assertEquals(2,node.getC());
    }

    @Test
    void getH() {
        assertEquals(5,node.getH());
    }

    @Test
    void setH() {
        node.setH(2);
        assertEquals(2,node.getH());
    }

    @Test
    void getF() {
        assertEquals(10,node.getF());
    }

    @Test
    void setF() {
        node.setF(15);
        assertEquals(15,node.getF());
    }

    @Test
    void getParent() {
        assertEquals(parentNode,node.getParent());
    }

    @Test
    void setParent() {
        Node parent2 = new Node(12,12);
        node.setParent(parent2);
        assertEquals(parent2,node.getParent());
    }

    @Test
    void compareTo() {
        assertEquals(0,node.compareTo(node2));
        assertEquals(1,node.compareTo(node3));
    }

    @Test
    void testEquals() {
        assertFalse(node.equals(node2));
        assertTrue(node.equals(node));
        assertTrue(node2.equals(node3));
        assertFalse(node.equals(new int[2][2]));
    }

    @Test
    void testHashCode() {
        assertEquals(160,node.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Node{" +
                "x=" + 5 +
                ", y=" + 5 +
                "}",node.toString());
    }
}