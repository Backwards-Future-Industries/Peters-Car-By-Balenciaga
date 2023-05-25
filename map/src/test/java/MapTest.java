import abstractClasses.CommonMap;
import map.Bitmap;
import map.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utilities.GameData;


public class MapTest {

    private Map map;
    private Bitmap bitmap;
    @Mock
    private GameData gameData;


    @BeforeEach
    public void setUp() {
        map = new Map();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() {
        CommonMap mapResult = map.create(gameData);
        Assertions.assertNotNull(mapResult);
    }

    @Test
    public void Map() {
        this.bitmap = new Bitmap();
        Assertions.assertNotNull(bitmap.getMap());
    }


}