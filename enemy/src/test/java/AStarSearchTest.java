import enemy.aiMovement.AStarSearch;
import enemy.aiMovement.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AStarSearchTest {
    private AStarSearch aStarSearch;
    private List<Node> path;
    private Node start;
    private Node goal;
    @BeforeEach
    void setUp() {
        aStarSearch = new AStarSearch(new int[][]{
                {1,1,1,1,1},
                {1,1,3,1,1},
                {1,1,3,1,1},
        });

        start = new Node(1,2);
        start.setC(0);
        goal = new Node(1,2);

        path = new ArrayList<>();
        path.add(start);
    }

    @Test
    void findPath() {
        List<Node> generatedList = aStarSearch.findPath(start,goal);
        int index = 0;
        for (Node node: generatedList) {
            assertEquals(path.get(index),node);
            index++;

        }
    }
}