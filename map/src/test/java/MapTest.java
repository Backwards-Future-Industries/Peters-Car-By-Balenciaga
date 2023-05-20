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
        //Arrange
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() {
        //Act
        CommonMap mapResult = map.create(gameData);

        //Assert
        Assertions.assertNotNull(mapResult);
    }

    @Test
    public void Map() {
        //Arrange
        this.bitmap = new Bitmap();

        //Assert
        Assertions.assertNotNull(bitmap.getMap());
    }


}