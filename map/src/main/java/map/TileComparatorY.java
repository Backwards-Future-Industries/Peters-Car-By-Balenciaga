package map;

import java.util.Comparator;

public class TileComparatorY implements Comparator<Tile> {
    @Override
    public int compare(Tile o1, Tile o2) {
        if (o1.getPosition()[1]!=o2.getPosition()[1]){
            return o1.getPosition()[1] - o2.getPosition()[1];
        }else {
            return o1.getPosition()[0] - o2.getPosition()[0];
        }
    }
}
